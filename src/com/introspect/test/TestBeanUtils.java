/**
 * org.apache.commons.collections.FastHashMap ����û���������ʱʧ����
 */
package com.introspect.test;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author    Ҷ����
 * @time      2015��3��3������8:20:18
 * @version   com.introspect.testTestBeanUtils V1.0
 */
public class TestBeanUtils
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		 Student employee=new Student();
         try {
        	
             String firstName = (String)
               PropertyUtils.getSimpleProperty(employee, "Id");
             String lastName = (String)
               PropertyUtils.getSimpleProperty(employee, "Age");
             
             PropertyUtils.setSimpleProperty(employee, "Id", "001");
             PropertyUtils.setSimpleProperty(employee, "lastName", 233);
        /*	 PropertyUtils.setSimpleProperty(userInfo, "Id", "0001");
            
            System.out.println("set userName:"+userInfo.getId());
            
            System.out.println("get userName:"+PropertyUtils.getSimpleProperty(userInfo, "Id"));
            
            PropertyUtils.setSimpleProperty(userInfo, "Age", 18);
            System.out.println("set age:"+userInfo.getAge());
            
            System.out.println("get age:"+PropertyUtils.getSimpleProperty(userInfo, "Age"));
             
            System.out.println("get userName type:"+PropertyUtils.getSimpleProperty(userInfo, "Id").getClass().getName());
            System.out.println("get age type:"+PropertyUtils.getSimpleProperty(userInfo, "Age").getClass().getName());
            
            PropertyUtils.setSimpleProperty(userInfo, "Age", 8);
            System.out.println(PropertyUtils.getSimpleProperty(userInfo, "Age"));
            
            System.out.println(PropertyUtils.getSimpleProperty(userInfo, "Age").getClass().getName());
                  
            PropertyUtils.setSimpleProperty(userInfo, "Age", "8"); */  
        } 
         catch (IllegalAccessException e) {
            e.printStackTrace();
        } 
         catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
	}

}