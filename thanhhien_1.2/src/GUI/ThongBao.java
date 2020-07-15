/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.webkit.ContextMenu;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ThongBao {
    public static void ThongBao(String noidung, String tieude){
        JOptionPane.showMessageDialog(new JFrame(), noidung, tieude, JOptionPane.INFORMATION_MESSAGE);
    }
    public static int ThongBaoLoai2(String mess, String tittle){
        return JOptionPane.showConfirmDialog(new Frame(), mess, tittle, new JOptionPane().OK_CANCEL_OPTION);
    }
    
  
}

