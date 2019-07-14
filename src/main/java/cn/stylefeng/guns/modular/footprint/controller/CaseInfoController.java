package cn.stylefeng.guns.modular.footprint.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.footprint.service.ICaseInfoService;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;
import cn.stylefeng.guns.modular.footprint.vo.CaseInfoVO;
import cn.stylefeng.guns.modular.footprint.vo.FootprintVO;
import cn.stylefeng.guns.modular.system.model.CaseInfo;
import cn.stylefeng.guns.modular.system.model.Footprint;
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
    @Autowired
    private IFootprintService footprintService;
    /**
     * 跳转到案件基本信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "caseInfo.html";
    }
    /**
     * 跳转到案件基本信息首页
     */
    @RequestMapping("/caseInfoFootprintAdd")
    public String caseInfoFootprintAdd() {
        return PREFIX + "caseInfo_footprint_add.html";
    }
    /**
     * 跳转到添加案件基本信息
     */
    @RequestMapping("/caseInfo_add")
    public String caseInfoAdd() {
        return PREFIX + "caseInfo_add.html";
    }
    /**
     * 跳转到添加案件基本信息
     */
    @RequestMapping("/caseInfo_add_v2")
    public String caseInfoAddV2() {
        return PREFIX + "caseInfo_add_v2.html";
    }
    
    /**
     * 跳转到添加案件基本信息
     */
    @RequestMapping("/imageInfo_v2")
    public String imageInfo_v2() {
        return PREFIX + "imageInfo_v2.html";
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
        
        List<Footprint> list = footprintService.selectList(new EntityWrapper<Footprint>().eq("case_no", caseInfo.getCaseNo()));
        String images = "" ;
        for (Footprint footprint : list) {
        	images += footprint.getOriginalImg()+",";
		}
        
        vo.setSelectImages(images);
        model.addAttribute("item",vo);
        
        List<FootprintVO> listVo = CommonUtil.listPo2VO(list, FootprintVO.class);
    	listVo.forEach((footprintVO)->{
    		footprintVO.setPositionName(ConstantFactory.me().getDictsByName("position", footprintVO.getPosition()));
    		footprintVO.setExtractionMethodName(ConstantFactory.me().getDictsByName("extraction_method", footprintVO.getExtractionMethod()));
    		footprintVO.setLegacyModeName(ConstantFactory.me().getDictsByName("legacy_mode", footprintVO.getLegacyMode()));
        });
        
        model.addAttribute("footprints",listVo);
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
    	List<CaseInfo> list =  caseInfoService.selectList(new EntityWrapper<CaseInfo>().orderBy("crt_tm",false));
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
    
    @RequestMapping(value = "/list_select")
    @ResponseBody
    public Object listSelect(String condition) {
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
     * 跳转到添加案件基本信息
     */
    @RequestMapping("/selectPage")
    public String selectPage() {
        return PREFIX + "caseInfo_select.html";
    }

    /**
     * 新增案件基本信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CaseInfoVO caseInfo) {
    	caseInfo.setCaseNo(noService.busiNo("A"));
    	EntityUtils.setCreateInfo(caseInfo);
        caseInfoService.insert(caseInfo);
        String images  = caseInfo.getSelectImages();
        if(StringUtil.isNotEmpty(images)) {
        	for (String image : images.split(",")) {
    			if(StringUtil.isNotEmpty(image)) {
    				Footprint footprint = new Footprint();
    				footprint.setFpNo(noService.busiNo("F"));
    				footprint.setCaseNo(caseInfo.getCaseNo());
    				footprint.setStatus("caseInfo");
    				footprint.setOriginalImg(image);
    				EntityUtils.setCreateInfo(footprint);
    				footprintService.insert(footprint);
    			}
    		}
        }
        return SUCCESS_TIP;
    }
    /**
     * 新增案件基本信息
     */
    @PostMapping(value = "/add_v2")
    @ResponseBody
    public Object add_v2(@RequestBody CaseInfoVO caseInfo) {
    	caseInfo.setCaseNo(noService.busiNo("A"));
    	EntityUtils.setCreateInfo(caseInfo);
        caseInfoService.insert(caseInfo);
        String images  = caseInfo.getSelectImages();
        if(StringUtil.isNotEmpty(images)) {
        	for (FootprintVO footprintVO : caseInfo.getImageInfos()) {
    			Footprint footprint = new Footprint();
    			footprint.setFpNo(noService.busiNo("F"));
    			footprint.setCaseNo(caseInfo.getCaseNo());
    			footprint.setStatus("caseInfo");
    			
    			footprint.setPosition(footprintVO.getPosition());
    			footprint.setExtractionMethod(footprintVO.getExtractionMethod());
    			footprint.setLegacyMode(footprintVO.getLegacyMode());
    			
    			footprint.setOriginalImg(footprintVO.getId());
    			EntityUtils.setCreateInfo(footprint);
    			footprintService.insert(footprint);
    		}
        }
    	
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
    public Object update(CaseInfoVO caseInfo) {
        caseInfoService.updateById(caseInfo);
        footprintService.delete(new EntityWrapper<Footprint>().eq("case_no", caseInfo.getCaseNo()));
        String images  = caseInfo.getSelectImages();
        if(StringUtil.isNotEmpty(images)) {
        	for (String image : images.split(",")) {
    			if(StringUtil.isNotEmpty(image)) {
    				Footprint footprint = new Footprint();
    				footprint.setFpNo(noService.busiNo("F"));
    				footprint.setCaseNo(caseInfo.getCaseNo());
    				footprint.setOriginalImg(image);
    				footprint.setStatus("caseInfo");
    				EntityUtils.setCreateInfo(footprint);
    				footprintService.insert(footprint);
    			}
    		}
        }
        return SUCCESS_TIP;
    }
    /**
     * 修改案件基本信息
     */
    @RequestMapping(value = "/update_v2")
    @ResponseBody
    public Object update_v2(@RequestBody CaseInfoVO caseInfo) {
        caseInfoService.updateById(caseInfo);
        footprintService.delete(new EntityWrapper<Footprint>().eq("case_no", caseInfo.getCaseNo()));
        String images  = caseInfo.getSelectImages();
        if(StringUtil.isNotEmpty(images)) {
        	for (FootprintVO footprintVO : caseInfo.getImageInfos()) {
    			Footprint footprint = new Footprint();
    			footprint.setFpNo(noService.busiNo("F"));
    			footprint.setCaseNo(caseInfo.getCaseNo());
    			footprint.setStatus("caseInfo");
    			
    			footprint.setPosition(footprintVO.getPosition());
    			footprint.setExtractionMethod(footprintVO.getExtractionMethod());
    			footprint.setLegacyMode(footprintVO.getLegacyMode());
    			
    			footprint.setOriginalImg(footprintVO.getId());
    			EntityUtils.setCreateInfo(footprint);
    			footprintService.insert(footprint);
    		}
        }
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
