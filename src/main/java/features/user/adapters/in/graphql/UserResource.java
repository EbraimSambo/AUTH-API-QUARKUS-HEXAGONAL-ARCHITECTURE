package features.user.adapters.in.graphql;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import features.user.domain.models.UserModel;
import features.user.domain.services.UserService;
import jakarta.inject.Inject;
import shared.infrastructure.utils.uuid.UUIDValidator;

@GraphQLApi
public class UserResource {

    @Inject
    UserService userService;

    @Query
    @Description("Get User")
    public UserModel getUser(@Name("uuid") String uuid) {
        return userService.validateUserNotFound(UUIDValidator.toUUID(uuid));
    }

}
