package cmsteam2.backend;

import cmsteam2.common.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

/**
 * Main class for starting the cmsteam2.backend
 */
@SpringBootApplication

public class Main {

    public static void main(String[] args) {
//        System.out.println("This is cmsteam2.backend");
        SpringApplication.run(Main.class, args);

//        initialize();
//        Recommendation recommendation=new Recommendation("dsad",new Review(new Date(), Qualifier.ACCEPT));
//        session.save(sessions);
//        user.addResearchPaper(researchPaper);
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();
    }
//    static SessionFactory sessionFactory;
 /*   static void initialize(){
        final StandardServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory =new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }
    static void close(){
        if(sessionFactory !=null){
            sessionFactory.close();
        }
    }*/
}
