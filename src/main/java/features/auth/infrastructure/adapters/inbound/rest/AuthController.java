package features.auth.infrastructure.adapters.inbound.rest;

import features.auth.domain.ports.SignInUseCase;
import features.auth.infrastructure.adapters.outbound.dto.CreateUserDto;
import features.auth.infrastructure.adapters.outbound.dto.SignInRequest;
import features.user.domain.ports.CreateUserUseCase;
import features.user.infrastructure.adapters.outbound.mappers.UserMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {
    @Inject
    CreateUserUseCase createUserUseCase;

    @Inject
    SignInUseCase signInUseCase;

    @POST
    @Path("/sign-up")
    public Response create(@Valid CreateUserDto dto) {
        return Response.status(Response.Status.CREATED).entity(
                createUserUseCase.execute(UserMapper.toUserCreateData(dto))).build();
    }

    @POST
    @Path("/sign-in")
    public Response login(@Valid SignInRequest request) {
        return Response.ok(signInUseCase.execute(request.contact(), request.password())).build();
    }
}
