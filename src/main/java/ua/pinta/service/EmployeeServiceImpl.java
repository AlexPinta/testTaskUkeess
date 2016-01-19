package ua.pinta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.pinta.dao.DepartmentDao;
import ua.pinta.dao.EmployeeDao;
import ua.pinta.model.Department;
import ua.pinta.model.Employee;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    final Logger logger = LoggerFactory.getLogger("log4jLog");

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<Employee> getList() {
        return employeeDao.getList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmployee(String name, boolean active, int departmentID) {
        if (name != null) {
            if (employeeDao.findEmployeeByName(name) != null) {
                RuntimeException e = new RuntimeException("Create operation: attempt to add existing employee: " + name);
                logger.info("Attempt to add existing employee: " + name, e);
                throw e;
            } else {
                Department department = departmentDao.findDepartmentByID(departmentID);
                if (department != null) {
                    employeeDao.addEmployee(new Employee(name, active, department));
                } else {
                    RuntimeException e = new RuntimeException("Can't find department by id: " + departmentID);
                    logger.info("Can't find department by id: " + departmentID, e);
                    throw e;
                }
            }
        }
    }

    @Override
//    @Transactional(propagation = PROPAGATION_NOT_SUPPORTED)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateEmployee(int id, String name, boolean active, int departmentID) {
        Employee employee = employeeDao.findEmployeeByID(id);
        if (employee == null){
            RuntimeException e = new RuntimeException("Update operation: can't find employee by id: " + id);
            logger.info("Update operation: can't find employee by id: " + id, e);
            throw e;
        } else {
            Department department = departmentDao.findDepartmentByID(departmentID);
            if (department != null){
                employee.setName(name);
                employee.setActive(active);
                employee.setDepartment(department);
                employeeDao.updateEmployee(employee);
            } else {
                RuntimeException e = new RuntimeException("Can't find department by id: " + departmentID);
                logger.info("Can't find department by id: " + departmentID, e);
                throw e;
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeEmployee(int id) {
        Employee employee = employeeDao.findEmployeeByID(id);
        if (employee == null){
            RuntimeException e = new RuntimeException("Remove operation: can't find employee by id: " + id);
            logger.info("Remove operation: can't find employee by id: " + id, e);
            throw e;
        } else {
            employeeDao.removeEmployee(employee);
        }
    }

    @Override
    public List<Employee> findEmployeeByNameStartWith(String reqexp) {
        List<Employee> employees = employeeDao.findEmployeeByNameStartWith(reqexp);
        if (employees.size() == 0){
            RuntimeException e = new RuntimeException("Search operation: can't find employees by expression: " + reqexp);
            logger.info("Search operation: can't find employees by expression: " + reqexp, e);
            throw e;
        } else {
            return employees;
        }
    }
}
