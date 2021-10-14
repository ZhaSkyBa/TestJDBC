package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author 余涛
 * Description 功能说明
 * Time 2019/10/20 20:27
 */
public class JDBCUtils {
    public static String connectionUrl =  "jdbc:mysql://localhost:3306/web01" +
            "?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "246320";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //是用什么驱动连接数据库
            Connection con = DriverManager.getConnection(connectionUrl, user, password);
            return con;
        } catch (Exception e) {
            return null;
        }
    }


    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询全部
     */
    public static void selectAll() {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = JDBCUtils.getConnection();

            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println("id:" + resultSet.getInt("id") + ",\n"
                        + "用户名:" +  resultSet.getString("username") + ",\n"
                        + "密码:" + resultSet.getString("password") + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 条件查询
     * @param userName
     * @param password
     * @return
     */
    public static void selectByUserInfo(String userName, String password) {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = JDBCUtils.getConnection();

            //存在sql注入问题
//            statement = con.createStatement();
//            String sql = "select * from user where username = '"+userName+"' and password = '"+password+"' ";
//            resultSet = statement.executeQuery(sql);
//            return  resultSet.next();

            String sql = "select * from user where username = ? and password = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("id:" + resultSet.getInt("id") + ",\n"
                        + "用户名:" +  resultSet.getString("username") + ",\n"
                        + "密码:" + resultSet.getString("password") + "\n");
            }else {
                System.out.println("未查询到");
            }
            JDBCUtils.close(resultSet, preparedStatement, con);
        } catch (Exception e) {
        }
    }

    /**
     * 分页查询
     * @param pageNumber 查询第几页
     * @param pageCount  查询该页的数量
     */
    public static void selectByPage(int pageNumber, int pageCount) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = JDBCUtils.getConnection();

            String sql = "select * from user limit ? , ?";
            preparedStatement = con.prepareStatement(sql);
            //偏移量,从哪一行开始,所以是:(pageNumber-1) * pageCount  将行数转换成页数
            preparedStatement.setInt(1, (pageNumber-1) * pageCount);
            preparedStatement.setInt(2, pageCount);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id:" + resultSet.getInt("id") + ",\n"
                        + "用户名:" +  resultSet.getString("username") + ",\n"
                        + "密码:" + resultSet.getString("password") + "\n");
            }

            JDBCUtils.close(resultSet, preparedStatement, con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入
     * @param userName
     * @param password
     */
    public static void insert(String userName, String password) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = JDBCUtils.getConnection();

            String sql = "insert into user (username, password) value(?,?)";
            preparedStatement = con.prepareStatement(sql);
            //偏移量,从哪一行开始,所以是:(pageNumber-1) * pageCount  将行数转换成页数
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();  //更新

            selectByUserInfo(userName, password);

            JDBCUtils.close(resultSet, preparedStatement, con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     * @param id
     */
    public static void delete(int id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = JDBCUtils.getConnection();

            String sql = "delete from user where id = ?";
            preparedStatement = con.prepareStatement(sql);
            //偏移量,从哪一行开始,所以是:(pageNumber-1) * pageCount  将行数转换成页数
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();  //更新

            if (result > 0) {
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
            JDBCUtils.close(resultSet, preparedStatement, con);  //释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     * @param id
     * @param newPassword
     */
    public static void update(int id, String newPassword) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = JDBCUtils.getConnection();

            String sql = "update user set password = ? where id = ?";
            preparedStatement = con.prepareStatement(sql);
            //偏移量,从哪一行开始,所以是:(pageNumber-1) * pageCount  将行数转换成页数
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, id);
            int result = preparedStatement.executeUpdate();  //更新 result表示被影响的数据

            if (result > 0) {
                System.out.println("修改成功");
            }else{
                System.out.println("修改失败");
            }
            JDBCUtils.close(resultSet, preparedStatement, con);  //释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
