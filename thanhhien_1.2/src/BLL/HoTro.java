/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import GUI.ThongBao;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author bumte
 */
public class HoTro {

    static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static Date string(String ngay) {

        try {
            return df.parse(ngay);
        } catch (ParseException ex) {
            System.out.println("Lỗi Chuyển ngày!");

        }
        return null;
    }

    public static String GetNgay(Date date) {
        return df.format(date);
    }

//    public static String doubleToString(double so) {
//
//        return NumberFormat.getNumberInstance().format(so);
//    }
//
//    public static double stringToDouble(String chu) {
//        try {
//            return NumberFormat.getNumberInstance().parse(chu).doubleValue();
//        } catch (ParseException ex) {
//            System.out.println("lỗi chuyển số");
//            return 0;
//        }
//    }

    public static String so(String chu, int len) {
        String so = chu;
        if (!chu.matches("^[0-9]*$") || chu.length() > len) {
            so = chu.substring(0, chu.length() - 1);
        }
        return so;
    }

    public static Period soSanhDate(String startDate, String endDate) {
        /*
         *starDate & endDate : dd/MM/yyyy
         */
        String beginSplit[] = startDate.split("/");
        String endDateSplit[] = endDate.split("/");
        LocalDate start = LocalDate.of(Integer.parseInt(beginSplit[2]), Integer.parseInt(beginSplit[1]), Integer.parseInt(beginSplit[0]));
        LocalDate end = LocalDate.of(Integer.parseInt(endDateSplit[2]), Integer.parseInt(endDateSplit[1]), Integer.parseInt(endDateSplit[0]));
        Period diffirent = Period.between(start, end);
        /*
         *use diffirent.getYears(getDay...)
         */
        return diffirent;
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }

    public static String dateTimeString(Date date) {
        try {
            Timestamp ts = new Timestamp(date.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return formatter.format(ts);
        } catch (Exception e) {
            return null;
        }

    }

    public static String dateTimeMySQL(Date date) {
        try {
            Timestamp ts = new Timestamp(date.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return formatter.format(ts);
        } catch (Exception e) {
            return null;
        }

    }

    public static void filter(String timKiem, JTable tbl) {
        DefaultTableModel dm = (DefaultTableModel) tbl.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        tbl.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(timKiem));
    }

    public static void ClockExample(JLabel time, JLabel date) {

        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Calendar cal = new GregorianCalendar();
                        int second = cal.get(Calendar.SECOND);
                        String ss = second < 10 ? "0" + second: "" + second;
                        int minute = cal.get(Calendar.MINUTE);
                        String mm = minute < 10 ? "0" + minute : "" + minute;
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        String hh = hour < 10 ? "0" + hour : "" + hour;
                        String thu;
                        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek == 1) {
                            thu = "Chủ nhật";
                        } else {
                            thu = "Thứ " + Integer.toString(dayOfWeek);
                        }
                        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);

                        time.setText(hh + ":" + mm + ":" + ss);
//                    timeSystemBD.setText(hour + ":" + minute + ":" + second);
//                    timeSystemTK.setText(hour + ":" + minute + ":" + second);
                        date.setText(thu + " ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);
//                    date.setText(thu + dayOfWeek + " ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    public static String ThuocTinhSP(String dai, String day, String trongLuong, String quyCach, String tt1, String tt2) {
        System.out.println(dai + day + trongLuong + quyCach + tt1 + tt2);

        String thuocTinhSP = "";
        thuocTinhSP += day.trim().isEmpty() ? "" : " Dày: " + day;
        thuocTinhSP += trongLuong.trim().isEmpty() ? "" : " Trọng lượng: " + trongLuong;
        thuocTinhSP += dai.trim().isEmpty() ? "" : " Dài: " + dai;
        thuocTinhSP += quyCach.trim().isEmpty() ? "" : " " + quyCach;
        thuocTinhSP += tt1.trim().isEmpty() ? "" : " " + tt1;
        thuocTinhSP += tt2.trim().isEmpty() ? "" : " " + tt2;
        return thuocTinhSP;
    }

    public static String DoubleToString(double so) {
        String Chu = "";
        try {
            DecimalFormat df = new DecimalFormat("#,###,##0.##");
            Chu = df.format(so);
        } catch (Exception e) {
            ThongBao.ThongBao("Lỗi chuyển đổi số tiền: " + so, "Lỗi");
            Chu = String.valueOf(so);
        }
        return Chu;
    }

    public static double StringToDouble(String str) {
        double so = 0;
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            so = Double.parseDouble(str.replace(",", "."));
        } catch (Exception e) {
            System.out.println("Lỗi chuyển sô");
        }
        return so;
    }

    public static String StringDouble(double so) {
        String chuSo = "";
        try {
            DecimalFormat df = new DecimalFormat("#,###,##0.00");
            chuSo = df.format(so);
        } catch (Exception e) {
            ThongBao.ThongBao("Lỗi chuyển đổi số: " + so, "Lỗi");
            chuSo = String.valueOf(so);
        }
        return chuSo;
    }

    public static void fillCombobox(List<String> list, JComboBox cbb) {
        DefaultComboBoxModel cbbBoxModel = (DefaultComboBoxModel) cbb.getModel();
        cbbBoxModel.removeAllElements();
        for (String item : list) {
            cbbBoxModel.addElement(item);
        }
    }

}
