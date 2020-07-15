/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author bumte
 */
public class daoSanPham {

    private static Connection conn;

    public static List<dtoSanPham> SanPham() {
        //    kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return null;
        }
//    -----------------
//    Làm việc
        List<dtoSanPham> sp = new ArrayList<>();
        String sql = "select * from sanphamchitiet where trangthaisanpham = 1";
        ResultSet rs = dbconnection.getData(sql);
        try {
            while (rs.next()) {
//                dtoThuocTinhSanPham tt = new dtoThuocTinhSanPham(
//                        rs.getString("thuoctinhkhac"),
//                        rs.getString("quycach"),
//                        rs.getDouble("trongluong"),
//                        rs.getDouble("doday"),
//                        rs.getDouble("dai"),
//                        rs.getInt("idthuoctinh"));
////                System.out.println(tt.getDai());
//                dtoSanPham s = new dtoSanPham(
//                        rs.getString("idsanpham"),
//                        rs.getString("tenncc"),
//                        rs.getString("tendanhmuc"),
//                        rs.getString("tensp"),
//                        rs.getString("donvitinh"),
//                        rs.getDouble("giasi"),
//                        rs.getDouble("giale"),
//                        rs.getDouble("soluong"),
//                        rs.getInt("idthuoctinh"),
//                        tt);
////                System.out.println(s.getTenSanPham());
                sp.add(GhiThongTinSPVaoDto(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

//    ------------- 
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return sp;
    }

    public static dtoSanPham GetSanPhamByID(String idsp) {
        dtoSanPham sp = null;
        String cauLenh = "select * from sanphamchitiet where IDSanPham = '" + idsp + "'";
        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            if (rs.next()) {
                sp = GhiThongTinSPVaoDto(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sp;
    }

    public static List<dtoSanPham> SanPhamBy(String where) {
        //    kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return null;
        }
//    -----------------
//    Làm việc
        List<dtoSanPham> sp = new ArrayList<>();
        String sql = "select * from sanphamchitiet where trangthaisanpham = 1  and ( " + where + " )";
        ResultSet rs = dbconnection.getData(sql);

        try {
            while (rs.next()) {
                sp.add(GhiThongTinSPVaoDto(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

//    ------------- 
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return sp;
    }

    public static dtoSanPham GhiThongTinSPVaoDto(ResultSet rs) {
        dtoSanPham s = null;
        try {
            dtoThuocTinhSanPham tt = new dtoThuocTinhSanPham(
                    rs.getString("thuoctinhkhac"),
                    rs.getString("quycach"),
                    rs.getDouble("trongluong"),
                    rs.getDouble("doday"),
                    rs.getDouble("dai"),
                    rs.getInt("idthuoctinh"));
            s = new dtoSanPham(
                    rs.getString("idsanpham"),
                    rs.getString("tenncc"),
                    rs.getString("tendanhmuc"),
                    rs.getString("tensp"),
                    rs.getString("donvitinh"),
                    rs.getDouble("giasi"),
                    rs.getDouble("giale"),
                    rs.getDouble("soluong"),
                    rs.getInt("idthuoctinh"),
                    tt);
        } catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static boolean daoThemSanPham(dtoSanPham sp) {
        PreparedStatement themThuocTinhSP = null;
        PreparedStatement themSanPham = null;
        try {
            conn = dbconnection.connect();
            conn.setAutoCommit(false);
//            sql Thêm sản phâm:
            String sqlSP = "INSERT INTO SanPham("
                    + " IDSanPham, tenncc, IDThuocTinh, tendanhmuc, TenSP,SoLuong, DonViTinh, giale, giasi, trangthaisanpham)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?)";

            String sqlThuocTinh = "INSERT INTO ThuocTinhSanPham ( Dai, DoDay,  IDThuocTinh,  QuyCach, thuoctinhkhac, TrongLuong )"
                    + "VALUES( ?, ?,?,?, ?,?);";
            themSanPham = conn.prepareStatement(sqlSP);
            themThuocTinhSP = conn.prepareStatement(sqlThuocTinh);

            themSanPham.setString(1, sp.getIdSanPham());
            themSanPham.setString(2, sp.getTenNhaCungCap());
            themSanPham.setInt(3, sp.getIdThuocTinh());
            themSanPham.setString(4, sp.getTenDanhMuc());
            themSanPham.setString(5, sp.getTenSanPham());
            themSanPham.setDouble(6, sp.getSoLuong());
            themSanPham.setString(7, sp.getDonViTinh());
            themSanPham.setDouble(8, sp.getGiaLe());
            themSanPham.setDouble(9, sp.getGiaSi());
            themSanPham.setInt(10, 1);

            dtoThuocTinhSanPham tt = sp.getTtSanPham();

            themThuocTinhSP.setDouble(1, tt.getDai());
            themThuocTinhSP.setDouble(2, tt.getDoDay());
            themThuocTinhSP.setInt(3, sp.getIdThuocTinh());
            themThuocTinhSP.setString(4, tt.getQuyCach());
            themThuocTinhSP.setString(5, tt.getThuocTinhKhac());
            themThuocTinhSP.setDouble(6, tt.getTrongLuong());
            System.out.println(((PreparedStatement) themThuocTinhSP));
            System.out.println(((PreparedStatement) themSanPham));
            themSanPham.execute();
            themThuocTinhSP.execute();
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rolled back.");
                } catch (SQLException exrb) {
                    exrb.printStackTrace();
                }
            }
        } finally {
            try {
                if (themSanPham != null) {
                    themSanPham.close();
                }

                if (themThuocTinhSP != null) {
                    themThuocTinhSP.close();
                }

            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
        return false;
    }

    public static boolean UpdateSP(dtoSanPham sp) {
        PreparedStatement suaSanPham = null;
        PreparedStatement suaThuocTinh = null;

        try {
            conn = dbconnection.connect();
            conn.setAutoCommit(false);

            String sqlSP = "UPDATE SanPham SET  DonViTinh = ?, giale = ?, giasi = ?, IDSanPham = ?, IDThuocTinh = ?, SoLuong = ?,"
                    + "    tendanhmuc = ?, tenncc = ?, TenSP = ?, trangthaisanpham = ?"
                    + "  WHERE IDSanPham = ?";
            String sqlThuoTinh = "UPDATE ThuocTinhSanPham SET Dai = ?, DoDay = ?, IDThuocTinh = ?, QuyCach = ?, thuoctinhkhac = ?,TrongLuong = ? "
                    + " WHERE IDThuocTinh = ?";

            suaSanPham = conn.prepareStatement(sqlSP);
            suaThuocTinh = conn.prepareStatement(sqlThuoTinh);

            suaSanPham.setString(1, sp.getDonViTinh());
            suaSanPham.setDouble(2, sp.getGiaLe());
            suaSanPham.setDouble(3, sp.getGiaSi());
            suaSanPham.setString(4, sp.getIdSanPham());
            suaSanPham.setInt(5, sp.getIdThuocTinh());
            suaSanPham.setDouble(6, sp.getSoLuong());
            suaSanPham.setString(7, sp.getTenDanhMuc());
            suaSanPham.setString(8, sp.getTenNhaCungCap());
            suaSanPham.setString(9, sp.getTenSanPham());
            suaSanPham.setInt(10, 1);
            suaSanPham.setString(11, sp.getIdSanPham());
            dtoThuocTinhSanPham tt = sp.getTtSanPham();

            suaThuocTinh.setDouble(1, tt.getDai());
            suaThuocTinh.setDouble(2, tt.getDoDay());
            suaThuocTinh.setInt(3, sp.getIdThuocTinh());
            suaThuocTinh.setString(4, tt.getQuyCach());
            suaThuocTinh.setString(5, tt.getThuocTinhKhac());
            suaThuocTinh.setDouble(6, tt.getTrongLuong());
            suaThuocTinh.setInt(7, sp.getIdThuocTinh());
//            System.out.println(((PreparedStatement) suaSanPham));
//            System.out.println(((PreparedStatement) suaThuocTinh));
            suaSanPham.execute();
            suaThuocTinh.execute();

            conn.commit();
            conn.setAutoCommit(true);
            dbconnection.disconnect();
            return true;
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        dbconnection.disconnect();
        return false;
    }

    public static boolean DeleteSanPham(dtoSanPham sp) {
        int idThuocTinh = sp.getIdThuocTinh();
        String idSP = sp.getIdSanPham();

        String sql = "update SanPham set trangthaisanpham = 0 where idSanPham = '" + idSP + "'";

        conn = dbconnection.connect();
        if (dbconnection.ExecuteData(sql) > 0) {
            return true;
        }
        return false;
    }

    public static boolean UpDateDonViTinh(String set) {
        String cauLenh = "update donvitinh set " + set + " ";
        System.out.println(cauLenh);
        dbconnection.connect();
        if (dbconnection.ExecuteData(cauLenh) > 0) {
            return true;
        }
        return false;
    }

    public static double GetSLSP(String id) {
        double sl = 0;
        String cauLenh = "SELECT `SoLuong` FROM `SanPham` WHERE IDSanPham = '" + id + "'";
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            sl = -999;
        }

        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            if (rs.next()) {
                sl = rs.getDouble("SoLuong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sl;
    }

    public static boolean UpdateSL(String id, double soLuong) {

        String cauLenh = "UPDATE `SanPham` SET `SoLuong`=" + soLuong + " WHERE IDSanPham = '" + id + "'";
        if (conn == null) {
            conn = dbconnection.connect();
        }

        int result = dbconnection.ExecuteData(cauLenh);
        if (result > 0) {
            return true;
        }
        return false;
    }
}
//System.out.println(((PreparedStatement) themSanPham));
