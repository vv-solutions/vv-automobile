package dk.vv.automobile.facades;

import dk.vv.automobile.dtos.BrandDTO;
import dk.vv.automobile.entities.Brand;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrandFacade {

    private final EntityManager slaveEntityManager;
    private final EntityManager masterEntityManager;

    @Inject
    public BrandFacade(EntityManager slaveEntityManager, EntityManager masterEntityManager) {
        this.slaveEntityManager = slaveEntityManager;
        this.masterEntityManager = masterEntityManager;
    }


    public BrandDTO getBrandById(int brandId){
        return new BrandDTO(slaveEntityManager.find(Brand.class,brandId));
    }

    public List<BrandDTO> getAllBrands(){
        return slaveEntityManager.createQuery("Select new dk.vv.automobile.dtos.BrandDTO(b) from Brand b", BrandDTO.class).getResultList();
    }

    public BrandDTO createBrand(BrandDTO brandDTO){
        Brand brand = new Brand(brandDTO);

        masterEntityManager.persist(brand);

        masterEntityManager.flush();
        masterEntityManager.refresh(brand);

        return new BrandDTO(brand);

    }
    public BrandDTO updateBrand(BrandDTO brandDTO){
        var toUpdate = masterEntityManager.find(Brand.class,brandDTO.getId());

        toUpdate.setDescription(brandDTO.getDescription());
        toUpdate.setName(brandDTO.getName());

        masterEntityManager.merge(toUpdate);

        return new BrandDTO(toUpdate);
    }

}
