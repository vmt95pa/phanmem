/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.daoNhapHang;
import DTO.dtoCTPhieuNhap;
import DTO.dtoNhaCungCap;
import DTO.dtoPhieuNhap;
import GUI.NhapHangForm2;
import GUI.ThongBao;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.jdesktop.swingx.table.ColumnControlButton;
import sun.awt.resources.awt;

/**
 *
 * @author bumte
 */
public class phieuNhapCtrl {
    
    public static List<dtoPhieuNhap> ListPhieuNhap = daoNhapHang.GetListPhieuNhap(0);
    public static dtoPhieuNhap phieuNhapDaChon = null;
    public static dtoPhieuNhap phieuNhapDangChon = null;
    public static dtoNhaCungCap nhaCungCapDangChon = null;
    
    public static void Refresh() {
        phieuNhapDangChon = null;
    }
    
    public static dtoPhieuNhap TaoDTOPhieuNhap(String ghiChuPhieuNhap, String idPhieuNhap, String tenPhieuNhap, Date ngayTao, double traTruoc, double tongPhieuNhap, double conLai, int trangThaiPhieuNhap, String idNCC) {
        dtoPhieuNhap phieuNhap = null;
        
        phieuNhap = new dtoPhieuNhap(ghiChuPhieuNhap, idNCC, idPhieuNhap, tenPhieuNhap, ngayTao, traTruoc, tongPhieuNhap, conLai, trangThaiPhieuNhap);
        return phieuNhap;
    }
    
    public static void fillPhieuNhap(JTable tbl, List<dtoPhieuNhap> pn) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (dtoPhieuNhap p : pn) {
            Object[] row = new Object[7];
            THFormat.fillTablePhieuNhap(p, row);
            row[3] = p.getCtPhieuNhap();
            tblModel.addRow(row);
            
        }
        
    }
    
    public static void fillGioHang(JTable tbl, List<dtoCTPhieuNhap> ctpn) {
        
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        THFormat.HeaderTable(tbl);
        
        for (dtoCTPhieuNhap ct : ctpn) {
            Object[] row = new Object[4];
            row[1] = tbl.getRowCount() + 1;
            THFormat.fillTableCTPhieuNhap2(ct, row);
            tblModel.addRow(row);
        }
        THFormat.tblColAlign(tbl, 0, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 1, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 2, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 3, JLabel.CENTER);
//        THFormat.tblColAlign(tbl, 4, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 5, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 6, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 7, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 8, JLabel.RIGHT);
//        THFormat.tblColAlign(tbl, 9, JLabel.RIGHT);
        tbl.setShowGrid(true);
        
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
           
                int col = tbl.columnAtPoint(e.getPoint());
                int row = tbl.getSelectedRow();
    
                dtoCTPhieuNhap ct = (dtoCTPhieuNhap) tbl.getValueAt(row, 0);
                if (col == 3 && e.getClickCount() == 2) {
                    if (phieuNhapDangChon == null) {
                        System.out.println("null");
                        return;
                    }
                    if (ct != null) {
                        System.out.println("đã chọn");
//                        if (XoaCTPhieuNhap(ct)) {
//                            fillGioHang(tbl, phieuNhapDangChon.getCtPhieuNhap());
//                        }

                    }
                }
            }
            
        });
    }
    
    public static dtoPhieuNhap ThemPhieuNhap(dtoPhieuNhap pn) {
        if (!daoNhapHang.TaoPhieuNhap(pn)) {
            ThongBao.ThongBao("Nhập thiếu hoặc sai thông tin phiếu nhập! Vui lòng kiểm tra lại", "Thông báo");
            return null;
        } else {
            ThongBao.ThongBao("Đã tạo phiếu nhập. Chọn các sản phẩm cần nhập vào kho", "Thông báo");
            
            dtoPhieuNhap pnMoi = daoNhapHang.GetPhieuNhapById(pn.getIdPhieuNhap());
            ListPhieuNhap.add(pnMoi);
            return pnMoi;
        }
        
    }

