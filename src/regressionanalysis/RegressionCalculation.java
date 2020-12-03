package regressionanalysis;

import java.util.ArrayList;

public class RegressionCalculation {

    double test;
    double[][] array = new double[][]{{2, 3, 2, 3, 3.5, 2.5},{3, 3.5, 2.5}};
    double mean;
    double variance;
    double R;

    CreateTownData townData;

    public RegressionCalculation() {// throws FileNotFoundException {
        //this.townData = new CreateTownData();

    }

    public int N(ArrayList<House> town) {
        int N = town.size();

        System.out.println(String.format("N is: %s", N));
        return N;
    }

    public double GetMean(ArrayList<Double> variable) {
        double meanSum = 0;

        for (int i = 0; i < variable.size(); i++) {
            meanSum += variable.get(i);
        }

        mean = meanSum / variable.size();

        System.out.println(String.format("The mean is: %s", mean));
        return mean;
    }



//    private double GetR() {
//        R = 0;
//        return mean;
//    }
//
//    private double GetRSquared() {
//        double RSquared = R*R;
//        return RSquared;
//    }
//
//    private double GetSlope() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double GetYIntercept() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double SortedRSquaredArray() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double SXY() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double SXX() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double SYY() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double B1() {
//        double mean = 0;
//        return mean;
//    }
//
//    private double B0() {
//        double mean = 0;
//        return mean;
//    }
}
