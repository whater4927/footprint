package cn.stylefeng.guns.modular.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.util.CommonUtil;
import cn.stylefeng.guns.modular.system.dao.NoMapper;
import cn.stylefeng.guns.modular.system.model.No;
import cn.stylefeng.guns.modular.system.service.INoService;

/**
 * <p>
 * 业务编号 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-28
 */
@Service
public class NoServiceImpl extends ServiceImpl<NoMapper, No> implements INoService {

	private Map<String,AtomicInteger> numbers = new HashMap<>();
	
	private static final int cachNumber = 1000 ;
	
	@Override
	public String busiNo(String prefix) {
		if(prefix == null) {
			prefix = "" ;
		}
		Date systemDate = CommonUtil.getCurrentTime() ;
		String orgId = CommonUtil.formatNumber(ShiroKit.getUser().getDeptId(), 3) ;
		String yearMonth = new SimpleDateFormat("yyyyMM").format(systemDate);
		
		AtomicInteger num = numbers.get(orgId+yearMonth);
		No no = null ;
		if(num == null) {
			no = this.selectOne(new EntityWrapper<No>()
					.eq("org_id", ShiroKit.getUser().getDeptId())
					.eq("year", systemDate.getYear())
					.eq("month", systemDate.getMonth()));
			if(no == null) {
				no = new No();
				no.setId(CommonUtil.UUID());
				no.setOrgId(ShiroKit.getUser().getDeptId());
				no.setYear(systemDate.getYear());
				no.setMonth(systemDate.getMonth());
				no.setSeqNo(cachNumber);
				this.insert(no);
				num = new AtomicInteger(0);
			} else {
				num = new AtomicInteger(no.getSeqNo() + cachNumber);
				no.setSeqNo(num.intValue());
				No newNo = new No();
				newNo.setSeqNo(num.intValue());
				this.update(newNo, new EntityWrapper<No>()
						.eq("id", no.getId()) );
			}
		}
		int seqNum = num.incrementAndGet() ;
		if(seqNum % cachNumber == 0) {
			No newNo = new No();
			newNo.setSeqNo(seqNum + cachNumber);
			this.update(newNo, new EntityWrapper<No>()
					.eq("org_id", ShiroKit.getUser().getDeptId())
					.eq("year", systemDate.getYear())
					.eq("month", systemDate.getMonth()) );
		}
		numbers.put(orgId+yearMonth, num);
		return prefix+orgId+yearMonth+CommonUtil.formatNumber(seqNum, 4) ;
	}
	

}
