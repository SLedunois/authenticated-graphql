package fr.sledunois;

import io.quarkus.security.Authenticated;
import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
@Authenticated
public class AuthenticatedGraphql {

    private final BroadcastProcessor<User> processor = BroadcastProcessor.create();

    @Query
    public Uni<User> get() {
        return Uni.createFrom().item(new User());
    }

    @Mutation
    public Uni<User> create() {
        var user = new User();
        processor.onNext(user);
        return Uni.createFrom().item(user);
    }

    @Subscription
    public Multi<User> userCreated() {
        return processor;
    }
}