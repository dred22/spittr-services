package spittr.data;


import org.springframework.data.repository.CrudRepository;
import spittr.domain.model.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, String> {
}
