package spittr.dbproxy.controller;


import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spittr.data.ReferenceDao;
import spittr.dbproxy.assembler.ReferenceResourceAssembler;
import spittr.domain.dto.ReferenceResource;
import spittr.domain.model.ReferenceEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/data/references",
        produces = "application/json")
public class DataReferenceController {

    private ReferenceDao referenceDao;
    private ReferenceResourceAssembler referenceResourceAssembler;

    public DataReferenceController(ReferenceDao referenceDao, ReferenceResourceAssembler referenceResourceAssembler) {
        this.referenceDao = referenceDao;
        this.referenceResourceAssembler = referenceResourceAssembler;
    }

    @GetMapping
    public Resources<ReferenceResource> getReferences() {

        List<ReferenceEntity> entitiesList = StreamSupport.stream(referenceDao.findAll().spliterator(), false)
                .collect(Collectors.toList());

        List<ReferenceResource> referenceResources = referenceResourceAssembler.toResources(entitiesList);

        Resources<ReferenceResource> resources =
                new Resources<>(referenceResources);

        resources.add(ControllerLinkBuilder.linkTo(DataReferenceController.class).withRel("recents"));
        return resources;
    }

    @GetMapping("{id}")
    public ResponseEntity<ReferenceResource> getReference(@PathVariable Long id) {
        Optional<ReferenceEntity> referenceEntity = referenceDao.findById(id);

        if (referenceEntity.isPresent()) {
            return new ResponseEntity<>(referenceResourceAssembler.toResource(referenceEntity.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
