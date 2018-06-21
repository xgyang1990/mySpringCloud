package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelOperationUtils {

    private static DataFormatter formatter = new DataFormatter();

    //default sheet is 2
    public Map<Integer, String> getColumn(File file, int column) {
        return getColumn(file, 2, column);
    }


    public Map<Integer, String> getColumn(File file, int sheetIndex, int column) {
        Map<Integer, String> res = new HashMap<Integer, String>();
        Workbook book = null;
        try {
            book = new XSSFWorkbook(new FileInputStream(file));
            Sheet sheet = book.getSheetAt(sheetIndex);
            for (int i = 2; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                res.put(i - 2, formatter.formatCellValue(row.getCell(column)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (book != null) {
                    book.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    //todo 同一列的数据可能存在重复
    private void rmDuplicated(){};
}
