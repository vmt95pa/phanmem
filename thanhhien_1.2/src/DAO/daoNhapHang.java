/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BLL.HoTro;
import BLL.nhaCungCapCtrl;
import BLL.sanPhamCtrl;
import DTO.dtoCTPhieuNhap;
import DTO.dtoNhaCungCap;
import DTO.dtoPhieuNhap;
import GUI.ThongBao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author bumte
 */
public class daoNhapHang {
    /*
     trạng thái phiếu nhập: 
     - 1 đang thao tác (đã lưu)
     - 2 đã nhập (Không thể chỉnh sửa hoặc thao tác thêm)
     - -1 phiếu nhập đã xóa (cập nhât trạng thái)
    
     */

    private static Connection conn;

    public static List<dtoPhieuNhap> GetListPhieuNhap(int trangThai) {

        ArrayList<dtoPhieuNhap> list = new ArrayList<>();
//    kết nối db
        if ((conn = dbconnection.connect()) == null) {

            System.out.println("Chưa kết nối được db");
            return null;
        }
//    -----------------
        String getPN = "select * from phieunhap where TrangThaiPhieuNhap >= " + trangThai + " ORDER BY phieunhap.NgayTaoPhieuNhap DESC limit 10";

        ResultSet rs = dbconnection.getData(getPN);
        dtoPhieuNhap pn = null;

        try {
            while (rs.next()) {//String ghiChuPhieuNhap, String idNhaCungCap, String idPhieuNhap, String tenPhieuNhap, Date nagyTaoPhieuNhap, double soTienTraTruoc, double tongTienPhieuNhap, double tongTienConLai, int trangThaiPhieuNhap, dtoCTPhieuNhap ctPhieuNhap
                String idPhieuNhap = rs.getString("IdPhieuNhap");
                String idNCC = rs.getString("IDNhaCungCap");
                String tenNCC = "Không có nhà cung cấp";
                dtoNhaCungCap nhaCungCap = null;
                List<dtoCTPhieuNhap> listCTPhieuNhap = GetListCTPhieuNhap(idPhieuNhap);
                ResultSet ncc = dbconnection.getData("select * from NhaCungCap where IDNhaCungCap = '" + idNCC + "'");
                if (ncc.next()) {
                    tenNCC = ncc.getString("TenNhaCungCap");
                    nhaCungCap = daoNhaCungCap.SetDTONhaCungCap(ncc);

                };

                pn = new dtoPhieuNhap(
                        rs.getString("GhiChuPhieuNhap"),
                        idNCC,
                        idPhieuNhap,
                        rs.getString("TenPhieuNhap"),
                        tenNCC,
                        rs.getTimestamp("NgayTaoPhieuNhap"),
                        rs.getDouble("SoTienTraTruoc"),
                        rs.getDouble("TongPhieuNhap"),
                        rs.getDouble("TongTienConLai"),
                        rs.getInt("TrangThaiPhieuNhap"),
                        nhaCungCap,
                        listCTPhieuNhap);
                list.add(pn);

            }
        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

//    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return list;
    }

    public static List<dtoCTPhieuNhap> GetListCTPhieuNhap(String id) {
        ArrayList<dtoCTPhieuNhap> list = new ArrayList<>();
//    kết nối db
//        if ((conn = dbconnection.connect()) == null) {
//            System.out.println("Chưa kết nối được db");
//            return null;
//        }
//    -----------------
        String getCTPN = "select * from chitietphieunhap where IDPhieuNhap = '" + id + "' ";

        ResultSet rs = dbconnection.getData(getCTPN);
        dtoCTPhieuNhap ctPhieuNhap = null;
        try {
            while (rs.next()) {
                ctPhieuNhap = new dtoCTPhieuNhap(
                        rs.getDouble("DonGiaNhapSP"),
                        rs.getDouble("SoLuongSP"),
                        rs.getString("DonViNhap"),
                        rs.getDouble("TongTienCTPhieuNhap"),
                        rs.getInt("IDCTPhieuNhap"),
                        rs.getString("IDPhieuNhap"),
                        rs.getString("IDSanPham"),
                        rs.getString("TenSP"));

                ctPhieuNhap.setSp(daoSanPham.GetSanPhamByID(ctPhieuNhap.getIdSanPham()));
                list.add(ctPhieuNhap);
            }

        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

//    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return list;
    }

    public static dtoPhieuNhap GetPhieuNhapById(String idPhieuNhap) {
        if (idPhieuNhap.trim().isEmpty()) {
            ThongBao.ThongBao("Lỗi chưa lấy được id phiếu nhập. daoNhapHang#118", "Thông báo!");
            return null;
        }

        dtoPhieuNhap pn = null;

//        kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return null;
        }
//    -----------------
        String getPN = "select * from phieunhap where TrangThaiPhieuNhap = 1 and (IDPhieuNhap = '" + idPhieuNhap + "')";
        ResultSet rs = dbconnection.getData(getPN);
        try {
            if (rs.next()) {//String ghiChuPhieuNhap, String idNhaCungCap, String idPhieuNhap, String tenPhieuNhap, Date nagyTaoPhieuNhap, double soTienTraTruoc, double tongTienPhieuNhap, double tongTienConLai, int trangThaiPhieuNhap, dtoCTPhieuNhap ctPhieuNhap

                String idNCC = rs.getString("IDNhaCungCap");
                String tenNCC = "Không có nhà cung cấp";
                List<dtoCTPhieuNhap> listCTPhieuNhap = GetListCTPhieuNhap(idPhieuNhap);
                ResultSet ncc = dbconnection.getData("select * from NhaCungCap where IDNhaCungCap = '" + idNCC + "'");
                if (ncc.next()) {
                    tenNCC = ncc.getString("TenNhaCungCap");
                };
                pn = new dtoPhieuNhap(
                        rs.getString("GhiChuPhieuNhap"),
                        idNCC,
                        idPhieuNhap,
                        rs.getString("TenPhieuNhap"),
                        rs.getTimestamp("NgayTaoPhieuNhap"),
                        rs.getDouble("SoTienTraTruoc"),
                        rs.getDouble("TongPhieuNhap"),
                        rs.getDouble("TongTienConLai"),
                        rs.getInt("TrangThaiPhieuNhap"),
                        tenNCC,
                        listCTPhieuNhap);

            }
        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

//    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return pn;
    }

    public static boolean TaoPhieuNhap(dtoPhieuNhap phieuNhap) {
        boolean flag = false;
//    kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return false;
        }
//    -----------------

        String cauLenh = "INSERT INTO `cob03206_thanhhien`.`phieunhap` ( "
                + "    GhiChuPhieuNhap, "
                + "    IDNhaCungCap, "
                + "    IDPhieuNhap, "
                + "    SoTienTraTruoc, "
                + "    TenPhieuNhap, "
                + "    TongPhieuNhap, "
                + "    TongTienConLai, "
                + "    TrangThaiPhieuNhap "
                + "  ) "
                + "VALUES "
                + "  ( "
                + "    N'" + phieuNhap.getGhiChuPhieuNhap() + "', "
                + "    '" + phieuNhap.getIdNhaCungCap() + "', "
                + "    '" + phieuNhap.getIdPhieuNhap() + "', "
                + "    " + phieuNhap.getSoTienTraTruoc() + ", "
                + "    N'" + phieuNhap.getTenPhieuNhap() + "', "
                + "    '" + phieuNhap.getTongTienPhieuNhap() + "', "
                + "    '" + phieuNhap.getTongTienConLai() + "', "
                + "    '" + phieuNhap.getTrangThaiPhieuNhap() + "' "
                + "  );";
        System.out.println(cauLenh);
        if (dbconnection.ExecuteData(cauLenh) > 0) {
            flag = true;
        }
////    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return flag;
    }

    public static boolean capNhatToanBoTrangThaiVe(int trangThai) {
        boolean flag = false;
//    kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return false;
        }
//    -----------------

        String cauLenh = "update phieunhap set TrangThaiPhieuNhap = " + trangThai + "";
        if (dbconnection.ExecuteData(cauLenh) > 0) {
            flag = true;
        }

//    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return flag;
    }

