package cn.stylefeng.guns.modular.footprint.vo;

import cn.stylefeng.guns.modular.system.model.CaseInfoPrintfoot;
import lombok.Data;
@Data
public class CaseInfoPrintfootVO extends CaseInfoPrintfoot{
	
	private String caseStateName;
	private String unitName;
	private String caseTypeName;
	private String intrusionModeName;
	private String createUserName ;
	private String createOrgName ;
	private String selectImages ;
	private String id ;
	private String legacyModeName;
	private String extractionMethodName;
	private String positionName;
	
}
