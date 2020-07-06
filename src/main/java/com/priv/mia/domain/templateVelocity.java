package com.priv.mia.domain;

import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class templateVelocity {

    private static Properties properties = new Properties();

    static {
        properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        properties.setProperty(Velocity.RESOURCE_LOADER, "class");
        properties.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    public static Properties getProperties(){
        return templateVelocity.properties;
    }



}
