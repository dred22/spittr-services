package spittr.dbproxy.controller;


import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.data.ReferenceDao;
import spittr.dbproxy.assembler.ReferenceResourceAssembler;
import spittr.dbproxy.hateos.resource.ReferenceResource;
import spittr.data.model.ReferenceEntity;

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
    public Resources<ReferenceResource> getReferences(@RequestParam("page") Optional<Integer> pageNumber) {
        PageRequest page;
        if (pageNumber.isPresent()) {
            page = PageRequest.of(pageNumber.get(), 5);
        } else {
            page = PageRequest.of(0, 5);
        }
        List<ReferenceEntity> entitiesList = StreamSupport.stream(referenceDao.findAll(page).spliterator(), false)
                .collect(Collectors.toList());

        List<ReferenceResource> referenceResources = referenceResourceAssembler.toResources(entitiesList);

        Resources<ReferenceResource> resources =
                new Resources<>(referenceResources);

        resources.add(ControllerLinkBuilder.linkTo(DataReferenceController.class).withSelfRel());
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
