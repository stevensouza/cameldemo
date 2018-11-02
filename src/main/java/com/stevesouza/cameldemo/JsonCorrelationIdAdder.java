package com.stevesouza.cameldemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Body;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("JsonCorrelationIdAdder")
public class JsonCorrelationIdAdder {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Handler
    public String addCorrelationId(@Body String body, @Header("JMSCorrelationID") String correlationId) throws IOException {
        ObjectNode root = (ObjectNode) objectMapper.readTree(body);
        root.put("JMSCorrelationID", correlationId);
        root.put("msgSource", "old");
        return objectMapper.writeValueAsString(root);
    }
}