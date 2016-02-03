package com.ice.dragbrief.util;

import java.util.Iterator;

import com.ice.methodcall.model.CallInfo;
import com.ice.methodcall.model.CallInfoChain;

public class MethodCallInfoUtil {

	public static void printRecords() {
		Iterator<CallInfoChain> iterator = MethodCallMonitorCache.getCallInfoListCache().iterator();
		System.out.println("路线数： " + MethodCallMonitorCache.getCallInfoListCache().size());
		while (iterator.hasNext()) {
			System.out.println("---------------------------------start----------------------------");
			CallInfoChain callInfoChain = iterator.next();
			for (CallInfo callInfo : callInfoChain.getCallInfos()) {
				System.out.println(callInfo.toString());
			}
			System.out.println("----------------------------------end-----------------------------");

		}
	}

}
