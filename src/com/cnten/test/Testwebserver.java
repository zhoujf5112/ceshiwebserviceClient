/**
 * @author Administrator
 *
 */
package com.cnten.test;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class Testwebserver{
	
	public static void main(String[] args) {
		try {
			EndpointReference targetEPR  = new EndpointReference("http://localhost:8080/ceshiwebservice/services/Testservice"); 
			RPCServiceClient  sender = new RPCServiceClient();
			Options  options = sender.getOptions();
			options.setTimeOutInMilliSeconds(2*20000L);
			options.setTo(targetEPR);
			 /**
             * 参数:
             * 1：在网页上执行 wsdl后xs:schema标签的targetNamespace路径
             * <xs:schema  targetNamespace="http://test.axiswevservice.com">
             * 2：<xs:element name="test"> ======这个标签中name的值
             * 
             */
			QName qName = new QName("http://test.cnten.com", "demo");
			String str = "客户端调用成功";
			int a = 0;
			Object[] param = new Object[]{str,a};
			Class<?>[] types = new Class[]{String.class};  //这是针对返值类型的  
			 /** 
             * RPCServiceClient类的invokeBlocking方法调用了WebService中的方法。 
             * invokeBlocking方法有三个参数 
             * 第一个参数的类型是QName对象，表示要调用的方法名； 
             * 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]； 
             * 第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。 
             *  
             * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。 
             */  
			Object[] invokeBlocking = sender.invokeBlocking(qName, param, types);
			System.out.println(invokeBlocking[0]);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}
}