package spittr.domain.model;


import lombok.Data;
import spittr.domain.converter.LocalDateAttributeConverter;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "REFERENCE")
@Data
public class ReferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="FIRSTNAME")
    private String firstName;

    @Column(name="LASTNAME")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name="TS")
    private LocalDate ts;

    @Column(name="DELETED")
    private boolean isDeleted;

}