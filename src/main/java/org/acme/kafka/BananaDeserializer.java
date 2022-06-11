package org.acme.kafka;

import io.apicurio.registry.serde.avro.AvroKafkaDeserializer;
import org.acme.kafka.quarkus.Banana;

public class BananaDeserializer extends AvroKafkaDeserializer<Banana> {

}
