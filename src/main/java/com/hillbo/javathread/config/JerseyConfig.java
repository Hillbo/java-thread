package com.hillbo.javathread.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Jersey配置
 */
@Component
@ApplicationPath("/v1/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.hillbo");
    }

}
