package ua.pinta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.pinta.service.DepartmentService;
import ua.pinta.service.EmployeeService;

@Controller
public class MainController {
    public static final String START_PAGE = "/index";
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = {"/", START_PAGE}, method = RequestMethod.GET)
    public ModelAndView start(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments", departmentService.getList());
        modelAndView.addObject("employees", employeeService.getList());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/employee/add", method= RequestMethod.POST)
    public ModelAndView addEmployer(@RequestParam("department") String departmentId, @RequestParam("name") String name, @RequestParam(value = "register_operation") String operation){
        employeeService.addEmployee(name, true, Integer.parseInt(departmentId));
        ModelAndView modelAndView = new ModelAndView(new RedirectView(START_PAGE));
        return modelAndView;
    }

    @RequestMapping(value = "/employee/remove", method= RequestMethod.POST)
    public ModelAndView removeEmployer(@RequestParam("employeeId") String id, @RequestParam(value = "remove_operation") String operation){
        employeeService.removeEmployee(Integer.parseInt(id));
        ModelAndView modelAndView = new ModelAndView(new RedirectView(START_PAGE));
        return modelAndView;
    }

    @RequestMapping(value = "/employee/search", method= RequestMethod.POST)
    public ModelAndView findEmployer(@RequestParam("employeeName") String name, @RequestParam(value = "search_operation") String operation){
        ModelAndView modelAndView = new ModelAndView(new RedirectView(START_PAGE));
        modelAndView.addObject("employees", employeeService.findEmployeeByNameStartWith(name));
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
