package cn.stylefeng.guns.core.util;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lmh
 */
@Slf4j
public class CommonUtil {

	static {
		ConvertUtils.register(new DateConverter(null), Date.class);
	}

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * 获取UUID
	 *
	 * @return UUID
	 */
	public static String UUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static boolean isHave(String column, String columns, String table) {
		if (column.contains(".")) {
			String[] strs = column.split("\\.");
			return table.equals(strs[0]) && (columns + ",").contains(strs[1] + ",");
		} else {
			return (columns + ",").contains(column + ",");
		}
	}

	/**
	 * 将List<P> 转成 List<V>
	 *
	 * @param list List<P>
	 * @param v    Class<V>
	 * @return List<V>
	 */
	public static <P, V> List<V> listPo2VO(List<P> list, Class<V> v) {
		if (isEmpty(list)) {
			return new ArrayList<>();
		}
		List<V> listVo = new ArrayList<>();
		for (P p : list) {
			listVo.add(po2VO(p, v));
		}
		return listVo;
	}

	/**
	 * 将P对象转成V对象
	 *
	 * @param p P对象
	 * @param v V对象
	 * @return V对象
	 */
	public static <V, P> V po2VO(P p, Class<V> v) {
		if (p == null) {
			try {
				return v.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		V tObject = null;
		try {
			tObject = v.newInstance();
			BeanUtils.copyProperties(tObject, p);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			log.error("PO转VO出错!", e);
		}
		return tObject;
	}

	/**
	 * 判断List是否为空
	 *
	 * @param list List
	 * @return 结果
	 */
	public static boolean isEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

	/**
	 * 判断List是否不为空
	 *
	 * @param list List
	 * @return 结果
	 */
	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	/**
	 * 将 List<Map<String, Object>> 转成 List<T>
	 *
	 * @param listRecord List<Map<String, Object>>对象
	 * @param t          Class<T>
	 * @return List<T>
	 */
	public static <T> List<T> map2PO(List<Map<String, Object>> listRecord, Class<T> t, boolean flag) {
		if (isEmpty(listRecord)) {
			return null;
		}
		List<T> listT = new ArrayList<>();
		for (Map<String, Object> record : listRecord) {
			if (record == null) {
				log.info("map2PO: record is null");
				continue;
			}
			try {
				T newT = t.newInstance();
				for (String column : record.keySet()) {
					try {
						Object value = record.get(column);
						if (value == null) {
							continue;
						}
						if (flag) {
							BeanUtils.setProperty(newT, column2Filed(column), value);
						} else {
							BeanUtils.setProperty(newT, column, value);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				listT.add(newT);

			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return listT;
	}

	/**
	 * 将 List<Map<String, Object>> 转成 List<T>
	 *
	 * @param listRecord List<Map<String, Object>>对象
	 * @param t          Class<T>
	 * @return List<T>
	 */
	public static <T> List<T> map2PO(List<Map<String, Object>> listRecord, Class<T> t) {
		return map2PO(listRecord, t, true);
	}

	/**
	 * 将 Map<String, Object> 对象转成 T 对象
	 * 注意：
	 * 这个方法会调用 column2Filed()，只适合从数据库中查出的字段名含下划线的数据转成对象
	 *
	 * @param record Map<String, Object>对象
	 * @param t      T对象
	 * @return T对象
	 */
	public static <T> T map2PO(Map<String, Object> record, Class<T> t) {
		if (record == null) {
			return null;
		}
		try {
			T newT = t.newInstance();
			for (String column : record.keySet()) {
				try {
					BeanUtils.setProperty(newT, column2Filed(column), record.get(column));
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			return newT;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T map2POx(Map<String, Object> record, Class<T> t) {
		ConvertUtils.register(new DateConverter(null), Date.class);

		if (record == null) {
			return null;
		}
		try {
			T newT = t.newInstance();
			for (String column : record.keySet()) {
				try {
					// 可能出现 org.apache.commons.beanutils.ConversionException: No value specified for 'Date' 的问题
//					if (record.get(column) == null) {
//						continue;
//					}

					BeanUtils.setProperty(newT, column, record.get(column));
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			return newT;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将下划线分隔的单词转为小驼峰风格
	 *
	 * @param column 下划线分隔的单词
	 * @return 小驼峰风格
	 */
	private static String column2Filed(String column) {
		String[] names = column.toLowerCase().split("_");
		StringBuilder filed = new StringBuilder();
		filed.append(names[0]);
		for (int i = 1; i < names.length; i++) {
			filed.append(names[i].substring(0, 1).toUpperCase()).append(names[i].substring(1));
		}
		return filed.toString();
	}

	/**
	 * 拼接分页查询语句
	 *
	 * @param pageIndex 页码
	 * @param pageSize  每页大小
	 * @return SQL语句
	 */
	public static String getPageSQL(Integer pageIndex, Integer pageSize) {
		if (pageIndex == null || pageIndex < 0 || pageSize == null || pageSize < 1) {
			return "";
		}
		return " limit " + pageIndex + "," + pageSize;
	}

	/**
	 * 将 List<T> 转成 Set<T>
	 *
	 * @param list List<T>
	 * @return Set<T>
	 */
	public static <T> Set<T> list2Set(List<T> list) {
		return new HashSet<>(list);
	}

	/**
	 * 获取当前时间的 yyyy-MM-dd HH:mm:ss 形式的字符串
	 *
	 * @return 时间字符串
	 */
	public static synchronized String getSystemDateStr() {
		return FORMAT.format(new Date());
	}

//	/**
//	 * 获取当前时间的 yyyy-MM-dd 形式（仅年月日）的字符串
//	 *
//	 * @return 时间字符串
//	 */
//	public static String getSystemDateStr2() {
//		return new SimpleDateFormat("yyyy-MM-dd").FORMAT(new Date());
//	}

	/**
	 * 将List<String>变成以逗号分隔的字符串，形如 hello,world,again
	 */
	public static String getCommaString(List<String> list) {
		return list.stream().collect(Collectors.joining(","));
	}


	/**
	 * 拼接数据库插入语句
	 *
	 * @param tableName 表名
	 * @param keyList   字段列表
	 * @return SQL语句
	 */
	public static String conInsert(String tableName, List<String> keyList) {
		return "insert into " + tableName +
				"(" + CommonUtil.getCommaString(keyList) + ") values (" +
				keyList.stream().map(x -> "?").collect(Collectors.joining(",")) + ")";
	}

	/**
	 * 判断original字符串是否和list中的任意一个相等
	 */
	public static boolean orEquals(String original, String... list) {
		return Arrays.stream(list).anyMatch(original::equals);
	}

	/**
	 * 获取当前时间
	 *
	 * @return Date对象
	 */
	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 获取当前时间
	 *
	 * @return Date对象
	 */
	public static Date getSystemDate() {
		return getCurrentTime();
	}

	/**
	 * 将同类型多个参数转为List
	 *
	 * @param ts  不定参数列表
	 * @param <T> 类型
	 * @return List<T>
	 */
	public static <T> List<T> array2List(T... ts) {
		return new ArrayList<>(Arrays.asList(ts));
	}


	/**
	 * 获取数据字节大小
	 *
	 * @param list Map列表
	 * @return 数据字节大小
	 */
	public static Integer getSizeOfData(List<Map<String, Object>> list) {
		Integer size = 0;
		for (Map<String, Object> map : list) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				Object obj = map.get(key);
				if (obj instanceof Date) {
					size += "yyyy-MM-dd HH:mm:ss".getBytes().length;
				} else if (obj != null) {
					size += obj.toString().getBytes().length;
				}
			}
		}
		return size;
	}


	public static String formatNumber(Integer seq) {
		DecimalFormat format = new DecimalFormat("########");
		return format.format(seq + 10000000);
	}

	public static String removeOrgList(String authScope, List<String> removeList) {
		List<String> scopeList = new ArrayList<>(Arrays.asList(authScope.split(",")));
		for (String s : removeList) {
			scopeList.removeIf(x -> x.equals(s));
		}
		return scopeList.stream().collect(Collectors.joining(","));
	}


	public static boolean checkPageIsNull(Integer pageIndex, Integer pageSize) {
		return pageIndex == null || pageSize == null;
	}
}
