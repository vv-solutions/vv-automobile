package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.ProductAvailabilityDTO;
import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.entities.Product;
import dk.vv.automobile.entities.ProductAvailability;
import dk.vv.automobile.entities.types.ProductQuantityPair;
import dk.vv.automobile.entities.types.ProductQuantityPairArrayType;
import dk.vv.automobile.errorhandling.ProductQuantityException;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.jdbc.Work;
import org.postgresql.util.PGbytea;
import org.postgresql.util.PGobject;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFacade {

    private final EntityManager slaveEntityManager;
    private final EntityManager masterEntityManager;

    @Inject
    public ProductFacade(EntityManager slaveEntityManager, EntityManager masterEntityManager) {
        this.slaveEntityManager = slaveEntityManager;
        this.masterEntityManager = masterEntityManager;
    }

    public ProductDTO  getProductById(int id){
        return new ProductDTO(slaveEntityManager.find(Product.class,id));
    }

    public List<ProductDTO> getProductsWithPaginationByCategory(int count, int page,int categoryId,String orderBy,List<Integer> brands) {


            StringBuilder queryBuilder = new StringBuilder("select new dk.vv.automobile.dtos.ProductDTO(p) from Product p");
            if(orderBy.equalsIgnoreCase("popularity")){
                queryBuilder.append(" join ProductPopularity  pp on p.id = pp.productId");
            }
            queryBuilder.append(" where p.categoryId = :categoryId");

            if(brands != null && !brands.isEmpty()){
                queryBuilder.append(" and p.brandId in :brands");
            }

            if(orderBy.equalsIgnoreCase("priceDesc")){
                queryBuilder.append(" order by p.price desc, p.id asc");

            } else if(orderBy.equalsIgnoreCase("priceAsc")){
                queryBuilder.append(" order by p.price asc, p.id asc");
            }
            else {
                queryBuilder.append(" order by pp.purchaseCount desc, p.id asc");
            }


            TypedQuery<ProductDTO> query =  slaveEntityManager.createQuery(queryBuilder.toString()
                            , ProductDTO.class);
            if(brands != null && !brands.isEmpty()){
                query.setParameter("brands", brands);
            }

            query.setParameter("categoryId", categoryId);

            return query.setMaxResults(count)
                    .setFirstResult(page * count)
                    .getResultList();
        }


        public List<ProductDTO> getPopularProducts(int count){
            return slaveEntityManager.createQuery("select new dk.vv.automobile.dtos.ProductDTO(p) from Product p " +
                    " join ProductPopularity pp on pp.productId = p.id " +
                    "order by pp.purchaseCount desc", ProductDTO.class).setMaxResults(count).getResultList();
        }


        public List<ProductDTO> searchProducts(String search){
            return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.ProductDTO(p) from Product  p " +
                    "join Brand b on p.brandId = b.id" +
                    " where lower(p.name) LIKE :search or lower(p.description) like :search or lower(b.name) like :search", ProductDTO.class)
                    .setParameter("search","%"+search.toLowerCase()+"%")
                    .getResultList();
        }

        public List<ProductDTO> getProductsByIds(List<Integer> ids){
        return slaveEntityManager.createQuery("SELECT new dk.vv.automobile.dtos.ProductDTO(p) from Product p " +
                "where p.id in :ids",ProductDTO.class)
                .setParameter("ids",ids)
                .getResultList();
        }


    public List<ProductDTO> getAll(int count, int page){
        return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.ProductDTO(p) from Product p order by p.id asc", ProductDTO.class)
                .setMaxResults(count)
                .setFirstResult(page*count)
                .getResultList();
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        var toUpdate = masterEntityManager.find(Product.class,productDTO.getId());

        toUpdate.setBrandId(productDTO.getBrandId());
        toUpdate.setName(productDTO.getName());
        toUpdate.setCategoryId(productDTO.getCategoryId());
        toUpdate.setDescription(productDTO.getDescription());
        toUpdate.setImgUrl(productDTO.getImgUrl());
        toUpdate.setPrice(productDTO.getPrice());

        masterEntityManager.merge(toUpdate);

        return new ProductDTO(toUpdate);
    }

    public ProductAvailabilityDTO getProductAvailability(int productId){
        return slaveEntityManager.createQuery("SELECT new dk.vv.automobile.dtos.ProductAvailabilityDTO(p.id,p.quantity) from ProductAvailability p " +
                "where p.product.id = :productId",ProductAvailabilityDTO.class)
                .setParameter("productId",productId)
                .getSingleResult();
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        product.setProductAvailability(new ProductAvailability(product,0));

        masterEntityManager.persist(product);

        masterEntityManager.flush();
        masterEntityManager.refresh(product);

        return new ProductDTO(product);
    }


        public void decreaseProductAvailability(List<ProductQuantityPair> pairs) throws ProductQuantityException {
            StringBuilder queryBuilder = new StringBuilder();

            queryBuilder.append("SELECT shop.decrease_product_availability(ARRAY[");

            for (int i = 0; i < pairs.size(); i++) {
                ProductQuantityPair pair = pairs.get(i);
                queryBuilder.append("(")
                        .append(pair.getProductId())
                        .append(", ")
                        .append(pair.getQuantity())
                        .append(")::shop.product_quantity_pair");

                if (i < pairs.size() - 1) {
                    queryBuilder.append(", ");
                }
            }

            queryBuilder.append("]);");

            String query = queryBuilder.toString();

            String result = (String) masterEntityManager.createNativeQuery(query).getSingleResult();

            if(!result.equalsIgnoreCase("ok")){
                throw new ProductQuantityException(result);
            }


        }

        public ProductAvailabilityDTO increaseProductAvailability(ProductAvailabilityDTO productAvailabilityDTO){
            System.out.println(productAvailabilityDTO.getQuantity());
            var query = masterEntityManager.createNativeQuery("SELECT shop.update_product_availability(:productId, :quantity)");
            query.setParameter("productId", productAvailabilityDTO.getProduct().getId());
            query.setParameter("quantity", productAvailabilityDTO.getQuantity());

            int newQuantity = (int) query.getSingleResult();

            productAvailabilityDTO.setQuantity(newQuantity);

            return productAvailabilityDTO;
        }
}
