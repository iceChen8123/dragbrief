package com.ice.dragbrief.override;

import com.ice.dragbrief.annotation.MethodBrief;
import com.ice.dragbrief.annotation.NeedScanBriefClass;

//@NeedScanBriefClass
public abstract class BaseService {

	@MethodBrief("父类:静态方法")
	public final static void staticMethodInBase() {

	}

	@MethodBrief("父类:实例方法")
	public final void simpleMethodInBase() {

	}

	@MethodBrief("父类:可被覆盖静态方法")
	public static void staticMethodInBaseForOverride() {

	}

	@MethodBrief("父类:可被覆盖实例方法")
	public void simpleMethodInBaseForOverride() {

	}

	@MethodBrief("父类:抽象方法")
	public abstract void abstractMethod();

}
