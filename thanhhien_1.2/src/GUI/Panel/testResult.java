/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panel;

import BLL.sanPhamCtrl;
import DTO.dtoSanPham;
import java.util.List;

/**
 *
 * @author bumte
 */
public class testResult extends javax.swing.JPanel {
    
    public int offset = 0;
    public int count = 8;
    public int total = DAO.daoSanPham.TongSanPham("");
    public int currenPage = currenPage();
    public List<dtoSanPham> listSP = DAO.daoSanPham.SanPhamSearch(offset, count,"");

    /**
     * Creates new form testResult
     */
    public testResult() {
        initComponents();
        String text = contenPage();
        lblPage.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel19 = new javax.swing.JPanel();
        clpBangSP2 = new javax.swing.JScrollPane();
        tblChonSanPham1 = new javax.swing.JTable();
        txtTimKiem1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();

        jPanel19.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel19AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        tblChonSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblChonSanPham1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"123213213", null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên sản phẩm", "Quy cách", "ĐVT", "Tỉ trọng", "<html><p style='text-align: center'>Tổng<br>khối lượng<p><html>", "<html><p style='text-align:center'>Tổng <br> độ dài</p><html>", "Số lượng", "<html><p style='text-align: center'>Đơn giá<br>lẽ<p><html>", "<html><p style='text-align: center'>Đơn giá<br>sỉ<p><html>", "sp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChonSanPham1.setFillsViewportHeight(true);
        tblChonSanPham1.setGridColor(new java.awt.Color(0, 0, 0));
        tblChonSanPham1.setRowHeight(80);
        tblChonSanPham1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblChonSanPham1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblChonSanPham1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblChonSanPham1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblChonSanPham1FocusLost(evt);
            }
        });
        tblChonSanPham1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChonSanPham1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblChonSanPham1MousePressed(evt);
            }
        });
        clpBangSP2.setViewportView(tblChonSanPham1);
        if (tblChonSanPham1.getColumnModel().getColumnCount() > 0) {
            tblChonSanPham1.getColumnModel().getColumn(4).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(4).setMaxWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(5).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(5).setMaxWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(6).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(6).setMaxWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(7).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(7).setMaxWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(8).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(8).setMaxWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(9).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(9).setMaxWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(10).setMinWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblChonSanPham1.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        txtTimKiem1.setText("jTextField1");

        jButton7.setText("jButton3");

        jButton8.setText("pre");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton10.setText("next");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        lblPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPage.setText("jLabel12");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPage, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(clpBangSP2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(230, 230, 230))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(clpBangSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblChonSanPham1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblChonSanPham1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChonSanPham1AncestorAdded

    private void tblChonSanPham1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblChonSanPham1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChonSanPham1FocusGained

    private void tblChonSanPham1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblChonSanPham1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChonSanPham1FocusLost

    private void tblChonSanPham1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChonSanPham1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChonSanPham1MouseClicked

    private void tblChonSanPham1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChonSanPham1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChonSanPham1MousePressed

    private void jPanel19AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel19AncestorAdded
        sanPhamCtrl.fillBangSP(tblChonSanPham1, listSP);
    }//GEN-LAST:event_jPanel19AncestorAdded

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        page("pre");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        page("next");
    }//GEN-LAST:event_jButton10ActionPerformed
    public int currenPage() {
        int page = 0;
        page = offset / count;
        page++;
        
        return page;
    }
    
    public int countPage() {
        
        int a = total % count;
        int b = total / count;
        if (a != 0) {
            b++;
        }
        return b;
    }
    
    public String contenPage() {
        String str = "";
        int currentP = currenPage();
        int cPage = countPage();
        str = currentP + "/" + cPage;
        return str;
    }
    
    public void page(String nap) {
        switch (nap) {
            case "next":
                offset += count;
                
                break;
            case "pre":
                offset -= count;
                break;
            
        }
        listSP = DAO.daoSanPham.SanPhamSearch(offset, count,"");
        lblPage.setText(contenPage());
        sanPhamCtrl.fillBangSP(tblChonSanPham1, listSP);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane clpBangSP2;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JLabel lblPage;
    private javax.swing.JTable tblChonSanPham1;
    private javax.swing.JTextField txtTimKiem1;
    // End of variables declaration//GEN-END:variables
}
