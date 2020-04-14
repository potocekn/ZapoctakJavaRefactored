package writer;//package net.codejava.excel;

import calculations.Calculator;
import calculations.TreeCalculator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import treeParts.ds.Asset;
import treeParts.ds.Names;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Class for creating and writing output file.*/
public class ExcelWriter {

    Calculator data;
    public ExcelWriter(Calculator data)
    {
        this.data = data;
    }

    /**This method writes row filled with years which are marked in input data.*/
    private void writeYears(XSSFSheet sheet, int row, int startCol)
    {
        String key = (String) data.getAdditionalData().keySet().toArray()[0];
        String[] years = key.split("_");
        int beginning = Integer.parseInt(years[0]);
        int ending = Integer.parseInt(years[1]);
        Row newRow = sheet.createRow(row);
        for (int i = beginning; i <= ending; i++)
        {
            Cell cell = newRow.createCell(startCol);
            startCol++;
            cell.setCellValue(i);
        }
    }

    /**this method writes one row into given sheet filled with given data*/
    private void writeRow(XSSFSheet sheet, int row, int startCol, String rowName, double[] args)
    {
        Row newRow = sheet.createRow(row);

        //creating name of row
        if (rowName != null)
        {
            Cell cell = newRow.createCell(startCol);
            cell.setCellValue(rowName);
        }

        //setting values
        if (args != null)
        {
            for (double field : args) {
                Cell cell = newRow.createCell(++startCol);

                cell.setCellValue(field);

            }
        }

    }
    private List<Asset> getAF1List(TreeCalculator af1Tree) {
        List<Asset> result = new ArrayList<>();
        result.add(af1Tree.getDS().getVvn_nn().getEs().getAssets().get(Names.HV_TRAFO.getName()));
        result.add(af1Tree.getDS().getVvn_nn().getEs().getAssets().get(Names.HV_FIELD.getName()));
        result.add(af1Tree.getDS().getVvn_nn().getEs().getAssets().get(Names.PROTECTIONS.getName()));
        result.add(af1Tree.getDS().getVvn_nn().getEs().getAssets().get(Names.OWN_CONSUMPTION.getName()));
        result.add(af1Tree.getDS().getVvn_nn().getEs().getAssets().get(Names.BUILDING.getName()));
        result.add(af1Tree.getDS().getVvn_nn().getEs().getAssets().get(Names.MV_FIELD.getName()));
        result.add(af1Tree.getDS().getVvn().getVVN_vzduch().getAssets().get(Names.VVN_VEDENIE.getName()));
        result.add(af1Tree.getDS().getVvn().getVVN_vzduch().getAssets().get(Names.VVN_STOZIAR.getName()));
        result.add(af1Tree.getDS().getVn().getVn_vzduch().getAssets().get(Names.VN_OCEL.getName()));
        result.add(af1Tree.getDS().getVn().getVn_vzduch().getAssets().get(Names.VN_DREVO.getName()));
        result.add(af1Tree.getDS().getVn().getVn_vzduch().getAssets().get(Names.VN_BETON.getName()));
        result.add(af1Tree.getDS().getVn().getVn_kabel().getAssets().get(Names.VN_OLEJ.getName()));
        result.add(af1Tree.getDS().getVn().getVn_kabel().getAssets().get(Names.VN_PLAST.getName()));
        result.add(af1Tree.getDS().getVn_nn().getDts().getAssets().get(Names.DTS_MUROVANA.getName()));
        result.add(af1Tree.getDS().getVn_nn().getDts().getAssets().get(Names.DTS_KIOSK.getName()));
        result.add(af1Tree.getDS().getVn_nn().getDts().getAssets().get(Names.DTS_STOZIAROVA.getName()));
        result.add(af1Tree.getDS().getNn().getNn_vzduch().getAssets().get(Names.NN_VEDENIE.getName()));
        result.add(af1Tree.getDS().getNn().getNn_kabel().getAssets().get(Names.NN_IZOLOVANE.getName()));

        return result;
    }

    /**This method writes data for AF1.*/
    private void writeAF1(XSSFSheet sheet)
    {
        List<Asset> list = getAF1List(data.getAf1Tree());
        writeYears(sheet,0,2);
        int row = 1;
        for (Asset asset: list) {
            writeRow(sheet, row++, 1, asset.name, asset.amount);
        }
        row++;
        writeRow(sheet,row++,1,"Netz",data.getAf1Tree().getDS().getNetz().getPlannedFinance());

    }

    private List<Asset> getAF4List(TreeCalculator af4Tree) {
        List<Asset> result = new ArrayList<>();
        result.add(af4Tree.getDS().getVvn().getVVN_vzduch().getAssets().get(Names.VVN_VEDENIE.getName()));
        result.add(af4Tree.getDS().getVvn().getVVN_vzduch().getAssets().get(Names.VVN_STOZIAR.getName()));
        result.add(af4Tree.getDS().getVn().getVn_vzduch().getAssets().get(Names.VN_OCEL.getName()));
        result.add(af4Tree.getDS().getVn().getVn_vzduch().getAssets().get(Names.VN_DREVO.getName()));
        result.add(af4Tree.getDS().getVn().getVn_vzduch().getAssets().get(Names.VN_BETON.getName()));
        result.add(af4Tree.getDS().getVn().getVn_kabel().getAssets().get(Names.VN_OLEJ.getName()));
        result.add(af4Tree.getDS().getVn().getVn_kabel().getAssets().get(Names.VN_PLAST.getName()));
        result.add(af4Tree.getDS().getVn_nn().getDts().getAssets().get(Names.DTS_KIOSK.getName()));
        result.add(af4Tree.getDS().getVn_nn().getDts().getAssets().get(Names.DTS_STOZIAROVA.getName()));
        result.add(af4Tree.getDS().getNn().getNn_vzduch().getAssets().get(Names.NN_VEDENIE.getName()));
        result.add(af4Tree.getDS().getNn().getNn_kabel().getAssets().get(Names.NN_IZOLOVANE.getName()));

        return result;
    }

    /**This method writes data for AF4.*/
    private void writeAF4(XSSFSheet sheet)
    {
        List<Asset> list = getAF4List(data.getAf4Tree());
        writeYears(sheet,0,2);
        int row = 1;
        for (Asset asset: list) {
            writeRow(sheet,row++,1,asset.name,asset.amount);
        }
        row++;
        writeRow(sheet,row++,1,"Netz",data.getAf4Tree().getDS().getNetz().getPlannedFinance());

    }

    /**This method writes data for AF6.*/
    private void writeAF6(XSSFSheet sheet)
    {
        writeYears(sheet,0,2);
        int row = 1;
        writeRow(sheet,row++,1,"Netz",data.getAf6Tree().getDS().getNetz().getPlannedFinance());

    }

    /**This method writes the tree data into excel file.*/
    public void write(String path) throws IOException {
        try
        {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("AF1");
            writeAF1(sheet);

            sheet =  workbook.createSheet("AF4");
            writeAF4(sheet);
            sheet =  workbook.createSheet("AF6");
            writeAF6(sheet);

            try (FileOutputStream outputStream = new FileOutputStream(path+"/AsimData.xlsx")) {
                workbook.write(outputStream);
            }
            System.out.println("Output file successfully created as AsimData.xlsx in given directory.");
        }
        catch (IOException e)
        {
            System.out.println("Troubles occured while trying to write into output file.");
        }

    }

}