/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.donViTinh;
import DTO.dtoCTPhieuNhap;
import DTO.dtoNhaCungCap;
import DTO.dtoPhieuNhap;
import DTO.dtoSanPham;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author bumte
 */
public class THFormat {

    public static String MauXanhLa = "#058000";
    public static String MauXanhDuong = "#0425e4";
    public static String MauDo = "red";

    public static void tblColAlign(JTable tbl, int col, int viTri) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(viTri);
        tbl.getColumnModel().getColumn(col).setCellRenderer(rightRenderer);
    }
    public static Color backGroundColorHeadertable = new Color(239, 239, 239);
    public static Color textColorHeadertable = new Color(24, 22, 28);
    public static Font fontHeaderTable = new Font("Segoe UI", Font.BOLD, 16);

    public static void HeaderTable(JTable tbl) {

        tbl.getTableHeader().setDefaultRenderer(new HeaderColor());
        tbl.getTableHeader().repaint();
        tbl.getTableHeader().revalidate();
    }

    public static class HeaderColor extends DefaultTableCellRenderer {

        public HeaderColor() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable tblNCC, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(tblNCC, value, selected, focused, row, column);
            setBackground(backGroundColorHeadertable);
            setForeground(textColorHeadertable);
            setFont(fontHeaderTable);
            setSize(70, 100);
            JLabel title = new JLabel();
            setHorizontalAlignment(title.CENTER);
            setBorder(BorderFactory.createMatteBorder(1, 1, 2, 1, Color.BLACK));
            setPreferredSize(new Dimension(100, 50));
            return this;
        }
    }

    public static void fillTable(dtoSanPham s, Object[] row) {
        donViTinh dvt = new donViTinh(s.getDonViTinh());
        row[1] = THFormat.TenSanPhamTable(s.getTtSanPham().getQuyCach(), s.getTtSanPham().getDoDay(), s.getTtSanPham().getDai(), s.getTenSanPham(), s.getDonViTinh(), s.getTenNhaCungCap());
        row[2] = s.getTtSanPham().getQuyCach();
        row[3] = s.getTtSanPham().getDoDay() + dvt.getDay();
        row[4] = s.getTtSanPham().getTrongLuong() + " (" + dvt.getTrongLuong() + "/" + dvt.getDvtSi() + ")";
        double tongKhoiLuong = s.getTtSanPham().getTrongLuong() * s.getSoLuong();
        double tongChieuDai = s.getTtSanPham().getDai() * s.getSoLuong();
        row[5] = HoTro.DoubleToString(tongKhoiLuong) + " (" + dvt.getTrongLuong() + ")";
        row[6] = HoTro.DoubleToString(tongChieuDai) + " (" + dvt.getDai() + ")";
        row[7] = HoTro.DoubleToString(s.getSoLuong()) + " (" + dvt.getDvtSi() + ")";
        row[8] = HoTro.DoubleToString(s.getGiaLe()) + "/" + dvt.getDvtLe();
        row[9] = HoTro.DoubleToString(s.getGiaSi()) + "/" + dvt.getDvtSi();
        row[10] = s;

    }

    public static void fillTablePhieuNhap(dtoPhieuNhap pn, Object[] row) {
        String ngayThang = HoTro.dateTimeString(pn.getNagyTaoPhieuNhap());
        String ten = pn.getTenPhieuNhap() + "-" + pn.getTenNCC();
        String dangChon = "";
        int tt = pn.getTrangThaiPhieuNhap();
        String trangThai = tt == 0 ? "Đã xóa" : tt == 1 ? "Chưa nhập" : tt == 2 ? "Đã nhập" : "Chưa xác định";
        String mau = tt == 0 ? MauDo : tt == 1 ? MauXanhDuong : tt == 2 ? MauXanhLa : "";
        if (phieuNhapCtrl.phieuNhapDangChon != null) {
            dangChon = pn.getIdPhieuNhap().equals(phieuNhapCtrl.phieuNhapDangChon.getIdPhieuNhap()) ? "<span style='font-size: 24px; color: #208806'>&#9679</span>" : "";
        }

        row[0] = "<html><span>" + ten + " " + dangChon + " </span><br><span style=' font-size: 10px ;font-style: italic '><strong>Ngày tạo:</strong>" + ngayThang + "<br><span style='color: " + mau + ";padding: 10px'>" + trangThai + "</span></span><html>";
        row[1] = HoTro.dateTimeString(pn.getNagyTaoPhieuNhap());
        row[4] = pn;

    }

    public static void fillTableCTPhieuNhap(dtoCTPhieuNhap ctpn, Object[] row) {
        String donviNhap = ctpn.getDonViNhap();

        row[8] = ctpn.getIdCTPhieuNhap();
        row[1] = TenSanPhamTable(ctpn.getSp().getTtSanPham().getQuyCach(), ctpn.getSp().getTtSanPham().getDoDay(), ctpn.getSp().getTtSanPham().getDai(), ctpn.getTenSP(), ctpn.getSp().getDonViTinh(), ctpn.getSp().getTenNhaCungCap());
        row[2] = ctpn.getSp().getTtSanPham().getQuyCach();
        row[4] = HoTro.DoubleToString(ctpn.getSoLuong()) + "(" + donviNhap + ")";
        row[3] = ctpn.getDonViNhap();
        row[5] = "<html>" + HoTro.DoubleToString(ctpn.getDonGiaNhap()) + " <strong>VND</strong><html>";
        row[6] = "<html>" + HoTro.DoubleToString(ctpn.getTongCTPhieuNhap()) + " <strong>VND</strong><html>";
        row[7] = ctpn;

    }

    public static void fillTableCTPhieuNhap2(dtoCTPhieuNhap ctpn, Object[] r) {
        String donviNhap = ctpn.getDonViNhap();
        String tenSP = ctpn.getTenSP();
        double soLuong = ctpn.getSoLuong();
        double donGiaNhap = ctpn.getDonGiaNhap();
        double tongTien = ctpn.getTongCTPhieuNhap();

        String thongTinSanPham = "<html>"
                + "<span><strong>Sản phẩm: </strong> " + tenSP + "</span><br>"
                + "<span><strong>Số lượng: </strong> " + HoTro.DoubleToString(soLuong) + " <strong> " + donviNhap + "</strong></span><br>"
                + "<span><strong>Đơn giá: </strong> " + HoTro.DoubleToString(donGiaNhap) + "đ</span><br>"
                + "<span><strong>Tổng tiền: </strong> " + HoTro.DoubleToString(tongTien) + "đ</span><br>"
                + " <html>";

        r[0] = ctpn;
        r[2] = thongTinSanPham;
        r[3] = "<html><a href='#' style='color: red'>XÓA<a><html>";

    }

    public static void TableChonNhaCungCap(dtoNhaCungCap ncc, Object[] row) {
        try {
            String tenNCC = "<p><strong>Tên: </strong>" + ncc.getTenNCC() + "</p>";
            String dc = "<p><strong>Địa chỉ: </strong>" + ncc.getDc() + "</p>";
            String sdt = "<p><strong>Liên hệ: </strong>" + ncc.getSdt() + "</p>";
            String khuVuc = "<p><strong>Khu vực: </strong>" + ncc.getKhuVuc() + "</p>";
            String thongTinThem = "<p><strong>Thông tin khác: </strong>" + ncc.getTtThem() + "</p>";

            row[0] = "<html>" + tenNCC + sdt + khuVuc + dc + "<html>";
            row[1] = "<html>" + thongTinThem + "<html>";
            row[2] = ncc;

        } catch (Exception e) {
        }

    }

    public static String TenSanPhamTable(String quyCach, double day, double dai, String ten, String dvt, String tenNCC) {
        donViTinh dv = new donViTinh(dvt);
        String str1, str2;
        str1 = ten;
        str2 = "";
        if (!quyCach.trim().isEmpty()) {
            str2 += "(" + quyCach + ")";
        }
        if (day > 0) {
            if (!str2.trim().isEmpty()) {
                str2 += "x";
            }
            str2 += HoTro.DoubleToString(day) + dv.getDay();
        }
        if (dai > 0) {
            if (!str2.trim().isEmpty()) {
                str2 += "x";
            }
            str2 += HoTro.DoubleToString(dai) + dv.getDai();
        }
        String str = "<html>"
                + "<span style='font-weight: bold'>" + str1 + " </span>"
                + "<br><span style='font-style: italic; opacity: 0.4'>" + tenNCC + "</span>"
                + "<br><span style='font-style: italic'>" + str2 + "</span>"
                + "<html>";
        return str;
    }

}
