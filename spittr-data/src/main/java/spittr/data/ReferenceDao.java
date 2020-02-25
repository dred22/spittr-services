package spittr.data;


import org.springframework.data.repository.PagingAndSortingRepository;
import spittr.domain.model.ReferenceEntity;

public interface ReferenceDao
        extends PagingAndSortingRepository<ReferenceEntity, Long> {
    ReferenceEntity findOneByUserName(String userName);
}
