package dk.vv.automobile.entities.neo4j;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "REVIEWED")
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private int rating;
    private String description;

    @StartNode
    private User user;

    @EndNode
    private ReviewProduct reviewProduct;

}
