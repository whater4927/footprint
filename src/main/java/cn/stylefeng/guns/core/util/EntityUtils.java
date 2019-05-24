package cn.stylefeng.guns.core.util;


import java.util.Date;

import cn.stylefeng.guns.core.shiro.ShiroKit;

/**
 * 实体类相关工具类 解决问题： 1、快速对实体的常驻字段，如：crtUser、crtHost、updUser等值快速注入
 *
 * @author Ace
 * @version 1.0
 * @date 2016年4月18日
 * @since 1.7
 */
public class EntityUtils {

	/**
	 * 快速将bean的crtUser、crtHost、crtTime、updUser、updHost、updTime附上相关值
	 *
	 * @param entity 实体bean
	 */
	public static <T> void setCreateAndUpdateInfo(T entity) {
		setCreateInfo(entity);
		setUpdateInfo(entity);
	}

	/**
	 * 快速将bean的crtUser、crtHost、crtTime附上相关值
	 *
	 * @param entity 实体bean
	 */
	public static <T> void setCreateInfo(T entity) {
		// 默认属性
		String[] fields = { "delStatus", "crtUserId", "crtOrgId", "crtTm" };
		Date date = CommonUtil.getCurrentTime();
		String orgId = ShiroKit.getUser().getDeptId()+"" ;
		String userId = ShiroKit.getUser().getId()+"";
		Object[] value = { "NOR", userId, orgId, date };
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

	/**
	 * 快速将bean的updUser、updHost、updTime附上相关值
	 *
	 * @param entity 实体bean
	 */
	public static <T> void setUpdateInfo(T entity) {
		// 默认属性
		String[] fields = { "updUserId", "updOrgId", "updTm" };
		Date date = CommonUtil.getCurrentTime();
		String orgId = ShiroKit.getUser().getDeptId()+"" ;
		String userId = ShiroKit.getUser().getId()+"";
		Object[] value = { userId, orgId, date };
		// 填充默认属性值
		setDefaultValues(entity, fields, value);
	}

	/**
	 * 依据对象的属性数组和值数组对对象的属性进行赋值
	 *
	 * @param entity 对象
	 * @param fields 属性数组
	 * @param value  值数组
	 */
	private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			if (ReflectionUtils.hasField(entity, field)) {
				ReflectionUtils.invokeSetter(entity, field, value[i]);
			}
		}
	}

	/**
	 * 根据主键属性，判断主键是否值为空
	 *
	 * @param entity 对象
	 * @param field  属性
	 * @return 主键为空，则返回false；主键有值，返回true
	 * @date 2016年4月28日
	 */
	public static <T> boolean isPKNotNull(T entity, String field) {
		if (! ReflectionUtils.hasField(entity, field)) {
			return false;
		}
		Object value = ReflectionUtils.getFieldValue(entity, field);
		return value != null && ! "".equals(value);
	}

}
