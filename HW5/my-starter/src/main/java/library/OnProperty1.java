package library;

import org.springframework.beans.factory.InitializingBean;

public class OnProperty1 implements InitializingBean, InterfaceForConditional {
    public void something(){
        System.out.println("OnProperty1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        something();
    }
}
