package Excel_sheet;

import AdminViews.Back;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import static Excel_sheet.ExcelDAO.ExcelDetail;

public class CreateExcel extends JFrame {

    private final JTextField filePathField;
    private final JTextField fileNameField;

    public CreateExcel() {
        setTitle("Excel Generator");

        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel filePathLabel = new JLabel("File Path:");
        filePathField = new JTextField();
        JLabel fileNameLabel = new JLabel("File Name:");
        fileNameField = new JTextField();
        JButton generateButton = new JButton("Generate Excel");
        JButton backButton = new JButton("Back");
        JButton exitButton = new JButton("Exit");

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                generateExcel();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    new Back("user");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(filePathLabel);
        panel.add(filePathField);
        panel.add(fileNameLabel);
        panel.add(fileNameField);
        panel.add(generateButton);
        panel.add(backButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    private void generateExcel() {
        String filePath = filePathField.getText();
        String fileName = fileNameField.getText();

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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateExcel();
            }
        });
    }
}
