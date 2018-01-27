package com.howtodoinjava.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.dao.EmployeeDAO;
import com.howtodoinjava.demo.model.EmployeeVO;

@Service
public class EmployeeManagerImpl implements EmployeeManager {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeManagerImpl.class);

	@Autowired
	EmployeeDAO dao;
	
	public List<EmployeeVO> getAllEmployees() 
	{
		System.out.println("====>请求com.howtodoinjava.demo.service接口，请注意观察控制台输出");
		logger.trace("do trace");
		logger.debug("do debug");
		logger.info("do info");
		logger.warn("do warn");
		logger.error("do error");
		return dao.getAllEmployees();
	}
}
