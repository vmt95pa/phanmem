/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author bumte
 */
public class dtoNhaCungCap {
    String idNCC, dc, sdt, tenNCC, khuVuc,ttThem;
    Date ngayThem;

    public dtoNhaCungCap(String idNCC, String dc, String sdt, String tenNCC, String khuVuc, String ttThem, Date ngayThem) {
        this.idNCC = idNCC;
        this.dc = dc;
        this.sdt = sdt;
        this.tenNCC = tenNCC;
        this.khuVuc = khuVuc;
        this.ttThem = ttThem;
        this.ngayThem = ngayThem;
    }

    public String getIdNCC() {
        return idNCC;
    }

    public void setIdNCC(String idNCC) {
        this.idNCC = idNCC;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getTtThem() {
        return ttThem;
    }

    public void setTtThem(String ttThem) {
        this.ttThem = ttThem;
    }

    public Date getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }
    
}
