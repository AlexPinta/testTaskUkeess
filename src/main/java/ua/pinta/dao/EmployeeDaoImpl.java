package ua.pinta.dao;

import org.springframework.stereotype.Repository;
import ua.pinta.model.Employee;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    @PersistenceContext
//    (unitName = "postgresImplementation")
    private EntityManager entityManager;

    @Override
    public List<Employee> getList() {
        return entityManager.createQuery("FROM Employee", Employee.class).getResultList();
    }

    @Override
    public Employee findEmployeeByID(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.select(employeeRoot).where(builder.equal(employeeRoot.get("id"), id));
        List<Employee> resultList = entityManager.createQuery(query).getResultList();
        if (resultList.size() == 1){
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Employee findEmployeeByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.select(employeeRoot).where(builder.equal(employeeRoot.get("name"), name));
        List<Employee> resultList = entityManager.createQuery(query).getResultList();
        if (resultList.size() == 1){
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void removeEmployee(Employee employee) {
        entityManager.remove(employee);
    }

    @Override
    public List<Employee> findEmployeeByNameStartWith(String reqexp) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.select(employeeRoot).where(builder.like(employeeRoot.<String>get("name"), reqexp+"%"));
        return entityManager.createQuery(query).getResultList();
    }
}
