	/**
 * 
 */
package com.introspect.test;

/**
 * @author    Ҷ����
 * @time      2015��3��3������7:31:34
 * @version   com.introspect.testTestStudent2 V1.0
 */
import java.util.*;
import java.beans.*;
import java.lang.reflect.*;
public class TestStudent2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Map<String,Object> studentInfo = new HashMap<String,Object>();
		studentInfo.put("id", "001");
		studentInfo.put("name","wangyin");
		studentInfo.put("age",3);
		
		//����student����
		Student s1 = new Student();
		
		System.out.println("��װ����֮ǰ"+s1);
		
		//packagingStudent(s1,studentInfo);
		try
		{
			//packagingObject(s1,studentInfo);
			packagingStudentUsingInstropector(s1,studentInfo);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("��װ����֮��"+s1);
	}
	
	public static void packagingStudent(Student stu,Map<String,Object> map)
	{
		/*stu.setId((String)map.get("Id"));
		stu.setName((String)map.get("Name"));
		stu.setAge((int)map.get("Age"));*/
		
		//���� �ֶ� ʹ�ô�д��Сд�����ԣ��� ����ʹ��BeansInfo������Сд��
		//�ֶ�
		stu.setId((String)map.get("id"));
		stu.setName((String)map.get("name"));
		stu.setAge((int)map.get("age"));
	}
	
	//ʹ�÷�������ݷ�װ��������
	public static void packagingObject(Object obj,Map<String,Object> map)
	throws Exception
	{
		Class clazz = obj.getClass();
		
		//����1
		/*Field[] fields = clazz.getDeclaredFields();
		for(Field temp :fields)
		{
			String fieldName = temp.getName();
			Object value = map.get(fieldName);
			temp.setAccessible(true);
			temp.set(obj, value);
		}*/
		//����2
		Field[] fields = clazz.getDeclaredFields();
		for(Field temp :fields)
		{
			String fieldName = temp.getName();
			//��ӵõ�setter�����Ĳ�������(�ֶο��ԣ� �������Ա���ô�����ԣ�
			Class paramType  = temp.getType();
			String methodName = "set"+fieldName.substring(0, 1).toUpperCase()+
					fieldName.substring(1).toLowerCase();
			Method methodSet = clazz.getMethod(methodName, paramType);
			methodSet.invoke(obj, map.get(fieldName));

		}
	}
	
	//java��ʡ�� �˷�����ֶε�ƴ��
	public static void packagingStudentUsingInstropector(Object obj,
			Map<String,Object> map) throws Exception
	{
		
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		
		//��������ֶε�����ֵ
		PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor temp:pro)
		{
			String fieldName = temp.getName();
			//ͨ�����Ե�javabeans������ö�Ӧ��set����
			//Class c1 = temp.getPropertyType();
			//temp.getPropertyType()
			
			//age  ���Խ��   fieldName��age�� Ҳ������߿϶����������
			System.out.println(fieldName);
			Method methodSet = temp.getWriteMethod();
			if(methodSet !=null) //���ȱ��if�ж�  �ͻᱨ������
					//��Ϊ���е��඼���� class�ֶΣ� �������״���ĵ㣡
					//һ����ע�����
			{
				methodSet.invoke(obj, map.get(fieldName));
			}
		}
	}

}