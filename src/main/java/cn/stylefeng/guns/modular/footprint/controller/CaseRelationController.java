package cn.stylefeng.guns.modular.footprint.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.footprint.service.ICaseInfoService;
import cn.stylefeng.guns.modular.footprint.service.ICaseRelationDetailService;
import cn.stylefeng.guns.modular.footprint.service.ICaseRelationService;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;
import cn.stylefeng.guns.modular.footprint.vo.CaseInfoVO;
import cn.stylefeng.guns.modular.footprint.vo.CaseRelationVO;
import cn.stylefeng.guns.modular.system.model.CaseInfo;
import cn.stylefeng.guns.modular.system.model.CaseRelation;
import cn.stylefeng.guns.modular.system.model.CaseRelationDetail;
import cn.stylefeng.guns.modular.system.model.Footprint;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 串并案件控制器
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:29:55
 */
@Controller
@RequestMapping("/caseRelation")
public class CaseRelationController extends BaseController {

    private String PREFIX = "/footprint/caseRelation/";

    @Autowired
    private ICaseRelationService caseRelationService;
    @Autowired
    private ICaseInfoService caseInfoService;
    @Autowired
    private INoService noService ;
    @Autowired
    private IFootprintService footprintService;
    @Autowired
    private ICaseRelationDetailService caseRelationDetailService ;
    
    /**
     * 跳转到串并案件首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "caseRelation.html";
    }
    
    @RequestMapping("/mgr")
    public String indexMgr() {
        return PREFIX + "caseRelationMgr.html";
    }
    
    /**
     * 足迹照片比对
     * @return
     */
    @RequestMapping("/imageCompareImages/{images}")
    public String imageCompareImages(@PathVariable String images, Model model) {
    	model.addAttribute("images",images);
    	return PREFIX + "imageCompareImages.html";
    }
    
    /**
     * 足迹比对
     * @return
     */
    @RequestMapping("/imageCompare/{caseRelationId}")
    public String ImageCompare(@PathVariable String caseRelationId, Model model) {
    	List<CaseRelationDetail> list = caseRelationDetailService.selectList(new EntityWrapper<CaseRelationDetail>().eq("relation_no", caseRelationId));
    	List<CaseInfo> listCaseInfo = new ArrayList<>();
    	list.forEach((e)->listCaseInfo.add(caseInfoService.selectById(e.getCaseNo())));
    	Set<String> images = new HashSet<>();
    	Set<Footprint> footprints = new HashSet<>();
    	for (CaseInfo caseInfo : listCaseInfo) {
    		List<Footprint> listFootprint = footprintService.selectList(new EntityWrapper<Footprint>().eq("case_no", caseInfo.getCaseNo()));
            for (Footprint footprint : listFootprint) {
            	images.add(footprint.getOriginalImg());
            	footprints.add(footprint);
     		}
		}
    	String imgs = "" ;
    	for (String image : images) {
    		imgs += image + ",";
		}
    	model.addAttribute("footprints",footprints);
    	model.addAttribute("images",imgs);
    	LogObjectHolder.me().set(caseRelationId);
        return PREFIX + "imageCompare.html";
    }
    /**
     * 跳转到添加串并案件
     */
    @RequestMapping("/caseRelation_add")
    public String caseRelationAdd() {
        return PREFIX + "caseRelation_add.html";
    }

    /**
     * 跳转到修改串并案件
     */
    @RequestMapping("/caseRelation_update/{caseRelationId}")
    public String caseRelationUpdate(@PathVariable String caseRelationId, Model model) {
        CaseRelation caseRelation = caseRelationService.selectById(caseRelationId);
        model.addAttribute("item",caseRelation);
        LogObjectHolder.me().set(caseRelation);
        return PREFIX + "caseRelation_edit.html";
    }
    /**
     * 跳转到修改串并案件
     */
    @RequestMapping("/caseRelationMgr_update/{caseRelationId}")
    public String caseRelationMgrUpdate(@PathVariable String caseRelationId, Model model) {
        CaseRelation caseRelation = caseRelationService.selectById(caseRelationId);
        Wrapper<CaseRelationDetail> entityWrapper = new EntityWrapper<CaseRelationDetail>() ;
        entityWrapper.eq("relation_no", caseRelation.getRelationNo());
        List<CaseRelationDetail> listCaseRelationDetail = caseRelationDetailService.selectList(entityWrapper);
        List<String> ids = new ArrayList<>();
        listCaseRelationDetail.forEach((e)->ids.add(e.getCaseNo()));
        List<CaseInfoVO> listVo =  new ArrayList<>();
        if(CommonUtil.isNotEmpty(ids)) {
        	List<CaseInfo> list = caseInfoService.selectList(new EntityWrapper<CaseInfo>().in("case_no", ids));
            listVo = CommonUtil.listPo2VO(list, CaseInfoVO.class);
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
        }
        model.addAttribute("caseInfos",listVo);
        model.addAttribute("item",caseRelation);
        LogObjectHolder.me().set(caseRelation);
        return PREFIX + "caseRelationMgr_edit.html";
    }
    /**
     * 获取串并案件列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<CaseRelationVO> listVo = CommonUtil.listPo2VO(caseRelationService.selectList(new EntityWrapper<CaseRelation>().orderBy("crt_tm desc")), CaseRelationVO.class);
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
     * 新增串并案件
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CaseRelation caseRelation) {
    	caseRelation.setRelationNo(noService.busiNo("R"));
    	EntityUtils.setCreateInfo(caseRelation);
        caseRelationService.insert(caseRelation);
        return SUCCESS_TIP;
    }

    
    /**
     * 新增串并案件
     */
    @RequestMapping(value = "/addRel")
    @ResponseBody
    public Object addRel(@RequestBody CaseRelationVO caseRelation) {
    	caseRelationDetailService.delete(new EntityWrapper<CaseRelationDetail>().eq("relation_no", caseRelation.getRelationNo()));
    	if(CommonUtil.isNotEmpty(caseRelation.getCaseInfos())) {
    		caseRelation.getCaseInfos().forEach((caseInfos)->{
    			CaseRelationDetail caseRelationDetail = new CaseRelationDetail();
    			caseRelationDetail.setCaseNo(caseInfos.getCaseNo());
    			caseRelationDetail.setId(CommonUtil.UUID());
    			caseRelationDetail.setRelationNo(caseRelation.getRelationNo());
    	    	caseRelationDetailService.insert(caseRelationDetail);
    		});
    	}
        return SUCCESS_TIP;
    }
    
    /**
     * 删除串并案件
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String caseRelationId) {
        caseRelationService.deleteById(caseRelationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改串并案件
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CaseRelation caseRelation) {
    	EntityUtils.setUpdateInfo(caseRelation);
        caseRelationService.updateById(caseRelation);
        return SUCCESS_TIP;
    }

    /**
     * 串并案件详情
     */
    @RequestMapping(value = "/detail/{caseRelationId}")
    @ResponseBody
    public Object detail(@PathVariable("caseRelationId") String caseRelationId) {
        return caseRelationService.selectById(caseRelationId);
    }
}
