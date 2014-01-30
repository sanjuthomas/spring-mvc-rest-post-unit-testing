package com.ourownjava.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ourownjava.spring.mvc.model.Employee;

/**
 * @author ourownjava.com
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController 
{
	/**
	 * This method shall accept an Employee request and save it in the mongodb.
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(value= "/save/", method = RequestMethod.POST)
	@ResponseBody
	public Employee save(@RequestBody final Employee employee){
		//call the service save method		
		employee.setId("e1");
		return employee;
	}
}
