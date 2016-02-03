package com.ice.dragbrief.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.ice.dragbrief.annotation.MethodBrief;
import com.ice.dragbrief.annotation.NeedScanBriefClass;
import com.ice.dragbrief.model.BriefInfoCache;
import com.ice.dragbrief.model.ClassBrief;
import com.ice.dragbrief.model.WrongInfoCache;

public final class BriefGenUtil {

	private static final Map<String, String> startMethodCache = new HashMap<>();

	private static final Map<String, String> simpleMethodCache = new HashMap<>();

	private static boolean isDebug = false;

	public static void setDebug(boolean isDebug) {
		BriefGenUtil.isDebug = isDebug;
	}

	private BriefGenUtil() {

	}

	public static List<ClassBrief> getBrief(String packageName) {
		initBriefInfo(packageName);
		return getBrief();
	}

	private static void initBriefInfo(String packageName) {
		for (String className : getClassName(packageName, true)) {
			loadClassMethodInfos(className);
		}
		if (isDebug) {
			System.out.println("初始化完成.............");
			System.out.println("入口方法: " + startMethodCache.toString());
			System.out.println("非入口方法: " + simpleMethodCache.toString());
		}
	}

	/**
	 * 获取某包下所有类
	 */
	private static Set<String> getClassName(String packageName, boolean isRecursion) {
		Set<String> classNames = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");

		URL url = loader.getResource(packagePath);
		classNames = getClassNameFromDir(url.getPath(), packageName, isRecursion);

		return classNames;
	}

	/**
	 * 从项目文件获取某包下所有类
	 * 
	 * @param filePath
	 *            文件路径
	 * @param className
	 *            类名集合
	 * @param isRecursion
	 *            是否遍历子包
	 * @return 类的完整名称
	 */
	private static Set<String> getClassNameFromDir(String filePath, String packageName, boolean isRecursion) {
		Set<String> className = new HashSet<String>();
		File file = new File(filePath);
		File[] files = file.listFiles();
		for (File childFile : files) {
			if (childFile.isDirectory()) {
				if (isRecursion) {
					className.addAll(getClassNameFromDir(childFile.getPath(), packageName + "." + childFile.getName(),
							isRecursion));
				}
			} else {
				String fileName = childFile.getName();
				if (fileName.endsWith(".class") && !fileName.contains("$")) {
					className.add(packageName + "." + fileName.replace(".class", ""));
				}
			}
		}

		return className;
	}

	private static List<ClassBrief> getBrief() {

		try {
			for (Map.Entry<String, String> entry : startMethodCache.entrySet()) {
				MethodKey methodKey = new MethodKey(entry.getKey());
				BriefInfoCache.add(methodKey.getClassName(), methodKey.getMethodName(),
						genBriefFromStartMethod(methodKey.getClassName(), methodKey.getMethodName()));
			}
		} catch (Exception e) {
			WrongInfoCache.add("文档生成失败", e);
		}
		return BriefInfoCache.getBriefs();
	}

	private static String genBriefFromStartMethod(String className, String methodName) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void loadClassMethodInfos(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			WrongInfoCache.add(className + " 类加载失败.");
		}
		if (isNeedDragBrief(clazz)) {
			for (Method method : clazz.getDeclaredMethods()) {
				method.setAccessible(true);
				if (method.isAnnotationPresent(MethodBrief.class)) {
					MethodBrief methodBrief = (MethodBrief) method.getAnnotation(MethodBrief.class);
					if (methodBrief.isBeginPoint()) {
						startMethodCache.put(genMethodKey(clazz, method), methodBrief.value());
					} else {
						simpleMethodCache.put(genMethodKey(clazz, method), methodBrief.value());
					}
				}

			}
		}
	}

	private static String genMethodKey(Class<?> clazz, Method method) {
		StringBuilder params = new StringBuilder();
		for (Class tmpclazz : method.getParameterTypes()) {
			params.append(tmpclazz.getSimpleName());
		}
		return new MethodKey(clazz.getName(), method.getName(), params.toString()).getKey();
	}

	private static boolean isNeedDragBrief(Class<?> clazz) {
		return clazz.isAnnotationPresent(NeedScanBriefClass.class);
	}

	static class MethodKey {

		private static final String SEPRATOR = "-";

		private String className;
		private String methodName;
		private String params;

		public String getClassName() {
			return className;
		}

		public String getMethodName() {
			return methodName;
		}

		public MethodKey(String className, String methodName, String params) {
			super();
			this.className = className;
			this.methodName = methodName;
			this.params = params;
		}

		public MethodKey(String keyInfo) {
			super();
			StringTokenizer stringTokenizer = new StringTokenizer(keyInfo, SEPRATOR);
			this.className = stringTokenizer.nextToken();
			this.methodName = stringTokenizer.nextToken();
		}

		public String getKey() {
			if (params != null && params.length() > 0) {
				return className + SEPRATOR + methodName + SEPRATOR + params;
			} else {
				return className + SEPRATOR + methodName;
			}
		}
	}

}
