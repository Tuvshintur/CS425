package com.smt.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources(@PropertySource("classpath:common.properties"))
public class PropertiesConfig implements EnvironmentAware {

    private Environment env;

    public PropertiesConfig(Environment env) {
        this.env = env;
    }

    public String getConfigValue(String configKey){
        return env.getProperty(configKey);
    }

    public void setEnvironment(Environment env) {
        this.env = env;
    }
}