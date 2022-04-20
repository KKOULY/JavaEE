package library;

import org.springframework.beans.factory.InitializingBean;

public class OnProperty0 implements InitializingBean, InterfaceForConditional {
    public void something(){
        System.out.println("OnProperty0");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        something();
    }
}
