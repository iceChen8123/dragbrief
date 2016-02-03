package com.ice.methodcall.model;

public class CallInfo {

	private String className;
	private String methodName;
	private int lineNumber;

	public CallInfo(String className, String methodName, int lineNumber) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	@Override
	public String toString() {
		return "CallInfo [className=" + className + ", methodName=" + methodName + ", lineNumber=" + lineNumber + "]";
	}

}
