/**
 * Project Name:Guns-Medical-master
 * File Name:QueryController.java
 * Package Name:cn.stylefeng.guns.modular.gs.controller
 * Date:2019年4月17日下午3:34:36
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.controller;

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
@RequestMapping("/test")
public class TestController extends BaseController {
	private String PREFIX = "/footprint/test/";

	@RequestMapping("/test1")
	public String test1() {
		return PREFIX + "test1.html";
	}

	@RequestMapping("/test2")
	public String test2() {
		return PREFIX + "test2.html";
	}
	@RequestMapping("/test3")
	public String test3() {
		return PREFIX + "test3.html";
	}
	
	@RequestMapping("/test4")
	public String test4() {
		return PREFIX + "test4.html";
	}

}
