package spittr.dbproxy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.data.ReferenceRepository;
import spittr.data.model.ReferenceEntity;
import spittr.dbproxy.util.mapper.ReferenceMapper;
import spittr.domain.model.Reference;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping(path = "/data/references", produces = "application/json")
public class DataReferenceController {

    private ReferenceRepository referenceRepository;

    private ReferenceMapper referenceMapper;

    public DataReferenceController(ReferenceRepository referenceRepository, ReferenceMapper referenceMapper) {
        this.referenceRepository = referenceRepository;
        this.referenceMapper = referenceMapper;
    }

    @GetMapping
    public List<Reference> getReferences(@RequestParam("page") Optional<Integer> pageNumber) {
        PageRequest page;
        if (pageNumber.isPresent()) {
            page = PageRequest.of(pageNumber.get(), 10);
        } else {
            page = PageRequest.of(0, 10);
        }
        log.info("Getting references, page=[{}]", page);
        List<Reference> references = StreamSupport.stream(referenceRepository.findAll(page).spliterator(), false)
                .map(referenceMapper::referenceEntityToReference)
                .collect(Collectors.toList());

        return references;
    }

    @GetMapping("{id}")
    public ResponseEntity<Reference> getReference(@PathVariable Long id) {
        log.info("Getting reference by id [{}]", id);
        Optional<ReferenceEntity> referenceEntity = referenceRepository.findById(id);

        if (referenceEntity.isPresent()) {
            return new ResponseEntity<>(referenceMapper.referenceEntityToReference(referenceEntity.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting reference by id [{}]", id);
        referenceRepository.deleteById(id);
    }

    @PostMapping()
    public ResponseEntity<Reference> save(@RequestBody Reference reference) {
        log.info("Getting reference [{}]", reference);
        ReferenceEntity saved = referenceRepository.save(referenceMapper.referenceToReferenceEntity(reference));
        return new ResponseEntity<>(referenceMapper.referenceEntityToReference(saved), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Reference> update(@RequestBody Reference reference) {
        log.info("Getting reference [{}]", reference);
        ReferenceEntity saved = referenceRepository.save(referenceMapper.referenceToReferenceEntity(reference));
        return new ResponseEntity<>(referenceMapper.referenceEntityToReference(saved), HttpStatus.NO_CONTENT);
    }
}
