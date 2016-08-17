package org.winter.fromwork.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.bind.Binder;

import com.app.service.DtService;
import com.app.service.impl.DtServiceImpl;

public class JdkProxyFactory implements InvocationHandler {

	Object target;

	public Object getProxyInstance(Object target) {

		this.target = target;
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				target.getClass().getInterfaces(),
				this);

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		System.out.println("before");
		result = method.invoke(target, args);

		return result;
	}

	public static void main(String[] args) {

		DtServiceImpl impl = new DtServiceImpl();
		System.out.println(impl);
		JdkProxyFactory factory = new JdkProxyFactory();
		
         Object proxyInstance = factory.getProxyInstance(impl);
         System.out.println((proxyInstance instanceof DtService));
         //DtService proxyInstance = (DtService) factory.getProxyInstance(impl);

         
         System.out.println(proxyInstance);
	
	}

}
