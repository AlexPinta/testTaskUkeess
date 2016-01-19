package ua.pinta.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.pinta.dao.DepartmentDao;
import ua.pinta.dao.EmployeeDao;
import ua.pinta.model.Department;
import ua.pinta.model.Employee;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration({"classpath*:/application-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeDao employeeDao;
    @Mock
    DepartmentDao departmentDao;
    @InjectMocks
    EmployeeService employeeService;
    @Mock
    Department department;

    @Before
    public void setUp() throws Exception {
        employeeService = new EmployeeServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetList() throws Exception {
        when(employeeService.getList()).thenReturn(new ArrayList<Employee>());
        employeeService.getList();
        verify(employeeDao, times(1)).getList();
    }

    @Test
    public void testAddEmployeeNull() throws Exception {
        employeeService.addEmployee(null, true, 1);
        verify(employeeDao, never()).addEmployee(any(Employee.class));
    }

    @Test(expected = RuntimeException.class)
    public void testAddEmployeeTwice() throws Exception {
        when(departmentDao.findDepartmentByID(1)).thenReturn(department).thenReturn(department);
        when(employeeDao.findEmployeeByName(eq("Employee"))).thenReturn(null)
                .thenReturn(new Employee("Employee", true, department));
        employeeService.addEmployee("Employee", true, 1);
        employeeService.addEmployee("Employee", true, 1);
        verify(employeeDao, times(1)).addEmployee(any(Employee.class));
        verify(employeeDao, times(2)).findEmployeeByName(contains("Employee"));
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateEmployee() throws Exception {
        when(departmentDao.findDepartmentByID(1)).thenReturn(department);
        when(employeeDao.findEmployeeByName(eq("Employee"))).thenReturn(null);
        when(employeeDao.findEmployeeByID(eq(0))).thenReturn(new Employee("Employee", true, department));
        employeeService.updateEmployee(0, "Updated Name", true, 1);
        employeeService.updateEmployee(100, "Updated name for not existing employee", true, 1);
        verify(employeeDao, times(2)).findEmployeeByID(anyInt());
        verify(employeeDao, times(1)).updateEmployee(any(Employee.class));
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveEmployee() throws Exception {
        when(departmentDao.findDepartmentByID(1)).thenReturn(department);
        when(employeeDao.findEmployeeByID(eq(0))).thenReturn(new Employee("Employee", true, department));
        employeeService.removeEmployee(1);
        employeeService.removeEmployee(100);
        verify(employeeDao, times(2)).findEmployeeByID(anyInt());
        verify(employeeDao, times(1)).removeEmployee(any(Employee.class));
    }

    @Test(expected = RuntimeException.class)
    public void testFindEmployeeByNameStartWith() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Employee", true, department));
        when(employeeDao.findEmployeeByNameStartWith("Empl")).thenReturn(employees);
        when(employeeDao.findEmployeeByNameStartWith("Empl1")).thenReturn(new ArrayList<Employee>());
        employeeService.findEmployeeByNameStartWith("Empl");
        employeeService.findEmployeeByNameStartWith("Empl1");
        verify(employeeDao, times(2)).findEmployeeByNameStartWith(anyString());
    }
}