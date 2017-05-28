package cmsteam2.middleware;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class for starting the cmsteam2.middleware
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cmsteam2.rest.controller"})
public class Main {

    public static SessionFactory sessionFactory;

    public static void main(String[] args) {
//        System.out.println("This is middleware");
        initialize();
        SpringApplication.run(Main.class, args);
    }

    static void initialize(){
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
    }

}
