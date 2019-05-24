/**
 * Project Name:Guns-Medical-master
 * File Name:EmployeeWarpper.java
 * Package Name:cn.stylefeng.guns.modular.gs.warpper
 * Date:2019年4月24日下午5:26:49
 * Copyright (c) 2019, chenzhou1025@126.com All Rights Reserved.
 *
*/

package cn.stylefeng.guns.modular.gs.warpper;

import java.util.List;
import java.util.Map;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;

/**
 * ClassName:EmployeeWarpper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月24日 下午5:26:49 <br/>
 * @author   whater
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class EmployeeWarpper extends BaseControllerWrapper {

	public EmployeeWarpper(List<Map<String, Object>> multi) {
		super(multi);
	}
	@Override
    protected void wrapTheMap(Map<String, Object> map) {
        String comId = (String) map.get("comId");
        if (ToolUtil.isEmpty(comId) || comId.equals(0)) {
            map.put("companyName", "--");
        } else {
            map.put("companyName", ConstantFactory.me().getSingleCompanyName(comId));
        }
    }

}

