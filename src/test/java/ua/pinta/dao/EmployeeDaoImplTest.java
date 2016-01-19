package ua.pinta.dao;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.pinta.model.Department;
import ua.pinta.model.Employee;

import static org.junit.Assert.*;

@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@ContextConfiguration({"classpath*:/application-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoImplTest {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    Department department;

    @Before
    @Transactional
    public void start() throws Exception {
        department = new Department();
        department.setName("HR");
        departmentDao.addDepartment(department);
    }

    @Test
    @Transactional
    public void testGetList() throws Exception {
        Employee employee = new Employee("Employee1", true, departmentDao.getList().get(0));
        employeeDao.addEmployee(employee);
        employee = new Employee("Employee2", false, departmentDao.getList().get(0));
        employeeDao.addEmployee(employee);
        Assert.assertEquals(2, employeeDao.getList().size());
    }

    @Test
    @Transactional
    public void testFindEmployeeByID() throws Exception {
        Employee employee = new Employee("Employee1", true, department);
        employeeDao.addEmployee(employee);
        employee = new Employee("Employee2", true, department);
        employeeDao.addEmployee(employee);
        Assert.assertEquals(employee, employeeDao.findEmployeeByID(employee.getId()));
    }

    @Test
    @Transactional
    public void testFindEmployeeByName() throws Exception {
        Employee employee = new Employee("Employee1", true, department);
        employeeDao.addEmployee(employee);
        employee = new Employee("Employee2", true, department);
        employeeDao.addEmployee(employee);
        Assert.assertEquals(employee, employeeDao.findEmployeeByName(employee.getName()));
    }

    @Test
    @Transactional
    public void testAddEmployee() throws Exception {
        Employee employee = new Employee("Employee1", true, department);
        employeeDao.addEmployee(employee);
        Assert.assertEquals(employee, employeeDao.getList().get(0));
    }

    @Test
    @Transactional
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee("Employee1", true, department);
        employeeDao.addEmployee(employee);
        employee.setActive(false);
        employee.setName("Updated name");
        employeeDao.updateEmployee(employee);

        Employee comparedEmployee = employeeDao.getList().get(0);
        Assert.assertEquals(employee.getName(), comparedEmployee.getName());
        Assert.assertEquals(employee.isActive(), comparedEmployee.isActive());

    }

    @Test
    @Transactional
    public void testRemoveEmployee() throws Exception {
        Employee employee = new Employee("Employee1", true, department);
        employeeDao.addEmployee(employee);
        int removedID = employee.getId();

        employee = new Employee("Employee2", true, department);
        employeeDao.addEmployee(employee);
        employeeDao.removeEmployee(employeeDao.findEmployeeByID(removedID));
        Assert.assertEquals(employee, employeeDao.getList().get(0));

    }

    @Test
    @Transactional
    public void testFindEmployeeByNameStartWith() throws Exception {
        Employee employee = new Employee("Employee1", true, department);
        employeeDao.addEmployee(employee);
        String reqexp = employee.getName().substring(0, 4);
        employee = new Employee("Employee2", true, department);
        employeeDao.addEmployee(employee);
        employee = new Employee("TestEmployee3", true, department);
        employeeDao.addEmployee(employee);

        Assert.assertEquals(2, employeeDao.findEmployeeByNameStartWith(reqexp).size());
    }
}