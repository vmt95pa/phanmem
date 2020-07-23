/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.daoSanPham;
import DAO.getList;
import DTO.MyComboBox;
import DTO.donViTinh;
import DTO.dtoSanPham;
import GUI.SanPhamForm;
import GUI.ThongBao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

/**
 *
 * @author bumte
 */
public class sanPhamCtrl {

    public static int offset = 0;
    public static int count = 5;
    public static int tongSP = daoSanPham.TongSanPham("");
    public static int currentPage = (offset / count) + 1;
    public static int countPage = (tongSP / count) + 1;

//    public static void LoadBangSanPham(JTable tbl, String timKiem) {
//        List<dtoSanPham> list = new ArrayList<>();
//        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
//        tblModel.setRowCount(0);
//        THFormat.HeaderTable(tbl);
//        if (timKiem.trim().isEmpty()) {
//            list = daoSanPham.SanPham();
//        } else {
//            String sql = "tensp like N'%" + timKiem + "%' or ";
//            sql += "tendanhmuc like N'%" + timKiem + "%' or ";
//            sql += "tenncc like N'%" + timKiem + "%' or ";
//            sql += "quycach like N'%" + timKiem + "%'";
//            list = daoSanPham.SanPhamBy(sql);
//        }
//        Object[] row = new Object[12];
//        for (dtoSanPham s : list) {
//            row[0] = tblModel.getRowCount() + 1;
//            THFormat.fillTable(s, row);
//            tblModel.addRow(row);
//            tbl.setShowGrid(true);
//
//        }
//        THFormat.tblColAlign(tbl, 0, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 2, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 3, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 4, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 5, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 6, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 7, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 8, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 9, JLabel.RIGHT);
//
//    }

    public static List<dtoSanPham> NhapNhieuSanPham(List<dtoSanPham> list) {
        List<dtoSanPham> listLoi = new ArrayList<>();
        for (dtoSanPham sp : list) {
            if (!NhapSanPham(sp)) {
                listLoi.add(sp);
            }
        }
        return listLoi;
    }

    public static boolean NhapSanPham(dtoSanPham sp) {
        if (sp == null) {
            System.out.println("Chưa nhận được sản phẩm!");
            return false;
        }
        if (daoSanPham.daoThemSanPham(sp)) {
            return true;
        }

        return false;
    }

    public static boolean SuaSanPham(dtoSanPham sp) {
        if (sp == null) {
            System.out.println("Chưa lấy được sản phẩm để sửa");
            return false;

        }
        if (daoSanPham.UpdateSP(sp)) {
            return true;
        }
        return false;
    }

    public static boolean XoaSanPham(dtoSanPham sp) {
        if (sp == null) {
            return false;
        }

        if (daoSanPham.DeleteSanPham(sp)) {
            ThongBao.ThongBao("Đã xóa sản phẩm thành công!", "Thông báo!");
            return true;
        }

        return false;
    }

    public static boolean ThemDonViTinhGia(String dvt) {
        String str = "";
        for (String dv : getList.listLoai()) {
            if (dv.equalsIgnoreCase(dvt)) {
                return false;
            }
            str += dv + ";";

        }
        str += dvt;
        String set = "loai = N'" + str + "' where id = 0";
        if (daoSanPham.UpDateDonViTinh(set)) {
            System.out.println("Thêm sản phẩm mới thành công!");
            return true;
        }
        return false;
    }

    public static double TongSoLuong(dtoSanPham sp, double sl, String donViTinh) {
        double tongSL = 0;
        donViTinh d = new donViTinh(sp.getDonViTinh());
//        String dai, nang, dvt;
//        MyComboBox mbDai = new MyComboBox(sp.getTtSanPham().getDai(), d.getDai());
//        MyComboBox mbTrongLuong = new MyComboBox(sp.getTtSanPham().getTrongLuong(), d.getTrongLuong());
//        MyComboBox mbDvt = new MyComboBox(1, d.getDvtSi());

        if (donViTinh.equals(d.getDvtSi())) {
            tongSL = sl;
        } else if (donViTinh.equals(d.getDai())) {
            tongSL = sl / sp.getTtSanPham().getDai();
        } else if (donViTinh.equals(d.getTrongLuong())) {
            tongSL = sl / sp.getTtSanPham().getTrongLuong();
        } else {
            ThongBao.ThongBao("Lỗi chuyển đổi số lượng. sanphamCtrl #139", "Lỗi");

        }

        return tongSL;
    }

    public static boolean UpDateSLSP(String idSanPham, double soLuong) {

        return false;

    }

