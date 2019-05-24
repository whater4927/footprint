/**
 * Project Name:Guns-Medical-master
 * File Name:InvitationDetailVO.java
 * Package Name:cn.stylefeng.guns.modular.gs.vo
 * Date:2019年5月15日下午1:55:14
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.vo;

import cn.stylefeng.guns.modular.system.model.InvitationDetail;
import lombok.Data;

/**
 * ClassName:InvitationDetailVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年5月15日 下午1:55:14 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class InvitationDetailVO extends InvitationDetail {
	private String createUserName ;
	private String createOrgName ;
}

