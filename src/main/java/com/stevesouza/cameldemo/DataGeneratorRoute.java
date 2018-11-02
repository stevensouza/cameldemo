package com.stevesouza.cameldemo;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataGeneratorRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        MyPojo pojo = new MyPojo(1, "joe", 50);
        from("timer:foo?period={{timer.publisher}}")
                .routeId("jms pojo publisher")
                .setBody(() -> pojo)
                .marshal().json(JsonLibrary.Jackson)
                .log("publishing ${body}")
                .convertBodyTo(String.class)
                .setHeader("JMSCorrelationID", () -> UUID.randomUUID().toString())
                .to("jms:{{amq.publish}}");
    }
}