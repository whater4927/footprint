/**
 * Project Name:footprint
 * File Name:CaseInfoFootprintMapper.java
 * Package Name:cn.stylefeng.guns.modular.system.dao
 * Date:2019年6月22日下午4:11:36
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.system.dao;

import java.util.List;

import cn.stylefeng.guns.modular.system.model.CaseInfoPrintfoot;

/**
 * ClassName:CaseInfoFootprintMapper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年6月22日 下午4:11:36 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public interface CaseInfoFootprintMapper {
	List<CaseInfoPrintfoot> selectAll(CaseInfoPrintfoot CaseInfoPrintfoot);
}

