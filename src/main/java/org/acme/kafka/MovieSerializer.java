package org.acme.kafka;

import io.apicurio.registry.serde.avro.AvroKafkaSerializer;
import org.acme.kafka.quarkus.Movie;

public class MovieSerializer extends AvroKafkaSerializer<Movie> {

}
