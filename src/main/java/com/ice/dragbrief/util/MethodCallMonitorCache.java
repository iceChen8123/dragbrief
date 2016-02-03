package com.ice.dragbrief.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ice.methodcall.model.CallInfo;
import com.ice.methodcall.model.CallInfoChain;

public class MethodCallMonitorCache {

	private static final String SEPRATOR = "-";

	private static final Set<CallInfoChain> callInfoListCache = new HashSet<>();

	/**
	 * 调用堆栈监控功能已经获取过的缓存
	 */
	private static final Map<String, CallInfo> callInfoCache = new HashMap<String, CallInfo>();

	static ThreadLocal<CallInfoChain> callInfoChain = new ThreadLocal<>();

	static Set<CallInfoChain> getCallInfoListCache() {
		return callInfoListCache;
	}

	public static void add(String className, String methodName, int lineNumber) {
		if (!callInfoCache.containsKey(genkey(className, methodName, lineNumber))) {
			callInfoCache.put(genkey(className, methodName, lineNumber),
					new CallInfo(className, methodName, lineNumber));
		}
		if (callInfoChain.get() == null) {
			callInfoChain.set(new CallInfoChain());
		}
		callInfoChain.get().add(callInfoCache.get(genkey(className, methodName, lineNumber)));
	}

	private static String genkey(String className, String methodName, int lineNumber) {
		return new StringBuilder(className).append(SEPRATOR).append(methodName).append(SEPRATOR).append(lineNumber)
				.toString();
	}

	public static void tidyRecords() {
		callInfoListCache.add(callInfoChain.get());
		callInfoChain.remove();
	}

}
