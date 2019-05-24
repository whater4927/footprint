/**
 * Project Name:Guns-Medical-master
 * File Name:PayController.java
 * Package Name:cn.stylefeng.guns.modular.gs.controller
 * Date:2019年5月5日上午9:50:57
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:PayController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年5月5日 上午9:50:57 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping("/pay")
public class PayController {
	private String PREFIX = "/gs/pay/";

	@RequestMapping("/employee_pay")
	public String employeeQuery() {
		return PREFIX + "employee_pay.html";
	}

	@RequestMapping("/company_pay")
	public String companyQuery() {
		return PREFIX + "company_pay.html";
	}
}

