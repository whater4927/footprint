package cn.stylefeng.guns.modular.gs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.gs.service.IInvitationDetailService;
import cn.stylefeng.guns.modular.gs.service.IInvitationService;
import cn.stylefeng.guns.modular.gs.vo.InvitationDetailVO;
import cn.stylefeng.guns.modular.gs.vo.InvitationVO;
import cn.stylefeng.guns.modular.system.model.Invitation;
import cn.stylefeng.guns.modular.system.model.InvitationDetail;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 社区问答控制器
 *
 * @author fengshuonan
 * @Date 2019-05-14 20:18:15
 */
@Controller
@RequestMapping("/invitation")
public class InvitationController extends BaseController {

    private String PREFIX = "/gs/invitation/";

    @Autowired
    private IInvitationService invitationService;
    @Autowired
    private IInvitationDetailService invitationDetailService;
    /**
     * 跳转到社区问答首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "invitation.html";
    }

    /**
     * 跳转到添加社区问答
     */
    @RequestMapping("/invitation_add")
    public String invitationAdd() {
        return PREFIX + "invitation_add.html";
    }

    /**
     * 跳转到修改社区问答
     */
    @RequestMapping("/invitation_update/{invitationId}")
    public String invitationUpdate(@PathVariable String invitationId, Model model) {
//        Invitation invitation = invitationService.selectById(invitationId);
        InvitationVO invitation = (InvitationVO) detail(invitationId);
        model.addAttribute("item",invitation);
        LogObjectHolder.me().set(invitation);
        return PREFIX + "invitation_edit.html";
    }

    /**
     * 获取社区问答列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Invitation> list =  invitationService.selectList(null);
        List<InvitationVO> listVo = CommonUtil.listPo2VO(list, InvitationVO.class);
        listVo.forEach((vo)->{
        	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
    			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
    		}
    		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
    			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
    		}
        });
        return listVo;
    }

    /**
     * 新增社区问答
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Invitation invitation) {
    	invitation.setId(CommonUtil.UUID());
    	invitation.setUserId(ShiroKit.getUser().getId()+"");
    	EntityUtils.setCreateInfo(invitation);
        invitationService.insert(invitation);
        return SUCCESS_TIP;
    }
    /**
     * 新增社区问答
     */
    @RequestMapping(value = "/addDetail")
    @ResponseBody
    public Object addDetail(InvitationDetail invitationDetail) {
    	invitationDetail.setId(CommonUtil.UUID());
    	invitationDetail.setUserId(ShiroKit.getUser().getId()+"");
    	EntityUtils.setCreateInfo(invitationDetail);
    	invitationDetailService.insert(invitationDetail);
        return SUCCESS_TIP;
    }
    /**
     * 删除社区问答
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String invitationId) {
        invitationService.deleteById(invitationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改社区问答
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Invitation invitation) {
        invitationService.updateById(invitation);
        return SUCCESS_TIP;
    }

    /**
     * 社区问答详情
     */
    @RequestMapping(value = "/detail/{invitationId}")
    @ResponseBody
    public Object detail(@PathVariable("invitationId") String invitationId) {
    	Invitation invitation = invitationService.selectById(invitationId) ;
    	InvitationVO vo = CommonUtil.po2VO(invitation, InvitationVO.class);
    	List<InvitationDetail> list = invitationDetailService.selectList(new EntityWrapper<InvitationDetail>().eq("pid", invitationId));
    	List<InvitationDetailVO> listVo = CommonUtil.listPo2VO(list, InvitationDetailVO.class);
    	listVo.forEach((v)->{
    		if(StringUtil.isNotEmpty(v.getCrtUserId())) {
    			v.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(v.getCrtUserId())));
    		}
    	});
    	vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
    	vo.setList(listVo);
        return vo;
    }
}
