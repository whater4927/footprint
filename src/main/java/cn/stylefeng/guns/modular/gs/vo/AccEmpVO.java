/**
 * Project Name:Guns-Medical-master
 * File Name:AccEmpVO.java
 * Package Name:cn.stylefeng.guns.modular.gs.vo
 * Date:2019年4月29日下午4:10:33
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.vo;

import cn.stylefeng.guns.modular.system.model.AccEmp;
import lombok.Data;

/**
 * ClassName:AccEmpVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月29日 下午4:10:33 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class AccEmpVO extends AccEmp {
	private String payStatus ;
	private String empName ;
	private String companyName ;
	private String dealUserName ;
	private String dealOrgName ;
	private String createUserName ;
	private String createOrgName ;
}

