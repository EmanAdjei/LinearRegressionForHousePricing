package regressionanalysis;

import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CreateTable extends CreateTownData {

    // DefaultTableModels used to pass on calculations
    DefaultTableModel inputDatamodel;
    DefaultTableModel DataSummaryPart1model;
    DefaultTableModel DataSummaryPart2model;
    DefaultTableModel RegressionAnalysisResultsmodel;
    DefaultTableModel ForecastValuesmodel;
    DefaultTableModel SortedVariablesListmodel;

    // Make results to three decimal places
    DecimalFormat dp = new DecimalFormat("#.####");

    JTable table;
    JScrollPane pane;
    JScrollPane dataSummaryPart1Panel1;
    JScrollPane dataSummaryPart1Panel2;
    JScrollPane analysisResultsPanel;
    JScrollPane forcastPanel;
    JScrollPane sortedPanel;
    RegressionCalculation formula;

    public CreateTable() throws FileNotFoundException {

        dp.setRoundingMode(RoundingMode.HALF_UP);

//        InputData(window);
//        DataSummaryPart1(window);
//        DataSummaryPart2(window);
//        RegressionAnalysisResults(window);
//        ForecastValues(window);
//        SortedVariablesList(window);
    }

    public void NewTable(JPanel window, Object[][] data, String activeTown) {
        
        window.add(InputData(data));
        window.add(DataSummaryPart1());
        window.add(DataSummaryPart2());
        window.add(RegressionAnalysisResults());
        window.add(ForecastValues());
        window.add(SortedVariablesList(activeTown));
    }

    public void UpdateTables() {

        pane.repaint();
        dataSummaryPart1Panel1.repaint();
        dataSummaryPart1Panel2.repaint();
        analysisResultsPanel.repaint();
        forcastPanel.repaint();
        sortedPanel.repaint();

    }

    public void RemoveTables(JPanel window) {

        window.remove(pane);
        window.remove(dataSummaryPart1Panel1);
        window.remove(dataSummaryPart1Panel2);
        window.remove(analysisResultsPanel);
        window.remove(forcastPanel);
        window.remove(sortedPanel);
        System.out.println("Remove Tables Method Called");
    }

    // Methods for creating each table
    private JScrollPane InputData(Object[][] data) {//JPanel window

        String col[] = {"Xi", "Y"};
        inputDatamodel = new DefaultTableModel(data, col);
        table = new JTable(inputDatamodel);
        table.getPreferredScrollableViewportSize();
        pane = new JScrollPane(table);
        //pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setSize(460, 200);
        pane.setLocation(680, 50);
        pane.setVisible(false);
        System.out.println("Input Table Created");
        return pane;
    }

    private JScrollPane DataSummaryPart1() {//JPanel window

        Object data[][] = {{"N", inputDatamodel.getRowCount(), inputDatamodel.getRowCount()},
        {"Mean", dp.format(GetMean(GetSum(0))), dp.format(GetMean(GetSum(1)))},
        {"Variance", dp.format(GetVariance(GetMean(GetSum(0)), 0)), dp.format(GetVariance(GetMean(GetSum(1)), 1))},
        {"Std.Dev.", dp.format(GetStdDev(GetVariance(GetMean(GetSum(0)), 0))), dp.format(GetStdDev(GetVariance(GetMean(GetSum(1)), 1)))}};
        String col[] = {" ", "Xi", "Y"};
        DataSummaryPart1model = new DefaultTableModel(data, col);
        JTable table = new JTable(DataSummaryPart1model);
        dataSummaryPart1Panel1 = new JScrollPane(table);
        table.getPreferredScrollableViewportSize();
        dataSummaryPart1Panel1.setSize(460, 90);
        dataSummaryPart1Panel1.setLocation(680, 100);
        System.out.println("DataSummaryPart1 Table Created");
        return dataSummaryPart1Panel1;
    }

    private JScrollPane DataSummaryPart2() {//JPanel window

        Object data[][] = {{"∑X", dp.format(GetSum(0))},
        {"∑X\u00b2", dp.format(GetSumSquared(0))},
        {"∑Y", dp.format(GetSum(1))},
        {"∑Y\u00b2", dp.format(GetSumSquared(1))},
        {"∑XY", dp.format(GetSumXY())}};
        String col[] = {" ", " "};
        DataSummaryPart2model = new DefaultTableModel(data, col);
        JTable table = new JTable(DataSummaryPart2model);
        dataSummaryPart1Panel2 = new JScrollPane(table);
        dataSummaryPart1Panel2.setSize(460, 106);
        dataSummaryPart1Panel2.setLocation(680, 220);
        return dataSummaryPart1Panel2;
    }

    private JScrollPane RegressionAnalysisResults() {//JPanel window

        Object data[][] = {{dp.format(Math.sqrt(GetRsquared(GetMean(GetSum(0)), GetMean(GetSum(1))))),
            dp.format(GetRsquared(GetMean(GetSum(0)), GetMean(GetSum(1)))),
            dp.format(GetB1(GetMean(GetSum(0)), GetMean(GetSum(1)))),
            dp.format(GetB0(GetMean(GetSum(0)), GetMean(GetSum(1)), GetB1(GetMean(GetSum(0)), GetMean(GetSum(1)))))}};
        String col[] = {"R", "R\u00b2", "Slope", "Y Intercept"};
        RegressionAnalysisResultsmodel = new DefaultTableModel(data, col);
        JTable table = new JTable(RegressionAnalysisResultsmodel);
        analysisResultsPanel = new JScrollPane(table);
        table.getPreferredScrollableViewportSize();
        analysisResultsPanel.setSize(460, 42);
        analysisResultsPanel.setLocation(680, 350);
        return analysisResultsPanel;
    }

    private JScrollPane ForecastValues() {//JPanel window

        String col[] = {"Xi", "Y", "Forecasted Y", "Std. Err. of Estimate"};
        ForecastValuesmodel = new DefaultTableModel(
                ForcastedValuesData(GetB1(GetMean(GetSum(0)), GetMean(GetSum(1))), GetB0(GetMean(GetSum(0)), GetMean(GetSum(1)), GetB1(GetMean(GetSum(0)), GetMean(GetSum(1))))),
                col);
        JTable table = new JTable(ForecastValuesmodel);
        forcastPanel = new JScrollPane(table);
        forcastPanel.setSize(460, 185);
        forcastPanel.setLocation(680, 420);
        return forcastPanel;
    }

    private JScrollPane SortedVariablesList(String activeTown) {//JPanel window

        String col[] = {"Variables in ascending order", "Correlation Strength"};
        SortedVariablesListmodel = new DefaultTableModel(SortedData(activeTown), col);
        JTable table = new JTable(SortedVariablesListmodel);
        sortedPanel = new JScrollPane(table);
        sortedPanel.setSize(460, 138);
        sortedPanel.setLocation(680, 640);
        sortedPanel.add(pane);
        System.out.println("r2 for num of bath ; " + SortedData(activeTown)[0][1]);
        return sortedPanel;
    }

    // Creating Data to fill the relevant tables
    public Object[][] InputDataObjectAge(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Age vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetAge());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] InputDataObjectArea(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Area vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetArea());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] InputDataObjectBathroom(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Bathrooms vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetBathrooms());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] InputDataObjectBedroom(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Bedrooms vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetBedrooms());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] InputDataObjectGarages(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Garages vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetGarages());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] InputDataObjectLivingSpace(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Living Space vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetLivingSpace());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] InputDataObjectRooms(ArrayList<House> townData) {
        Object[][] xYInput = new Object[townData.size()][2];
        ArrayList<Double> data1 = new ArrayList();
        ArrayList<Double> data2 = new ArrayList();

        System.out.println("House Rooms vs Selling Price");

        townData.forEach((house) -> {
            data1.add(house.GetRooms());
            data2.add(house.GetPrice());
        });

        for (int i = 0; i < data1.size(); i++) {
            xYInput[i][0] = data1.toArray()[i];
            xYInput[i][1] = data2.toArray()[i];

        }

        return xYInput;
    }

    public Object[][] ForcastedValuesData(double b1, double b0) {
        Object[][] data = new Object[table.getRowCount()][4];

        for (int i = 0; i < table.getRowCount(); i++) {
            data[i][0] = ((Double) GetValue(table, i, 0));
            data[i][1] = ((Double) GetValue(table, i, 1));
            data[i][2] = dp.format(RegressionY(((Double) GetValue(table, i, 0)), b1, b0));
            data[i][3] = dp.format(Math.abs(  ((Double) GetValue(table, i, 1)) - RegressionY(((Double) GetValue(table, i, 0)), b1, b0)   ));
        }

        return data;
    }

    public Object[][] SortedData(String activeTown) {

        Object sortedData[][] = new Object[7][2];
        System.out.println("----------------Fetching Data for Sorting Method Start----------------");
        Object data[][] = {{"Number of bathrooms", (double) (GetRsquaredForTable(InputDataObjectBathroom(super.Choice(activeTown))))},
        {"Area of the site", (double) (GetRsquaredForTable(InputDataObjectArea(super.Choice(activeTown))))},
        {"Size of living space", (double) (GetRsquaredForTable(InputDataObjectLivingSpace(super.Choice(activeTown))))},
        {"Number of garages", (double) (GetRsquaredForTable(InputDataObjectGarages(super.Choice(activeTown))))},
        {"Number of rooms", (double) (GetRsquaredForTable(InputDataObjectRooms(super.Choice(activeTown))))},
        {"Number of bedrooms", (double) (GetRsquaredForTable(InputDataObjectBedroom(super.Choice(activeTown))))},
        {"Age of property", (double) (GetRsquaredForTable(InputDataObjectAge(super.Choice(activeTown))))}};
        System.out.println("----------------Fetching Data for Sorting Method End----------------");
        // Put second column into an array
        double[] r2 = new double[7];
        for (int i = 0; i < 7; i++) {
            r2[i] = (double) data[i][1];
        }
        Arrays.sort(r2);

        // Match the text with the right value and populate sortedData
        for (int i = 0; i < 7; i++) {
            if ((double) data[i][1] == r2[0]) {
                sortedData[0][1] = r2[0];
                sortedData[0][0] = data[i][0];
            } else if ((double) data[i][1] == r2[1]) {
                sortedData[1][1] = r2[1];
                sortedData[1][0] = data[i][0];
            } else if ((double) data[i][1] == r2[2]) {
                sortedData[2][1] = r2[2];
                sortedData[2][0] = data[i][0];
            } else if ((double) data[i][1] == r2[3]) {
                sortedData[3][1] = r2[3];
                sortedData[3][0] = data[i][0];
            } else if ((double) data[i][1] == r2[4]) {
                sortedData[4][1] = r2[4];
                sortedData[4][0] = data[i][0];
            } else if ((double) data[i][1] == r2[5]) {
                sortedData[5][1] = r2[5];
                sortedData[5][0] = data[i][0];
            } else if ((double) data[i][1] == r2[6]) {
                sortedData[6][1] = r2[6];
                sortedData[6][0] = data[i][0];
            }
        }
        if (r2[5] == r2[6]) {
            sortedData[6][1] = r2[6];
            sortedData[6][0] = data[1][0];
        }
        return sortedData;
    }

    // Methods used for the calculation
    public Object GetValue(JTable table, int row, int col) {
        return table.getModel().getValueAt(row, col);
    }

    public Double LowestX() {
        double x = ((Double) GetValue(table, 0, 0));

        if (x == 0) {
            return x;
        } else {
            for (int i = 0; i < table.getRowCount(); i++) {
                if (x > ((Double) GetValue(table, i, 0))) {
                    x = ((Double) GetValue(table, i, 0));
                }
            }
            return x;
        }
    }

    public Double HighestX() {
        double x = ((Double) GetValue(table, 0, 0));

        for (int i = 0; i < table.getRowCount(); i++) {
            if (x < ((Double) GetValue(table, i, 0))) {
                x = ((Double) GetValue(table, i, 0));
            }
        }
        return x;
    }

    public Double GetMean(double sum) {
        double mean = sum / table.getRowCount();
        return mean;
    }

    public Double GetVariance(double mean, int col) {
        double variance = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            variance += Math.pow((Double) GetValue(table, i, col) - mean, 2);
        }
        variance /= table.getRowCount();
        System.out.println(String.format("The variance is: %s", variance));
        return variance;
    }

    public Double GetStdDev(double variance) {
        double stdDev = Math.sqrt(variance);
        System.out.println(String.format("The standard deviation is: %s", stdDev));
        return stdDev;
    }

    public Double GetSum(int col) {
        double sum = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            sum += ((Double) GetValue(table, i, col));
        }
        return sum;
    }

    public Double GetSumSquared(int col) {
        double sum = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            sum += Math.pow((Double) GetValue(table, i, col), 2);
        }
        return sum;
    }

    public Double GetSumXY() {
        double sum = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            sum += ((Double) GetValue(table, i, 0) * (Double) GetValue(table, i, 1));
        }
        return sum;
    }

    public Double GetB1(double meanx, double meany) {
        double b1;
        double sXY = 0;
        double sXX = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            sXY += (((Double) GetValue(table, i, 0) - meanx) * ((Double) GetValue(table, i, 1) - meany));
            sXX += Math.pow(((Double) GetValue(table, i, 0) - meanx), 2);
        }

        b1 = sXY / sXX;

        return b1;
    }

    public Double GetB0(double meanx, double meany, double B1) {

        double b0 = meany - (B1 * meanx);

        return b0;
    }

    public Double GetRsquared(double meanx, double meany) {
        double r2;
        double sXY = 0;
        double sXX = 0;
        double sYY = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            sXY += ((Double) GetValue(table, i, 0) - meanx) * ((Double) GetValue(table, i, 1) - meany);
            sXX += Math.pow(((Double) GetValue(table, i, 0) - meanx), 2);
            sYY += Math.pow(((Double) GetValue(table, i, 1) - meany), 2);
        }

        r2 = Math.pow(sXY, 2) / (sXX * sYY);

        return r2;
    }

    public Double RegressionY(double x, double b1, double b0) {
        double y;

        y = (b1 * x) + b0;

        return y;
    }

    public Double StdError(double b1, double b0) {

        double stdErr = 0;

        for (int i = 0; i < table.getRowCount(); i++) {

            stdErr += Math.pow((RegressionY(((Double) GetValue(table, i, 0)), b1, b0)) - ((Double) GetValue(table, i, 1)), 2);

        }

        stdErr /= (table.getRowCount() - 2);

        Math.sqrt(stdErr);

        return stdErr;
    }

    public Double GetRsquaredForTable(Object[][] data) {
        double r2;
        double sXY = 0;
        double sXX = 0;
        double sYY = 0;

        double meanx = 0;
        double meany = 0;

        for (int i = 0; i < 10; i++) {
            meanx += (Double) data[i][0];
            meany += (Double) data[i][1];
        }
        meanx /= 10;
        meany /= 10;

        for (int i = 0; i < 10; i++) {
            sXY += ((Double) data[i][0] - meanx) * ((Double) data[i][1] - meany);
            sXX += Math.pow(((Double) data[i][0] - meanx), 2);
            sYY += Math.pow(((Double) data[i][1] - meany), 2);
        }

        r2 = Math.pow(sXY, 2) / (sXX * sYY);

        return r2;
    }

}
