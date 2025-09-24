package features.user.infrastructure.adapters.outbound.mappers;

import features.auth.infrastructure.adapters.outbound.dto.CreateUserDto;
import features.contact.domain.models.UserContactModel;
import features.contact.domain.models.enums.TypeState;
import features.password.domain.models.UserPasswordModel;
import features.user.domain.models.UserModel;
import features.user.domain.models.records.UserCreateData;
import features.user.infrastructure.adapters.outbound.entities.UserEntity;

public class UserMapper {

    public static UserModel toDomain(UserEntity entity) {
        UserModel domain = new UserModel();
        domain.setId(entity.id);
        domain.setUuid(entity.getUuid());
        domain.setName(entity.getName());
        domain.setGender(entity.getGender());
        domain.setCreatedAt(entity.getCreatedAt());
        domain.setDeletedAt(entity.getDeletedAt());
        domain.setUpdatedAt(entity.getUpdatedAt());
        return domain;
    }

    public static UserEntity toEntity(UserModel domain) {
        UserEntity entity = new UserEntity();
        entity.id = domain.getId();
        entity.setUuid(domain.getUuid());
        entity.setName(domain.getName());
        entity.setGender(domain.getGender());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setDeletedAt(domain.getDeletedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    public static UserModel toUserCreateData(UserCreateData data) {
        UserModel user = new UserModel();
        user.setName(data.name());
        user.setGender(data.gender());
        UserContactModel contact = new UserContactModel();
        contact.setUser(user);
        contact.setValue(data.contact());
        contact.setState(TypeState.ACTIVE);
        contact.setType(data.contactType());
        user.getContacts().add(contact);
        UserPasswordModel password = new UserPasswordModel();
        password.setValue(data.password());
        password.setUser(user);
        password.setState(TypeState.ACTIVE);
        user.getPasswords().add(password);
        return user;
    }

    public static UserCreateData toUserCreateData(CreateUserDto dto) {
        return new UserCreateData(
                dto.contact(),
                dto.password(),
                dto.gender(),
                dto.name(),
                dto.contactType());
    }

}
