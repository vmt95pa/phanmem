/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.ThongBao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bumte
 */
public class getList {

    public static Connection conn;

    public static List<String> listKhoangCach() {

        List<String> dvt = new ArrayList<>();
        String sql = "select dairong from donvitinh where id = 0";
        dvt = listDVT(sql);

        return dvt;
    }

    public static List<String> listTrongLuong() {

        List<String> dvt = new ArrayList<>();
        String sql = "select trongluong from donvitinh where id = 0";
        dvt = listDVT(sql);

        return dvt;
    }

    public static List<String> listLoai() {

        List<String> dvt = new ArrayList<>();
        String sql = "select loai from donvitinh where id = 0";
        dvt = listDVT(sql);

        return dvt;
    }

    public static List<String> listNCC() {
        List<String> list = new ArrayList<>();
        String sql = "select tenncc from SanPham where trangthaisanpham = 1 group by tenncc ";
        list = listStringGroup(sql);
        return list;
    }
 public static List<String> listDanhMucSP() {
        List<String> list = new ArrayList<>();
        String sql = "select tendanhmuc from SanPham where trangthaisanpham = 1 group by tendanhmuc ";
        list = listStringGroup(sql);
        return list;
    }
    public static List<String> listStringGroup(String sql) {
        if ( (conn = dbconnection.connect()) == null) {
            ThongBao.ThongBao("Lỗi kết nối cơ sở dữ liệu! getList #66", "Thông báo");
        }
        List<String> list = new ArrayList<>();
        ResultSet rs = dbconnection.getData(sql);
        try {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(getList.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return list;
    }

    public static List<String> listDVT(String cauLenh) {
        if ( (conn = dbconnection.connect()) == null) {
            ThongBao.ThongBao("Lỗi kết nối cơ sở dữ liệu! getList #81", "Thông báo");
        }
        List<String> dvt = new ArrayList<>();
        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            if (rs.next()) {
                String[] r = rs.getString(1).split(";");
                for (int i = 0; i < r.length; i++) {
                    dvt.add(r[i]);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(getList.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        return dvt;
    }
}
