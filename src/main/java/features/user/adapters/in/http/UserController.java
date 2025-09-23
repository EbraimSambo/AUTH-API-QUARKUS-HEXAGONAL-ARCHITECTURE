package features.user.adapters.in.http;

import org.eclipse.microprofile.jwt.JsonWebToken;

import features.user.domain.services.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import shared.infrastructure.utils.uuid.UUIDValidator;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    @GET
    @RolesAllowed("user")
    @Path("/protected")
    public Response protectedEndpoint() {
        String uuid = jwt.getSubject();
        return Response.ok(
                userService.validateUserUnauthorized(UUIDValidator.toUUID(uuid))).build();
    }
}
