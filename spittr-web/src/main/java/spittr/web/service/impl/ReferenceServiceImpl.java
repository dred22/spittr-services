package spittr.web.service.impl;

import org.springframework.stereotype.Service;
import spittr.web.dto.Reference;
import spittr.web.service.ReferenceService;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {
    @Override
    public Reference save(Reference reference) {
        return null;
    }

    @Override
    public Reference findByUsername(String reference) {
        return null;
    }

    @Override
    public List<Reference> findReferences(long max, int count) {
        return null;
    }

    @Override
    public void deleteById(long i) {

    }
}
