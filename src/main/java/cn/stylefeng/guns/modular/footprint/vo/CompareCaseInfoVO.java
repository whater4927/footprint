/**
 * Project Name:footprint
 * File Name:CompareCaseInfoVO.java
 * Package Name:cn.stylefeng.guns.modular.footprint.vo
 * Date:2019年6月24日下午5:24:16
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.vo;

import java.util.List;

import lombok.Data;

/**
 * ClassName:CompareCaseInfoVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年6月24日 下午5:24:16 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class CompareCaseInfoVO {
	
	private List<CaseInfoVO> caseInfos ;
	
	private List<FootprintVO> footprints ;
	
}

