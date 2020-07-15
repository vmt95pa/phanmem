/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author bumte
 */
public class dtoThuocTinhSanPham {

    String thuocTinhKhac,quyCach, thuocTinh;
    double  trongLuong, doDay, dai;
    int idThuocTinhSanPham;

    public String getThuocTinh() {
        this.thuocTinh = this.dai + "x" + this.doDay + "x" + this.trongLuong;
        return thuocTinh;
    }

    public void setThuocTinh(String thuocTinh) {
        this.thuocTinh = thuocTinh;
    }

    public dtoThuocTinhSanPham(String thuocTinhKhac, String quyCach, double trongLuong, double doDay, double dai, int idThuocTinhSanPham) {
        this.thuocTinhKhac = thuocTinhKhac;
        this.quyCach = quyCach;
        this.trongLuong = trongLuong;
        this.doDay = doDay;
        this.dai = dai;
        this.idThuocTinhSanPham = idThuocTinhSanPham;
    }

    public String getThuocTinhKhac() {
        return thuocTinhKhac;
    }

    public void setThuocTinhKhac(String thuocTinhKhac) {
        this.thuocTinhKhac = thuocTinhKhac;
    }

    public String getQuyCach() {
        return quyCach;
    }

    public void setQuyCach(String quyCach) {
        this.quyCach = quyCach;
    }

    public double getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(double trongLuong) {
        this.trongLuong = trongLuong;
    }

    public double getDoDay() {
        return doDay;
    }

    public void setDoDay(double doDay) {
        this.doDay = doDay;
    }

    public double getDai() {
        return dai;
    }

    public void setDai(double dai) {
        this.dai = dai;
    }

    public int getIdThuocTinhSanPham() {
        return idThuocTinhSanPham;
    }

    public void setIdThuocTinhSanPham(int idThuocTinhSanPham) {
        this.idThuocTinhSanPham = idThuocTinhSanPham;
    }

}
