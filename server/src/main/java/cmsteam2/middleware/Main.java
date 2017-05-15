package cmsteam2.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class for starting the cmsteam2.middleware
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cmsteam2.rest.controller"})
public class Main {

    public static void main(String[] args) {
//        System.out.println("This is middleware");
        SpringApplication.run(Main.class, args);

    }

}
