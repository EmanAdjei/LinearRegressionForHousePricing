package regressionanalysis;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddLabels {

    JLabel addHouseLabel;

    public AddLabels(JPanel window, Font headerFnt, Font fnt) {

        // Add label titling the whole program
        JLabel title = new JLabel("House Selling Price Calculator");
        title.setFont(headerFnt);
        title.setSize(390, 40);
        title.setLocation(420, 10);
        window.add(title);

        // Add Town label
        JLabel townLabel = new JLabel("Select Town :");
        townLabel.setFont(fnt);
        townLabel.setSize(280, 40);
        townLabel.setLocation(40, 50);
        window.add(townLabel);

        // Add Variable label
        JLabel variableLabel = new JLabel("Selling Price based on :");
        variableLabel.setFont(fnt);
        variableLabel.setSize(380, 40);
        variableLabel.setLocation(40, 95);
        window.add(variableLabel);

        // Add AddHouse label
        addHouseLabel = new JLabel("Add house :");
        addHouseLabel.setFont(fnt);
        addHouseLabel.setSize(380, 40);
        addHouseLabel.setLocation(40, 790);
        window.add(addHouseLabel);
        
                // Add Town label
        JLabel bestMeasure = new JLabel("Calculate with \"best\" variable");
        bestMeasure.setFont(fnt);
        bestMeasure.setSize(300, 40);
        bestMeasure.setLocation(750, 790);
        window.add(bestMeasure);
    }
    
    // Dynamically change add house label
    public void setAddHouseLabel(String newAdd){
        String variable = newAdd.toLowerCase();
        addHouseLabel.setText(String.format("Add House by %s :", variable));
    };

}
