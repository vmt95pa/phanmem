/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;

/**
 *
 * @author bumte
 */
public class CopyFileExample {

    public static void copyFolder(File sourceFolder, File targetFolder)
            throws IOException {
        // Check neu sourceFolder la mot thu muc hoac file
        // neu sourceFolder la file thi copy den thu muc dich
        if (sourceFolder.isDirectory()) {
            // Xac nhan neu targetFolder chua ton tai thi tao moi 
            if (!targetFolder.exists()) {
                targetFolder.mkdir();
                System.out.println("Thu muc da duoc tao " + targetFolder);
            }
            // Liet ke tat ca cac file va thu muc trong sourceFolder
            String files[] = sourceFolder.list();
            for (String file : files) {
                System.out.println(file);
                File srcFile = new File(sourceFolder, file);
                File tarFile = new File(targetFolder, file);
                // goi lai phuong thuc copyFolder
                copyFolder(srcFile, tarFile);
            }
        } else {
            System.out.println("Thu muc mới " + targetFolder.toPath());
            System.out.println("Thư mục nguồn: " + sourceFolder.toPath());
//             copy file tu thuc muc nguon den thu muc dich
            Files.copy(sourceFolder.toPath(), targetFolder.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File da duoc copy " + targetFolder);
        }
    }

    public static File saveMap() {
        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home/me/Documents"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                return chooser.getSelectedFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
