/**
 * Project Name:Guns-Medical-master
 * File Name:QueryController.java
 * Package Name:cn.stylefeng.guns.modular.gs.controller
 * Date:2019年4月17日下午3:34:36
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * ClassName:QueryController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2019年4月17日 下午3:34:36 <br/>
 * 
 * @author whater
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
@RequestMapping("/query")
public class QueryController extends BaseController {
	private String PREFIX = "/gs/query/";

	@RequestMapping("/employee_query")
	public String employeeQuery() {
		return PREFIX + "employee_query.html";
	}

	@RequestMapping("/company_query")
	public String companyQuery() {
		return PREFIX + "company_query.html";
	}

	@RequestMapping("/acc_query")
	public String accQuery() {
		return PREFIX + "acc_query.html";
	}

	@RequestMapping("/acc_emp_query")
	public String accEmpQuery() {
		return PREFIX + "acc_emp_query.html";
	}

	@RequestMapping(value = "/company/list")
	@ResponseBody
	public Object companyList(String condition) {
		return null;
	}

	@RequestMapping(value = "/employee/list")
	@ResponseBody
	public Object employeeList(String condition) {
		return null;
	}

	@RequestMapping(value = "/acc/list")
	@ResponseBody
	public Object accList(String condition) {
		return null;
	}

	@RequestMapping(value = "/acc_emp/list")
	@ResponseBody
	public Object accEmpList(String condition) {
		return "/footprint/test/test.html";
	}

}
