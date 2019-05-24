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
import cn.stylefeng.guns.modular.gs.service.ICompanyService;
import cn.stylefeng.guns.modular.gs.vo.CompanyVO;
import cn.stylefeng.guns.modular.system.model.Company;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 单位管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-17 14:56:06
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    private String PREFIX = "/gs/company/";

    @Autowired
    private ICompanyService companyService;

    /**
     * 跳转到单位管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "company.html";
    }

    /**
     * 跳转到添加单位管理
     */
    @RequestMapping("/company_add")
    public String companyAdd() {
        return PREFIX + "company_add.html";
    }

    /**
     * 跳转到修改单位管理
     */
    @RequestMapping("/company_update/{companyId}")
    public String companyUpdate(@PathVariable String companyId, Model model) {
        Company company = companyService.selectById(companyId);
        model.addAttribute("item",company);
        LogObjectHolder.me().set(company);
        return PREFIX + "company_edit.html";
    }

    /**
     * 获取单位管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Company> list =  companyService.selectList(null);
        List<CompanyVO> listVo = CommonUtil.listPo2VO(list, CompanyVO.class);
        listVo.forEach((vo)->{
        	vo.setJoinStatusName(ConstantFactory.me().getDictsByName("status_yn", vo.getJoinStatus()));
        	vo.setAccTypeName(ConstantFactory.me().getDictsByName("company_status", vo.getAccType()));
        	vo.setAccUseTypeName(ConstantFactory.me().getDictsByName("acc_use_type", vo.getAccUseType()));
        	vo.setStatusName(ConstantFactory.me().getDictsByName("company_status", vo.getStatus()));
        	
        	
        	if(StringUtil.isNotEmpty(vo.getCrtUserId())) {
    			vo.setCreateUserName(ConstantFactory.me().getUserNameById(Integer.parseInt(vo.getCrtUserId())));
    		}
    		if(StringUtil.isNotEmpty(vo.getCrtOrgId())) {
    			vo.setCreateOrgName(ConstantFactory.me().getDeptName(Integer.parseInt(vo.getCrtOrgId())));
    		}
        });
        return listVo ;
    }

    /**
     * 新增单位管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Company company) {
    	company.setId(CommonUtil.UUID());
        companyService.insert(company);
        return SUCCESS_TIP;
    }

    /**
     * 删除单位管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String companyId) {
        companyService.deleteById(companyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改单位管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Company company) {
        companyService.updateById(company);
        return SUCCESS_TIP;
    }

    /**
     * 单位管理详情
     */
    @RequestMapping(value = "/detail/{companyId}")
    @ResponseBody
    public Object detail(@PathVariable("companyId") String companyId) {
        return companyService.selectById(companyId);
    }
}
