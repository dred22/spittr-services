package spittr.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spittr.domain.model.Reference;
import spittr.web.service.ReferenceService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ReferenceServiceImpl implements ReferenceService {

    private RestTemplate restClient;
    private String referenceUrl;

    public ReferenceServiceImpl(RestTemplate restClient, @Value("${spittr.reference-url}") String referenceUrl) {
        this.restClient = restClient;
        this.referenceUrl = referenceUrl;
    }

    @Override
    public Reference save(Reference reference) {
        ResponseEntity<Reference> referenceResponseEntity = restClient.postForEntity(referenceUrl, reference, Reference.class);

        return null;
    }

    @Override
    public Reference findByUsername(String userName) {
        //TODO ti finish
/*        ParameterizedTypeReference<Resource<Reference>> referenceType =
                new ParameterizedTypeReference<>() {
                };*/

        Reference reference = restClient.getForObject(referenceUrl + "/{name}", Reference.class, userName);

        return reference;
    }

    @Override
    public Reference findById(Long id) {
        Reference reference = restClient.getForObject(referenceUrl + "/{id}", Reference.class, id);

        return reference;
    }

    @Override
    public List<Reference> findReferences(long max, int count) {
        Reference[] referencesArray = restClient.getForObject(referenceUrl, Reference[].class);
        List<Reference> references = referencesArray != null ? Arrays.asList(referencesArray) : Collections.emptyList();
        log.info("Got references [{}]", references.size());
        return references;
    }

    @Override
    public void deleteById(long i) {

    }
}