//    Chi tiết phiếu nhập
    public static dtoPhieuNhap CT_Delete(dtoCTPhieuNhap ctpn) {
        dtoPhieuNhap pn = null;
        String idpn = ctpn.getIdPhieuNhap();
        if (daoNhapHang.DeleteCTPhieuNhap(ctpn)) {
            pn = daoNhapHang.GetPhieuNhapById(idpn);
        }
        return pn;
    }
    
    public static boolean XoaVinhVienPhieuNhap(dtoPhieuNhap pn) {
        if (pn == null) {
            ThongBao.ThongBao("Chưa chọn phiếu nhập cần xóa!", "Thông báo");
            return false;
        }
        if (daoNhapHang.DeletePhieuNhap(pn)) {
            ThongBao.ThongBao("Đã xóa phiếu nhập! ", "Thông báo");
            return true;
        }
        return false;
    }
    
    public static boolean GetAllData() {
        return false;
    }
    
    public static void ViewOnpened(JTable tblPhieuNhap, JTable tblGioHang) {
        
    }
    
    public static List<dtoPhieuNhap> ReloadListPhieuNhap(dtoPhieuNhap pn) {
        if (daoNhapHang.CapNhatPhieuNhap(pn)) {
            return daoNhapHang.GetListPhieuNhap(pn.getTrangThaiPhieuNhap());
        }
        return null;
    }
    
    public static void SetPhieuNhap(dtoPhieuNhap pn) {
        phieuNhapDaChon = pn;
        phieuNhapDangChon = pn;
        
    }
//public static List<dtoPhieuNhap> ListPhieuNhap(){
//
//    
//    
//    return phieuNhap;
//}

    public static boolean XoaCTPhieuNhap(dtoCTPhieuNhap ct) {
        if (phieuNhapDangChon == null) {
            ThongBao.ThongBao("Phiếu nhập đang chọn lỗi Null. phieuNhapCrtl #176", "Lỗi");
            return false;
        }
        
        dtoPhieuNhap pn = CT_Delete(ct);
        
        if (pn == null) {
            ThongBao.ThongBao("Lỗi xóa sản phẩm trong db. phieuNhapCtrl #181", "Lỗi");
            return false;
        }
        ListPhieuNhap.set(ListPhieuNhap.indexOf(phieuNhapDaChon), pn);
        SetPhieuNhap(pn);
        return true;
    }
    
    public static boolean NhapHang() {
        if (phieuNhapDangChon == null) {
            ThongBao.ThongBao("Chưa chọn được phiếu cần nhâp! Vui lòng chọn lại phiếu cần nhập", "Thông báo");
            return false;
        }
        
        boolean nhap = daoNhapHang.NhapHang(phieuNhapDangChon);
        if (!nhap) {
            ThongBao.ThongBao("Đã xãy ra lõi. Chưa nhập được hàng #phieuNhapCtrl", "Thông báo");
            return false;
        }
        return true;
    }
    
    public static boolean ThemChiTietPhieuNhap(dtoCTPhieuNhap ct) {
        if (ct == null) {
            ThongBao.ThongBao("Lỗi chưa tạo được phiếu nhập", "Lỗi");
            return false;
        }
        
        int index = ListPhieuNhap.indexOf(phieuNhapDaChon);
        
        dtoPhieuNhap pnMoi = daoNhapHang.TaoCTPhieuNhap(ct);
        if (pnMoi != null) {
            phieuNhapDangChon = pnMoi;
            ListPhieuNhap.set(index, phieuNhapDangChon);
            SetPhieuNhap(phieuNhapDangChon);
            return true;
        }
        return false;
    }
    
    public static boolean UpDatePhieuNhap(dtoPhieuNhap pn) {
        boolean flag = daoNhapHang.CapNhatPhieuNhap(pn);
        dtoPhieuNhap phieuNhapMoiCapNhap = null;
        if (flag) {
            int index = ListPhieuNhap.indexOf(phieuNhapDaChon);
            phieuNhapMoiCapNhap = daoNhapHang.GetPhieuNhapById(pn.getIdPhieuNhap());
            ListPhieuNhap.set(index, phieuNhapMoiCapNhap);
            SetPhieuNhap(ListPhieuNhap.get(index));
        }
        
        return flag;
    }
}
