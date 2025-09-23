package shared.infrastructure.utils.uuid;

import java.util.UUID;

import jakarta.ws.rs.BadRequestException;


public class UUIDValidator {
    public static UUID toUUID(String uuid) {
        if (uuid == null || uuid.trim().isEmpty()) {
            throw new BadRequestException("UUID string não pode ser nula ou vazia");
        }
    
        try {
            return UUID.fromString(uuid.trim());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Formato de UUID inválido: " + uuid, e);
        }
    }
}