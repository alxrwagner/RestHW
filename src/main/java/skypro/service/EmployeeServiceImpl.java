package skypro.service;

import org.springframework.stereotype.Service;
import skypro.dao.EmployeeDAO;
import skypro.entity.Employee;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional // Передает управление транзакциями спрингу
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    @Transactional
    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    @Transactional
    public void removeById(int id) {
        employeeDAO.removeById(id);
    }
}
