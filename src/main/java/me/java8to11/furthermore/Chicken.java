package me.java8to11.furthermore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// @Target(ElementType.TYPE_PARAMETER)
@Target(ElementType.TYPE_USE) // type에도 사용 가능
@Repeatable(ChickenContainer.class) // 중복사용 가능하도록
public @interface Chicken {

    String value();
}
