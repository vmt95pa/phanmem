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
public class dtoSanPham {

    String idSanPham, tenNhaCungCap, tenDanhMuc, tenSanPham, donViTinh;
    double giaSi, giaLe, soLuong;
    int idThuocTinh;
    dtoThuocTinhSanPham ttSanPham;

    public dtoSanPham(String idSanPham, String tenNhaCungCap, String tenDanhMuc, String tenSanPham, String donViTinh, double giaSi, double giaLe, double soLuong, int idThuocTinh) {
        this.idSanPham = idSanPham;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenDanhMuc = tenDanhMuc;
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.giaSi = giaSi;
        this.giaLe = giaLe;
        this.soLuong = soLuong;
        this.idThuocTinh = idThuocTinh;
    }

    public dtoSanPham(String idSanPham, String tenNhaCungCap, String tenDanhMuc, String tenSanPham, String donViTinh, double giaSi, double giaLe, double soLuong, int idThuocTinh, dtoThuocTinhSanPham ttSanPham) {
        this.idSanPham = idSanPham;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenDanhMuc = tenDanhMuc;
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.giaSi = giaSi;
        this.giaLe = giaLe;
        this.soLuong = soLuong;
        this.idThuocTinh = idThuocTinh;
        this.ttSanPham = ttSanPham;
    }

    public dtoThuocTinhSanPham getTtSanPham() {
        return ttSanPham;
    }

    public void setTtSanPham(dtoThuocTinhSanPham ttSanPham) {
        this.ttSanPham = ttSanPham;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public double getGiaSi() {
        return giaSi;
    }

    public void setGiaSi(double giaSi) {
        this.giaSi = giaSi;
    }

    public double getGiaLe() {
        return giaLe;
    }

    public void setGiaLe(double giaLe) {
        this.giaLe = giaLe;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdThuocTinh() {
        return idThuocTinh;
    }

    public void setIdThuocTinh(int idThuocTinh) {
        this.idThuocTinh = idThuocTinh;
    }
    
}
