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
import cn.stylefeng.guns.modular.system.model.InvitationDetail;
import cn.stylefeng.guns.modular.gs.service.IInvitationDetailService;

/**
 * 社区问答控制器
 *
 * @author fengshuonan
 * @Date 2019-05-14 20:20:13
 */
@Controller
@RequestMapping("/invitationDetail")
public class InvitationDetailController extends BaseController {

    private String PREFIX = "/gs/invitationDetail/";

    @Autowired
    private IInvitationDetailService invitationDetailService;

    /**
     * 跳转到社区问答首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "invitationDetail.html";
    }

    /**
     * 跳转到添加社区问答
     */
    @RequestMapping("/invitationDetail_add")
    public String invitationDetailAdd() {
        return PREFIX + "invitationDetail_add.html";
    }

    /**
     * 跳转到修改社区问答
     */
    @RequestMapping("/invitationDetail_update/{invitationDetailId}")
    public String invitationDetailUpdate(@PathVariable Integer invitationDetailId, Model model) {
        InvitationDetail invitationDetail = invitationDetailService.selectById(invitationDetailId);
        model.addAttribute("item",invitationDetail);
        LogObjectHolder.me().set(invitationDetail);
        return PREFIX + "invitationDetail_edit.html";
    }

    /**
     * 获取社区问答列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return invitationDetailService.selectList(null);
    }

    /**
     * 新增社区问答
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(InvitationDetail invitationDetail) {
        invitationDetailService.insert(invitationDetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除社区问答
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer invitationDetailId) {
        invitationDetailService.deleteById(invitationDetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改社区问答
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(InvitationDetail invitationDetail) {
        invitationDetailService.updateById(invitationDetail);
        return SUCCESS_TIP;
    }

    /**
     * 社区问答详情
     */
    @RequestMapping(value = "/detail/{invitationDetailId}")
    @ResponseBody
    public Object detail(@PathVariable("invitationDetailId") Integer invitationDetailId) {
        return invitationDetailService.selectById(invitationDetailId);
    }
}
