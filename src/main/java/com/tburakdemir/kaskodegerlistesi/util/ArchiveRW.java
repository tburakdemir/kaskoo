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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ArchiveRW {

   public static void main(String[] args){

       String excelFilePath = "C:\\Users\\Burak\\IdeaProjects\\kaskoo\\src\\main\\resources\\archives\\202409R1.xlsx";
       ArchiveRW archiveRW = new ArchiveRW();
       // archiveRW.postVehicleReqWithArchiveFile(excelFilePath);
      // archiveRW.postInsuranceReqWithArchiveFile();
      // Get all files in the archives folder
      File folder = new File("C:\\Users\\Burak\\IdeaProjects\\kaskoo\\src\\main\\resources\\archives");
      File[] listOfFiles = folder.listFiles();
      //archiveRW.postInsuranceReqWithArchiveFiles(listOfFiles);


   }

   public void postVehicleReqWithArchiveFile(String filePath) {

       HttpClient client = HttpClient.newHttpClient();
       try (FileInputStream fis = new FileInputStream(new File(filePath));
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
                       int year = 2024 + 4 - i;
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

   public void postInsuranceReqWithArchiveFiles(File[] filePaths){
    //Making this thread pool
    ExecutorService executorService = Executors.newFixedThreadPool(filePaths.length);
    for (var filePath : filePaths )
        executorService.execute(() -> postInsuranceReqWithArchiveFile(filePath.getAbsolutePath()));

   }

   private void postInsuranceReqWithArchiveFile(String filePath){
       int year, month;
       HttpClient client = HttpClient.newHttpClient();

       Pattern pattern = Pattern.compile("\\\\(\\d{6})");
       Matcher matcher = pattern.matcher(filePath);

       if (matcher.find()) {
           String yearMonth = matcher.group(1);  // Get the first capturing group (YYYYMM)
           year =  Integer.parseInt(yearMonth.substring(0, 4)); // First 4 characters for year
           month =  Integer.parseInt(yearMonth.substring(4, 6)); // Next 2 characters for month
           System.out.println("Year: " + year);
           System.out.println("Month: " + month);
       } else {
           System.out.println("No year and month found in the string.");
           return;
       }

       try (FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis)) {


           // Get the first sheet from the workbook
           Sheet sheet = workbook.getSheetAt(0);

           // Iterate over rows and columns
           for (Row row : sheet) {
               if(row.getRowNum() < 3 ) continue;

               int brandCode = (int) row.getCell(0).getNumericCellValue();
               int modelCode = (int) row.getCell(1).getNumericCellValue();
               for (int i = 4; i < 18 ; i++) {
                   if (row.getCell(i).getNumericCellValue() > 0){
                       int modelYear = 2024 + 4 - i;
                       double tlPrice = row.getCell(i).getNumericCellValue();
                       String bodyJson =  """
                                        {
                                            "brandCode": %d,
                                            "modelCode": %d,
                                            "modelYear": %d,
                                            "month": "%d",
                                            "year": "%d",
                                            "tlPrice": "%f"
                                        }
                                    """.formatted(brandCode, modelCode, modelYear, month, year, tlPrice);
                       System.out.println(bodyJson);
                       HttpRequest request = HttpRequest.newBuilder()
                               .uri(URI.create("http://localhost:8080/api/insurances"))
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
