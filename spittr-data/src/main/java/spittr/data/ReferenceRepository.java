package spittr.data;


import org.springframework.data.repository.PagingAndSortingRepository;
import spittr.data.model.ReferenceEntity;

public interface ReferenceRepository
        extends PagingAndSortingRepository<ReferenceEntity, Long> {

}
