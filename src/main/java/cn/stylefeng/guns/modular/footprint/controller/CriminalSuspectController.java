package cn.stylefeng.guns.modular.footprint.controller;

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
import cn.stylefeng.guns.modular.footprint.service.ICriminalSuspectService;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;
import cn.stylefeng.guns.modular.footprint.vo.CriminalSuspectVO;
import cn.stylefeng.guns.modular.system.model.CriminalSuspect;
import cn.stylefeng.guns.modular.system.model.Footprint;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 嫌疑人信息控制器
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:20:41
 */
@Controller
@RequestMapping("/criminalSuspect")
public class CriminalSuspectController extends BaseController {

    private String PREFIX = "/footprint/criminalSuspect/";

    @Autowired
    private ICriminalSuspectService criminalSuspectService;
    @Autowired
    private INoService noService ;
    @Autowired
    private IFootprintService footprintService;
    /**
     * 跳转到嫌疑人信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "criminalSuspect.html";
    }

    /**
     * 跳转到添加嫌疑人信息
     */
    @RequestMapping("/criminalSuspect_add")
    public String criminalSuspectAdd() {
        return PREFIX + "criminalSuspect_add.html";
    }

    /**
     * 跳转到修改嫌疑人信息
     */
    @RequestMapping("/criminalSuspect_update/{criminalSuspectId}")
    public String criminalSuspectUpdate(@PathVariable String criminalSuspectId, Model model) {
        CriminalSuspect criminalSuspect = criminalSuspectService.selectById(criminalSuspectId);
        CriminalSuspectVO vo = CommonUtil.po2VO(criminalSuspect, CriminalSuspectVO.class);
        vo.setSexName(ConstantFactory.me().getDictsByName("sys_sex", vo.getSex()));
     	if(StringUtil.isNotEmpty(vo.getGraspUnit())) 
     		vo.setGraspUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getGraspUnit())));
     	vo.setCsTypeName(ConstantFactory.me().getDictsByName("case_type", vo.getCsType()));
     	vo.setInputUserName(ConstantFactory.me().getUserNameById(vo.getInputUser()));
     	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
 			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
 		}
 		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
 			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
 		}
 		
 		 List<Footprint> list = footprintService.selectList(new EntityWrapper<Footprint>().eq("cs_no", criminalSuspectId));
         String images = "" ;
         for (Footprint footprint : list) {
         	images += footprint.getOriginalImg()+",";
 		}
        vo.setSelectImages(images);
        model.addAttribute("footprints",list);
        model.addAttribute("item",vo);
        LogObjectHolder.me().set(criminalSuspect);
        return PREFIX + "criminalSuspect_edit.html";
    }

    /**
     * 获取嫌疑人信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<CriminalSuspectVO> listVo = CommonUtil.listPo2VO(criminalSuspectService.selectList(null), CriminalSuspectVO.class);
    	
    	 listVo.forEach((vo)->{
         	vo.setSexName(ConstantFactory.me().getDictsByName("sys_sex", vo.getSex()));
         	if(StringUtil.isNotEmpty(vo.getGraspUnit())) 
         		vo.setGraspUnitName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getGraspUnit())));
         	vo.setCsTypeName(ConstantFactory.me().getDictsByName("case_type", vo.getCsType()));
         	vo.setInputUserName(ConstantFactory.me().getUserNameById(vo.getInputUser()));
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
     * 新增嫌疑人信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CriminalSuspectVO criminalSuspect) {
    	criminalSuspect.setCsNo(noService.busiNo("P"));
    	criminalSuspect.setInputUser(ShiroKit.getUser().getId());
    	EntityUtils.setCreateInfo(criminalSuspect);
        criminalSuspectService.insert(criminalSuspect);
        String images  = criminalSuspect.getSelectImages();
        if(StringUtil.isNotEmpty(images)) {
        	for (String image : images.split(",")) {
    			if(StringUtil.isNotEmpty(image)) {
    				Footprint footprint = new Footprint();
    				footprint.setFpNo(noService.busiNo("F"));
    				footprint.setCsNo(criminalSuspect.getCsNo());
    				footprint.setStatus("cs");
    				footprint.setOriginalImg(image);
    				EntityUtils.setCreateInfo(footprint);
    				footprintService.insert(footprint);
    			}
    		}
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除嫌疑人信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String criminalSuspectId) {
        criminalSuspectService.deleteById(criminalSuspectId);
        return SUCCESS_TIP;
    }

    /**
     * 修改嫌疑人信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CriminalSuspectVO criminalSuspect) {
        criminalSuspectService.updateById(criminalSuspect);
        footprintService.delete(new EntityWrapper<Footprint>().eq("cs_no", criminalSuspect.getCsNo()));
        String images  = criminalSuspect.getSelectImages();
        if(StringUtil.isNotEmpty(images)) {
        	for (String image : images.split(",")) {
    			if(StringUtil.isNotEmpty(image)) {
    				Footprint footprint = new Footprint();
    				footprint.setFpNo(noService.busiNo("F"));
    				footprint.setCsNo(criminalSuspect.getCsNo());
    				footprint.setOriginalImg(image);
    				footprint.setStatus("cs");
    				EntityUtils.setCreateInfo(footprint);
    				footprintService.insert(footprint);
    			}
    		}
        }
        return SUCCESS_TIP;
    }

    /**
     * 嫌疑人信息详情
     */
    @RequestMapping(value = "/detail/{criminalSuspectId}")
    @ResponseBody
    public Object detail(@PathVariable("criminalSuspectId") String criminalSuspectId) {
        return criminalSuspectService.selectById(criminalSuspectId);
    }
}
