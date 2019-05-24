package cn.stylefeng.guns.modular.gs.controller;

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
import cn.stylefeng.guns.core.util.StringUtil;
import cn.stylefeng.guns.modular.gs.service.IGenerAccService;
import cn.stylefeng.guns.modular.gs.vo.GenerAccVO;
import cn.stylefeng.guns.modular.system.model.GenerAcc;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 生成财务数据控制器
 *
 * @author fengshuonan
 * @Date 2019-04-26 11:13:37
 */
@Controller
@RequestMapping("/generAcc")
public class GenerAccController extends BaseController {

    private String PREFIX = "/gs/generAcc/";

    @Autowired
    private IGenerAccService generAccService;

    /**
     * 跳转到生成财务数据首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "generAcc.html";
    }

    /**
     * 跳转到添加生成财务数据
     */
    @RequestMapping("/generAcc_add")
    public String generAccAdd() {
        return PREFIX + "generAcc_add.html";
    }

    /**
     * 跳转到修改生成财务数据
     */
    @RequestMapping("/generAcc_update/{generAccId}")
    public String generAccUpdate(@PathVariable Integer generAccId, Model model) {
        GenerAcc generAcc = generAccService.selectById(generAccId);
        model.addAttribute("item",generAcc);
        LogObjectHolder.me().set(generAcc);
        return PREFIX + "generAcc_edit.html";
    }

    /**
     * 获取生成财务数据列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	
    	List<GenerAcc> listGenerAcc = generAccService.selectList(null); 
    	List<GenerAccVO> listVO = CommonUtil.listPo2VO(listGenerAcc, GenerAccVO.class);
    	listVO.forEach((vo)->{
    		vo.setCompanyName(ConstantFactory.me().getSingleCompanyName(vo.getComId()));
    		if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
    			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
    		}
    		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
    			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
    		}
    	});
        return listVO;
    }

    /**
     * 新增生成财务数据
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GenerAcc generAcc) {
        generAccService.insert(generAcc);
        return SUCCESS_TIP;
    }

    /**
     * 删除生成财务数据
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer generAccId) {
        generAccService.deleteById(generAccId);
        return SUCCESS_TIP;
    }

    /**
     * 修改生成财务数据
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GenerAcc generAcc) {
        generAccService.updateById(generAcc);
        return SUCCESS_TIP;
    }

    /**
     * 生成财务数据详情
     */
    @RequestMapping(value = "/detail/{generAccId}")
    @ResponseBody
    public Object detail(@PathVariable("generAccId") Integer generAccId) {
        return generAccService.selectById(generAccId);
    }
    
    
    
    /**
     * 修改生成财务数据
     */
    @RequestMapping(value = "/generation/{genDt}")
    @ResponseBody
    public Object generation(@PathVariable("genDt") String genDt) {
        generAccService.generation(genDt);
        return SUCCESS_TIP;
    }
}
