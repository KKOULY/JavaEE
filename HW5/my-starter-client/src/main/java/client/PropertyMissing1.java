package client;

import org.springframework.beans.factory.InitializingBean;

public class PropertyMissing1 implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("PropertyMissing1");
    }


}
