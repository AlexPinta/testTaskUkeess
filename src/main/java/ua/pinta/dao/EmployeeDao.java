package ua.pinta.dao;

import ua.pinta.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getList();
    Employee findEmployeeByID(int id);
    Employee findEmployeeByName(String name);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void removeEmployee(Employee employee);
    List<Employee> findEmployeeByNameStartWith(String reqexp);
}
