package com.ice.methodcall.model;

import java.util.LinkedList;
import java.util.List;

public class CallInfoChain {

	private List<CallInfo> callInfos = new LinkedList<>();

	private StringBuilder keyInfo = new StringBuilder();

	public void add(CallInfo callInfo) {
		callInfos.add(callInfo);
		keyInfo.append(callInfo.getClassName()).append(callInfo.getMethodName()).append(callInfo.getLineNumber());
	}

	public List<CallInfo> getCallInfos() {
		return callInfos;
	}

	public int hashCode() {
		System.out.println(keyInfo.toString().hashCode() + " ------hashcode....: " + keyInfo.toString());
		return keyInfo.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CallInfoChain) {
			if (keyInfo.toString().equals(((CallInfoChain) obj).keyInfo.toString())) {
				return true;
			}
		}
		return false;
	}

}
