package com.stevesouza.cameldemo;

import org.apache.camel.spring.SpringRouteBuilder;

//@Component
public class MyTimerRoute1 extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:foo?period={{timer.period1}}")
                .to("direct:jmsgenerator");
    }
}