package cn.kgc.tangcco.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PropertyUtil {
	private static Map<String, Bean> beanMap = new HashMap<String, Bean>();
	static {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader
					.read(PropertyUtil.class.getClassLoader().getResourceAsStream("applicationContext.xml"));
			Element beansEle = document.getRootElement();
			Iterator<Element> it = beansEle.elementIterator("bean");
			while (it.hasNext()) {
				Element beanEle = (Element) it.next();
				Bean bean = new Bean(beanEle.attributeValue("id"), beanEle.attributeValue("class"));
				beanMap.put(bean.getId(), bean);
				if (beanEle.elements("property").size() > 0) {
					List<Property> propertyList = new ArrayList<Property>();
					Iterator<Element> it2 = beanEle.elements("property").iterator();
					while (it2.hasNext()) {
						Element propertyEle = (Element) it2.next();
						Property property = new Property(propertyEle.attributeValue("name"),
								propertyEle.attributeValue("ref"));
						propertyList.add(property);
					}
					bean.setPropertyList(propertyList);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public static Bean getBean(String id) {
		return beanMap.get(id);
	}
}
