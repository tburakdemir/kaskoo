package com.tburakdemir.kaskodegerlistesi.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrencyAndMinWageCsvCreator implements ApplicationContextAware {

    private static Environment environment;

    private static final Double[] XAU_TRY_RATE = {381.189, 392.809, 442.907, 465.535, 468.68, 506.603, 447.834, 454.121, 434.587, 415.067, 453.452, 471.483, 520.959, 494.922, 493.07, 485.431, 501.015, 548.947, 770.057, 779.684, 769.081, 848.68, 913.772, 905.365, 968.452, 970.139, 1015.74, 999.73, 987.688, 977.261, 977.261, 1062.59, 1096.93, 1165.67, 1108.61, 1214.35, 1242.82, 1312.42, 1575.04, 1701.08, 1665.03, 1629.16, 1805.39, 1895.00, 1954.96, 1991.52, 2056.98, 2340.66, 2386.96, 2408.35, 2449.99, 2609.34, 2743.60, 2885.43};
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    
    @Override
    public void setApplicationContext(@SuppressWarnings("null") ApplicationContext applicationContext) {
        environment = applicationContext.getEnvironment();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        saveCurrencyAndMinWageToDatabase();
        //createCsv();
    }

    private static void saveCurrencyAndMinWageToDatabase() {
        try {
          HttpClient client = HttpClient.newHttpClient();
          String url = "http://localhost:8080/api/currencies";
          // Read the csv file
          String csvData = new String(Files.readAllBytes(Paths.get("currency_and_wage_data.csv")));
       
          String[] lines = csvData.split("\n");
          // skip the first line
          for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] data = line.split(",");
            int month = Integer.parseInt(data[0]);
            int year = Integer.parseInt(data[1]);
            BigDecimal usdTry = new BigDecimal(data[2]);
            BigDecimal xauTryg = new BigDecimal(data[3]);
            BigDecimal minWageTry = new BigDecimal("2324.70");

             String bodyJson =  """
                    {
                        "month": "%d",
                        "year": "%d",
                        "usdTry": "%f",
                        "xauTryg": "%f",
                        "minWageTry": "%f"
                    }
                """.formatted(month, year, usdTry, xauTryg, minWageTry);
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(bodyJson, StandardCharsets.UTF_8))
                    .build();
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .thenAccept(System.out::println)
                    .join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private static void createCsv() throws IOException, InterruptedException {
        LocalDate startDate = LocalDate.of(2020, 4, 1);
        LocalDate endDate = LocalDate.now().isBefore(LocalDate.of(2024, 9, 30)) 
                            ? LocalDate.now() 
                            : LocalDate.of(2024, 9, 30);

        Map<YearMonth, Double> usdTryRates = getMonthlyRates("USD", startDate, endDate);
        Map<YearMonth, Double> xauTryRates = getXauTryRates(startDate, endDate);
        Map<YearMonth, Double> minWages = getMinimumWages(startDate, endDate);

        try {
            writeToCSV(usdTryRates, xauTryRates, minWages, "currency_and_wage_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<YearMonth, Double> getXauTryRates(LocalDate startDate, LocalDate endDate) {
        Map<YearMonth, Double> rates = new TreeMap<>();
        
        for (int i =0 ; i < XAU_TRY_RATE.length; i++) {
            rates.put(YearMonth.from(startDate.plusMonths(i)), XAU_TRY_RATE[i]);
        }
        return rates;
    }

    private static Map<YearMonth, Double> getMonthlyRates(String symbol, LocalDate startDate, LocalDate endDate) 
            throws IOException, InterruptedException {
        String API_KEY = environment.getProperty("alpha.vantage.api.key");
        String url = BASE_URL + "?function=FX_MONTHLY&from_symbol=" + symbol + "&to_symbol=TRY&apikey=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(response.body());
        JsonNode timeSeries = root.get("Time Series FX (Monthly)");

        Map<YearMonth, Double> rates = new TreeMap<>();
        if (timeSeries != null) {
            timeSeries.fields().forEachRemaining(entry -> {
                YearMonth date = YearMonth.parse(entry.getKey().substring(0, 7));
                if (!date.isBefore(YearMonth.from(startDate)) && !date.isAfter(YearMonth.from(endDate))) {
                    double closeRate = entry.getValue().get("4. close").asDouble();
                    rates.put(date, closeRate);
                }
            });
        }
        return rates;
    }

    private static Map<YearMonth, Double> getMinimumWages(LocalDate startDate, LocalDate endDate) {
        Map<YearMonth, Double> minWages = new TreeMap<>();
        Map<YearMonth, Double> wageData = new TreeMap<>();
        wageData.put(YearMonth.of(2020, 1), 2324.70);
        wageData.put(YearMonth.of(2020, 7), 2324.70);
        wageData.put(YearMonth.of(2021, 1), 2825.90);
        wageData.put(YearMonth.of(2021, 7), 2825.90);
        wageData.put(YearMonth.of(2022, 1), 4253.40);
        wageData.put(YearMonth.of(2022, 7), 5500.35);
        wageData.put(YearMonth.of(2023, 1), 8506.80);
        wageData.put(YearMonth.of(2023, 7), 11402.32);
        wageData.put(YearMonth.of(2024, 1), 17002.00);

        YearMonth current = YearMonth.from(startDate);
        YearMonth end = YearMonth.from(endDate);
        double lastWage = 0;

        while (!current.isAfter(end)) {
            if (wageData.containsKey(current)) {
                lastWage = wageData.get(current);
            }
            minWages.put(current, lastWage);
            current = current.plusMonths(1);
        }

        return minWages;
    }

    private static void writeToCSV(Map<YearMonth, Double> usdTryRates, Map<YearMonth, Double> xauTryRates,
                                   Map<YearMonth, Double> minWages, String fileName) throws IOException {
        List<String[]> csvData = new ArrayList<>();
        csvData.add(new String[]{"month", "year", "usd_try", "xau_tryg", "min_wage_try"});

        usdTryRates.forEach((date, usdRate) -> {
            String month = String.format("%02d", date.getMonthValue());
            String year = String.valueOf(date.getYear());
            String usdTry = String.format("%.2f", usdRate);
            String xauTry = String.format("%.2f", xauTryRates.getOrDefault(date, 0.0));
            String minWage = String.format("%.2f", minWages.getOrDefault(date, 0.0));
            csvData.add(new String[]{month, year, usdTry, xauTry, minWage});
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String[] row : csvData) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
        System.out.println("Data has been written to " + fileName);
    }
}