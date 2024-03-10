package Excel_sheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static Excel_sheet.ExcelDAO.ExcelDetail;

public class CreateExcel {

    public CreateExcel() {
        // Get the file path from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path where you want to save the Excel file:");
        String filePath = scanner.nextLine();

        System.out.println("Enter the name of the new file : ");
        String fileName = scanner.next();

        // Create a new Workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new Sheet
            Sheet sheet = workbook.createSheet(fileName);

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Email", "Name", "Game_Name", "Language", "Score"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Get data from database
            String[][] data;
            try {
                data = ExcelDetail();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            // Populate data rows
            int rowNum = 1;
            for (String[] rowData : data) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < rowData.length; i++) {
                    row.createCell(i).setCellValue(rowData[i]);
                }
            }

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath + fileName + ".xlsx")) {
                workbook.write(fileOut);
            }

            System.out.println("Excel file has been created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new CreateExcel();
    }
}
