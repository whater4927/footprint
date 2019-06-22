package cn.stylefeng.guns.modular.footprint.vo;

import cn.stylefeng.guns.modular.system.model.Footprint;
import lombok.Data;
@Data
public class FootprintVO extends Footprint {
	
	private String id ;
	private String legacyModeName;
	private String extractionMethodName;
	
	private String createUserName ;
	private String createOrgName ;
}
