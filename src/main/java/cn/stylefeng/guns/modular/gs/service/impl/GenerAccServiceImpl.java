package cn.stylefeng.guns.modular.gs.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.core.util.EntityUtils;
import cn.stylefeng.guns.modular.gs.service.IAccEmpService;
import cn.stylefeng.guns.modular.gs.service.ICompanyService;
import cn.stylefeng.guns.modular.gs.service.IEmployeeService;
import cn.stylefeng.guns.modular.gs.service.IGenerAccService;
import cn.stylefeng.guns.modular.system.dao.GenerAccMapper;
import cn.stylefeng.guns.modular.system.model.AccEmp;
import cn.stylefeng.guns.modular.system.model.Company;
import cn.stylefeng.guns.modular.system.model.Employee;
import cn.stylefeng.guns.modular.system.model.GenerAcc;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 生成财务信息 服务实现类
 * </p>
 *
 * @author whater
 * @since 2019-04-26
 */
@Service
@Slf4j
public class GenerAccServiceImpl extends ServiceImpl<GenerAccMapper, GenerAcc> implements IGenerAccService {
	@Autowired
	private ICompanyService companyService ;
	@Autowired
	private IEmployeeService employeeService ;
	@Autowired
	private IAccEmpService accEmpService ;
	
	@Override
	public void generation(String genDt) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("join_status", "y");// 单位参保状态：是
		List<Company> listCompany = companyService.selectByMap(columnMap);
		try {
			for (Company company : listCompany) {
				String comId = company.getId();
				
				int size = this.selectCount(new EntityWrapper<GenerAcc>().eq("gen_dt", genDt).eq("com_Id", comId));
				if(size > 0) {
					log.error("企业【{}】财务数据已经生成！！！",company.getName());
					continue;
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
				List<Employee> listEmployee = employeeService
						.selectList(new EntityWrapper<Employee>()
								.eq("com_Id", comId)
								.eq("status", "1")// 状态：r在职
								.gt("entry_dt", format.parse(genDt)));
				BigDecimal totalAmt = new BigDecimal(0);
				if(CommonUtil.isEmpty(listEmployee)) {
					continue;
				}
				for (Employee employee : listEmployee) {
					AccEmp entity = new AccEmp();
					entity.setId(CommonUtil.UUID());
					entity.setEmpId(employee.getId());
					entity.setComId(comId);
					entity.setEmpAmt(BigDecimal.valueOf(employee.getSalary().longValue() * company.getEmpRation().longValue() / 100));
					entity.setComAmt(BigDecimal.valueOf(employee.getSalary().longValue() * company.getComRation().longValue() / 100));
					entity.setPayStatus("N");
					entity.setPayDt(genDt);
					EntityUtils.setCreateInfo(entity);
					accEmpService.insert(entity);
					totalAmt = totalAmt.add(entity.getEmpAmt()).add(entity.getComAmt());
				}
				GenerAcc generAcc = new GenerAcc();
				generAcc.setGenDt(genDt);
				generAcc.setId(CommonUtil.UUID());
				generAcc.setComId(comId);
				generAcc.setEmpCount(listEmployee.size());
				generAcc.setTotalAmt(totalAmt);
				EntityUtils.setCreateInfo(generAcc);
				this.insert(generAcc);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
