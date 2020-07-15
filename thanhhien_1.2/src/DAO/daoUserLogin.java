/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.dtoUserLogin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bumte
 */
public class daoUserLogin {

    private static Connection conn;

    public static dtoUserLogin getUserLogin(String userName) {
        dtoUserLogin user = null;
        String cauLenh = "select * from taikhoan where usern = '" + userName + "'";

        if (dbconnection.connect() != null) {
            ResultSet rs = dbconnection.getData(cauLenh);
            try {
                if (rs.next()) {
                    String usn, passw, hoTen, thongTin;
                    int quyen;
                    usn = rs.getString("usern");
                    passw = rs.getString("psw");
                    hoTen = rs.getString("hoten");
                    thongTin = rs.getString("thongtin");
                    quyen = rs.getInt("quyen");
                    user = new dtoUserLogin(usn, passw, hoTen, thongTin, quyen);
                }
            } catch (SQLException ex) {
                System.out.println("Lỗi lấy user!");
            }
        }

        return user;
    }

    public static boolean CheckPass(String pass) {
        if (conn == null) {
            conn = dbconnection.connect();
        }

        String cauLenh = "SELECT psw FROM `taikhoan` WHERE psw = '" + pass + "'";
        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
          
        }

        return false;
    }

}
