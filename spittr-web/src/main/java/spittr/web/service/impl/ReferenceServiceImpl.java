package spittr.web.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;
import spittr.domain.model.Reference;
import spittr.web.service.ReferenceService;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

    private Traverson traverson;

    public ReferenceServiceImpl(Traverson traverson) {
        this.traverson = traverson;

    }

    @Override
    public Reference save(Reference reference) {
        return null;
    }

    @Override
    public Reference findByUsername(String reference) {
        //TODO ti finish
        ParameterizedTypeReference<Resource<Reference>> referenceType =
                new ParameterizedTypeReference<>() {
                };

        Reference references = traverson.follow("References").toObject(referenceType)
                .getContent();

        return references;
    }

    @Override
    public List<Reference> findReferences(long max, int count) {
        return null;
    }

    @Override
    public void deleteById(long i) {

    }
}
