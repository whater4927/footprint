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
import cn.stylefeng.guns.modular.gs.service.IEmployeeService;
import cn.stylefeng.guns.modular.gs.vo.EmployeeVO;
import cn.stylefeng.guns.modular.system.model.Employee;
import cn.stylefeng.roses.core.base.controller.BaseController;

/**
 * 人员管理控制器
 *
 * @author fengshuonan
 * @Date 2019-04-17 15:03:08
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    private String PREFIX = "/gs/employee/";

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 跳转到人员管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "employee.html";
    }

    /**
     * 跳转到添加人员管理
     */
    @RequestMapping("/employee_add")
    public String employeeAdd() {
        return PREFIX + "employee_add.html";
    }

    /**
     * 跳转到修改人员管理
     */
    @RequestMapping("/employee_update/{employeeId}")
    public String employeeUpdate(@PathVariable String employeeId, Model model) {
        Employee employee = employeeService.selectById(employeeId);
        model.addAttribute("item",employee);
        LogObjectHolder.me().set(employee);
        return PREFIX + "employee_edit.html";
    }

    /**
     * 获取人员管理列表
     */
    @RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		List<Employee> list = employeeService.selectList(null);
		List<EmployeeVO> listVo = CommonUtil.listPo2VO(list, EmployeeVO.class);
		listVo.forEach((vo) -> {
			vo.setCompanyName(ConstantFactory.me().getSingleCompanyName(vo.getComId()));
			
			vo.setEducationName(ConstantFactory.me().getDictsByName("education", vo.getEducation()));
			vo.setNativePlaceName(ConstantFactory.me().getDictsByName("nativePlace", vo.getNativePlace()));
			vo.setStatusName(ConstantFactory.me().getDictsByName("emp_status", vo.getStatus()));
		});
		return listVo;
	}

    /**
     * 新增人员管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Employee employee) {
    	employee.setId(CommonUtil.UUID());
        employeeService.insert(employee);
        return SUCCESS_TIP;
    }

    /**
     * 删除人员管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String employeeId) {
        employeeService.deleteById(employeeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改人员管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Employee employee) {
        employeeService.updateById(employee);
        return SUCCESS_TIP;
    }

    /**
     * 人员管理详情
     */
    @RequestMapping(value = "/detail/{employeeId}")
    @ResponseBody
    public Object detail(@PathVariable("employeeId") String employeeId) {
        return employeeService.selectById(employeeId);
    }
}
