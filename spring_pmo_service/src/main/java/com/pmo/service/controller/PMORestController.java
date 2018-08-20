package com.pmo.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pmo.service.model.EmployeeAllDetails;
import com.pmo.service.model.EmployeeDetails;
import com.pmo.service.model.LoginForm;
import com.pmo.service.service.PMOEmpService;

@RestController("/pmoreg")
public class PMORestController {

	@Autowired
	PMOEmpService empService;

	@RequestMapping(method = RequestMethod.POST)
	private boolean saveEmployees(Model model, @RequestBody EmployeeDetails entity) {
		return empService.saveEmploeeDetails(entity);
	}

	@RequestMapping(method = RequestMethod.GET)
	private boolean saveEmployeesGet() {
		return false;
	}

	@RequestMapping(value = "/showHomePage")
	public String showHomePage(Model model, @ModelAttribute("loginForm") LoginForm form) {
		boolean is_admin = false;
		boolean is_manager = false;
		boolean is_user = false;
		String[] roles = null;
		try {
			roles = empService.loginService(form.getUsername(), form.getPassword());
		} catch (Exception e) {
			model.addAttribute("errormessage", "Invalid Credentilas");
			return "forward:/homePage";
		}
		for (String role : roles) {
			if (role.equals("ROLE_ADMIN"))
				is_admin = true;
			else if (role.equals("ROLE_MANAGER"))
				is_manager = true;
			else if (role.equals("ROLE_USER"))
				is_user = true;
		}
		if (is_admin) {
			EmployeeAllDetails[] details = empService.listAllEmployees(form.getUsername(), form.getPassword());
			model.addAttribute("result", details);
			return "showAdminPage";
		} else if (is_manager) {
			EmployeeDetails emp = empService.empService(form.getUsername(), form.getPassword());
			EmployeeAllDetails[] details = empService.listManagerReporties(form.getUsername(), form.getPassword());
			model.addAttribute("manager", emp);
			model.addAttribute("result", details);
			return "showManagerPage";
		} else if (is_user) {
			try {
				EmployeeDetails details = empService.empService(form.getUsername(), form.getPassword());
				model.addAttribute("result", details);
				return "showEmpDetails";
			} catch (Exception e) {
				model.addAttribute("error", e);
				return "errorPage";
			}
		} else
			return "errorPage";
	}

}
