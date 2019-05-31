package cn.stylefeng.guns.modular.footprint.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.CaseInfo;
import cn.stylefeng.guns.modular.footprint.service.ICaseInfoService;

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
    public String caseInfoUpdate(@PathVariable Integer caseInfoId, Model model) {
        CaseInfo caseInfo = caseInfoService.selectById(caseInfoId);
        model.addAttribute("item",caseInfo);
        LogObjectHolder.me().set(caseInfo);
        return PREFIX + "caseInfo_edit.html";
    }

    /**
     * 获取案件基本信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return caseInfoService.selectList(null);
    }

    /**
     * 新增案件基本信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CaseInfo caseInfo) {
        caseInfoService.insert(caseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除案件基本信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer caseInfoId) {
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
    public Object detail(@PathVariable("caseInfoId") Integer caseInfoId) {
        return caseInfoService.selectById(caseInfoId);
    }
}
