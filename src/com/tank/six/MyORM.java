/**
 *  ���� Student
 *  mysql��   Student
 *  
 *  �� ����Ӧ��  ͨ�����󴫵ݽ����飬��������ʡ���ƣ�������е��������ԣ�
 *   ��������е�ֵ��Ȼ����ϳ�Ϊһ��sql��䣬��ִ��JDBCUtils ������
 *   ����ɾ���޸ġ���Ȳ���
 *   
 *   Ŀ�� ������ --mysql�� ����һһ��Ӧ���ҿ��Խ��д���
 *   �򵥵�MyOrm.insert(
 *              delete
 *              select
 *              update ��
 *         
 *    Լ����������������ΪId������int���ͣ��Զ�����
 *             �ֶ��������������һ��   Ҳ����������Ժͱ����ֶα���һ��
 *             �����ֺ��������һ��
 */

package com.tank.six;
import com.jdbc.test.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;  //��Ҫ�洢�ֶε� list  ͬʱ��Ҫ�洢ֵ��list 
                     // �����װ��һ��mysql���
/**
 * @author    Ҷ����
 * @time      2015��3��4������8:57:46
 * @version   com.introspect.testMyORM V1.0
 */
public class MyORM
{

	/**
	 * sql = insert into Person(fieldName) values(?,?..)
	 *         1                 2             3   4
	 *  JDBCUTils.executeUpdate(sql,propValue)
	 * @param args
	 */
	public static void insert(Object obj) throws SQLException
	{
		Class clazz = obj.getClass();
		BeanInfo beanInfo = null;
		beanInfo = getBeanInfo(clazz);
		
		//�������ݿ��
		String className = clazz.getSimpleName(); 
		
		PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
		List<String> listFieldName = new ArrayList<String>();
		for(PropertyDescriptor temp:pro)
		{
			String propName = temp.getName();
			if(!propName.equals("id") && !propName.equals("class")) 
			{
				//�۳� id��class�������ڲ��ֶ�
				listFieldName.add(propName);
			}
		}
		
		StringBuilder sbSQL = new StringBuilder();
		
		//��һ�� ƴ��  insert
		sbSQL.append("insert into ").append(className);

		//�ڶ��� ƴ��  �ֶ�
		String fieldNames = listFieldName.toString();
		sbSQL.append(fieldNames.replace('[', '(').replace(']', ')'));
	
		//������  ƴ��  ֵ
		sbSQL.append(" values");
		
		//���Ĳ�  ƴ��  �����ʺ�
		char[] paramMarkArray  = new char[listFieldName.size()];
		for(int i = 0 ; i < listFieldName.size() ; i++)
		{
			paramMarkArray[i] = '?';
		}
		sbSQL.append(Arrays.toString(paramMarkArray).replace('[', '(').replace
				(']', ')'));
		
		//���һ��    ����JDBCUtils�� executeUpdate
		List<Object> paramValues = new ArrayList<Object>();
		for(String propName : listFieldName)
		{
			PropertyDescriptor propDesc = findPropertyDescriptor(propName, pro);
			Object propValue =null;
			//obj���������߱����Ķ���
			propValue = invoke(propDesc, obj);
			paramValues.add(propValue);
		}
		//���չ���  toString  toArray��ת��
		JDBCUtils.executeUpdate(sbSQL.toString(), paramValues.toArray());
		
	}
	/**
	 * 
	 * @param clazz
	 * @return  ����һ��beanInfo����
	 */
	private static BeanInfo getBeanInfo(Class clazz)
	{
		BeanInfo beanInfo = null;
		try
		{
			beanInfo = Introspector.getBeanInfo(clazz);
		}catch(IntrospectionException e)
		{
			throw new RuntimeException("��ʡ������"+e.getMessage());
		}
		return beanInfo;
	}
	/**
	 *  ��propDesc��������ΪpropName��PropertyDescriptor
	 * @param propName
	 * @param prop
	 * @return
	 */
	private static PropertyDescriptor findPropertyDescriptor(String propName,
			PropertyDescriptor[] prop)
	{
		for(PropertyDescriptor temp : prop)
		{
			if(temp.getName().equals(propName))
			{
				return temp;
			}
		}
		return null;
	}
	/**
	 *  ִ��ĳ���ֶεĶ�����  ������ propValue
	 * @param propDesc
	 * @param obj
	 * @return
	 */
	private static Object invoke(PropertyDescriptor propDesc,Object obj)
	{
		Object propValue = null;

		try
		{
			propValue = propDesc.getReadMethod().invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("��ȡ"+propDesc.getName()+"����");
		}
		return propValue; 
	}
	//------------------------insert ���� ����----------------
	/**
	 * delete from className where id = ?
	 * 
	 * 
	 *   ɾ��clazz��Ӧ���е��ֶ�idΪid��ֵ   delete(Person.class,5);
	 * @param clazz
	 * @param id
	 */
	public static void delete(Class clazz, int id) throws SQLException
	{
		BeanInfo beanInfo = null;
		beanInfo = getBeanInfo(clazz);
		
		//�������ݿ��
		String className = clazz.getSimpleName(); 
		
		PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
		List<String> listFieldName = new ArrayList<String>();
		
		
		StringBuilder sbSQL = new StringBuilder();
		
		//��һ�� ƴ��  insert
		sbSQL.append("delete from ").append(className).append(" where id = ?");
		
		JDBCUtils.executeUpdate(sbSQL.toString(), id);
		
	}
	
	
	/**
	 * select * from Person where id = 2;
	 * 
	 *   ��ȡclazz��Ӧ����idΪ�ֶ�Ϊid�Ķ�Ӧ����   ������䵽������
	 *   Person p1 = (Person)select(Person.class,2)
	 *   p1.getName(), p1.getAge();
	 * @param clazz
	 * @param id
	 * @return
	 */
	//----------------------delete �������--------------------
	public static Object select(Class clazz,int id) throws SQLException
	{
		Object b1 = null;
		b1 = getInstance(clazz);
		
		BeanInfo beanInfo = null;
		beanInfo = getBeanInfo(clazz);
		
		//�������ݿ��
		String className = clazz.getSimpleName(); 
		
		PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
		List<String> listFieldName = new ArrayList<String>();
		for(PropertyDescriptor temp:pro)
		{
			String propName = temp.getName();
			if(!propName.equals("id") && !propName.equals("class")) 
			{
				//�۳� id��class�������ڲ��ֶ�
				listFieldName.add(propName);
			}
		}
		
		StringBuilder sbSQL = new StringBuilder();
		
		//��һ�� ƴ��  insert
		sbSQL.append("select * from ").append(className).append(" where id = ?");
		//��ȡ�� ResultSet
		ResultSet rs = JDBCUtils.executeQuery(sbSQL.toString(), id);
		if(!rs.next())
		{
			System.out.println("��ǰ�汾û��"+id+"����Ϣ");
//			return;
		}else
		{
			System.out.println("��ǰ�汾��"+id+"����Ϣ");
			for(String propName : listFieldName)
			{
				PropertyDescriptor propDesc = findPropertyDescriptor(propName, pro);
				//obj���������߱����Ķ���
				invoke(propDesc, b1,rs.getObject(propName));
				//invoke(propDesc, b1,rs.getString(propName));
			}
		}
		//����id�ֶ�
		PropertyDescriptor propDesc1 = findPropertyDescriptor("id", pro);
		invoke(propDesc1, b1,id);
		System.out.println("�ɹ�");
		return b1;
		
		//�ȷǷ���  �ٷ��͵�selectById
	}
	private static Object getInstance(Class clazz)
	{
		Object b1 = null;
		try
		{
			b1 = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b1;
	}
	//private static void invoke(PropertyDescriptor propDesc,Object obj,String value)
	
	private static void invoke(PropertyDescriptor propDesc,Object obj,Object value)
	{

		//������ ��Ϊ
		/*try
		{
			propDesc.getWriteMethod().invoke(obj,value);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("��ȡ"+propDesc.getName()+"����");
		} */
		try
		{
			Method methodSet= propDesc.getWriteMethod();
			if(methodSet !=null)
			{
				methodSet.invoke(obj,value);
			}
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("��ȡ"+propDesc.getName()+"����");
		} 
	}
	
	//------------------select ����------------------
	
	// update Person set age='' where id=4;
	
	/**
	 *   ����id= id�Ķ��� �� ��������Ϊ������ Ҳ�������� �������޸�update ���
	 * @param clazz
	 * @param newValue    �����������ֵ
	 * @param id          ����ĳ��id
	 * @throws SQLException
	 */
	public static void update(Class clazz,Object newValue,int id) throws SQLException
	{
		BeanInfo beanInfo = null;
		beanInfo = getBeanInfo(clazz);
		
		//�������ݿ��
		String className = clazz.getSimpleName(); 
		
		PropertyDescriptor[] pro = beanInfo.getPropertyDescriptors();
		List<String> listFieldName = new ArrayList<String>();
		for(PropertyDescriptor temp:pro)
		{
			String propName = temp.getName();
			if(!propName.equals("id") && !propName.equals("class")) 
			{
				//�۳� id��class�������ڲ��ֶ�
				listFieldName.add(propName);
			}
		}
		
		StringBuilder sbSQL = new StringBuilder();
		
		//��һ�� ƴ��  insert
		//�������� update �Ǹ��ַ���  ���� String temp = "age"
		sbSQL.append("update ").append(className).append(" set age = ").append(newValue).append(""
				+ " where id = ?");
		JDBCUtils.executeUpdate(sbSQL.toString(), id);
	}

}