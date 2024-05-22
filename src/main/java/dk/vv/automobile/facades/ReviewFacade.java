package dk.vv.automobile.facades;


import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.dtos.ReviewDTO;
import dk.vv.automobile.entities.neo4j.Review;
import dk.vv.automobile.entities.neo4j.ReviewProduct;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewFacade {

    private final SessionFactory sessionFactory;


    public ReviewFacade(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public ReviewDTO createReview(ReviewDTO reviewDTO){
        Session session = sessionFactory.openSession();

        // Create user if not exisits:
        var createUserQuery = "MERGE (u:User {email: $email})";
        session.query(ReviewProduct.class,createUserQuery, Map.of("email", reviewDTO.getEmail()));

        // Create product if not exists:
        var createProductQuery = "MERGE (p:Product {productId: $productId})";
        session.query(ReviewProduct.class,createProductQuery, Map.of("productId", reviewDTO.getProductId()));

        //Create review:
        var createReviewQuery = " MATCH (u:User {email: $email})\n" +
                "        MATCH (p:Product {productId: $productId})\n" +
                "        MERGE (u)-[r:REVIEWED {rating: $rating, description: $description}]->(p)";
        session.query(ReviewProduct.class,createReviewQuery, Map.of(
                "email", reviewDTO.getEmail(),
                "productId", reviewDTO.getProductId(),
                "rating",reviewDTO.getRating(),
                "description",reviewDTO.getDescription()
                ));
        return reviewDTO;
    }

    public List<ReviewDTO> getReviewsByProductId(int productId){
        Session session = sessionFactory.openSession();

        var query ="MATCH(p:Product{productId:$productId})<-[r:REVIEWED]-(u:User)\n" +
                    "return u.email as email, p.productId as productId, r.description as description, r.rating as rating";
        return session.queryDto(query,Map.of("productId",productId), ReviewDTO.class);
    }

    public List<Integer> recommendProducts(int productId) {
        Session session = sessionFactory.openSession();

        // Step 1: Project the graph
        var createProjectionQuery = "CALL gds.graph.project(" +
                "'userProductGraph', " +
                "['User', 'Product'], " +
                "{" +
                "  REVIEWED: {" +
                "    type: 'REVIEWED', " +
                "    orientation: 'UNDIRECTED', " +
                "    properties: ['rating']" +
                "  }" +
                "}" +
                ")";
        session.query(createProjectionQuery, Map.of());


        var similarityQuery = "CALL gds.nodeSimilarity.stream('userProductGraph', {" +
                "  relationshipWeightProperty: 'rating'" +
                "}) " +
                "YIELD node1, node2, similarity " +
                "WITH gds.util.asNode(node1) AS product1, gds.util.asNode(node2) AS product2, similarity " +
                "WHERE product1:Product AND product1.productId = $currentProductId AND product2:Product " +
                "RETURN " +
                "  CASE WHEN product1.productId = $currentProductId THEN product2.productId ELSE product1.productId END AS recommendedProductId " +
                "ORDER BY similarity DESC " +
                "LIMIT 10";
        Iterable<Integer> recommendations = session.query(Integer.class,similarityQuery, Map.of("currentProductId", productId));



        // Step 3: Drop the graph projection
        var dropProjectionQuery = "CALL gds.graph.drop('userProductGraph') YIELD graphName";
        session.query(dropProjectionQuery, Map.of());

        List<Integer> productIds = new ArrayList<>();

        recommendations.forEach(productIds::add);

        return productIds;
    }

    public List<ReviewProduct> recommendProducts(String userEmail) {
        Session session = sessionFactory.openSession();
        String query = "MATCH (u:User {email: $email})-[:REVIEWED]->(p:Product)<-[:REVIEWED]-(similar:User)-[:REVIEWED]->(rec:Product) " +
                "WHERE NOT (u)-[:REVIEWED]->(rec) " +
                "RETURN rec, COUNT(*) AS score " +
                "ORDER BY score DESC " +
                "LIMIT 5";
        Iterable<ReviewProduct> result = session.query(ReviewProduct.class,query, Map.of("email", userEmail));

        List<ReviewProduct> products = new ArrayList<>();
        result.forEach(products::add);

        return products;
    }
}
