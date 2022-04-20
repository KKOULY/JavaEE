package library;

import org.springframework.beans.factory.InitializingBean;

public class OnBeanClass implements InitializingBean{
    public void hello(){
        System.out.println("OnBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        hello();
    }
}
