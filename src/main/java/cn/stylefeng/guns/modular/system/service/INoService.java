package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.No;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 业务编号 服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-05-28
 */
public interface INoService extends IService<No> {
	public String busiNo(String prefix);
}
