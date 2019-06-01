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
import cn.stylefeng.guns.modular.system.model.ShoesDemo;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.guns.modular.footprint.service.IShoesDemoService;

/**
 * 鞋厂鞋样控制器
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:25:48
 */
@Controller
@RequestMapping("/shoesDemo")
public class ShoesDemoController extends BaseController {

    private String PREFIX = "/footprint/shoesDemo/";

    @Autowired
    private IShoesDemoService shoesDemoService;
    @Autowired
    private INoService noService ;
    /**
     * 跳转到鞋厂鞋样首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "shoesDemo.html";
    }

    /**
     * 跳转到添加鞋厂鞋样
     */
    @RequestMapping("/shoesDemo_add")
    public String shoesDemoAdd() {
        return PREFIX + "shoesDemo_add.html";
    }

    /**
     * 跳转到修改鞋厂鞋样
     */
    @RequestMapping("/shoesDemo_update/{shoesDemoId}")
    public String shoesDemoUpdate(@PathVariable String shoesDemoId, Model model) {
        ShoesDemo shoesDemo = shoesDemoService.selectById(shoesDemoId);
        model.addAttribute("item",shoesDemo);
        LogObjectHolder.me().set(shoesDemo);
        return PREFIX + "shoesDemo_edit.html";
    }

    /**
     * 获取鞋厂鞋样列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return shoesDemoService.selectList(null);
    }

    /**
     * 新增鞋厂鞋样
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ShoesDemo shoesDemo) {
        shoesDemoService.insert(shoesDemo);
        return SUCCESS_TIP;
    }

    /**
     * 删除鞋厂鞋样
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String shoesDemoId) {
        shoesDemoService.deleteById(shoesDemoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改鞋厂鞋样
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ShoesDemo shoesDemo) {
        shoesDemoService.updateById(shoesDemo);
        return SUCCESS_TIP;
    }

    /**
     * 鞋厂鞋样详情
     */
    @RequestMapping(value = "/detail/{shoesDemoId}")
    @ResponseBody
    public Object detail(@PathVariable("shoesDemoId") String shoesDemoId) {
        return shoesDemoService.selectById(shoesDemoId);
    }
}
