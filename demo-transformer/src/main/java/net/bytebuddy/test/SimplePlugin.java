package net.bytebuddy.test;

import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;

public class SimplePlugin implements Plugin {
	@Override
	public boolean matches(TypeDescription target) {
		return target.getName().equals("foo.Bar");
	}

	@Override
	public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription) {
		if (typeDescription.getTypeName().equals("foo.Bar")) {
			builder = builder.defineField("qux", String.class, Visibility.PRIVATE)
                    .defineMethod("getQux", String.class, Visibility.PUBLIC)
					.intercept(FieldAccessor.ofField("qux"))
					.defineMethod("setQux", void.class, Visibility.PUBLIC)
					.withParameter(String.class)
					.intercept(FieldAccessor.ofField("qux"));
		}
		return builder;
	}
}