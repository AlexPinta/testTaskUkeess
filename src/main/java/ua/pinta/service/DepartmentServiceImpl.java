package ua.pinta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.pinta.dao.DepartmentDao;
import ua.pinta.model.Department;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public List<Department> getList() {
        return departmentDao.getList();
    }
}
