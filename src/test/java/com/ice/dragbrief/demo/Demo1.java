package com.ice.dragbrief.demo;

import com.ice.dragbrief.annotation.MethodBrief;
import com.ice.dragbrief.annotation.NeedScanBriefClass;
import com.ice.dragbrief.util.MethodCallMonitor;

@NeedScanBriefClass
public class Demo1 {

	@MethodBrief(value = "启动方法", isBeginPoint = true)
	public void main(String[] args) {
		try {
			checkParameters(args);
			String result = doBusiness(args);
			if (isSucceed(result)) {
				doSomethingIfSucceed();
			} else {
				doSomethingIfFailed();
			}
		} catch (IllegalArgumentException e) { // 只是为了demo用
			genRestultIfException(e);
		} catch (NullPointerException e) {
			doSomethingWithNullPointerException(e);
			genRestultIfException(e);
		} catch (Exception e) {
			doSomethingWithException(e);
			genRestultIfException(e);
		} finally {
			MethodCallMonitor.end();
		}
	}

	@MethodBrief("根据异常，生成结果")
	private void genRestultIfException(Exception e) {
		// do something
		MethodCallMonitor.monitor();
	}

	@MethodBrief("处理空指针异常")
	private void doSomethingWithNullPointerException(NullPointerException e) {
		// do something
		MethodCallMonitor.monitor();
	}

	@MethodBrief("异常处理通用方法")
	private void doSomethingWithException(Exception e) {
		// do something
		MethodCallMonitor.monitor();
	}

	@MethodBrief("失败处理，记录失败日志，并发送报警邮件")
	private void doSomethingIfFailed() {
		// do something
		MethodCallMonitor.monitor();
	}

	@MethodBrief("成功处理，记录日志到DB，并发送短信通知当事人")
	private void doSomethingIfSucceed() {
		// do something
		MethodCallMonitor.monitor();
		recordLogToDB();
		sendSns();
	}

	@MethodBrief("记录日志到DB")
	private void recordLogToDB() {
		// do something
		MethodCallMonitor.monitor();
	}

	@MethodBrief("发送短信通知当事人")
	private void sendSns() {
		// do something
		MethodCallMonitor.monitor();
	}

	@MethodBrief("判断处理是否成功")
	private boolean isSucceed(String result) {
		MethodCallMonitor.monitor();
		return "ok".equals(result);
	}

	@MethodBrief("处理请求")
	private String doBusiness(String[] args) {
		// do something
		MethodCallMonitor.monitor();
		return "ok";
	}

	@MethodBrief("校验请求参数")
	private void checkParameters(String[] args) {
		MethodCallMonitor.monitor();
	}

}
