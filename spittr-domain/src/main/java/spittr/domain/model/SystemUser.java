package spittr.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemUser {
    private String userName;
    private String password;
    private String authority;
}
