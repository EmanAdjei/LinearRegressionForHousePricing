package regressionanalysis;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DrawGraph {

    public XYSeriesCollection dataset;
    private XYSeries scatter;
    public XYSeries userAdded;
    public XYSeries lineData;
    private String xAxis = "";
    private String townChoice = "";

    public JFreeChart chart;
    public ChartPanel chartPanel;

    public DrawGraph() {

    }

    public void NewGraph(JPanel window, String town, String xaxis, XYDataset dataset) {

        townChoice = town;
        xAxis = xaxis;

        //XYDataset dataset = createTownADataset();
        // Create bestFitChart
        chart = ChartFactory.createXYLineChart(
                townChoice + "'s Selling Price vs. " + xAxis,
                SetxAxis(xAxis), "Selling Price / £100,000", dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        chart.getTitle().setFont(new Font("Calibri", Font.PLAIN, 24));

        //Changes background color
        XYPlot plotter = (XYPlot) chart.getPlot();
        plotter.setBackgroundPaint(new Color(238, 238, 238));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // The scatter plotter values
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        plotter.setRenderer(renderer);

        // The line plotter values
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plotter.setRenderer(renderer);

        // The userAdded scatter plotter values
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShapesVisible(2, true);
        plotter.setRenderer(renderer);

        // The TrainTown scatter plotter values
        renderer.setSeriesLinesVisible(3, false);
        renderer.setSeriesShapesVisible(3, true);
        renderer.setSeriesPaint(3, new Color(165, 94, 234));
        plotter.setRenderer(renderer);

        chartPanel = new ChartPanel(chart); 
        chartPanel.setSize(600, 600);
        chartPanel.setLocation(40, 180);
        window.add(chartPanel);
    }

    public void RemoveGraph(JPanel window) {
        window.remove(chartPanel);
    }

    public XYDataset AgeDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetAge() + "," + house.GetPrice());
            scatter.add(house.GetAge(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYDataset AreaDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetArea() + "," + house.GetPrice());
            scatter.add(house.GetArea(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYDataset BathroomDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetBathrooms() + "," + house.GetPrice());
            scatter.add(house.GetBathrooms(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYDataset BedroomsDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetBedrooms() + "," + house.GetPrice());
            scatter.add(house.GetBedrooms(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYDataset GaragesDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetGarages() + "," + house.GetPrice());
            scatter.add(house.GetGarages(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYDataset LivingSpaceDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetLivingSpace() + "," + house.GetPrice());
            scatter.add(house.GetLivingSpace(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYDataset RoomsDataset(ArrayList<House> townData) {
        dataset = new XYSeriesCollection();

        // scatter plotter
        scatter = new XYSeries("houses in " + townChoice);

        townData.forEach((house) -> {
            System.out.println(house.GetRooms() + "," + house.GetPrice());
            scatter.add(house.GetRooms(), house.GetPrice());
        });
        dataset.addSeries(scatter);

        // line plotter
        lineData = new XYSeries("Forecast Line");

        dataset.addSeries(lineData);

        // userAdded scatter plotter
        userAdded = new XYSeries("Added Houses");

        dataset.addSeries(userAdded);

        return dataset;
    }

    public XYSeries TrainTownAgeXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetAge() + "," + house.GetPrice());
            trainTown.add(house.GetAge(), house.GetPrice());
        });
                return trainTown;
        }

    public XYSeries TrainTownAreaXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetArea() + "," + house.GetPrice());
            trainTown.add(house.GetArea(), house.GetPrice());
        });
                return trainTown;
        }

    public XYSeries TrainTownBathroomXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetBathrooms() + "," + house.GetPrice());
            trainTown.add(house.GetBathrooms(), house.GetPrice());
        });
                return trainTown;
        }

    public XYSeries TrainTownBedroomsXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetBedrooms() + "," + house.GetPrice());
            trainTown.add(house.GetBedrooms(), house.GetPrice());
        });
                return trainTown;
        }

    public XYSeries TrainTownGaragesXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetGarages() + "," + house.GetPrice());
            trainTown.add(house.GetGarages(), house.GetPrice());
        });
                return trainTown;
        }

    public XYSeries TrainTownLivingSpaceXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetLivingSpace() + "," + house.GetPrice());
            trainTown.add(house.GetLivingSpace(), house.GetPrice());
        });
                return trainTown;
        }

    public XYSeries TrainTownRoomsXYSeries( ArrayList<House> townData) {
        XYSeries trainTown = new XYSeries("Train Town");

        townData.forEach((house) -> {
            System.out.println(house.GetRooms() + "," + house.GetPrice());
            trainTown.add(house.GetRooms(), house.GetPrice());
        });
                return trainTown;
        }    

    public XYSeriesCollection GetXYSeries() {
        return dataset;
    }

    public String SetxAxis(String xAxisLabel) {
        switch (xAxisLabel) {
            case "Area of the site":
            case "Size of living space":
                xAxis = xAxisLabel + "/ 1,000's square feet";
                break;
            case "Age of property":
                xAxis = xAxisLabel + "/ years";
                break;
            default:
                xAxis = xAxisLabel;
                break;
        }
        return xAxis;
    }

}
