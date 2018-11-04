package com.estafeta.test.ui.helper;

import com.estafeta.test.ui.data.vehicles.VehicleData;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestData {
  public static final String filenameVinXLSX = "src/test/resources/RenaultVehicles.xlsx";
  public static final String filenameLotsXLSX = "src/test/resources/LotsRenaultVehicles.xlsx";
  public static final String dealerDeliveryFile = "src/test/resources/DealerDeliveryFile.xlsx";

  public static void main(String[] args) throws IOException {

    TestData testData = new TestData();
    List<String> vins = testData.generateVinsNumber(6);
    List<String> lots = testData.generateLotsNumber(3);
    testData.createRenaultNewVehiclesForImportByXLSX(vins);
    testData.createLotsFileForImportDillerArmaMotors(vins);
    testData.createLotsFileForImport(lots, vins);
  }

  public List<String> generateVinsNumber(int amount) throws IOException {

    List<String> vinns = new ArrayList<>();
    for (int i = 1; i <= amount; i++) {
      String url = "http://randomvin.com/getvin.php?type=fake";
      HttpClient client = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(url);
      HttpResponse response = client.execute(request);
      String responseBody = EntityUtils.toString(response.getEntity());
      vinns.add(responseBody);
    }
    System.out.println(amount + " Vins generateds sucess");
    return vinns;
  }

  // TODO
  public List<VehicleData> generateVinsNumber2(int amount) throws IOException {

    List<VehicleData> vinns = new ArrayList<>();
    for (int i = 1; i <= amount; i++) {
      String url = "http://randomvin.com/getvin.php?type=fake";
      HttpClient client = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(url);
      HttpResponse response = client.execute(request);
      String responseBody = EntityUtils.toString(response.getEntity());
      vinns.add(new VehicleData.Builder().vin(" " + responseBody + " ").build());
    }
    System.out.println(amount + " Vins generateds sucess");
    return vinns;
  }

  public List<String> generateLotsNumber(int amountLots) {
    List<String> lotNumber = new ArrayList<>();

    int length = 5;
    boolean useLetters = false;
    boolean useNumbers = true;
    for (int i = 1; i <= amountLots; i++) {
      lotNumber.add(RandomStringUtils.random(length, useLetters, useNumbers));
    }
    System.out.println(amountLots + " Lots generateds sucess");
    return lotNumber;
  }

  public void createRenaultNewVehiclesForImportByXLSX(List<String> amountofVins)
      throws IOException {

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("FirstSheet");

    Row rowhead = sheet.createRow((short) 0);
    rowhead.createCell(0).setCellValue("Модель");
    rowhead.createCell(1).setCellValue("VIN");
    rowhead.createCell(2).setCellValue("Full Katashiki");
    rowhead.createCell(3).setCellValue("SFX");

    for (int i = 1; i <= amountofVins.size(); i++) {
      Row row = sheet.createRow((short) i);
      row.createCell(0).setCellValue("");
      row.createCell(1).setCellValue(amountofVins.get(i - 1));
      row.createCell(2).setCellValue("79H");
      row.createCell(3).setCellValue("E2 2 16K 4CR");
    }

    FileOutputStream fileOut = new FileOutputStream(filenameVinXLSX);
    workbook.write(fileOut);
    fileOut.close();
    System.out.println("Your excel file has been generated! " + filenameVinXLSX);
  }

  public void createLotsFileForImport(List<String> lots, List<String> vins) throws IOException {

    int vinsForLots = vins.size() / lots.size();
    System.out.println("amount vins: " + vinsForLots + " for: " + lots + " lots");

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("FirstSheet");

    Row rowhead = sheet.createRow((short) 0);
    rowhead.createCell(0).setCellValue("Număr DU");
    rowhead.createCell(1).setCellValue("Cod VIN");
    rowhead.createCell(2).setCellValue("Ţară");
    rowhead.createCell(3).setCellValue("Cod model");
    rowhead.createCell(4).setCellValue("Requested loading date");

    for (int i = 0; i < vins.size(); i++) {
      Row row = sheet.createRow((short) i + 1);
      row.createCell(1).setCellValue(vins.get(i));
      row.createCell(2).setCellValue("UKRAINA");
      row.createCell(3).setCellValue("JD1");
    }

    int rw = 0;
    for (int i = 0; i < lots.size(); i++) {
      for (int ii = 1; ii <= vinsForLots; ii++) {
        Row row = sheet.getRow((short) ii + rw);
        row.createCell(0).setCellValue(lots.get(i));
      }
      rw = rw + vinsForLots;
    }

    FileOutputStream fileOut = new FileOutputStream(filenameLotsXLSX);
    workbook.write(fileOut);
    fileOut.close();
    System.out.println("Your excel file has been generated! " + filenameLotsXLSX);
  }

  public void createLotsFileForImportDillerArmaMotors(List<String> amountofVins) throws IOException {

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("FirstSheet");

    Row rowhead = sheet.createRow((short) 0);
    rowhead.createCell(0).setCellValue("VIN");
    rowhead.createCell(1).setCellValue("From");
    rowhead.createCell(2).setCellValue("To");
    rowhead.createCell(3).setCellValue("PRIORITY");
    rowhead.createCell(4).setCellValue("DeliveryDeadline");
    rowhead.createCell(5).setCellValue("DispatchDeadline");
    rowhead.createCell(6).setCellValue("Customer");
    rowhead.createCell(7).setCellValue("isPaid");
    rowhead.createCell(8).setCellValue("Client");
    rowhead.createCell(9).setCellValue("DeliveryPoint");
    rowhead.createCell(10).setCellValue("priceDealer");
    rowhead.createCell(11).setCellValue("LogisticOperator");
    ;

    for (int i = 1; i <= amountofVins.size(); i++) {
      Row row = sheet.createRow((short) i);
      row.createCell(0).setCellValue(amountofVins.get(i - 1));
      row.createCell(1).setCellValue("C UA KIEVAVT");
      row.createCell(2).setCellValue("ARM");
      row.createCell(3).setCellValue("high");
      row.createCell(7).setCellValue("1");
      row.createCell(8).setCellValue("1489");
      row.createCell(11).setCellValue("AVT");

    }

    FileOutputStream fileOut = new FileOutputStream(dealerDeliveryFile);
    workbook.write(fileOut);
    fileOut.close();
    System.out.println("Your excel file has been generated! " + dealerDeliveryFile);
  }
}
