package com.howtodoinjava.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtodoinjava.demo.service.EmployeeManager;

@Controller
@RequestMapping("/employee-module")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeManager manager;

	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	public String getAllEmployees(Model model) {

        System.out.println("================>\n请求com.howtodoinjava.demo.controller接口，请注意观察控制台输出");
        logger.trace("do trace /getAllEmployees");
        logger.debug("do debug /getAllEmployees");
        logger.info("do info /getAllEmployees");
        logger.warn("do warn /getAllEmployees");
        logger.error("do error /getAllEmployees");

		model.addAttribute("employees", manager.getAllEmployees());
		return "employeesListDisplay";
	}
}