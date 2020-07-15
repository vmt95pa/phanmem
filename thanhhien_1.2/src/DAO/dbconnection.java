/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import GUI.ThongBao;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bumte
 */
public class dbconnection {

//    private static String dbURL = "jdbc:mysql://127.0.0.1/thanhhien?useUnicode=true&characterEncoding=utf-8";
//    private static String user = "root";
//    private static String password = "";
    private static String dbURL = "jdbc:mysql://112.78.2.164/cob03206_thanhhien?useUnicode=true&characterEncoding=utf-8";
    private static String user = "cob03206_thanhhien";
    private static String password = "Admin123@";
    private static Statement statement = null;
    private static Connection conn = null;

    public static Connection connect() {
        boolean flag = true;
        do {
            try {
                if (conn == null || conn.isClosed()) {
                    conn = DriverManager.getConnection(dbURL, user, password);
                    System.out.println("Connected.");
                    return conn;
                }
                return conn;
            } catch (SQLException ex) {
                int reconn = ThongBao.ThongBaoLoai2("<html>Không có internet! vui lòng kiểm tra lại<br>Nhấn "
                        + "<strong style='font-size: 14px; color: #415DFF'>\"ok\"</strong> để kết nối lại<br>Nhấn <strong style='font-size: 14px; color: red'>\"Cancel\"</strong> để thoát!<html>", "Lỗi kết nối");
                if (reconn != JOptionPane.OK_OPTION) {
                    System.exit(0);

                }
                flag = false;
            }
        } while (!flag);
        return conn;
    }

    public static void disconnect() {
        try {
            conn.close();
            System.out.println("Closed.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ResultSet getData(String cauLenh) {
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(cauLenh);
            return rs;
        } catch (Exception e) {
            System.out.println("Lỗi try vấn câu lệnh select!");
            return null;
        }
    }

    public static int ExecuteData(String cauLenh) {
        try {
            statement = conn.createStatement();
            return statement.executeUpdate(cauLenh);
        } catch (Exception e) {
            System.out.println("Lỗi câu truy vấn!");
            return -1;
        }
    }

    public void saveOrder(int productId, Date orderDate, float amount, int reportMonth) {
        PreparedStatement orderStatement = null;
        PreparedStatement saleStatement = null;

        try {
            conn.commit();
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
                if (orderStatement != null) {
                    orderStatement.close();
                }

                if (saleStatement != null) {
                    saleStatement.close();
                }

                conn.setAutoCommit(true);
            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
    }

    public void saveOer(int productId, Date orderDate, float amount, int reportMonth) {
        PreparedStatement orderStatement = null;
        PreparedStatement saleStatement = null;

        try {

            conn.setAutoCommit(false);

            String sqlSaveOrder = "insert into orders (product_id, order_date, amount)";
            sqlSaveOrder += " values (?, ?, ?)";

            String sqlUpdateTotal = "update monthly_sales set total_amount = total_amount + ?";
            sqlUpdateTotal += " where product_id = ? and report_month = ?";

            orderStatement = conn.prepareStatement(sqlSaveOrder);
            saleStatement = conn.prepareStatement(sqlUpdateTotal);

            orderStatement.setInt(1, productId);
            orderStatement.setDate(2, orderDate);
            orderStatement.setFloat(3, amount);

            saleStatement.setFloat(1, amount);
            saleStatement.setInt(2, productId);
            saleStatement.setInt(3, reportMonth);

            orderStatement.executeUpdate();
            saleStatement.executeUpdate();

            conn.commit();

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
                if (orderStatement != null) {
                    orderStatement.close();
                }

                if (saleStatement != null) {
                    saleStatement.close();
                }

                conn.setAutoCommit(true);
            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
    }

    public static double CheckVersion() {
        double version = 1.0;
        String cauLenh = "SELECT MAX(version) as version FROM hethong";
        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            if (rs.next()) {
                version = rs.getDouble("version");

            }
        } catch (SQLException ex) {
            Logger.getLogger(dbconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return version;
    }
}
