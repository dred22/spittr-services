package spittr.data;


import org.springframework.data.repository.PagingAndSortingRepository;
import spittr.data.model.ReferenceEntity;

public interface ReferenceDao
        extends PagingAndSortingRepository<ReferenceEntity, Long> {

}
