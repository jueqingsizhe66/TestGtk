/**
 * 
 */
package com.jdbc.test;
import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;
/**
 * @author    叶昭良
 * @time      2015年2月19日下午1:46:58
 * @version   com.jdbc.testCachedRowSetTest2 V1.0
 *               第二种方式利用CachedRowSetImpl
 *               
 *               很多情况我们使用ResultSet 就会因为这样那样的问题，rs被关闭或数据链接被关闭，
 *               导致ResultSet不能使用。其实这个问题我们可以用CachedRowSetImpl来解决。这是
 *               一个结果的缓存类，保存在其中的数据不会随着数据库和ResultSet的
 *               连接的关闭而丢失，可以传递。
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

