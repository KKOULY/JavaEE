package client;

import library.InterfaceForConditional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

public class Property0 implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Property0");
    }


}
