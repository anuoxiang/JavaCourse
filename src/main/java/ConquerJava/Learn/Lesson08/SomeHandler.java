package ConquerJava.Learn.Lesson08;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SomeHandler implements InvocationHandler {

	public Object createStudentProxy(Object stu) {
		
	}
	
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) 
			throws Throwable {
		// TODO Auto-generated method stub
		Object result = arg1.invoke(arg0, arg2);
		return result;
	}

}
