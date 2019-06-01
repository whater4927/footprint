/**
 * Project Name:footprint
 * File Name:CaseInfoVO.java
 * Package Name:cn.stylefeng.guns.modular.footprint.vo
 * Date:2019年6月1日下午2:54:08
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.vo;

import cn.stylefeng.guns.modular.system.model.CaseInfo;
import lombok.Data;

/**
 * ClassName:CaseInfoVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年6月1日 下午2:54:08 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class CaseInfoVO extends CaseInfo {
	
	private String caseStateName;
	private String unitName;
	private String caseTypeName;
	private String intrusionModeName;
	private String createUserName ;
	private String createOrgName ;
}

