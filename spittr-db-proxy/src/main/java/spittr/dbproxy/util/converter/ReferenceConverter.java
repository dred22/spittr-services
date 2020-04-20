package spittr.dbproxy.util.converter;

import spittr.data.model.ReferenceEntity;
import spittr.domain.model.Reference;

public class ReferenceConverter {
    public static Reference entityToDomain(ReferenceEntity referenceEntity) {
        return new Reference(
                referenceEntity.getId(),
                referenceEntity.getUserName(),
                referenceEntity.getPassword(),
                referenceEntity.getFirstName(),
                referenceEntity.getLastName(),
                referenceEntity.getEmail(),
                referenceEntity.getTs()
        );
    }
}
