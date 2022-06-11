package org.acme.kafka;

import io.apicurio.registry.serde.avro.AvroKafkaDeserializer;
import org.acme.kafka.quarkus.Movie;

public class MovieDeserializer extends AvroKafkaDeserializer<Movie> {

}
