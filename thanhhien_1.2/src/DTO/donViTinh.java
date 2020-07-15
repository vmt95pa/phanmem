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
public class donViTinh {

    String day, dai, trongLuong, dvtSi,dvtLe ,donViTinh;

    public String getDvtSi() {
        return dvtSi;
    }

    public void setDvtSi(String dvtSi) {
        this.dvtSi = dvtSi;
    }

    public String getDvtLe() {
        return dvtLe;
    }

    public void setDvtLe(String dvtLe) {
        this.dvtLe = dvtLe;
    }

    
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getDai() {
        return dai;
    }

    public void setDai(String dai) {
        this.dai = dai;
    }

    public String getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(String trongLuong) {
        this.trongLuong = trongLuong;
    }

   

    public donViTinh(String donViTinh) {
        String[] str = donViTinh.split(";");
        this.day = str[0];
        this.dai = str[1];
        this.trongLuong = str[2];
        this.dvtSi = str[3];
        this.dvtLe = str[4];
    }

    public donViTinh(String day, String dai, String trongLuong, String dvtSi,String dvtLe) {
        this.donViTinh = day + ";" + dai + ";" + trongLuong + ";" + dvtSi + ";" + dvtLe ;
    }

}
