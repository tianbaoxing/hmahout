package org.hmahout.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，在类的属性前加上此注解，则该属性就不会被持久化
 * @author fubaolong
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotPersistent {

	//外键关联字段 
	//若不为主键时，则作为被关联表的查询字段
	public String fk() default "";
	
	// 本对象中的关联字段
	public String key() default "";
}
