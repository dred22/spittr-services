package spittr.domain.model.massage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewReferenceCreated implements Serializable {

    static final long serialVersionUID = -8262818152527467001L;

    private Long id;

}
