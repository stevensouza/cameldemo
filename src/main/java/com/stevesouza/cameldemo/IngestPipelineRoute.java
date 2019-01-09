package com.stevesouza.cameldemo;

import org.apache.camel.spring.SpringRouteBuilder;

// just a dummy route that is more like pseudocode and wouldn't work.
public class IngestPipelineRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        // intercept().process(new MyInterceptor)
        // might allow us to track erroprs after each stage
        from("jms:queue:{{amq.subscribe}}")
                .bean("JsonCorrelationIdAdder")
                .to("mongodb:insert") // note this i believe is psuedocode.  look at MyJmsSubscriber for the real way to insert into mongodb
                .toD("{{amq.shadowtopic}}, {{kafka.topic}}")
                .setBody().constant("{id=id, amq=success, kafka=success}")
                .to("direct:ingestsuccesstatus");

    }
}
