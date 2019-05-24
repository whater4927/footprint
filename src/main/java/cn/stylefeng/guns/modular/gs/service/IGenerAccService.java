package cn.stylefeng.guns.modular.gs.service;

import cn.stylefeng.guns.modular.system.model.GenerAcc;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 生成财务信息 服务类
 * </p>
 *
 * @author whater
 * @since 2019-04-26
 */
public interface IGenerAccService extends IService<GenerAcc> {

	void generation(String genDt);

}
