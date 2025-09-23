package features.contact.adapters.out.mappers;

import features.contact.adapters.out.entities.UserContactEntity;
import features.contact.domain.models.UserContactModel;
import features.user.adapters.out.mappers.UserMapper;

public class UseContactMapper {

    public static UserContactModel toDomain(UserContactEntity entity) {
        UserContactModel domain = new UserContactModel();
        domain.setId(entity.id);
        domain.setValue(entity.getValue());
        domain.setState(entity.getState());
        domain.setType(entity.getType());
        if (entity.getUser() != null) {
            domain.setUser(UserMapper.toDomain(entity.getUser()));
        }
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setDeletedAt(entity.getDeletedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public static UserContactEntity toEntity(UserContactModel domain) {
        UserContactEntity entity = new UserContactEntity();
        entity.id = domain.getId();
        entity.setValue(domain.getValue());
        entity.setState(domain.getState());
        entity.setType(domain.getType());
        if (domain.getUser() != null) {
            entity.setUser(UserMapper.toEntity(domain.getUser()));
        }
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setDeletedAt(domain.getDeletedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

}
