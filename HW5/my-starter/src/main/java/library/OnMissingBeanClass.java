package library;

import org.springframework.beans.factory.InitializingBean;

public class OnMissingBeanClass implements InitializingBean{
    public void doService() {
        System.out.println("OnMissingBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        doService();
    }
}
