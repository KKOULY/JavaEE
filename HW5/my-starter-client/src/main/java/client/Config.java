package client;

import library.OnProperty0;
import library.OnProperty1;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan("library")
@Configuration
public class Config {

    @Bean
    @ConditionalOnBean(OnProperty0.class)
    public Property0 property0(){
        return new Property0();
    }

    @Bean
    @ConditionalOnBean(OnProperty1.class)
    public Property1 property1(){
        return new Property1();
    }

    @Bean
    @ConditionalOnMissingBean(OnProperty0.class)
    public PropertyMissing0 propertyMissing0(){
        return new PropertyMissing0();
    }
    @Bean
    @ConditionalOnMissingBean(OnProperty1.class)
    public PropertyMissing1 propertyMissing1(){
        return new PropertyMissing1();
    }
}
