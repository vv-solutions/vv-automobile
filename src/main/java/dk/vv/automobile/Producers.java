package dk.vv.automobile;

import dk.vv.automobile.facades.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import org.jboss.logging.Logger;
import org.neo4j.ogm.session.SessionFactory;

@ApplicationScoped
public class Producers {


    @Inject
    EntityManager masterEntityManager;

    @Inject
    @PersistenceUnit(name = "slave")
    EntityManager slaveEntityManager;

    @Inject
    Logger logger;


    @Produces
    ProductFacade getProductFacade(){
        return new ProductFacade(slaveEntityManager,masterEntityManager);
    }

    @Produces
    BrandFacade getBrandFacade(){
        return new BrandFacade(slaveEntityManager,masterEntityManager);
    }

    @Produces
    CategoryFacade getCategoryFacade(){
        return new CategoryFacade(slaveEntityManager,masterEntityManager);
    }

    @Produces
    OrderFacade getOrderFacade(){
        return new OrderFacade(slaveEntityManager,masterEntityManager);
    }

    @Inject
    SessionFactory sessionFactory;

    @Produces
    ReviewFacade getReviewFacade(){
        return  new ReviewFacade(sessionFactory);
    }


    @Produces
    PredictionFacade getPredictionFacade(){
        return new PredictionFacade(logger);
    }

    @Produces
    ChatFacade getChatFacade(){
        return new ChatFacade(logger);
    }
}
