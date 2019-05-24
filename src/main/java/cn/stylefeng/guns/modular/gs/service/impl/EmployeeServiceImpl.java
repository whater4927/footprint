package cn.stylefeng.guns.modular.gs.service.impl;

import cn.stylefeng.guns.modular.system.model.Employee;
import cn.stylefeng.guns.modular.system.dao.EmployeeMapper;
import cn.stylefeng.guns.modular.gs.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人信息 服务实现类
 * </p>
 *
 * @author whater
 * @since 2019-04-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
