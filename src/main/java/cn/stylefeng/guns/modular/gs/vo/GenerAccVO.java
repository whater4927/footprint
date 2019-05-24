/**
 * Project Name:Guns-Medical-master
 * File Name:GenerAccVO.java
 * Package Name:cn.stylefeng.guns.modular.gs.vo
 * Date:2019年4月26日下午3:36:33
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.vo;

import cn.stylefeng.guns.modular.system.model.GenerAcc;
import lombok.Data;

/**
 * ClassName:GenerAccVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月26日 下午3:36:33 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class GenerAccVO extends GenerAcc {
	private String companyName ;
	private String createUserName ;
	private String createOrgName ;
}

