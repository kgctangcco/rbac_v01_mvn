package cn.kgc.tangcco.common.utils;

import org.junit.Test;

public class PropertyFactoryTest {
	@Test
	public void test() {
		Utils[] values = Utils.values();
		for (int i = 0; i < values.length; i++) {
			System.out.println(PropertyFactory.getInstance(values[i].toString()));
		}
	}

}
