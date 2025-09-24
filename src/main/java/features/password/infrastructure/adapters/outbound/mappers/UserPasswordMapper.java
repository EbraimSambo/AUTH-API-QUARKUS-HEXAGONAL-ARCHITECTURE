package features.password.infrastructure.adapters.outbound.mappers;

import features.password.domain.models.UserPasswordModel;
import features.password.infrastructure.adapters.outbound.entities.UserPasswordEntity;
import features.user.infrastructure.adapters.outbound.mappers.UserMapper;

public class UserPasswordMapper {

    public static UserPasswordModel toDomain(UserPasswordEntity entity) {
        UserPasswordModel domain = new UserPasswordModel();
        domain.setId(entity.id);
        domain.setValue(entity.getValue());
        domain.setState(entity.getState());
        if (entity.getUser() != null) {
            domain.setUser(UserMapper.toDomain(entity.getUser()));
        }
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setDeletedAt(entity.getDeletedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public static UserPasswordEntity toEntity(UserPasswordModel domain) {
        UserPasswordEntity entity = new UserPasswordEntity();
        entity.id = domain.getId();
        entity.setValue(domain.getValue());
        entity.setState(domain.getState());
        if (domain.getUser() != null) {
            entity.setUser(UserMapper.toEntity(domain.getUser()));
        }
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setDeletedAt(domain.getDeletedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

}
