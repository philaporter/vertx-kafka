package com.philaporter.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerVerticle extends AbstractVerticle {

    Map<String, String> mapConfig = new HashMap<>();
    EventBus eb;

    @Override
    public void start() {
        eb = vertx.eventBus();
        mapConfig.put("bootstrap.servers", "localhost:9092");
        mapConfig.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        mapConfig.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        mapConfig.put("acks", "1");

        KafkaProducer<String, String> producer = KafkaProducer.create(vertx, mapConfig);
        eb.consumer("trigger", handle -> {
            producer.write(KafkaProducerRecord.create("test", handle.body().toString()));
        });
    }
}
