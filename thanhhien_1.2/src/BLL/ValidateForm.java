/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bumte
 */
public class ValidateForm {
    
    public static boolean typeString(String str, int minL, int maxL){        
        String kieuChu = ("^[^\\*\\<\\>\\|]{"+minL+","+maxL+"}$");    
//        "^[a-zA-Z\\\\s_-\\(\\)]{"+minL+","+maxL+"}$"
//        ^[a-zA-Z\\sàáạã_-]{3,25}$
        try {
               String txt =  HoTro.unAccent(str);
               System.out.println(txt);
                return txt.matches(kieuChu);
            } catch (Exception e) {
                System.out.println("Lỗi chuyển chữ");
                return false;
            }
    }
}
