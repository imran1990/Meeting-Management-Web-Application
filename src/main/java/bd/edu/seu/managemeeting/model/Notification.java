package bd.edu.seu.managemeeting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = {"notificationId"})
@Entity
public class Notification {
    @Id
    private String notificationId;
    private String employeeId;
    private String message;

}
