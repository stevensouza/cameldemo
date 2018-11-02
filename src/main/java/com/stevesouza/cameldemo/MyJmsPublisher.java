package com.stevesouza.cameldemo;

import org.apache.camel.spring.SpringRouteBuilder;

//@Component
public class MyJmsPublisher extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:{{amq.publisher}}")
                .routeId("jms publisher")
                .log("headers=${headers}");
    }
}