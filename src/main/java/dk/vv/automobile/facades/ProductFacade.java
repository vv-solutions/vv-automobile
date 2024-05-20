package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.ProductDTO;
import dk.vv.automobile.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

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

    public List<ProductDTO> getProductsWithPaginationByCategory(int count, int page,int categoryId, List<Integer> brands){
        return slaveEntityManager.createQuery("select new dk.vv.automobile.dtos.ProductDTO(p) from Product  p join ProductPopularity  pp on p.id = pp.productId " +
                                "where p.brandId in :brands and p.categoryId = :categoryId " +
                                "order by pp.purchaseCount desc"
                , ProductDTO.class)
                .setParameter("categoryId",categoryId)
                .setParameter("brands",brands)
                .setMaxResults(count)
                .setFirstResult(page*count).getResultList();
    }
}
