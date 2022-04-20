package library;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@ComponentScan
@Configuration(proxyBeanMethods = false)
public class CustomConfig {


    @Bean(name = "OnBean")
    @ConditionalOnMissingBean(OnMissingBeanClass.class)
    public OnMissingBeanClass onMissingBeanClass(){
        return new OnMissingBeanClass();
    }

    @Bean
    @ConditionalOnBean(name = "OnBean")
    public OnBeanClass onBeanClass(){
        return new OnBeanClass();
    }

    @Bean
    @ConditionalOnProperty(prefix = "somePref", name = "prop0")
    public OnProperty0 onProperty0(){
        return new OnProperty0();
    }

    @Bean
    @ConditionalOnProperty(prefix = "somePref", name = "prop1")
    public OnProperty1 onProperty1(){
        return new OnProperty1();
    }

}
