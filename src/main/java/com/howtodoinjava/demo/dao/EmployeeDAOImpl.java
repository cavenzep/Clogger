package com.howtodoinjava.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.EmployeeVO;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	public List<EmployeeVO> getAllEmployees() {
		List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
		
		EmployeeVO vo1 = new EmployeeVO();
		vo1.setId(1);
		vo1.setFirstName("Lokesh");
		vo1.setLastName("Gupta");
		employees.add(vo1);
		
		EmployeeVO vo2 = new EmployeeVO();
		vo2.setId(2);
		vo2.setFirstName("Raj");
		vo2.setLastName("Kishore");
		employees.add(vo2);

        System.out.println("====>请求com.howtodoinjava.demo.dao接口，请注意观察控制台输出");
        logger.trace("do trace,{}", JSONObject.toJSONString(employees));
        logger.debug("do debug,{}", JSONObject.toJSONString(employees));
        logger.info("do info,{}", JSONObject.toJSONString(employees));
        logger.warn("do warn,{}", JSONObject.toJSONString(employees));
        logger.error("do error,{}", JSONObject.toJSONString(employees));
		System.out.println("<================\n");
		return employees;
	}
}