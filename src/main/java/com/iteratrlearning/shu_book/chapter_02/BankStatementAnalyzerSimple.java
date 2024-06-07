package com.iteratrlearning.shu_book.chapter_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class BankStatementAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String[] args) throws Exception {
    		// ********** 파일 읽어 저장 *************
    		// 파일 접근
            final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
            // 파일의 모든 라인을 읽어서 리스트에 저장
            final List<String> lines = Files.readAllLines(path);
            
            
            // ********* 파싱 *********
            // 콤마로 csv 한줄을 분리한 뒤 입출금 내역 돈을 total에 저장
            double total = 0;
            for(final String line: lines) {
                String[] columns = line.split(",");
                
                // ********* 데이터 처리 *********
                double amount = Double.parseDouble(columns[1]);
                total += amount;
            }

            // ********* 보고서 작성 *********
            System.out.println("The total for all transactions is " + total);
    }
}