    public static boolean CopyFileTrongDocument(Component form, String tenFileCanLay, String tenFileSauKhiLayRa) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(form);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this directory: "
//                    + chooser.getSelectedFile().getAbsolutePath());
            String dir = chooser.getSelectedFile().getAbsolutePath();
            File documentFolder = new File("lib", "document/" + tenFileCanLay);
            File fNew = new File(dir, tenFileSauKhiLayRa);
            try {
                CopyFileExample.copyFolder(documentFolder, fNew);
                return true;
            } catch (IOException ex) {
                ThongBao.ThongBao("<html><span>Xãy ra lỗi trong quá trình lấy file.<span>"
                        + "<br>"
                        + "<span style='font-weight: bold'>Cách khắc phục:<span>"
                        + "<br>"
                        + "<span>    - Kiểm tra trong thư mục " + documentFolder.getPath() + " có chưa file " + tenFileCanLay + " không. Nếu không liên hệ Huy để lấy<span>"
                        + "<br>"
                        + "<span>    - Kiểm tra và cấp đầy đủ quyền cho folder chứa để thực hiện việc lấy file và lưu file<span><html>", "Thông báo");
            }

        }
        return false;
    }

    public static void fillBangSP(JTable tbl, List<dtoSanPham> listsp) {

        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        THFormat.HeaderTable(tbl);
//        if (timKiem.trim().isEmpty()) {
//            list = daoSanPham.SanPham();
//        } else {
//            String sql = "tensp like N'%" + timKiem + "%' or ";
//            sql += "tendanhmuc like N'%" + timKiem + "%' or ";
//            sql += "tenncc like N'%" + timKiem + "%' or ";
//            sql += "quycach like N'%" + timKiem + "%'";
//            list = daoSanPham.SanPhamBy(sql);
//        }
        Object[] row = new Object[12];
        for (dtoSanPham s : listsp) {
            row[0] = tblModel.getRowCount() + 1;
            THFormat.fillTable(s, row);
            tblModel.addRow(row);
            tbl.setShowGrid(true);

        }
        THFormat.tblColAlign(tbl, 0, JLabel.CENTER);
        THFormat.tblColAlign(tbl, 2, JLabel.CENTER);
        THFormat.tblColAlign(tbl, 3, JLabel.CENTER);
        THFormat.tblColAlign(tbl, 4, JLabel.RIGHT);
        THFormat.tblColAlign(tbl, 5, JLabel.RIGHT);
        THFormat.tblColAlign(tbl, 6, JLabel.RIGHT);
        THFormat.tblColAlign(tbl, 7, JLabel.RIGHT);
        THFormat.tblColAlign(tbl, 8, JLabel.RIGHT);
        THFormat.tblColAlign(tbl, 9, JLabel.RIGHT);

    }

    public static List<dtoSanPham> ListSPSearch(String ncc, String quyCach, String danhMuc) {
        List<dtoSanPham> list = null;
        String str;
        str = "and tendanhmuc like N'%" + danhMuc + "%'"
                + " and tenncc like N'%" + ncc + "%' "
                + "and QuyCach like N'%" + quyCach + "%'";

        list = daoSanPham.SanPhamSearch(offset, count, str);
        return list;
    }

    public static void FillComboboxSearch(JComboBox cbbNCC, JComboBox cbbDanhMuc, JComboBox cbbQuyCach) {
        List<String> ncc = daoSanPham.ListCBBBy("tenncc");
        List<String> danhMuc = daoSanPham.ListCBBBy("tendanhmuc");
        List<String> quyCach = daoSanPham.ListCBBBy("QuyCach");

        HoTro.fillCombobox2(ncc, cbbNCC);
        HoTro.fillCombobox2(danhMuc, cbbDanhMuc);
        HoTro.fillCombobox2(quyCach, cbbQuyCach);
    }

    public static void FillTableSearch(String nextOrPre, JTable tbl, JLabel lblTitle, String ncc, String quyCach, String danhMuc) {

        switch (nextOrPre) {
            case "":
                offset = 0;
                break;
            case "next":
                offset += count;
                break;
            case "pre":
                offset -= count;
                break;
            
                
        }

        List<dtoSanPham> list = null;
        String str;
        str = "and tendanhmuc like N'%" + danhMuc + "%'"
                + " and tenncc like N'%" + ncc + "%' "
                + "and QuyCach like N'%" + quyCach + "%'";
        tongSP = daoSanPham.TongSanPham(str);
        list = daoSanPham.SanPhamSearch(offset, count, str);
        fillBangSP(tbl, list);
        PageCount(lblTitle);
    }

    public static void PageCount(JLabel lblTitle) {
        currentPage = (offset / count) + 1;
        countPage = (tongSP / count) + 1;

        String title = "<html>" + currentPage + "/" + countPage + "<span style='font-style: italic'> Tổng " + tongSP + " sản phẩm </span></html>";
        lblTitle.setText(title);
    }
    public static void TableSanPhamSearch(String option, JButton btnPre, JButton btnNext, JComboBox cbbNCC, JComboBox cbbDanhMuc, JComboBox cbbQuyCach, JTable tblChonSanPham, JLabel lblPage  ) {
        
        String ncc = "";
        String quyCach = "";
        String danhMuc = "";
        if (cbbNCC.getSelectedIndex() != 0) {
            ncc = cbbNCC.getSelectedItem().toString();
        }
        if (cbbQuyCach.getSelectedIndex() != 0) {
            quyCach = cbbQuyCach.getSelectedItem().toString();
        }
        if (cbbDanhMuc.getSelectedIndex() != 0) {
            danhMuc = cbbDanhMuc.getSelectedItem().toString();
        }

        sanPhamCtrl.FillTableSearch(option, tblChonSanPham, lblPage, ncc, quyCach, danhMuc);
        btnNext.setEnabled(true);
        btnPre.setEnabled(true);
        if (sanPhamCtrl.currentPage == sanPhamCtrl.countPage) {
            btnNext.setEnabled(false);
        }
        if (sanPhamCtrl.currentPage == 1) {
            btnPre.setEnabled(false);
        }
    }
}
