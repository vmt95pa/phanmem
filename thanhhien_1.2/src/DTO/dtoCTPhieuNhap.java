/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.ResultSet;

/**
 *
 * @author bumte
 */
public class dtoCTPhieuNhap {

    double donGiaNhap, soLuong, tongCTPhieuNhap;
    int idCTPhieuNhap;
    String idPhieuNhap, idSanPham, tenSP,donViNhap;
    dtoSanPham sp;

    public dtoSanPham getSp() {
         
        return sp;
    }

    public void setSp(dtoSanPham sp) {
        this.sp = sp;
    }
    public String getDonViNhap() {
        return donViNhap;
    }

    public void setDonViNhap(String donViNhap) {
        this.donViNhap = donViNhap;
    }

    public dtoCTPhieuNhap(double donGiaNhap, double soLuong, double tongCTPhieuNhap, String idPhieuNhap, String idSanPham, String tenSP, String donViNhap) {
        this.donGiaNhap = donGiaNhap;
        this.soLuong = soLuong;
        this.tongCTPhieuNhap = tongCTPhieuNhap;
        this.idPhieuNhap = idPhieuNhap;
        this.idSanPham = idSanPham;
        this.tenSP = tenSP;
        this.donViNhap = donViNhap;
    }

    public dtoCTPhieuNhap(double donGiaNhap, double soLuong, String donViNhap, double tongCTPhieuNhap, int idCTPhieuNhap, String idPhieuNhap, String idSanPham, String tenSP) {
        this.donGiaNhap = donGiaNhap;
        this.soLuong = soLuong;
        this.donViNhap = donViNhap;
        this.tongCTPhieuNhap = tongCTPhieuNhap;
        this.idCTPhieuNhap = idCTPhieuNhap;
        this.idPhieuNhap = idPhieuNhap;
        this.idSanPham = idSanPham;
        this.tenSP = tenSP;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(double donGianNhap) {
        this.donGiaNhap = donGianNhap;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongCTPhieuNhap() {
        return tongCTPhieuNhap;
    }

    public void setTongCTPhieuNhap(double tongCTPhieuNhap) {
        this.tongCTPhieuNhap = tongCTPhieuNhap;
    }

    public int getIdCTPhieuNhap() {
        return idCTPhieuNhap;
    }

    public void setIdCTPhieuNhap(int idCTPhieuNhap) {
        this.idCTPhieuNhap = idCTPhieuNhap;
    }

    public String getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(String idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

}
