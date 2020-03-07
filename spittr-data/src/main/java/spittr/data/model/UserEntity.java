package spittr.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "ENABLED")
    private boolean isEnabled;

}
