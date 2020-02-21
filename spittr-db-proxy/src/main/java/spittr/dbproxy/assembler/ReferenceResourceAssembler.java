package spittr.dbproxy.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import spittr.dbproxy.controller.DataReferenceController;
import spittr.domain.dto.ReferenceResource;
import spittr.domain.model.ReferenceEntity;

@Component
public class ReferenceResourceAssembler extends ResourceAssemblerSupport<ReferenceEntity, ReferenceResource> {

    public ReferenceResourceAssembler() {
        super(DataReferenceController.class, ReferenceResource.class);
    }

    @Override
    public ReferenceResource instantiateResource(ReferenceEntity referenceEntity) {
        return new ReferenceResource(referenceEntity);
    }

    @Override
    public ReferenceResource toResource(ReferenceEntity entity) {
        return createResourceWithId(entity.getId().toString(), entity);
    }
}
