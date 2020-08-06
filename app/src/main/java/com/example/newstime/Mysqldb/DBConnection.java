package com.example.newstime.Mysqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnection {
    public static void link (){
        //要连接的数据库url,注意：此处连接的应该是服务器上的MySQl的地址
        String url = "jdbc:mysql://120.27.242.14:3306/news?useSSL=false";
        //连接数据库使用的用户名
        String userName = "cat";
        //连接的数据库时使用的密码
        String password = "123456";
        Connection connection=null;
        try {
            //1、加载驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("驱动加载成功！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            //2、获取与数据库的连接
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("连接数据库成功！！！");
            //3.sql语句
            String sql = "INSERT INTO NewsUser VALUES ( '1223', '1223')";
            //4.获取用于向数据库发送sql语句的ps
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.execute(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}
