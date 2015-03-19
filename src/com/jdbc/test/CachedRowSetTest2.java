/**
 * 
 */
package com.jdbc.test;
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;
/**
 * @author    Ҷ����
 * @time      2015��2��19������1:46:58
 * @version   com.jdbc.testCachedRowSetTest2 V1.0
 *               �ڶ��ַ�ʽ����CachedRowSetImpl
 *               
 *               �ܶ��������ʹ��ResultSet �ͻ���Ϊ�������������⣬rs���رջ��������ӱ��رգ�
 *               ����ResultSet����ʹ�á���ʵ����������ǿ�����CachedRowSetImpl�����������
 *               һ������Ļ����࣬���������е����ݲ����������ݿ��ResultSet��
 *               ���ӵĹرն���ʧ�����Դ��ݡ�
 */
public class CachedRowSetTest2
{

	/**
	 * @param args
	 */

    private static Connection con;
    /*private static String user = "root";
    private static String password = "root";
    private static  String className = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/study";*/
    public static void main(String[] args) {
        try {
            /*Class.forName(className);
            con = DriverManager.getConnection(url, user, password);*/
        	con = JDBCUtils.createConnection();
            String sql="select * from t_users";
            PreparedStatement pstm=con.prepareStatement(sql);
            pstm.execute();
            ResultSet rs=pstm.getResultSet();
            CachedRowSetImpl rowset=new CachedRowSetImpl();
            rowset.populate(rs);
            rs.close();
            pstm.close();
            con.close();
            while (rowset.next()) {
                System.out.println("id:"+rowset.getString("Id")+rowset.getString("userName"));
                 
            }
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
