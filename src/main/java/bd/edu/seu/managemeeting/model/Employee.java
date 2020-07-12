package bd.edu.seu.managemeeting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"employeeId", "name"})
@Entity
public class Employee {
    @Id
    private String employeeId;
    private String name;
    private String designation;
    private String department;
    private String mail;
    private String loginStatus;


    public Employee(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}