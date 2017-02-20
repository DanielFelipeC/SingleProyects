/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dfcastellanosc
 */
public class Excel {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {
        Thread a;
        
//        String nameFile = "C:\\Users\\dfcastellanosc.SOPORTECOS\\Downloads\\Files\\Información Etapa Productiva.xlsx";

//        Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + nameFile);

        FileInputStream file = new FileInputStream(new File("C:\\Users\\dfcastellanosc.SOPORTECOS\\Documents\\registroempleados.xlsx"));

        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            Row row;

            while (rowIterator.hasNext()) {

                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                Cell celda;

                while (cellIterator.hasNext()) {

                    celda = cellIterator.next();

                    switch (celda.getCellType()) {

                        case Cell.CELL_TYPE_NUMERIC:

                            if (DateUtil.isCellDateFormatted(celda)) {

                                if (celda.getColumnIndex() == 17) {
                                    System.out.println("|" + celda.getDateCellValue() + "|");
                                } else {
                                    System.out.print("|" + celda.getDateCellValue() + "|");
                                }

                            } else {

                                Double ds = celda.getNumericCellValue();
                                Long pt = ds.longValue();

                                if (celda.getColumnIndex() == 17) {
                                    System.out.println("|" + pt + "|");
                                } else {
                                    System.out.print("|" + pt + "|");
                                }
                            }
                            break;

                        case Cell.CELL_TYPE_STRING:
                            if (celda.getColumnIndex() == 17) {
                                System.out.println("|" + celda.getStringCellValue() + "|");
                            } else {
                                System.out.print("|" + celda.getStringCellValue() + "|");
                            }

                            break;

                        case Cell.CELL_TYPE_BOOLEAN:

                            if (celda.getColumnIndex() == 17) {
                                System.out.println("|" + celda.getBooleanCellValue() + "|");
                            } else {
                                System.out.print("|" + celda.getBooleanCellValue() + "|");
                            }

                            break;

                    }

                }

            }
            workbook.close();
        }

    }

}
