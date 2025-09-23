package features.user.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;

import features.contact.adapters.out.entities.UserContactEntity;
import features.contact.domain.enums.TypeState;
import features.password.adapters.out.entities.UserPasswordEntity;
import features.user.adapters.out.entities.UserEntity;
import features.user.adapters.out.mappers.UserMapper;
import features.user.domain.models.UserModel;
import features.user.domain.records.UserCreateData;
import features.user.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import shared.infrastructure.password.PasswordEncoder;

@ApplicationScoped
public class UserPersistence implements UserRepository {
    private static final Logger LOG = Logger.getLogger(UserPersistence.class);

    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserModel create(UserCreateData data) {
        String hash = passwordEncoder.hash(data.password());
        try {
            UserEntity entity = new UserEntity();
            entity.setName(data.name());
            entity.setGender(data.gender());
            UserContactEntity contactEntity = new UserContactEntity();
            contactEntity.setValue(data.contact());
            contactEntity.setType(data.contactType());
            contactEntity.setUser(entity);
            entity.getContacts().add(contactEntity);
            UserPasswordEntity passwordEntity = new UserPasswordEntity();
            passwordEntity.setValue(hash);
            passwordEntity.setState(TypeState.ACTIVE);
            passwordEntity.setUser(entity);
            entity.getPasswords().add(passwordEntity);
            entity.persist();
            return UserMapper.toDomain(entity);
        } catch (Exception e) {
            LOG.error("Falha ao criar usuário", e);
            throw new RuntimeException("Falha ao criar usuário: "
                    + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()), e);
        }
    }

    @Override
    public Optional<UserModel> findByContact(String contact) {
        Optional<UserContactEntity> contactEntity = UserContactEntity
                .find("value = ?1 and state = ?2", contact, TypeState.ACTIVE)
                .firstResultOptional();
        return contactEntity.map(UserContactEntity::getUser)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<UserModel> findByUUID(UUID uuid) {
        Optional<UserEntity> entity = UserEntity.find("uuid", uuid).firstResultOptional();
        return entity.map(UserMapper::toDomain);
    }

}
