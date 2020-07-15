/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.dtoNhaCungCap;
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
public class daoNhaCungCap {

    private static Connection conn;

    public static List<dtoNhaCungCap> getListNCC(String where) {
        //        kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return null;
        }
//    -----------------
        List<dtoNhaCungCap> NhaCungCap = new ArrayList<>();
        String cauLenh = "SELECT * FROM `NhaCungCap` " + where + "";

        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            while (rs.next()) {
                dtoNhaCungCap ncc = SetDTONhaCungCap(rs);

                NhaCungCap.add(ncc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(daoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NhaCungCap;
    }

    public static dtoNhaCungCap SetDTONhaCungCap(ResultSet rs) {
        dtoNhaCungCap ncc = null;
        try {
            ncc = new dtoNhaCungCap(rs.getString("IDNhaCungCap"),
                    rs.getString("DiaChi"),
                    rs.getString("SDT"),
                    rs.getString("TenNhaCungCap"),
                    rs.getString("KhuVuc"),
                    rs.getString("thongtinthem"),
                    rs.getTimestamp("ngaythem"));

        } catch (SQLException ex) {
            Logger.getLogger(daoNhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ncc;
    }
}
