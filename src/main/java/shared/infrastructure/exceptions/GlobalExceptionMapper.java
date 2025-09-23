package shared.infrastructure.exceptions;

import java.time.Instant;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import shared.domain.exceptions.BadRequestException;
import shared.domain.exceptions.ConflictException;
import shared.domain.exceptions.ErrorResponse;
import shared.domain.exceptions.NotFoundException;
import shared.domain.exceptions.UnauthorizedException;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        LOG.error("Exceção capturada", exception);

        // Validações com @Valid (Bean Validation)
        if (exception instanceof ConstraintViolationException e) {
            String errors = e.getConstraintViolations()
                    .stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));
            return build(Response.Status.BAD_REQUEST, "VALIDATION_ERROR", errors);
        }

        // Exceções customizadas
        if (exception instanceof BadRequestException e) {
            return build(Response.Status.BAD_REQUEST, "BAD_REQUEST", e.getMessage());
        }
        if (exception instanceof UnauthorizedException e) {
            return build(Response.Status.UNAUTHORIZED, "UNAUTHORIZED", e.getMessage());
        }
        if (exception instanceof NotFoundException e) {
            return build(Response.Status.NOT_FOUND, "NOT_FOUND", e.getMessage());
        }
        if (exception instanceof ConflictException e) {
            return build(Response.Status.CONFLICT, "CONFLICT", e.getMessage());
        }

        // Exceções JAX-RS
        if (exception instanceof jakarta.ws.rs.BadRequestException e) {
            return build(Response.Status.BAD_REQUEST, "BAD_REQUEST", e.getMessage());
        }
        if (exception instanceof jakarta.ws.rs.NotFoundException e) {
            return build(Response.Status.NOT_FOUND, "NOT_FOUND", e.getMessage());
        }

        // Fallback para erros não tratados
        String errorMessage = exception.getMessage() != null ? exception.getMessage() : "Erro interno: " + exception.getClass().getSimpleName();
        return build(Response.Status.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", errorMessage);
    }

    private Response build(Response.Status status, String code, String message) {
        return Response.status(status)
                .entity(new ErrorResponse(code, message, Instant.now()))
                .build();
    }
}