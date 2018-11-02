package com.stevesouza.cameldemo;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyJmsSubscriber extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:{{amq.subscribe}}")
                .routeId("jms listener")
                .log("incoming headers=${headers}")
                .bean("JsonCorrelationIdAdder")
                .log("writing to mongodb: ${body}")
                .to("mongodb:myDb?database=pojodb&collection=mypojo&operation=insert");
    }

}