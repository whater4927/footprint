/**
 * Project Name:footprint
 * File Name:CaseRelationVO.java
 * Package Name:cn.stylefeng.guns.modular.footprint.vo
 * Date:2019年6月4日下午4:03:42
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.vo;

import java.util.List;

import cn.stylefeng.guns.modular.system.model.CaseRelation;
import lombok.Data;

/**
 * ClassName:CaseRelationVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年6月4日 下午4:03:42 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class CaseRelationVO extends CaseRelation {
	private List<CaseInfoVO> caseInfos ;
	private String createUserName ;
	private String createOrgName ;
}

