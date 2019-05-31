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
import cn.stylefeng.guns.modular.system.model.CriminalSuspect;
import cn.stylefeng.guns.modular.footprint.service.ICriminalSuspectService;

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
    public String criminalSuspectUpdate(@PathVariable Integer criminalSuspectId, Model model) {
        CriminalSuspect criminalSuspect = criminalSuspectService.selectById(criminalSuspectId);
        model.addAttribute("item",criminalSuspect);
        LogObjectHolder.me().set(criminalSuspect);
        return PREFIX + "criminalSuspect_edit.html";
    }

    /**
     * 获取嫌疑人信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return criminalSuspectService.selectList(null);
    }

    /**
     * 新增嫌疑人信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CriminalSuspect criminalSuspect) {
        criminalSuspectService.insert(criminalSuspect);
        return SUCCESS_TIP;
    }

    /**
     * 删除嫌疑人信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer criminalSuspectId) {
        criminalSuspectService.deleteById(criminalSuspectId);
        return SUCCESS_TIP;
    }

    /**
     * 修改嫌疑人信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CriminalSuspect criminalSuspect) {
        criminalSuspectService.updateById(criminalSuspect);
        return SUCCESS_TIP;
    }

    /**
     * 嫌疑人信息详情
     */
    @RequestMapping(value = "/detail/{criminalSuspectId}")
    @ResponseBody
    public Object detail(@PathVariable("criminalSuspectId") Integer criminalSuspectId) {
        return criminalSuspectService.selectById(criminalSuspectId);
    }
}
