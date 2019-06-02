package cn.stylefeng.guns.modular.footprint.vo;

import cn.stylefeng.guns.modular.system.model.CriminalSuspect;
import lombok.Data;
@Data
public class CriminalSuspectVO extends CriminalSuspect {
	private String sexName;
	private String csTypeName;
	private String graspUnitName;
	private String inputUserName;
	private String createUserName ;
	private String createOrgName ;
}
