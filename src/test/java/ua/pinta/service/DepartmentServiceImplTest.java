package ua.pinta.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.pinta.dao.DepartmentDao;
import ua.pinta.dao.DepartmentDaoImpl;
import ua.pinta.model.Department;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration({"classpath*:/application-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceImplTest {
    @Mock
    DepartmentDaoImpl departmentDao;

    @InjectMocks
    DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetList() throws Exception {
        when(departmentService.getList()).thenReturn(new ArrayList<Department>());
        departmentService.getList();
        verify(departmentDao).getList();
    }
}