package com.example.newstime.Mysqldb;

/*
* MySQL数据库的辅助类
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbOpenHelper {

    private  static  final  String  CLS ="com.mysql.jdbc.Driver";
    private  static  final  String URL ="jdbc:mysql://120.27.242.14:3306/news?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
    private  static  final  String USER ="cat";
    private  static  final  String PWD ="123456";



    public static Connection conn;       //连接对象
    public  static Statement stmt ;     //命令集
    public  static PreparedStatement pStmt;   //预编译指令
    public  static ResultSet rs;       // 结果集




     //取得连接方法
    public  static  void getConnection(){
        try{
            Class.forName(CLS).newInstance(); //加载jdbc驱动
            conn = DriverManager.getConnection(URL,USER,PWD);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //关闭数据库‘
    public  static  void closeAll(){

        try {

            if (rs!=null){
                rs.close();
                rs=null;
            }
            if (stmt!=null){
                stmt.close();
                stmt=null;
            }
            if (pStmt!=null){
                pStmt.close();
                pStmt=null;
            }
            if (conn!=null){
                conn.close();
                conn=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
