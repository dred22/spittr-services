package spittr.data;


import org.springframework.data.repository.CrudRepository;
import spittr.domain.model.ReferenceEntity;

public interface ReferenceDao
        extends CrudRepository<ReferenceEntity, Long> {
    ReferenceEntity findOneByUserName(String userName);
}
