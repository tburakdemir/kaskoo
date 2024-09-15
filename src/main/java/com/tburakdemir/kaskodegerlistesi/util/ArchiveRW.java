package com.tburakdemir.kaskodegerlistesi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


public class ArchiveRW {

   public static void main(String[] args){
       HttpClient client = HttpClient.newHttpClient();



       String excelFilePath = "C:\\Users\\Burak\\IdeaProjects\\kaskoo\\src\\main\\resources\\archives\\202409R1.xlsx";
       try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
            Workbook workbook = new XSSFWorkbook(fis)) {


           // Get the first sheet from the workbook
           Sheet sheet = workbook.getSheetAt(0);

           // Iterate over rows and columns
           for (Row row : sheet) {
               if(row.getRowNum() < 3 ) continue;

               int brandCode = (int) row.getCell(0).getNumericCellValue();
               int modelCode = (int) row.getCell(1).getNumericCellValue();
               String brand = row.getCell(2).getStringCellValue();
               String model = row.getCell(3).getStringCellValue();
               System.out.println(model);
               System.out.println(brand);
               for (int i = 4; i < 18 ; i++) {
                   if (row.getCell(i).getNumericCellValue() > 0){
                       int year = 2024 - 4 - i;
                       String bodyJson =  """
                                        {
                                            "brandCode": %d,
                                            "modelCode": %d,
                                            "brand": "%s",
                                            "model": "%s",
                                            "year": %d
                                        }
                                    """.formatted(brandCode, modelCode, brand, model, year);
                       System.out.println(bodyJson);
                       HttpRequest request = HttpRequest.newBuilder()
                               .uri(URI.create("http://localhost:8080/api/vehicles"))
                               .header("Content-Type", "application/json")
                               .POST(HttpRequest.BodyPublishers.ofString(bodyJson, StandardCharsets.UTF_8))
                               .build();
                       client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                               .thenApply(HttpResponse::statusCode)
                               .thenAccept(System.out::println)
                               .join();  // Wait for the request to complete

                   }
               }
           }

       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
