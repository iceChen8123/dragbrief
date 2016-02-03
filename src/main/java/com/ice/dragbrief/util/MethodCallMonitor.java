package com.ice.dragbrief.util;

public class MethodCallMonitor {

	private volatile static boolean isOn = false;

	private MethodCallMonitor() {

	}

	public static void turnOn() {
		isOn = true;
	}

	public static void turnOff() {
		isOn = false;
	}

	public static void monitor() {
		if (isOn) {
			StackTraceElement[] stackElements = new Throwable().getStackTrace();
			if (stackElements != null) {
				for (StackTraceElement stackTraceElement : stackElements) { // TODO
																			// 相邻调用的顶层堆栈可以不要。
					String className = stackTraceElement.getClassName();
					String methodName = stackTraceElement.getMethodName();
					int lineNumber = stackTraceElement.getLineNumber();
					MethodCallMonitorCache.add(className, methodName, lineNumber);
				}
			}
		}
	}

	public static void end() {
		if (isOn) {
			MethodCallMonitorCache.tidyRecords();
		}
	}

}
