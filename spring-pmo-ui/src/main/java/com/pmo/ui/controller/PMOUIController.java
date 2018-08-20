package com.pmo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.pmo.ui.model.Employee;
import com.pmo.ui.model.EmployeeDetails;

@Controller
public class PMOUIController {
	@Autowired
	OAuth2RestTemplate oAuth2RestTemplate;

	@RequestMapping("/")
	public String homePage()
	{
		return "homePage";
	}
	@RequestMapping(value = "/signUpEmp")
	public ModelAndView signUpEmp() {
		EmployeeDetails details = new EmployeeDetails();
		return new ModelAndView("signUp", "signUpForm", details);

	}

	@RequestMapping(value = "/saveEmpDetails")
	public String saveEmpDetails(Model model, @ModelAttribute("signUpForm") EmployeeDetails details) {
		RestTemplate restTemplate = new RestTemplate();
		Boolean result = restTemplate.postForEntity("http://localhost:8588/pmoreg", details,Boolean.class).getBody();
		model.addAttribute("result", result);
		System.out.println("----------------- inside saveEmpDetails --------------------- " + result);
		model.addAttribute("value", details);
		return "showRegDetails";
	}

	@RequestMapping("/pmoemployees")
	private Employee pmoEmps() {

		System.out.println("----------------- inside pmoEmps --------------------- ");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + oAuth2RestTemplate.getAccessToken().getValue());
		System.out.println(oAuth2RestTemplate.getAccessToken());
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		Employee emp = restTemplate.exchange("http://localhost:8588/employees", HttpMethod.GET, entity, Employee.class).getBody();
		System.out.println(emp);
		return emp;
	}

}
