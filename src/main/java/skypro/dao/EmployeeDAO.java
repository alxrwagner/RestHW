package skypro.dao;

import skypro.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAll();
    Employee getById(int id);
    void add(Employee employee);
    void update(Employee employee);
    void removeById(int id);
}
