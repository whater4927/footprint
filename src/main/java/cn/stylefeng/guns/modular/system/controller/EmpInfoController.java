package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.EmpInfo;
import cn.stylefeng.guns.modular.system.service.IEmpInfoService;

/**
 * 员工信息控制器
 *
 * @author fengshuonan
 * @Date 2019-04-16 22:07:56
 */
@Controller
@RequestMapping("/empInfo")
public class EmpInfoController extends BaseController {

    private String PREFIX = "/system/empInfo/";

    @Autowired
    private IEmpInfoService empInfoService;

    /**
     * 跳转到员工信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "empInfo.html";
    }

    /**
     * 跳转到添加员工信息
     */
    @RequestMapping("/empInfo_add")
    public String empInfoAdd() {
        return PREFIX + "empInfo_add.html";
    }

    /**
     * 跳转到修改员工信息
     */
    @RequestMapping("/empInfo_update/{empInfoId}")
    public String empInfoUpdate(@PathVariable Integer empInfoId, Model model) {
        EmpInfo empInfo = empInfoService.selectById(empInfoId);
        model.addAttribute("item",empInfo);
        LogObjectHolder.me().set(empInfo);
        return PREFIX + "empInfo_edit.html";
    }

    /**
     * 获取员工信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return empInfoService.selectList(null);
    }

    /**
     * 新增员工信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EmpInfo empInfo) {
        empInfoService.insert(empInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除员工信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer empInfoId) {
        empInfoService.deleteById(empInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改员工信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EmpInfo empInfo) {
        empInfoService.updateById(empInfo);
        return SUCCESS_TIP;
    }

    /**
     * 员工信息详情
     */
    @RequestMapping(value = "/detail/{empInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("empInfoId") Integer empInfoId) {
        return empInfoService.selectById(empInfoId);
    }
}
