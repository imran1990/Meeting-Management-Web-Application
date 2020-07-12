package bd.edu.seu.managemeeting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"venueId"})
@Entity
public class Venue {
    @Id
    private String venueId;
    private Integer capacity;
    private List<String> facilityList;

}
