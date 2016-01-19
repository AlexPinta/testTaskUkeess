package ua.pinta.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.pinta.model.Department;

@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath*:/application-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoImplTest {
    @Autowired
    DepartmentDao departmentDao;

    @Test
    @Transactional
    public void testAddDepartment() throws Exception {
        Department department = new Department();
        department.setName("Dep1");
        departmentDao.addDepartment(department);
        Assert.assertEquals(department, departmentDao.getList().get(0));
    }

    @Test
    @Transactional
    public void testGetList() throws Exception {
        Department department = new Department();
        department.setName("Dep1");
        departmentDao.addDepartment(department);
        department = new Department();
        department.setName("Dep2");
        departmentDao.addDepartment(department);
        Assert.assertEquals(2, departmentDao.getList().size());
    }

    @Test
    @Transactional
    public void testFindDepartmentByID() throws Exception {
        Department department = new Department();
        department.setName("Dep1");
        departmentDao.addDepartment(department);
        department = new Department();
        department.setName("Dep2");
        departmentDao.addDepartment(department);
        Assert.assertEquals(department.getId(), departmentDao.findDepartmentByID(department.getId()).getId());
    }
}