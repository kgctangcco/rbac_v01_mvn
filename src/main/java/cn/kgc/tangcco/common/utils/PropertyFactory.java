package cn.kgc.tangcco.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class PropertyFactory {
	private static Map<String, Object> map = new HashMap<String, Object>();

	public static Object getInstance(String id) {
		if (map.containsKey(id)) {
			return map.get(id);
		}
		Bean bean = PropertyUtil.getBean(id);
		try {
			Class<?> clazz = Class.forName(bean.getClassName());
			Object obj = Class.forName(bean.getClassName()).getDeclaredConstructor().newInstance();
			map.put(id, obj);
			if (bean.getPropertyList() != null && bean.getPropertyList().size() > 0) {
				for (Property prop : bean.getPropertyList()) {
					String name = prop.getName();
					String ref = prop.getRef();
					Field field = obj.getClass().getDeclaredField(name);
					field.setAccessible(true);
					field.set(obj, getInstance(ref));
				}
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
