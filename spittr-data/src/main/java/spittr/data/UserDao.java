package spittr.data;


import org.springframework.data.repository.PagingAndSortingRepository;
import spittr.domain.model.UserEntity;

public interface UserDao extends PagingAndSortingRepository<UserEntity, String> {
}
