package bd.edu.seu.managemeeting.service;

import bd.edu.seu.managemeeting.exception.ResourceNotFoundException;
import bd.edu.seu.managemeeting.exception.ResourceAlreadyExistsException;
import bd.edu.seu.managemeeting.model.Employee;
import bd.edu.seu.managemeeting.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public  class EmployeeService {

    private EmployeeRepository employeeRepository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee insertEmployee(Employee employee) throws ResourceAlreadyExistsException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getEmployeeId());
        if(optionalEmployee.isPresent()) {
            logger.warn("Employee ID {} already exists", employee.getEmployeeId());
            throw new ResourceAlreadyExistsException(employee.getEmployeeId() + "");
        }
        else{
            return employeeRepository.save(employee);
        }
    }

    public Employee updateEmployee (String employeeId, Employee employee) throws ResourceNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isPresent()){
            employee.setEmployeeId(employeeId);
            return employeeRepository.save(employee);
        }
        else {
            logger.warn("Employee ID {} doesn't exist", employee.getEmployeeId());
            throw new ResourceNotFoundException(employeeId + "");}
    }

    public Employee findById(String employeeId) throws ResourceNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }else {
            logger.warn("Employee ID {} doesn't exist", employeeId);
            throw new ResourceNotFoundException(employeeId + "");
        }
    }

    public List<Employee>findAll(){
        List<Employee>employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    public  boolean deleteById(String employeeId) throws ResourceNotFoundException{
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        optionalEmployee.ifPresent(employee -> employeeRepository.deleteById(employeeId));
        logger.info("Employee ID {} deleted", employeeId);
        optionalEmployee.orElseThrow(() -> new ResourceNotFoundException(employeeId + ""));
        return true;
    }



}
