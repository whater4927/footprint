/**
 * Project Name:footprint
 * File Name:ShoesDemoVO.java
 * Package Name:cn.stylefeng.guns.modular.footprint.vo
 * Date:2019年6月3日下午5:16:34
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.footprint.vo;

import cn.stylefeng.guns.modular.system.model.ShoesDemo;
import lombok.Data;

/**
 * ClassName:ShoesDemoVO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年6月3日 下午5:16:34 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class ShoesDemoVO extends ShoesDemo {
	private String createUserName ;
	private String createOrgName ;
}

