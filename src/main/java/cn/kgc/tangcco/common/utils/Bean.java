package cn.kgc.tangcco.common.utils;

import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Bean {
	@NonNull
	private String id,className;
	private List<Property> propertyList;
}
