package regressionanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTownData {

    // Location of needed text files , relative to the project directory
    String Location = System.getProperty("user.dir") + System.getProperty("file.separator")
            + "townData" + System.getProperty("file.separator");

    private ArrayList<House> townA = new ArrayList<>();
    private ArrayList<House> townB = new ArrayList<>();
    private ArrayList<House> townC = new ArrayList<>();
    private ArrayList<House> trainingTown = new ArrayList<>();

    public CreateTownData() throws FileNotFoundException {

        // Reads the file location and converts the data from a string arraylist to an arraylist of type house
        townA = StringToHouseArrayList(ReadTownFile("Town A"));
        townB = StringToHouseArrayList(ReadTownFile("Town B"));
        townC = StringToHouseArrayList(ReadTownFile("Town C"));
        trainingTown = StringToHouseArrayList(ReadTownFile("TrainingTown"));

    }

    private ArrayList<String> ReadTownFile(String townName) {
        String fileLocation = Location + townName + ".txt";
        ArrayList<String> townData = new ArrayList();
        String singleHouseData;

        if (!fileLocation.equals("")) {

            System.out.println(String.format("%s found!", fileLocation));

            try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {

                if (!reader.ready()) {
                    throw new IOException();
                }

                // Keeps reading line by line until the next line read returns null
                while ((singleHouseData = reader.readLine()) != null) {
                    townData.add(singleHouseData);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Town Data could not be found");
        }

        return townData;
    }

    private ArrayList<House> StringToHouseArrayList(ArrayList<String> Town) {
        ArrayList<House> homeCollection = new ArrayList<>();

        for (String value : Town) {
            // For every string, break it up and assign then into differnt properties of a house object
            String[] houseStat = value.split("\t:\t");

            House home = new House(Double.parseDouble(houseStat[0]), Double.parseDouble(houseStat[1]),
                    Double.parseDouble(houseStat[2]), Double.parseDouble(houseStat[3]),
                    Double.parseDouble(houseStat[4]), Double.parseDouble(houseStat[5]),
                    Double.parseDouble(houseStat[6]), Double.parseDouble(houseStat[7]));
            homeCollection.add(home);
        }

        return homeCollection;
    }

    public ArrayList<House> GetTownA() {
        return townA;
    }

    public ArrayList<House> GetTownB() {
        return townB;
    }

    public ArrayList<House> GetTownC() {
        return townC;
    }

    public ArrayList<House> GetTrainingTown() {
        return trainingTown;
    }

    public ArrayList<House> Choice(String activeTown) {
        ArrayList<House> data = new ArrayList();
        switch (activeTown) {
            case "Town A":
                data = GetTownA();
                break;
            case "Town B":
                data = GetTownB();
                break;
            case "Town C":
                data = GetTownC();
                break;
        }
        return data;
    }

}
