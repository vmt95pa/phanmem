/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.daoUserLogin;
import DTO.dtoUserLogin;
import GUI.ThongBao;

/**
 *
 * @author bumte
 */
public class login {

    public static boolean login(String user, String pass) {
        dtoUserLogin userLogin = daoUserLogin.getUserLogin(user);
        if (userLogin == null) {
            ThongBao.ThongBao("Người dùng không tồn tại", "Thông báo");
            return false;
        } else {
            if (pass.trim().equals(userLogin.getPassw())) {
                return true;
            }
        }
        ThongBao.ThongBao("Sai mật khẩu", "Thông báo");

        return false;
    }
}
