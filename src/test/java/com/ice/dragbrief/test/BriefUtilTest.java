package com.ice.dragbrief.test;

import com.ice.dragbrief.demo.Demo1;
import com.ice.dragbrief.util.BriefGenUtil;
import com.ice.dragbrief.util.MethodCallMonitor;

public class BriefUtilTest {

	public static void main(String[] args) {
		BriefGenUtil.setDebug(true);
		System.out.println(BriefGenUtil.getBrief("com.ice"));

		new Demo1().main(null);
	}
}
