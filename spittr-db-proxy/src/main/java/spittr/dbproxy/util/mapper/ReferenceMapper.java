package spittr.dbproxy.util.mapper;

import org.mapstruct.Mapper;
import spittr.data.model.ReferenceEntity;
import spittr.domain.model.Reference;

@Mapper
public interface ReferenceMapper {
    Reference referenceEntityToReference(ReferenceEntity referenceEntity);

    ReferenceEntity referenceToReferenceEntity(Reference referenceEntity);
}
