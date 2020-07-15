/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.dtoSanPham;
import DTO.dtoThuocTinhSanPham;
import GUI.SanPhamForm;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static GUI.SanPhamForm.*;

/**
 *
 * @author bumte
 */
public class ExcelIO {
    
    public static boolean ImportSanPham(File file) {
        XSSFWorkbook workbook = null;
        try {
            FileInputStream excelFile = new FileInputStream(file);
            
            try {
                workbook = new XSSFWorkbook(excelFile);
            } catch (IOException ex) {
                Logger.getLogger(ExcelIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();

//            Row firstRow = iterator.next();
//            Cell firstCell = firstRow.getCell(0);
//            System.out.println(firstCell.getStringCellValue());
            List<dtoSanPham> listSP = new ArrayList<>();
            dtoSanPham sanPham = null;
            dtoThuocTinhSanPham tt;
            String dvt = "mm;m;kg;";
            while (iterator.hasNext()) {
                int idtt = new Date().hashCode();
                String idsp = "PN" + idtt;
                Row currentRow = iterator.next();
                int rowEr = 0;
                
                if (currentRow.getRowNum() > 6) {
                    
                    try {
                        
                        String id = ((String) getCellValue(currentRow.getCell(2))) == null ? idsp : (String) getCellValue(currentRow.getCell(2));
                        String tenNCC = (String) getCellValue(currentRow.getCell(3));
                        String tenDanhMuc = (String) getCellValue(currentRow.getCell(4));
                        String tenSanPham = (String) getCellValue(currentRow.getCell(5));
                        String quyCach = (String) getCellValue(currentRow.getCell(6));
                        double doDay = (double) getCellValue(currentRow.getCell(7));
                        double doDai = (double) getCellValue(currentRow.getCell(8));
                        double trongLuong = (double) getCellValue(currentRow.getCell(9));
                        String dvGiaLeSi = (String) getCellValue(currentRow.getCell(10));
                        double giaSi = (double) getCellValue(currentRow.getCell(11));
                        double giaLe = (double) getCellValue(currentRow.getCell(12));
                        double sl = (double) getCellValue(currentRow.getCell(13));
                        String thongTinKhac = (String) getCellValue(currentRow.getCell(14));
                        
                        String dv = dvt + dvGiaLeSi;
                        tt = new dtoThuocTinhSanPham(thongTinKhac, quyCach, trongLuong, doDay, doDai, idtt);
                        sanPham = new dtoSanPham(id, tenNCC, tenDanhMuc, tenSanPham, dv, giaSi, giaLe, sl, idtt, tt);
                        listSP.add(sanPham);
                        
                    } catch (Exception e) {
                         lblDung.setText(String.valueOf(currentRow.getRowNum()));
                        break;
                    }
                }

//                customer.setId(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
//                customer.setName(currentRow.getCell(1).getStringCellValue());
//                customer.setEmail(currentRow.getCell(2).getStringCellValue());
//                listSP.add(customer);
            }
            lblSS.setText(String.valueOf(listSP.size()));
           SanPhamForm.listExcel = listSP;
            for (dtoSanPham sp : listSP) {
                System.out.println(sp.getIdSanPham());
            }
            sanPhamCtrl.fillBangSP(SanPhamForm.tblSPExecel, listSP);
            workbook.close();
            System.out.println("Đã đóng");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.close();
            System.out.println("Đã đóng");
        } catch (IOException ex) {
            Logger.getLogger(ExcelIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = "";
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        
        return cellValue;
    }
    
}
