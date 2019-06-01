package cn.stylefeng.guns.modular.footprint.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.footprint.service.ICaseInfoService;
import cn.stylefeng.guns.modular.footprint.vo.CaseInfoVO;
import cn.stylefeng.guns.modular.system.model.CaseInfo;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 案件基本信息控制器
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:19:33
 */
@Controller
@RequestMapping("/caseInfo")
public class CaseInfoController extends BaseController {

    private String PREFIX = "/footprint/caseInfo/";

    @Autowired
    private ICaseInfoService caseInfoService;
    @Autowired
    private INoService noService ;
    
    /**
     * 跳转到案件基本信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "caseInfo.html";
    }

    /**
     * 跳转到添加案件基本信息
     */
    @RequestMapping("/caseInfo_add")
    public String caseInfoAdd() {
        return PREFIX + "caseInfo_add.html";
    }

    /**
     * 跳转到修改案件基本信息
     */
    @RequestMapping("/caseInfo_update/{caseInfoId}")
    public String caseInfoUpdate(@PathVariable String caseInfoId, Model model) {
        CaseInfo caseInfo = caseInfoService.selectById(caseInfoId);
        CaseInfoVO vo = CommonUtil.po2VO(caseInfo, CaseInfoVO.class);
        if(StringUtil.isNotEmpty(vo.getUnit())) 
    		vo.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getUnit())));
        model.addAttribute("item",vo);
        model.addAttribute("caseTm",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getCaseTm()));
        LogObjectHolder.me().set(vo);
        return PREFIX + "caseInfo_edit.html";
    }

    /**
     * 获取案件基本信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<CaseInfo> list =  caseInfoService.selectList(null);
        List<CaseInfoVO> listVo = CommonUtil.listPo2VO(list, CaseInfoVO.class);
        listVo.forEach((vo)->{
        	vo.setCaseStateName(ConstantFactory.me().getDictsByName("case_status", vo.getCaseState()));
        	if(StringUtil.isNotEmpty(vo.getUnit())) 
        		vo.setUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getUnit())));
        	vo.setCaseTypeName(ConstantFactory.me().getDictsByName("case_type", vo.getCaseType()));
        	vo.setIntrusionModeName(ConstantFactory.me().getDictsByName("intrusion_mode", vo.getIntrusionMode()));
        	
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
     * 新增案件基本信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CaseInfo caseInfo) {
    	caseInfo.setCaseNo(noService.busiNo("A"));
    	EntityUtils.setCreateInfo(caseInfo);
        caseInfoService.insert(caseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除案件基本信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String caseInfoId) {
        caseInfoService.deleteById(caseInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改案件基本信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CaseInfo caseInfo) {
        caseInfoService.updateById(caseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 案件基本信息详情
     */
    @RequestMapping(value = "/detail/{caseInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("caseInfoId") String caseInfoId) {
        return caseInfoService.selectById(caseInfoId);
    }
}
