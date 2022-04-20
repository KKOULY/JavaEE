package client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import library.MyLibraryClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class MyStarterClient {


    /*change application.properties to get different
        possible values  */
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MyStarterClient.class, args);
        MyLibraryClass myLibraryClassByClass = applicationContext.getBean(MyLibraryClass.class);
        myLibraryClassByClass.printInfo();
        MyLibraryClass myLibraryClass = (MyLibraryClass) applicationContext.getBean("myLibraryClass");
        myLibraryClass.printInfo();
    }

}