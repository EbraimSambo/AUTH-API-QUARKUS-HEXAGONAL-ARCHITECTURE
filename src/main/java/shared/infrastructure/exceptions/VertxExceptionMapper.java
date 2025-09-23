package shared.infrastructure.exceptions;

import java.time.Instant;

import io.vertx.core.impl.NoStackTraceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import shared.domain.exceptions.ErrorResponse;

@Provider
public class VertxExceptionMapper implements ExceptionMapper<NoStackTraceException> {
    @Override
    public Response toResponse(NoStackTraceException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("VERTX_ERROR", exception.getMessage(), Instant.now()))
                .build();
    }
}
