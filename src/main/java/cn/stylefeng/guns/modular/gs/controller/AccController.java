package cn.stylefeng.guns.modular.gs.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Acc;
import cn.stylefeng.guns.modular.gs.service.IAccService;

/**
 * 账目查询控制器
 *
 * @author fengshuonan
 * @Date 2019-04-17 15:01:13
 */
@Controller
@RequestMapping("/acc")
public class AccController extends BaseController {

    private String PREFIX = "/gs/acc/";

    @Autowired
    private IAccService accService;

    /**
     * 跳转到账目查询首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "acc.html";
    }

    /**
     * 跳转到添加账目查询
     */
    @RequestMapping("/acc_add")
    public String accAdd() {
        return PREFIX + "acc_add.html";
    }

    /**
     * 跳转到修改账目查询
     */
    @RequestMapping("/acc_update/{accId}")
    public String accUpdate(@PathVariable Integer accId, Model model) {
        Acc acc = accService.selectById(accId);
        model.addAttribute("item",acc);
        LogObjectHolder.me().set(acc);
        return PREFIX + "acc_edit.html";
    }

    /**
     * 获取账目查询列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return accService.selectList(null);
    }

    /**
     * 新增账目查询
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Acc acc) {
        accService.insert(acc);
        return SUCCESS_TIP;
    }

    /**
     * 删除账目查询
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer accId) {
        accService.deleteById(accId);
        return SUCCESS_TIP;
    }

    /**
     * 修改账目查询
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Acc acc) {
        accService.updateById(acc);
        return SUCCESS_TIP;
    }

    /**
     * 账目查询详情
     */
    @RequestMapping(value = "/detail/{accId}")
    @ResponseBody
    public Object detail(@PathVariable("accId") Integer accId) {
        return accService.selectById(accId);
    }
}