    public static boolean CapNhatPhieuNhap(dtoPhieuNhap pn) {
        PreparedStatement themCTPhieuNhap = null;
        PreparedStatement capNhatPhieuNhap = null;
        PreparedStatement capNhatCTPhieuNhap = null;

        try {
            conn = dbconnection.connect();
            conn.setAutoCommit(false);
//            sql Thêm sản phâm:
            String sqlctpn = "INSERT INTO `cob03206_thanhhien`.`chitietphieunhap` ( DonGiaNhapSP,  IDPhieuNhap, IDSanPham, SoLuongSP, TenSP, TongTienCTPhieuNhap , DonViNhap ) "
                    + "VALUES (  ?,  ?,  ?,  ?, ?,  ?, ? )";

            String sqlpn = "UPDATE `phieunhap` SET "
                    + "`TenPhieuNhap`=?,`IDNhaCungCap`=?,`TongPhieuNhap`=?,`SoTienTraTruoc`=?,`TongTienConLai`=?,`GhiChuPhieuNhap`=? ,`NgayTaoPhieuNhap`=?,`TrangThaiPhieuNhap`=? "
                    + "WHERE IDPhieuNhap = ?";

            String sqlUpDateCTPN = "UPDATE `chitietphieunhap` SET"
                    + " `IDCTPhieuNhap`= ?,`IDPhieuNhap`= ?,`IDSanPham`= ?,`TenSP`= ?,`SoLuongSP`= ?,`DonViNhap`= ?,`DonGiaNhapSP`=?,`TongTienCTPhieuNhap`=? "
                    + "WHERE IDCTPhieuNhap = ?";

            themCTPhieuNhap = conn.prepareStatement(sqlctpn);
            capNhatPhieuNhap = conn.prepareStatement(sqlpn);
            capNhatCTPhieuNhap = conn.prepareStatement(sqlUpDateCTPN);
            for (dtoCTPhieuNhap ct : pn.getCtPhieuNhap()) {
                System.out.println("id Chi tiết phiếu nhập: " + ct.getIdCTPhieuNhap());
                if (ct.getIdCTPhieuNhap() != 0) {
                    capNhatCTPhieuNhap.setInt(1, ct.getIdCTPhieuNhap());
                    capNhatCTPhieuNhap.setString(2, pn.getIdPhieuNhap());
                    capNhatCTPhieuNhap.setString(3, ct.getIdSanPham());
                    capNhatCTPhieuNhap.setDouble(5, ct.getSoLuong());
                    capNhatCTPhieuNhap.setString(4, ct.getTenSP());
                    capNhatCTPhieuNhap.setDouble(8, ct.getTongCTPhieuNhap());
                    capNhatCTPhieuNhap.setString(6, ct.getDonViNhap());
                    capNhatCTPhieuNhap.setDouble(7, ct.getDonGiaNhap());
                    capNhatCTPhieuNhap.setInt(9, ct.getIdCTPhieuNhap());

//                    System.out.println(((PreparedStatement) capNhatCTPhieuNhap));
                    capNhatCTPhieuNhap.execute();

                } else {
                    themCTPhieuNhap.setDouble(1, ct.getDonGiaNhap());
                    themCTPhieuNhap.setString(2, pn.getIdPhieuNhap());
                    themCTPhieuNhap.setString(3, ct.getIdSanPham());
                    themCTPhieuNhap.setDouble(4, ct.getSoLuong());
                    themCTPhieuNhap.setString(5, ct.getTenSP());
                    themCTPhieuNhap.setDouble(6, ct.getTongCTPhieuNhap());
                    themCTPhieuNhap.setString(7, ct.getDonViNhap());
//                    System.out.println(((PreparedStatement) themCTPhieuNhap));
                    themCTPhieuNhap.execute();
                }

            }

            capNhatPhieuNhap.setString(1, pn.getTenPhieuNhap());
            capNhatPhieuNhap.setString(2, pn.getIdNhaCungCap());
            capNhatPhieuNhap.setDouble(3, pn.getTongTienPhieuNhap());
            capNhatPhieuNhap.setDouble(4, pn.getSoTienTraTruoc());
            capNhatPhieuNhap.setDouble(5, pn.getTongTienConLai());
            capNhatPhieuNhap.setString(6, pn.getGhiChuPhieuNhap());
            capNhatPhieuNhap.setString(7, HoTro.dateTimeMySQL(pn.getNagyTaoPhieuNhap()));
            capNhatPhieuNhap.setInt(8, pn.getTrangThaiPhieuNhap());
            capNhatPhieuNhap.setString(9, pn.getIdPhieuNhap());
//            System.out.println(((PreparedStatement) capNhatPhieuNhap));

            capNhatPhieuNhap.execute();
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
                if (themCTPhieuNhap != null) {
                    themCTPhieuNhap.close();
                }

                if (capNhatPhieuNhap != null) {
                    capNhatPhieuNhap.close();
                }

            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
        return false;
    }

    public static boolean DeleteCTPhieuNhap(dtoCTPhieuNhap ctpn) {
        boolean flag = false;

        //    kết nối db
        if ((conn = dbconnection.connect()) == null) {
            System.out.println("Chưa kết nối được db");
            return false;
        }
//    -----------------
        String cauLenh = "DELETE FROM `chitietphieunhap` WHERE IDCTPhieuNhap = " + ctpn.getIdCTPhieuNhap() + "";
        if (dbconnection.ExecuteData(cauLenh) > 0) {
            flag = true;
        }

        //    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return flag;
    }

//    public static boolean InsertPhieuNhap(){
//        
//    }
    public static boolean NhapHang(dtoPhieuNhap pn) {
        int trangThai = CheckTrangThaiPhieuNhap(pn.getIdNhaCungCap());
        if (trangThai == pn.getTrangThaiPhieuNhap()) {
            ThongBao.ThongBao("Phiếu nhập đã được nhập trước đó. Vui lòng kiểm tra lại!", "Thông báo");
            return false;
        }
        PreparedStatement capNhatPhieuNhap = null;
        PreparedStatement capNhatSoLuongSP = null;
//        PreparedStatement capNhatCTPhieuNhap = null;
        Date newDate = new Date();
        try {
            conn = dbconnection.connect();
            conn.setAutoCommit(false);
//            sql Thêm sản phâm:
            String sqlCapNhatSl = "UPDATE `SanPham` SET `SoLuong`=? WHERE IDSanPham = ?";

            String sqlCapNhatTrangThaiPhieuNhap = "UPDATE `phieunhap` SET "
                    + "`TenPhieuNhap`=?,`IDNhaCungCap`=?,`TongPhieuNhap`=?,`SoTienTraTruoc`=?,`TongTienConLai`=?,`GhiChuPhieuNhap`=? ,`NgayTaoPhieuNhap`=?,`TrangThaiPhieuNhap`=? "
                    + "WHERE IDPhieuNhap = ?";

            capNhatPhieuNhap = conn.prepareStatement(sqlCapNhatTrangThaiPhieuNhap);
            capNhatSoLuongSP = conn.prepareStatement(sqlCapNhatSl);
            for (dtoCTPhieuNhap ct : pn.getCtPhieuNhap()) {

                double sl = daoSanPham.GetSLSP(ct.getIdSanPham());
                sl += sanPhamCtrl.TongSoLuong(ct.getSp(), ct.getSoLuong(), ct.getDonViNhap());
                capNhatSoLuongSP.setDouble(1, sl);
                capNhatSoLuongSP.setString(2, ct.getIdSanPham());
                capNhatSoLuongSP.execute();
            }

            capNhatPhieuNhap.setString(1, pn.getTenPhieuNhap());
            capNhatPhieuNhap.setString(2, pn.getIdNhaCungCap());
            capNhatPhieuNhap.setDouble(3, pn.getTongTienPhieuNhap());
            capNhatPhieuNhap.setDouble(4, pn.getSoTienTraTruoc());
            capNhatPhieuNhap.setDouble(5, pn.getTongTienConLai());
            capNhatPhieuNhap.setString(6, pn.getGhiChuPhieuNhap());
            capNhatPhieuNhap.setString(7, HoTro.dateTimeMySQL(newDate));
            capNhatPhieuNhap.setInt(8, pn.getTrangThaiPhieuNhap());
            capNhatPhieuNhap.setString(9, pn.getIdPhieuNhap());
//            System.out.println(((PreparedStatement) capNhatPhieuNhap));

            capNhatPhieuNhap.execute();
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

                if (capNhatPhieuNhap != null) {
                    capNhatPhieuNhap.close();
                }

            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
        return false;
    }

    public static boolean DeletePhieuNhap(dtoPhieuNhap pn) {
        PreparedStatement xoaPhieuNhap = null;
        PreparedStatement xoaCTPhieuNhap = null;
//        PreparedStatement capNhatCTPhieuNhap = null;

        try {
            conn = dbconnection.connect();
            conn.setAutoCommit(false);
//            sql Thêm sản phâm:
            String sqlXoaPhieuNhap = "DELETE FROM `phieunhap` WHERE IDPhieuNhap = ?";

            String sqlXoaCTPhieuNhap = "DELETE FROM `chitietphieunhap` WHERE IDCTPhieuNhap = ?";

            xoaPhieuNhap = conn.prepareStatement(sqlXoaPhieuNhap);
            xoaCTPhieuNhap = conn.prepareStatement(sqlXoaCTPhieuNhap);
            for (dtoCTPhieuNhap ct : pn.getCtPhieuNhap()) {
                System.out.println("id Chi tiết phiếu nhập: " + ct.getIdCTPhieuNhap());
                double sl = daoSanPham.GetSLSP(ct.getIdSanPham());
                sl += sanPhamCtrl.TongSoLuong(ct.getSp(), ct.getSoLuong(), ct.getDonViNhap());
                xoaCTPhieuNhap.setInt(1, ct.getIdCTPhieuNhap());
                xoaCTPhieuNhap.execute();
            }

            xoaPhieuNhap.setString(1, pn.getIdPhieuNhap());

//            System.out.println(((PreparedStatement) capNhatPhieuNhap));
            xoaPhieuNhap.execute();
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

                if (xoaPhieuNhap != null) {
                    xoaPhieuNhap.close();
                }
                if (xoaCTPhieuNhap != null) {
                    xoaCTPhieuNhap.close();
                }

            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
        return false;
    }

    public static void CapNhatTongTien(String idPhieuNhap) {
        if (conn == null) {
            conn = dbconnection.connect();

        }
        double tongTien = 0;
        double soTien = 0;

        String cauLenhDemTien = "SELECT `TongTienCTPhieuNhap` FROM `chitietphieunhap` WHERE IDPhieuNhap = '" + idPhieuNhap + "' ";
        ResultSet rs = dbconnection.getData(cauLenhDemTien);
        try {
            while (rs.next()) {
                soTien = rs.getDouble("TongTienCTPhieuNhap");
                tongTien += soTien;
            }

            String cauLenh = "UPDATE `phieunhap` SET `TongPhieuNhap`= " + tongTien + " WHERE IDPhieuNhap = '" + idPhieuNhap + "'";
            int capNhatTongTien = dbconnection.ExecuteData(cauLenh);
            if (capNhatTongTien <= 0) {
                ThongBao.ThongBao("Lỗi cập nhật tổng tiền. daoPhieuNhap. #518", "Lỗi");
            }

        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static dtoPhieuNhap TaoCTPhieuNhap(dtoCTPhieuNhap ct) {
        dtoPhieuNhap phieuNhapMoi = null;
        PreparedStatement themCTPhieuNhap = null;

        try {
            conn = dbconnection.connect();
            conn.setAutoCommit(false);
//            sql Thêm sản phâm:
            String sqlctpn = "INSERT INTO `cob03206_thanhhien`.`chitietphieunhap` ( DonGiaNhapSP,  IDPhieuNhap, IDSanPham, SoLuongSP, TenSP, TongTienCTPhieuNhap , DonViNhap ) "
                    + "VALUES (  ?,  ?,  ?,  ?, ?,  ?, ? )";

            themCTPhieuNhap = conn.prepareStatement(sqlctpn);

            themCTPhieuNhap.setDouble(1, ct.getDonGiaNhap());
            themCTPhieuNhap.setString(2, ct.getIdPhieuNhap());
            themCTPhieuNhap.setString(3, ct.getIdSanPham());
            themCTPhieuNhap.setDouble(4, ct.getSoLuong());
            themCTPhieuNhap.setString(5, ct.getTenSP());
            themCTPhieuNhap.setDouble(6, ct.getTongCTPhieuNhap());
            themCTPhieuNhap.setString(7, ct.getDonViNhap());
//                    System.out.println(((PreparedStatement) themCTPhieuNhap));
            themCTPhieuNhap.execute();

            phieuNhapMoi = GetPhieuNhapById(ct.getIdPhieuNhap());
//            System.out.println(((PreparedStatement) capNhatPhieuNhap));

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

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
                if (themCTPhieuNhap != null) {
                    themCTPhieuNhap.close();
                }

            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }

        return phieuNhapMoi;
    }

    public static int CheckTrangThaiPhieuNhap(String idPhieuNhap) {
        String cauLenh = "select TrangThaiPhieuNhap as trangthai from phieunhap where idPhieuNhap = '" + idPhieuNhap + "'";
        ResultSet rs = dbconnection.getData(cauLenh);
        int trangThai = -1;
        try {
            if (rs.next()) {
                trangThai = rs.getInt("trangthai");
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trangThai;

    }

    public static dtoPhieuNhap getPhieuNhapByID(String idPhieuNhap) {
        dtoPhieuNhap phieuNhap = null;

        return phieuNhap;
    }

    public static List<Date> ListNgayNhap() {
        List<Date> list = new ArrayList<>();

        return list;
    }

    public static List<dtoPhieuNhap> ListPhieuNhap(Date tuNgay, Date denNgay, String trangThai, int offset, int count) {
        ArrayList<dtoPhieuNhap> list = new ArrayList<>();
//    kết nối db
        if ((conn = dbconnection.connect()) == null) {

            System.out.println("Chưa kết nối được db");
            return null;
        }
//    -----------------
        String getPN = "select * FROM phieunhap WHERE "
                + "Date(phieunhap.NgayTaoPhieuNhap) >= '" + HoTro.dateMySQL(tuNgay) + "' and date(phieunhap.NgayTaoPhieuNhap) <= '" + HoTro.dateMySQL(denNgay) + "' "
                + " " + trangThai + " ORDER BY phieunhap.NgayTaoPhieuNhap DESC limit " + offset + ", " + count + " ";
//        String getPN = "select * from phieunhap where TrangThaiPhieuNhap >= " + trangThai + " ORDER BY phieunhap.NgayTaoPhieuNhap DESC limit 10";
        System.out.println(getPN);
        ResultSet rs = dbconnection.getData(getPN);
        dtoPhieuNhap pn = null;

        try {
            while (rs.next()) {//String ghiChuPhieuNhap, String idNhaCungCap, String idPhieuNhap, String tenPhieuNhap, Date nagyTaoPhieuNhap, double soTienTraTruoc, double tongTienPhieuNhap, double tongTienConLai, int trangThaiPhieuNhap, dtoCTPhieuNhap ctPhieuNhap
                String idPhieuNhap = rs.getString("IdPhieuNhap");
                String idNCC = rs.getString("IDNhaCungCap");
                String tenNCC = "Không có nhà cung cấp";
                dtoNhaCungCap nhaCungCap = null;
                List<dtoCTPhieuNhap> listCTPhieuNhap = GetListCTPhieuNhap(idPhieuNhap);
                ResultSet ncc = dbconnection.getData("select * from NhaCungCap where IDNhaCungCap = '" + idNCC + "'");
                if (ncc.next()) {
                    tenNCC = ncc.getString("TenNhaCungCap");
                    nhaCungCap = daoNhaCungCap.SetDTONhaCungCap(ncc);

                };

                pn = new dtoPhieuNhap(
                        rs.getString("GhiChuPhieuNhap"),
                        idNCC,
                        idPhieuNhap,
                        rs.getString("TenPhieuNhap"),
                        tenNCC,
                        rs.getTimestamp("NgayTaoPhieuNhap"),
                        rs.getDouble("SoTienTraTruoc"),
                        rs.getDouble("TongPhieuNhap"),
                        rs.getDouble("TongTienConLai"),
                        rs.getInt("TrangThaiPhieuNhap"),
                        nhaCungCap,
                        listCTPhieuNhap);
                list.add(pn);

            }
        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }

//    -------------
//        try {
//            conn.close();
//            System.out.println("kết thúc");
//        } catch (SQLException ex) {
//            Logger.getLogger(daoSanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return list;
    }

    public static int countPhieuNhap(Date tuNgay, Date denNgay, String trangThai) {
        int total = 0;
        String cauLenh = "select count(IdPhieuNhap) as tong FROM phieunhap WHERE "
                + "Date(phieunhap.NgayTaoPhieuNhap) >= '" + HoTro.dateMySQL(tuNgay) + "' and date(phieunhap.NgayTaoPhieuNhap) <= '" + HoTro.dateMySQL(denNgay) + "' "
                + " " + trangThai + " ";
        
        ResultSet rs = dbconnection.getData(cauLenh);
        try {
            if (rs.next()) {
                total = rs.getInt("tong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoNhapHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}