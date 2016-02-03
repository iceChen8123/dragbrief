package com.ice.dragbrief.override;

import com.ice.dragbrief.annotation.MethodBrief;
import com.ice.dragbrief.annotation.NeedScanBriefClass;

//@NeedScanBriefClass
public class Child1Service extends BaseService {

	@MethodBrief("子类1:静态方法(覆盖)")
	public static void staticMethodInBaseForOverride() {

	}

	@MethodBrief("子类1:实例方法(覆盖)")
	public void simpleMethodInBaseForOverride() {

	}

	@Override
	public void abstractMethod() {

	}

}
