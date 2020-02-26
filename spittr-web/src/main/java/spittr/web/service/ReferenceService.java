package spittr.web.service;

import spittr.web.dto.Reference;

import java.util.List;

public interface ReferenceService {

    Reference save(Reference reference);

    Reference findByUsername(String reference);

    List<Reference> findReferences(long max, int count);

    void deleteById(long i);
}