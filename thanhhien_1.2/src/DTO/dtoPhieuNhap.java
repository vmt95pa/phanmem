/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bumte
 */
public class dtoPhieuNhap {

    String ghiChuPhieuNhap, idNhaCungCap, idPhieuNhap, tenPhieuNhap, tenNCC;
    Date nagyTaoPhieuNhap;
    double soTienTraTruoc, tongTienPhieuNhap, tongTienConLai;
    int trangThaiPhieuNhap;
    dtoNhaCungCap ncc;
    List<dtoCTPhieuNhap> ctPhieuNhap;

    public dtoPhieuNhap(String ghiChuPhieuNhap, String idNhaCungCap, String idPhieuNhap, String tenPhieuNhap, String tenNCC, Date nagyTaoPhieuNhap, double soTienTraTruoc, double tongTienPhieuNhap, double tongTienConLai, int trangThaiPhieuNhap, dtoNhaCungCap ncc, List<dtoCTPhieuNhap> ctPhieuNhap) {
        this.ghiChuPhieuNhap = ghiChuPhieuNhap;
        this.idNhaCungCap = idNhaCungCap;
        this.idPhieuNhap = idPhieuNhap;
        this.tenPhieuNhap = tenPhieuNhap;
        this.tenNCC = tenNCC;
        this.nagyTaoPhieuNhap = nagyTaoPhieuNhap;
        this.soTienTraTruoc = soTienTraTruoc;
        this.tongTienPhieuNhap = tongTienPhieuNhap;
        this.tongTienConLai = tongTienConLai;
        this.trangThaiPhieuNhap = trangThaiPhieuNhap;
        this.ncc = ncc;
        this.ctPhieuNhap = ctPhieuNhap;
    }

    public dtoNhaCungCap getNcc() {
        return ncc;
    }

    public void setNcc(dtoNhaCungCap ncc) {
        this.ncc = ncc;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public List<dtoCTPhieuNhap> getCtPhieuNhap() {
        return ctPhieuNhap;
    }

    public void setCtPhieuNhap(List<dtoCTPhieuNhap> ctPhieuNhap) {
        this.ctPhieuNhap = ctPhieuNhap;
    }

    public dtoPhieuNhap(String ghiChuPhieuNhap, String idNhaCungCap, String idPhieuNhap, String tenPhieuNhap, Date nagyTaoPhieuNhap, double soTienTraTruoc, double tongTienPhieuNhap, double tongTienConLai, int trangThaiPhieuNhap,String tenNCC, List<dtoCTPhieuNhap> ctPhieuNhap) {
        this.ghiChuPhieuNhap = ghiChuPhieuNhap;
        this.idNhaCungCap = idNhaCungCap;
        this.idPhieuNhap = idPhieuNhap;
        this.tenPhieuNhap = tenPhieuNhap;
        this.nagyTaoPhieuNhap = nagyTaoPhieuNhap;
        this.soTienTraTruoc = soTienTraTruoc;
        this.tongTienPhieuNhap = tongTienPhieuNhap;
        this.tongTienConLai = tongTienConLai;
        this.trangThaiPhieuNhap = trangThaiPhieuNhap;
        this.tenNCC = tenNCC;
        this.ctPhieuNhap = ctPhieuNhap;
    }


    public dtoPhieuNhap(String ghiChuPhieuNhap, String idNhaCungCap, String idPhieuNhap, String tenPhieuNhap, Date nagyTaoPhieuNhap, double soTienTraTruoc, double tongTienPhieuNhap, double tongTienConLai, int trangThaiPhieuNhap) {
        this.ghiChuPhieuNhap = ghiChuPhieuNhap;
        this.idNhaCungCap = idNhaCungCap;
        this.idPhieuNhap = idPhieuNhap;
        this.tenPhieuNhap = tenPhieuNhap;
        this.nagyTaoPhieuNhap = nagyTaoPhieuNhap;
        this.soTienTraTruoc = soTienTraTruoc;
        this.tongTienPhieuNhap = tongTienPhieuNhap;
        this.tongTienConLai = tongTienConLai;
        this.trangThaiPhieuNhap = trangThaiPhieuNhap;
    }

    public String getGhiChuPhieuNhap() {
        return ghiChuPhieuNhap;
    }

    public void setGhiChuPhieuNhap(String ghiChuPhieuNhap) {
        this.ghiChuPhieuNhap = ghiChuPhieuNhap;
    }

    public String getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(String idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getIdPhieuNhap() {
        return idPhieuNhap;
    }

    public void setIdPhieuNhap(String idPhieuNhap) {
        this.idPhieuNhap = idPhieuNhap;
    }

    public String getTenPhieuNhap() {
        return tenPhieuNhap;
    }

    public void setTenPhieuNhap(String tenPhieuNhap) {
        this.tenPhieuNhap = tenPhieuNhap;
    }

    public Date getNagyTaoPhieuNhap() {
        return nagyTaoPhieuNhap;
    }

    public void setNagyTaoPhieuNhap(Date nagyTaoPhieuNhap) {
        this.nagyTaoPhieuNhap = nagyTaoPhieuNhap;
    }

    public double getSoTienTraTruoc() {
        return soTienTraTruoc;
    }

    public void setSoTienTraTruoc(double soTienTraTruoc) {
        this.soTienTraTruoc = soTienTraTruoc;
    }

    public double getTongTienPhieuNhap() {
        return tongTienPhieuNhap;
    }

    public void setTongTienPhieuNhap(double tongTienPhieuNhap) {
        this.tongTienPhieuNhap = tongTienPhieuNhap;
    }

    public double getTongTienConLai() {
        return tongTienConLai;
    }

    public void setTongTienConLai(double tongTienConLai) {
        this.tongTienConLai = tongTienConLai;
    }

    public int getTrangThaiPhieuNhap() {
        return trangThaiPhieuNhap;
    }

    public void setTrangThaiPhieuNhap(int trangThaiPhieuNhap) {
        this.trangThaiPhieuNhap = trangThaiPhieuNhap;
    }

}
