package spittr.dbproxy.hateos.resource;


import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import spittr.data.model.ReferenceEntity;

import java.time.LocalDate;

@Getter
@Relation(value = "Reference", collectionRelation = "References")
public class ReferenceResource extends ResourceSupport {

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate ts;

    public ReferenceResource(ReferenceEntity referenceEntity) {
        this.userName = referenceEntity.getUserName();
        this.password = referenceEntity.getPassword();
        this.firstName = referenceEntity.getFirstName();
        this.lastName = referenceEntity.getLastName();
        this.email = referenceEntity.getEmail();
        this.ts = referenceEntity.getTs();
    }
}