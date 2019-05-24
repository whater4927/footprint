package cn.stylefeng.guns.modular.gs.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.StringUtil;

import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.AccEmp;
import cn.stylefeng.guns.modular.gs.service.IAccEmpService;
import cn.stylefeng.guns.modular.gs.vo.AccEmpVO;
import cn.stylefeng.guns.modular.gs.vo.CompanyVO;

/**
 * 收缴记录明细控制器
 *
 * @author fengshuonan
 * @Date 2019-04-26 10:57:13
 */
@Controller
@RequestMapping("/accEmp")
public class AccEmpController extends BaseController {

    private String PREFIX = "/gs/accEmp/";

    @Autowired
    private IAccEmpService accEmpService;

    /**
     * 跳转到收缴记录明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "accEmp.html";
    }

    /**
     * 跳转到添加收缴记录明细
     */
    @RequestMapping("/accEmp_add")
    public String accEmpAdd() {
        return PREFIX + "accEmp_add.html";
    }

    /**
     * 跳转到修改收缴记录明细
     */
    @RequestMapping("/accEmp_update/{accEmpId}")
    public String accEmpUpdate(@PathVariable String accEmpId, Model model) {
        AccEmp accEmp = accEmpService.selectById(accEmpId);
        model.addAttribute("item",accEmp);
        LogObjectHolder.me().set(accEmp);
        return PREFIX + "accEmp_edit.html";
    }

    /**
     * 获取收缴记录明细列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	 List<AccEmpVO> listVo = CommonUtil.listPo2VO(accEmpService.selectList(null), AccEmpVO.class);
    	 listVo.forEach((vo)->{
    		 vo.setPayStatus(ConstantFactory.me().getDictsByName("pay_status", vo.getPayStatus()));
    		 vo.setEmpName(ConstantFactory.me().getSingleEmpName(vo.getEmpId()));
    		 vo.setCompanyName(ConstantFactory.me().getSingleCompanyName(vo.getComId()));
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
     * 新增收缴记录明细
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AccEmp accEmp) {
        accEmpService.insert(accEmp);
        return SUCCESS_TIP;
    }

    /**
     * 删除收缴记录明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String accEmpId) {
        accEmpService.deleteById(accEmpId);
        return SUCCESS_TIP;
    }

    /**
     * 修改收缴记录明细
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AccEmp accEmp) {
        accEmpService.updateById(accEmp);
        return SUCCESS_TIP;
    }

    /**
     * 收缴记录明细详情
     */
    @RequestMapping(value = "/detail/{accEmpId}")
    @ResponseBody
    public Object detail(@PathVariable("accEmpId") String accEmpId) {
        return accEmpService.selectById(accEmpId);
    }
}
