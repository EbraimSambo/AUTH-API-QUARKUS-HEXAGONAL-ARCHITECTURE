package features.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;

import features.contact.domain.models.enums.TypeState;
import features.contact.infrastructure.adapters.outbound.entities.UserContactEntity;
import features.password.infrastructure.adapters.outbound.entities.UserPasswordEntity;
import features.user.domain.models.UserModel;
import features.user.domain.models.records.UserCreateData;
import features.user.domain.ports.UserRepository;
import features.user.infrastructure.adapters.outbound.entities.UserEntity;
import features.user.infrastructure.adapters.outbound.mappers.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import shared.infrastructure.password.PasswordEncoder;

@ApplicationScoped
public class UserPersistence implements UserRepository {
    private static final Logger LOG = Logger.getLogger(UserPersistence.class);

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    UserPersistenceAdapter userPersistenceAdapter;

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
            userPersistenceAdapter.save(entity);
            return UserMapper.toDomain(entity);
        } catch (Exception e) {
            LOG.error("Falha ao criar usuário", e);
            throw new RuntimeException("Falha ao criar usuário: "
                    + (e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()), e);
        }
    }

    @Override
    public Optional<UserModel> findByContact(String contact) {
        return userPersistenceAdapter.findByContact(contact)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<UserModel> findByUUID(UUID uuid) {
        return userPersistenceAdapter.findByUUID(uuid)
                .map(UserMapper::toDomain);
    }

}
