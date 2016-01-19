package ua.pinta.service;

import ua.pinta.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getList();
    void addEmployee(String name, boolean active, int departmentID);
    void updateEmployee(int id, String name, boolean active, int departmentID);
    void removeEmployee(int id);
    List<Employee> findEmployeeByNameStartWith(String reqexp);
}
