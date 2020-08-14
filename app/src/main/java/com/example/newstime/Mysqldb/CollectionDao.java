package com.example.newstime.Mysqldb;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CollectionDao extends DbOpenHelper {


    /*
     * 用户数据库操作类
     * 实现CURD
     *  */
    //查询所有收藏信息   R
    public List<UserCollection> getAllCollectionList() {
        List<UserCollection> list = new ArrayList<>();
        try {
            getConnection();// 取得连接信息

            String sql = "select * from Collection";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                UserCollection item = new UserCollection();
                item.setId(rs.getString("id"));
                item.setYiyun(rs.getString("yiyun"));
                item.setSrc(rs.getString("src"));
                list.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return list;
    }




    /**
     * 添加用户信息 C
     *
     * @param item 实例
     * @return int  影响的行号
     */
    public int addCollection(UserCollection item) {
        int iRow = 0;

        try {
            getConnection();// 取得连接信息
            String sql = "insert into Collection (id,yiyun,src) " +
                    "values(?,?,?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, item.getId());
            pStmt.setString(2, item.getYiyun());
            pStmt.setString(3, item.getSrc());


            iRow = pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return iRow;
    }


    /**
     * 根据用户id删除
     *
     * @param id 和 yiyun
     * @return int  影响的行号
     */
    public int delUserCollection(int id,String yiyun) {
        int iRow = 0;
        try {
            getConnection();// 取得连接信息

            String sql = "delete from Collection where id=? and yiyun=?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.setString(2, yiyun);
            iRow = pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return iRow;
    }


}
