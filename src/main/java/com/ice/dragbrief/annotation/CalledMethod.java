package com.ice.dragbrief.annotation;

public @interface CalledMethod {

	public abstract String value();

	public boolean inIfCase() default false;
}
