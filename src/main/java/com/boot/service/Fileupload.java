package com.boot.service;

import com.boot.model.DataInsertion;
import com.boot.repository.DataInsertionRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class Fileupload {

    private final DataInsertionRepository repository;

    public Fileupload(DataInsertionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public int uploadFilesIntoDB(MultipartFile file) throws Exception {

        List<DataInsertion> records = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            // Start at row 1 because row 0 contains column headings
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                DataInsertion data = new DataInsertion();

                data.setName(formatter.formatCellValue(row.getCell(0)));
                data.setSurname(formatter.formatCellValue(row.getCell(1)));
                data.setEmail(formatter.formatCellValue(row.getCell(2)));
                data.setPassword(formatter.formatCellValue(row.getCell(3)));
                data.setRole(formatter.formatCellValue(row.getCell(4)));
                records.add(data);
            }
        }
        repository.saveAll(records);
        return records.size();
    }

}
