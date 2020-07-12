package bd.edu.seu.managemeeting.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"meetingId"})
@Entity
public class Meetings {
    @Id
    private String meetingId;
    private Venue venue;
    private String subject;
    private LocalDateTime DateAndTime;
    private Integer duration;
    private List<Employee> employeeList;

}

