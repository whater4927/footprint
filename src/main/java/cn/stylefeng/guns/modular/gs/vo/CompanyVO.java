/**
 * Project Name:Guns-Medical-master
 * File Name:CompanyVO.java
 * Package Name:cn.stylefeng.guns.modular.gs.warpper
 * Date:2019年4月24日下午6:51:13
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.vo;

import cn.stylefeng.guns.modular.system.model.Company;
import lombok.Data;

/**
 * ClassName:CompanyVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2019年4月24日 下午6:51:13 <br/>
 * 
 * @author whater
 * @version
 * @since JDK 1.8
 * @see
 */
@Data
public class CompanyVO extends Company {
	/**
	 * 单位参保状态，单位是否被注销作用
	 */
	private String joinStatusName;
	private String accTypeName;
	private String accUseTypeName;
	private String statusName;
	
	private String createUserName ;
	private String createOrgName ;
}
