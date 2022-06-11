package org.acme.kafka;

import io.apicurio.registry.serde.avro.AvroKafkaSerializer;
import org.acme.kafka.quarkus.Banana;

public class BananaSerializer extends AvroKafkaSerializer<Banana> {

}
