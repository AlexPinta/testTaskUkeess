package ua.pinta.dao;

import org.springframework.stereotype.Repository;
import ua.pinta.model.Department;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext
//            (unitName = "postgresImplementation")
    private EntityManager entityManager;

    public List<Department> getList() {
        return entityManager.createQuery("FROM Department", Department.class).getResultList();
    }

    public Department findDepartmentByID(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> departmentRoot = query.from(Department.class);
        query.select(departmentRoot).where(builder.equal(departmentRoot.get("id"), id));
        List<Department> resultList = entityManager.createQuery(query).getResultList();
        if (resultList.size() == 1){
            return resultList.get(0);
        } else {
            return null;
        }    }

    @Override
    public void addDepartment(Department department) {
        entityManager.persist(department);
    }
}
