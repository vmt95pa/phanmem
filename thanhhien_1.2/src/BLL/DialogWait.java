/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import GUI.FormLoading;
import GUI.WaitingForm;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author bumte
 */
public class DialogWait {

    private JDialog dialog;

    public void makeWait(String msg) {

        dialog = new WaitingForm(new JFrame(), true);
        WaitingForm.lblThongBao.setText(msg);
//        JProgressBar progressBar = new JProgressBar();
//        progressBar.setIndeterminate(true);
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(progressBar, BorderLayout.CENTER);
//        panel.add(new JLabel("Đang tải......."), BorderLayout.PAGE_START);
//        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void makeWaitItem(String msg, ItemEvent evt) {

        Window win = SwingUtilities.getWindowAncestor((AbstractButton) evt.getSource());
        dialog = new JDialog(win, msg, Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(new JLabel("Đang tải......."), BorderLayout.PAGE_START);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }

    public void loading() {
        GUI.FormLoading.lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/giphy.gif")));
    }

    public void close() {
        dialog.dispose();
    }
}
//    DialogWait wait = new DialogWait();
//        SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
////---------------------This action ------------------------------------------------------------
//
//
//// -----------------------------------------------------------------------
//                wait.close();
//                return null;
//            }
//        };
//
//        mySwingWorker.execute();
//        wait.makeWait("Test", evt);
