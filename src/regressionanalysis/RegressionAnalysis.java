package regressionanalysis;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegressionAnalysis extends JFrame implements ActionListener, KeyListener {

    // Comboboxs for storing Towns and  x1-7 variables
    JComboBox townList;
    JComboBox independentList;
    // Checkbox to make graph for TrainTown appear and disappear
    JCheckBox compare;
    // Setting the font for the the whole program
    public Font fnt = new Font("Calibri", Font.PLAIN, 24);
    public Font headerFnt = new Font("Calibri", Font.BOLD, 32);
    // Textfield to store user input
    JTextField userInput;
    // Outputs house selling price
    JTextField sellingPriceField;
    // Button to work out calucations
    JButton calculatePrice;
    JButton changeVariable;
    // Window panel fills the JFrame
    JPanel window;
    // Used for the action performed
    String activeTown = "";
    String activeVariable = "";
    Double finalPrice;
    // Objects from other Classes
    AddLabels labels;
    DrawGraph graph = new DrawGraph();
    CreateTable tables = new CreateTable();
    CreateTownData townData;

    public static void main(String[] args) throws FileNotFoundException {
        // Launches the Program
        RegressionAnalysis prg = new RegressionAnalysis();
    }

    public RegressionAnalysis() throws FileNotFoundException {
        // MVC
        model();
        view();
        controller();
    }

    private void model() throws FileNotFoundException {

        // String Array containing Towns A, B and C 
        String[] towns = {"Town A", "Town B", "Town C"};

        // Populate the ComboBox with the values in the towns array 
        townList = new JComboBox(towns);

        // String Array containing independant variables x1 to x7
        String[] independentVariables = {"Number of bathrooms", "Area of the site",
            "Size of living space", "Number of garages", "Number of rooms",
            "Number of bedrooms", "Age of property",};

        // Populate the ComboBox with values in the independantVariables array 
        independentList = new JComboBox(independentVariables);

        // Reads in the Data from files Town A, B and C
        townData = new CreateTownData();
        // Testing retrival of data from each town
        System.out.println("Age for the First House in Town A :" + townData.GetTownA().get(0).GetAge());
        System.out.println("Age for the First House in Town B :" + townData.GetTownB().get(0).GetAge());
        System.out.println("Age for the First House in Town C :" + townData.GetTownC().get(0).GetAge());
    }

    private void view() throws FileNotFoundException {

        window = new JPanel();
        // No Layout Manager - every element needs to be placed precisely 
        window.setLayout(null);
        this.add(window);

        // Add Labels, Table and Graph
        labels = new AddLabels(window, headerFnt, fnt);
        tables.NewTable(window, tables.InputDataObjectBathroom(townData.GetTownA()), "Town A");
        graph.NewGraph(window, "Town A", "Number of bathrooms", graph.BathroomDataset(townData.Choice("Town A")));

        // Add TownList ComboBox
        townList.setFont(fnt);
        townList.setBackground(Color.WHITE);
        townList.setSize(100, 40);
        townList.setLocation(290, 50);
        townList.setToolTipText("Select desired location");
        townList.addActionListener(this);
        townList.setActionCommand("Town");
        window.add(townList);

        // Add Variables JComboBox to window panel
        independentList.setFont(fnt);
        independentList.setBackground(Color.WHITE);
        independentList.setSize(290, 40);
        independentList.setLocation(290, 95);
        independentList.setToolTipText("Choose a variable");
        independentList.addActionListener(this);
        independentList.setActionCommand("Variable");
        window.add(independentList);

        // Add Compare CheckBox, by deflault Compare with Traintown is deselected
        compare = new JCheckBox("Compare with Train Town", false);
        compare.setFont(fnt);
        compare.setSize(300, 35);
        compare.setLocation(270, 140);
        compare.setToolTipText("Toggle TrainTown Data");
        compare.addActionListener(this);
        compare.setActionCommand("Compare");
        window.add(compare);

        // Add userInput TextField       
        userInput = new JTextField();
        userInput.setFont(fnt);
        userInput.setBackground(Color.WHITE);
        userInput.setSize(180, 40);
        userInput.setLocation(440, 790);
        userInput.setToolTipText("Enter number here");
        window.add(userInput);

        // Add Calculate button
        calculatePrice = new JButton("Calculate House Price");
        calculatePrice.setFont(fnt);
        calculatePrice.setSize(250, 40);
        calculatePrice.setLocation(140, 850);
        calculatePrice.setToolTipText("Calculate");
        calculatePrice.addActionListener(this);
        calculatePrice.setActionCommand("Calculate");
        window.add(calculatePrice);

        // Add Change variable button
        changeVariable = new JButton("Change variable");
        changeVariable.setFont(fnt);
        changeVariable.setSize(450, 40);
        changeVariable.setLocation(690, 850);
//        changeVariable.setBorderPainted( false );
        changeVariable.setToolTipText("Change to recommended variable");
        changeVariable.addActionListener(this);
        changeVariable.setActionCommand("Change");
        window.add(changeVariable);

        // Add Selling Price TextField , user cannot input data      
        sellingPriceField = new JTextField();
        sellingPriceField.setFont(fnt);
        sellingPriceField.setEditable(false);
        sellingPriceField.setBackground(Color.WHITE);
        sellingPriceField.setSize(180, 40);
        sellingPriceField.setLocation(440, 850);
        sellingPriceField.setToolTipText("Forecasted Selling Price");
        window.add(sellingPriceField);

        // JFrame Settings - at the end to set all elememts added to visable
        setTitle("Regression Analysis");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void controller() {

        // By default town A is selected 
        townList.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("Town".equals(e.getActionCommand())) {

            // Set selected town to activeTown
            activeTown = townList.getSelectedItem().toString();
            // Confirmation a town has been selected
            System.out.println(String.format("%s has been selected", activeTown));
            // When a town is selected, reselect the current variable 
            independentList.setSelectedIndex(independentList.getSelectedIndex());
        }

        if ("Variable".equals(e.getActionCommand())) {

            if (compare.isSelected()) {
                // If the compare checkbox is selected, deselect it
                compare.setSelected(false);
            }

            // Erase any values from previous calculations
            sellingPriceField.setText("");
            userInput.setText("");

            // Set selected variable to activeVariable
            activeVariable = independentList.getSelectedItem().toString();

            // Remove outdated graph and tables
            graph.RemoveGraph(window);
            tables.RemoveTables(window);

            switch (activeVariable) {
                case "Number of bathrooms":
                    // If bathrooms is selected, generate a graph and tables with the relevant data
                    graph.NewGraph(window, activeTown, activeVariable, graph.BathroomDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectBathroom(townData.Choice(activeTown)), activeTown);
                    break;
                case "Area of the site":
                    graph.NewGraph(window, activeTown, activeVariable, graph.AreaDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectArea(townData.Choice(activeTown)), activeTown);
                    break;
                case "Size of living space":
                    graph.NewGraph(window, activeTown, activeVariable, graph.LivingSpaceDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectLivingSpace(townData.Choice(activeTown)), activeTown);
                    break;
                case "Number of garages":
                    graph.NewGraph(window, activeTown, activeVariable, graph.GaragesDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectGarages(townData.Choice(activeTown)), activeTown);
                    break;
                case "Number of rooms":
                    graph.NewGraph(window, activeTown, activeVariable, graph.RoomsDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectRooms(townData.Choice(activeTown)), activeTown);
                    break;
                case "Number of bedrooms":
                    graph.NewGraph(window, activeTown, activeVariable, graph.BedroomsDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectBedroom(townData.Choice(activeTown)), activeTown);
                    break;
                case "Age of property":
                    graph.NewGraph(window, activeTown, activeVariable, graph.AgeDataset(townData.Choice(activeTown)));
                    tables.NewTable(window, tables.InputDataObjectAge(townData.Choice(activeTown)), activeTown);
                    break;
            }

            // Data regression line between the lowest and highest variable values
            graph.lineData.add(tables.LowestX(), tables.RegressionY(tables.LowestX(), tables.GetB1(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1))), tables.GetB0(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1)), tables.GetB1(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1))))));
            graph.lineData.add(tables.HighestX(), tables.RegressionY(tables.HighestX(), tables.GetB1(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1))), tables.GetB0(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1)), tables.GetB1(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1))))));

            // Repaint graph to make the new data appear quickly
            graph.chartPanel.repaint();
            tables.table.repaint();

            // Change label to tell user which variable they are calculating regression with
            labels.setAddHouseLabel(activeVariable);
            changeVariable.setText(String.format("Change variable to %s", tables.SortedVariablesListmodel.getValueAt(6, 0)));
            System.out.println(changeVariable.getText());

            // Confirmation a variable has been selected
            System.out.println(String.format("%s has been selected", activeVariable));
        }

        if ("Calculate".equals(e.getActionCommand())) {

            // Format settings for commas and two decimal places for currencies
            DecimalFormat dp = new DecimalFormat("#,###,###,###.##");
            dp.setRoundingMode(RoundingMode.CEILING);

            try {
                // Store users input as a string variable
                double userNumber = Double.parseDouble(userInput.getText());
                
                if (userNumber < 0){
                    throw new NumberFormatException();
                }

                // Calculate forcasted selling price based on users input
                finalPrice = tables.RegressionY(userNumber, tables.GetB1(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1))), tables.GetB0(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1)), tables.GetB1(tables.GetMean(tables.GetSum(0)), tables.GetMean(tables.GetSum(1)))));

                // Add the users inputed value to the graph and extend the regression line to the same point
                graph.userAdded.add(userNumber, finalPrice);
                graph.lineData.add(userNumber, finalPrice);

                // Print the forcasted selling price formatted into GBP
                sellingPriceField.setText(String.format("£ %s", dp.format(100000 * finalPrice)));

                // Comfirmation od regression calulation
                System.out.println(String.format("A value of £ %s has been entered", dp.format(100000 * finalPrice)));

            } catch (NumberFormatException m) {
                // If the users input cannot be converted into a double type or is null, catch exception and produce waring to input a number
                JOptionPane.showMessageDialog(null, "Please enter a valid positive number", "INVALID INPUT", JOptionPane.ERROR_MESSAGE);
            }
        }

        if ("Compare".equals(e.getActionCommand())) {
            if (compare.isSelected()) {
                // If compare is selected get the relevant data andadd it to the graph
                switch (activeVariable) {
                    case "Number of bathrooms":
                        graph.dataset.addSeries(graph.TrainTownBathroomXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Area of the site":
                        graph.dataset.addSeries(graph.TrainTownAreaXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Size of living space":
                        graph.dataset.addSeries(graph.TrainTownLivingSpaceXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Number of garages":
                        graph.dataset.addSeries(graph.TrainTownGaragesXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Number of rooms":
                        graph.dataset.addSeries(graph.TrainTownRoomsXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Number of bedrooms":
                        graph.dataset.addSeries(graph.TrainTownBedroomsXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Age of property":
                        graph.dataset.addSeries(graph.TrainTownAgeXYSeries(townData.GetTrainingTown()));
                        break;
                }
                // Compare selected Comfirmation
                System.out.println(String.format("%s has been selected", compare.getText()));

            } else {
                // If compare is deselected remove the data from the graph
                switch (activeVariable) {
                    case "Number of bathrooms":
                        graph.dataset.removeSeries(graph.TrainTownBathroomXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Area of the site":
                        graph.dataset.removeSeries(graph.TrainTownAreaXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Size of living space":
                        graph.dataset.removeSeries(graph.TrainTownLivingSpaceXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Number of garages":
                        graph.dataset.removeSeries(graph.TrainTownGaragesXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Number of rooms":
                        graph.dataset.removeSeries(graph.TrainTownRoomsXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Number of bedrooms":
                        graph.dataset.removeSeries(graph.TrainTownBedroomsXYSeries(townData.GetTrainingTown()));
                        break;
                    case "Age of property":
                        graph.dataset.removeSeries(graph.TrainTownAgeXYSeries(townData.GetTrainingTown()));
                        break;
                }
                // Compare deselected Comfirmation
                System.out.println(String.format("%s has been deselected", compare.getText()));
            }
        }
        if ("Change".equals(e.getActionCommand())) {
            independentList.setSelectedItem(tables.SortedVariablesListmodel.getValueAt(6, 0));
        }

    }

    // Other override Methods needed for actionPerfomed method
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
