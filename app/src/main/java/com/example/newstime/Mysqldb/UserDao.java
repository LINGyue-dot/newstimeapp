package com.example.newstime.Mysqldb;


/*
* 用户数据库操作类
* 实现CURD
*  */

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DbOpenHelper {

    //查询所有用户信息   R
    public List<Userinfo> getAllUserList(){
        List<Userinfo> list =new ArrayList<>();
        try {
            getConnection();// 取得连接信息

            String sql="select * from NewsUser";
            pStmt =conn.prepareStatement(sql);
            rs=pStmt.executeQuery();

            while(rs.next()){
                Userinfo item =new Userinfo();
                item.setId(rs.getString("id"));
                item.setUpass(rs.getString("password"));
                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return list;
    }


    /**
     *   按用户名和密码查询用户信息 R
     * @param id
     * @param upass
     * @return  Userinfo 实例
     */
    public   Userinfo getUserByIdAndUpass(String id,String upass)
    {
        Userinfo item =null;
        try {
            getConnection();// 取得连接信息

            String sql="select * from NewsUser where id=? and password=?";
            pStmt =conn.prepareStatement(sql);
            pStmt.setString(1,id);
            pStmt.setString(2,upass);
            rs=pStmt.executeQuery();
            if (rs.next()){
                item =new Userinfo();
                item.setId(id);
                item.setUpass(upass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return item;
    }


    /**
     *  添加用户信息 C
     * @param item  实例
     * @return  int  影响的行号
     */
    public int addUser(Userinfo item){
        int iRow =0;

        try {
            getConnection();// 取得连接信息
            String sql="insert into NewsUser (id,password) " +
                    "values(?,?)";
            pStmt =conn.prepareStatement(sql);
            pStmt.setString(1, item.getId());
            pStmt.setString(2, item.getUpass());


//            String sql = "INSERT INTO NewsUser VALUES ( '" +item.getId()+
//                    "', '" +item.getUpass()+
//                    "')";
//            //4.获取用于向数据库发送sql语句的ps
//            PreparedStatement ps=conn.prepareStatement(sql);
//            ps.execute(sql);



            iRow=pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     * 修改用户信息 U
     * @param item  实例
     * @return  int  影响的行号
     */
    public   int  editUser(Userinfo item){
        int iRow =0;
        try {
            getConnection();// 取得连接信息

            if(conn==null){
                System.out.println("UserDao       112");
            }


            String sql="update NewsUser set password=?where id=?";
            pStmt =conn.prepareStatement(sql);
            pStmt.setString(1,item.getUpass());
            pStmt.setString(2,item.getId());

            iRow=pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }

    /**
     *  根据用户id删除
     * @param id
     * @return int  影响的行号
     */
    public  int delUser(int id){
        int iRow= 0;
        try {
            getConnection();// 取得连接信息

            String sql="delete from NewsUser where id=?";
            pStmt =conn.prepareStatement(sql);
            pStmt.setInt(1,id);
            iRow=pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return iRow;
    }



}
