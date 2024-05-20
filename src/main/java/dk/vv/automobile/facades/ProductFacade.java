package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    public List<ProductDTO> getProductsWithPaginationByCategory(int count, int page,int categoryId,String orderBy, String direction,List<Integer> brands) {


            StringBuilder queryBuilder = new StringBuilder("select new dk.vv.automobile.dtos.ProductDTO(p) from Product p");
            if(orderBy.equalsIgnoreCase("popularity")){
                queryBuilder.append(" join ProductPopularity  pp on p.id = pp.productId");
            }
            queryBuilder.append(" where p.categoryId = :categoryId");

            if(brands != null && !brands.isEmpty()){
                queryBuilder.append(" and p.brandId in :brands");
            }

            if(orderBy.equalsIgnoreCase("price")){

                if(direction.equalsIgnoreCase("desc")){
                    queryBuilder.append(" order by p.price desc");
                } else {
                    queryBuilder.append(" order by p.price asc");
                }
            } else {
                queryBuilder.append(" order by pp.purchaseCount desc");
            }


            TypedQuery<ProductDTO> query =  slaveEntityManager.createQuery(queryBuilder.toString()
                            , ProductDTO.class);
            if(brands != null && !brands.isEmpty()){
                query.setParameter("brands", brands);
            }

            query.setParameter("categoryId", categoryId);

            return query.setMaxResults(count)
                    .setFirstResult(page * count + 1)
                    .getResultList();
        }


        public List<ProductDTO> getPopularProducts(int count){
            return slaveEntityManager.createQuery("select new dk.vv.automobile.dtos.ProductDTO(p) from Product p " +
                    " join ProductPopularity pp on pp.productId = p.id " +
                    "order by pp.purchaseCount desc", ProductDTO.class).setMaxResults(count).getResultList();
        }


        public List<ProductDTO> searchProducts(String search){
            System.out.println(search);
            return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.ProductDTO(p) from Product  p " +
                    "join Brand b on p.brandId = b.id" +
                    " where lower(p.name) LIKE :search or lower(p.description) like :search or lower(b.name) like :search", ProductDTO.class)
                    .setParameter("search","%"+search.toLowerCase()+"%")
                    .getResultList();
        }
}
