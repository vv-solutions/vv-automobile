package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.dtos.ProductCategoryDTO;
import dk.vv.automobile.entities.Brand;
import dk.vv.automobile.entities.ProductCategory;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryFacade {

    private final EntityManager slaveEntityManager;
    private final EntityManager masterEntityManager;

    @Inject
    public CategoryFacade(EntityManager slaveEntityManager, EntityManager masterEntityManager) {
        this.slaveEntityManager = slaveEntityManager;
        this.masterEntityManager = masterEntityManager;
    }


    public ProductCategoryDTO getCategoryById(int categoryId){
        return new ProductCategoryDTO(slaveEntityManager.find(ProductCategory.class,categoryId));
    }

    public List<ProductCategoryDTO> getAll(){
        return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.ProductCategoryDTO(pc) from ProductCategory pc", ProductCategoryDTO.class).getResultList();
    }



    public ProductCategoryDTO update(ProductCategoryDTO productCategoryDTO){
        var productCategory= masterEntityManager.find(ProductCategory.class,productCategoryDTO.getId());
        productCategory.setName(productCategoryDTO.getName());

        masterEntityManager.merge(productCategory);

        return new ProductCategoryDTO(productCategory);
    }
}
