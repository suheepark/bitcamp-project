//객체의 이름을 보관하여 컴파일러나 JVM에게 전달
package bitcamp.java89.ems.server.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

public @interface Component {
  String value() default "";
}
