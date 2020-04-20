package spittr.dbproxy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.data.ReferenceDao;
import spittr.data.model.ReferenceEntity;
import spittr.dbproxy.util.converter.ReferenceConverter;
import spittr.domain.model.Reference;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping(path = "/data/references",
        produces = "application/json")
public class DataReferenceController {

    private ReferenceDao referenceDao;

    public DataReferenceController(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @GetMapping
    public List<Reference> getReferences(@RequestParam("page") Optional<Integer> pageNumber) {
        PageRequest page;
        if (pageNumber.isPresent()) {
            page = PageRequest.of(pageNumber.get(), 5);
        } else {
            page = PageRequest.of(0, 5);
        }
        log.info("Getting references, page=[{}]", page);
        List<Reference> references = StreamSupport.stream(referenceDao.findAll(page).spliterator(), false)
                .map(ReferenceConverter::entityToDomain)
                .collect(Collectors.toList());

        return references;
    }

    @GetMapping("{id}")
    public ResponseEntity<Reference> getReference(@PathVariable Long id) {
        log.info("Getting reference by id [{}]", id);
        Optional<ReferenceEntity> referenceEntity = referenceDao.findById(id);

        if (referenceEntity.isPresent()) {
            return new ResponseEntity<>(ReferenceConverter.entityToDomain(referenceEntity.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
