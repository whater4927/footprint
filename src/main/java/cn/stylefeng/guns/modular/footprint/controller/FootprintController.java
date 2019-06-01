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
import cn.stylefeng.guns.modular.system.model.Footprint;
import cn.stylefeng.guns.modular.system.service.INoService;
import cn.stylefeng.guns.modular.footprint.service.IFootprintService;

/**
 * 足迹信息控制器
 *
 * @author fengshuonan
 * @Date 2019-05-31 11:30:42
 */
@Controller
@RequestMapping("/footprint")
public class FootprintController extends BaseController {

    private String PREFIX = "/footprint/footprint/";

    @Autowired
    private IFootprintService footprintService;
    @Autowired
    private INoService noService ;
    /**
     * 跳转到足迹信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "footprint.html";
    }

    /**
     * 跳转到添加足迹信息
     */
    @RequestMapping("/footprint_add")
    public String footprintAdd() {
        return PREFIX + "footprint_add.html";
    }

    /**
     * 跳转到修改足迹信息
     */
    @RequestMapping("/footprint_update/{footprintId}")
    public String footprintUpdate(@PathVariable String footprintId, Model model) {
        Footprint footprint = footprintService.selectById(footprintId);
        model.addAttribute("item",footprint);
        LogObjectHolder.me().set(footprint);
        return PREFIX + "footprint_edit.html";
    }

    /**
     * 获取足迹信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return footprintService.selectList(null);
    }

    /**
     * 新增足迹信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Footprint footprint) {
        footprintService.insert(footprint);
        return SUCCESS_TIP;
    }

    /**
     * 删除足迹信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String footprintId) {
        footprintService.deleteById(footprintId);
        return SUCCESS_TIP;
    }

    /**
     * 修改足迹信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Footprint footprint) {
        footprintService.updateById(footprint);
        return SUCCESS_TIP;
    }

    /**
     * 足迹信息详情
     */
    @RequestMapping(value = "/detail/{footprintId}")
    @ResponseBody
    public Object detail(@PathVariable("footprintId") String footprintId) {
        return footprintService.selectById(footprintId);
    }
}
