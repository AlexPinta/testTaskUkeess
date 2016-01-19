package ua.pinta.dao;

import ua.pinta.model.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> getList();
    Department findDepartmentByID(int departmentID);
    void addDepartment(Department department);
}
