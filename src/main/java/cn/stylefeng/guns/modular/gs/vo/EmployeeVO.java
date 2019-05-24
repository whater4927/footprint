/**
 * Project Name:Guns-Medical-master
 * File Name:EmployeeVO.java
 * Package Name:cn.stylefeng.guns.modular.gs
 * Date:2019年4月24日下午5:59:34
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.vo;

import cn.stylefeng.guns.modular.system.model.Employee;
import lombok.Data;

/**
 * ClassName:EmployeeVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2019年4月24日 下午5:59:34 <br/>
 * 
 * @author whater
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class EmployeeVO extends Employee {
	/**
	 * 公司名称
	 */
	private String companyName;
	private String educationName;
	private String nativePlaceName;
	private String statusName;
}
