package cn.stylefeng.guns.modular.gs.vo;

import java.util.List;

import cn.stylefeng.guns.modular.system.model.Invitation;
import lombok.Data;
@Data
public class InvitationVO extends Invitation{
	private String createUserName ;
	private String createOrgName ;
	private int size ;
	private List<InvitationDetailVO> list;
}
