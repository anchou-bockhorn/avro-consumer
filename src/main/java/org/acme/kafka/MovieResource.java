package ch.abraxas.steuern.polaris.personenregister.rest;

import java.util.concurrent.CompletionStage;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.acme.kafka.quarkus.Banana;
import org.acme.kafka.quarkus.Movie;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

@ApplicationScoped
@Path("v1")
public class MovieResource {

    @Channel("movies")
    Emitter<Movie> emitter;

    @Channel("bananas")
    Emitter<Banana> bananaEmitter;

    @POST
    @Path("movie")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMovie(Movie movie) {
        try {
            emitter.send(Message.of(movie));
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Incoming("movies-from-kafka")
    public CompletionStage<Void> consumeMovies(Message<Movie> movieMessage) {
        System.out.println("Consumed movie" + movieMessage.getPayload());
        return movieMessage.ack();
    }

    @POST
    @Path("banana")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBanana(Banana movie) {
        try {
            bananaEmitter.send(Message.of(movie));
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Incoming("bananas-from-kafka")
    public CompletionStage<Void> consumeBananas(Message<Banana> movieMessage) {
        System.out.println("Consumed banana" + movieMessage.getPayload());
        return movieMessage.ack();
    }
}
