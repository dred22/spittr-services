package spittr.dbproxy.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spittr.data.ReferenceDao;
import spittr.domain.model.ReferenceEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/data",
        produces = "application/json")
public class DataReferenceController {

    private ReferenceDao referenceDao;

    public DataReferenceController(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @GetMapping("/references")
    public ResponseEntity<List<ReferenceEntity>> getReferences() {

        List<ReferenceEntity> collect = StreamSupport.stream(referenceDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            return new ResponseEntity<>(collect, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reference/{username}")
    public ResponseEntity<ReferenceEntity> getReference(@PathVariable String username) {
        ReferenceEntity referenceEntity = referenceDao.findOneByUserName(username);
        if (referenceEntity != null) {
            return new ResponseEntity<>(referenceEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
