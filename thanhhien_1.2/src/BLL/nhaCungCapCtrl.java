/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.dtoNhaCungCap;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bumte
 */
public class nhaCungCapCtrl {
    
    public static List<dtoNhaCungCap> ListNhaCungCap = null;
    
    public static List<dtoNhaCungCap> ListNCC() {
        if (GetAllData()) {
            return ListNhaCungCap;
        } else {
            return null;
        }
    }
    
    public static boolean GetAllData() {
        if (DAO.daoNhaCungCap.getListNCC("") != null) {
            ListNhaCungCap = DAO.daoNhaCungCap.getListNCC("");
        }
        return true;
    }

    public static void RefreshData() {
        GetAllData();
    }
    
    public static void FillChonNhaCungCap(JTable tbl, List<dtoNhaCungCap> ncc) {
        DefaultTableModel tblModel = (DefaultTableModel) tbl.getModel();
        tblModel.setRowCount(0);
        for (dtoNhaCungCap nc : ncc) {
            Object[] row = new Object[5];
            THFormat.TableChonNhaCungCap(nc, row);
            tblModel.addRow(row);
        }
        
    }
}
