package dk.vv.automobile;

import dk.vv.automobile.entities.Order;
import dk.vv.automobile.facades.BrandFacade;
import dk.vv.automobile.facades.CategoryFacade;
import dk.vv.automobile.facades.OrderFacade;
import dk.vv.automobile.facades.ProductFacade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
@ApplicationScoped
public class Producers {

    @Inject
    EntityManager entityManager;


    @Produces
    ProductFacade getProductFacade(){
        return new ProductFacade(entityManager,entityManager);
    }

    @Produces
    BrandFacade getBrandFacade(){
        return new BrandFacade(entityManager,entityManager);
    }

    @Produces
    CategoryFacade getCategoryFacade(){
        return new CategoryFacade(entityManager,entityManager);
    }

    @Produces
    OrderFacade getOrderFacade(){
        return new OrderFacade(entityManager,entityManager);
    }

}
