package regressionanalysis;

public class House {

    private final double numberOfBathrooms;
    private final double areaOfTheSite;
    private final double sizeOfLivingSpace;
    private final double numberOfGarages;
    private final double numberOfRooms;
    private final double numberOfBedrooms;
    private final double ageOfProperty;
    private final double sellingPrice;

    public House(double sellingPrice, double numberOfBathrooms, double areaOfTheSite, double sizeOfLivingSpace,
            double numberOfGarages, double numberOfRooms, double numberOfBedrooms, double ageOfProperty) {

        this.sellingPrice = sellingPrice;
        this.numberOfBathrooms = numberOfBathrooms;
        this.areaOfTheSite = areaOfTheSite;
        this.sizeOfLivingSpace = sizeOfLivingSpace;
        this.numberOfGarages = numberOfGarages;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.ageOfProperty = ageOfProperty;

    }
    
    public double GetPrice(){
        return sellingPrice;
    }
    
    public double GetBathrooms(){
        return numberOfBathrooms;
    }
    
    public double GetArea(){
        return areaOfTheSite;
    }
    
    public double GetLivingSpace(){
        return sizeOfLivingSpace;
    }
    
    public double GetGarages(){
        return numberOfGarages;
    }
    
    public double GetRooms(){
        return numberOfRooms;
    }
    
    public double GetBedrooms(){
        return numberOfBedrooms;
    }
    
    public double GetAge(){
        return ageOfProperty;
    }

}
