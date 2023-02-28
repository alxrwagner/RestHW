package skypro.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import skypro.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private SessionFactory sessionFactory;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Employee").list();
    }


    @Override
    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void add(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }

    @Override
    public void removeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("DELETE FROM Employee where id=:id"); // org.hibernate.query.Query;

        query.setParameter("id", id);
        query.executeUpdate();
    }
}
