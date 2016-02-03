package com.ice.dragbrief.test;

import com.ice.dragbrief.demo.Demo1;
import com.ice.dragbrief.util.MethodCallInfoUtil;
import com.ice.dragbrief.util.MethodCallMonitor;

public class MethodCallMonitorTest {

	public static void main(String[] args) throws InterruptedException {
		final Demo1 demo1 = new Demo1();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					demo1.main(null);
					try {
						Thread.sleep(500L);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

		MethodCallMonitor.turnOn();
		System.out.println("..........................开启方法调用监控");
		Thread.sleep(1000L);
		MethodCallMonitor.turnOff();
		System.out.println("..........................关闭方法调用监控");

		MethodCallInfoUtil.printRecords();
		System.exit(1);

	}
}
