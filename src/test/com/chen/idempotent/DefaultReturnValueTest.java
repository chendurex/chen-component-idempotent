package com.chen.idempotent;

import com.chen.component.idempotent.annotation.ChenIdempotent;
import com.chen.component.idempotent.key.DefaultReturnValue;

import java.lang.reflect.Method;

/**
 * @author chen
 * @date 2017/9/13 9:37
 */
public class DefaultReturnValueTest {
    public static void main(String[] args) {
        DefaultReturnValue returnValue = new DefaultReturnValue();
        try {
            Method m = DefaultReturnValueTest.class.getMethod("custom");
            ObjectTest ot = (ObjectTest) returnValue.value(m.getAnnotation(ChenIdempotent.class));
            System.out.println(ot.getTestA());

            Method m2 = DefaultReturnValueTest.class.getMethod("primitive2");
            System.out.println(returnValue.value(m2.getAnnotation(ChenIdempotent.class)));

            Method m3 = DefaultReturnValueTest.class.getMethod("primitive3");
            System.out.println(returnValue.value(m3.getAnnotation(ChenIdempotent.class)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @ChenIdempotent(returnType = ObjectTest.class, returnValue = "1")
    public void custom() {}

    @ChenIdempotent(returnType = String.class, returnValue = "1222")
    public void primitive2() {}

    @ChenIdempotent(returnType = String.class)
    public void primitive3() {}
}
