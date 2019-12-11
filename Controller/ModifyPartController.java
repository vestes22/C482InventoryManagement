package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ModifyPartController implements Initializable
{
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField modifyPartIdText;

    @FXML
    private TextField modifyPartNameText;

    @FXML
    private TextField modifyPartInvText;

    @FXML
    private TextField modifyPartPriceText;

    @FXML
    private TextField modifyPartMaxText;

    @FXML
    private TextField modifyPartMinText;

    @FXML
    private Button modifyPartCancelButton;

    @FXML
    private Button modifyPartSaveButton;

    @FXML
    private RadioButton modifyPartInHouseRb;

    @FXML
    private ToggleGroup modifyPartToggleGroup;

    @FXML
    private RadioButton modifyPartOutsourcedRb;
    
    @FXML
    private Label outsourcedOrInHouseLabel;
    
    @FXML
    private TextField outsourcedOrInHouseText; 
    
    @FXML 
    private Label warningLabel;
    
    @FXML
    void inHouseSelected(MouseEvent event) 
    {
        outsourcedOrInHouseLabel.setText("Machine ID");
    }
    
    @FXML
    void outsourcedSelected(MouseEvent event) 
    {
        outsourcedOrInHouseLabel.setText("Company Name");
    }
    
    public void selectModifiedPart(Part part)
    {
        modifyPartIdText.setText(String.valueOf(part.getId()));
        modifyPartNameText.setText(part.getName());
        modifyPartInvText.setText(String.valueOf(part.getStock()));
        modifyPartPriceText.setText(String.valueOf(part.getPrice()));
        modifyPartMaxText.setText(String.valueOf(part.getMax()));
        modifyPartMinText.setText(String.valueOf(part.getMin()));
        
        
        if (part instanceof InHouse)
        {
            InHouse inHousePart = (InHouse) part;
            modifyPartInHouseRb.setSelected(true);
            outsourcedOrInHouseLabel.setText("Machine ID");
            outsourcedOrInHouseText.setText(String.valueOf(inHousePart.getMachineId()));
        }
        
        if (part instanceof Outsourced)
        {
            Outsourced outsourcedPart = (Outsourced) part;
            modifyPartOutsourcedRb.setSelected(true);
            outsourcedOrInHouseLabel.setText("Company Name");
            outsourcedOrInHouseText.setText(String.valueOf(outsourcedPart.getCompanyName()));
        }
    }

    @FXML
    void modifyPartCancelButtonClicked(MouseEvent event) throws Exception
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void modifyPartSaveButtonClicked(MouseEvent event) throws Exception
    {
        try 
        {
            int index = -1;
            Part newPart;
            int id = Integer.parseInt(modifyPartIdText.getText());
            String name = modifyPartNameText.getText();
            int inv = Integer.parseInt(modifyPartInvText.getText());
            double price = Double.parseDouble(modifyPartPriceText.getText());
            int max = Integer.parseInt(modifyPartMaxText.getText());
            int min = Integer.parseInt(modifyPartMinText.getText());
        
            //Finds the ObservableArray index of the part being modified by matching the Part ID, so we can replace it.
            for (int i = 0; i < Inventory.getAllParts().size(); i++)
            {
                if (Inventory.getAllParts().get(i).getId() == id)
                {
                    index = i;
                }
            }
        
            if(min > max)
            {
                warningLabel.setText("*Min values cannot be greater than Max values.");
            }
            
            else if (name.equals(""))
            {
                warningLabel.setText("*All fields must be completed before saving.");
            }
            
            else if (inv > max || inv < min)
            {
                warningLabel.setText("*Inventory must be between Max and Min values.");
            }
            
            else
            { 
                if (modifyPartInHouseRb.isSelected())
                {
                    int machId = Integer.parseInt(outsourcedOrInHouseText.getText());
                    newPart = new InHouse(id, name, price, inv, max, min, machId);
                    Inventory.updatePart(index, newPart);
                    
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                if (modifyPartOutsourcedRb.isSelected())
                {
                    String companyName = outsourcedOrInHouseText.getText();
                    if (companyName.equals(""))
                    {
                        warningLabel.setText("*All fields must be completed before saving");
                    }
                    else
                    {
                        newPart = new Outsourced(Part.partIdGenerator(), name, price, inv, max, min, companyName);
                        Inventory.updatePart(index, newPart);
                        
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        }
        catch(Exception e)
        {
            warningLabel.setText("*All fields must be completed before saving.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        modifyPartIdText.setDisable(true);
    }

}
