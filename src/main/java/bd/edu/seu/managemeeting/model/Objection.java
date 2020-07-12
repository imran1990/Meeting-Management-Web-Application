package bd.edu.seu.managemeeting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Objection {
    @Id
    private ObjectId id;
    private Meetings meeting;
    private Employee employee;
    private String objection;
}
