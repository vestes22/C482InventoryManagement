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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddPartController implements Initializable
{
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField addPartIdText;

    @FXML
    private TextField addPartNameText;

    @FXML
    private TextField addPartInvText;

    @FXML
    private TextField addPartPriceText;

    @FXML
    private TextField addPartMaxText;

    @FXML
    private TextField addPartMinText;
    
    @FXML
    private Label outsourcedOrInHouseLabel;

    @FXML
    private TextField outsourcedOrInHouseText;

    @FXML
    private RadioButton inHouseRb;

    @FXML
    private RadioButton outsourcedRb;

    @FXML
    private Button addPartCancelButton;
    
    @FXML
    private Label idWarningLabel;

    @FXML
    void addPartCancelButtonClicked(MouseEvent event) throws Exception 
    {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void addPartSaveButtonClicked(MouseEvent event) throws Exception
    {
        try
        {
            String name = addPartNameText.getText();
            int inv = Integer.parseInt(addPartInvText.getText());
            double price = Double.parseDouble(addPartPriceText.getText());
            int max = Integer.parseInt(addPartMaxText.getText());
            int min = Integer.parseInt(addPartMinText.getText());
            
            if (name.equals(""))
            {
                idWarningLabel.setText("*All fields must be completed before saving.");
            }
            
            else
            {
                if (inHouseRb.isSelected())
                {
                    int machId = Integer.parseInt(outsourcedOrInHouseText.getText());
                    Part newPart = new InHouse(Part.partIdGenerator(), name, price, inv, max, min, machId);
                    Inventory.addPart(newPart);
                    
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                if (outsourcedRb.isSelected())
                {
                    String companyName = outsourcedOrInHouseText.getText();
                    if (companyName.equals(""))
                    {
                        idWarningLabel.setText("*All fields must be completed before saving.");
                    }
                    else
                    {
                        Part newPart = new Outsourced(Part.partIdGenerator(), name, price, inv, max, min, companyName);
                        Inventory.addPart(newPart);
                        
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        }
        catch (Exception e)
        {
            idWarningLabel.setText("*All fields must be completed before saving.");
        }
    }

    @FXML
    void inHouseSelected(MouseEvent event) 
    {
        outsourcedOrInHouseLabel.setText("Machine ID");
        outsourcedOrInHouseText.setPromptText("Mach ID");
    }

    @FXML
    void outsourcedSelected(MouseEvent event) 
    {
        outsourcedOrInHouseLabel.setText("Company Name");
        outsourcedOrInHouseText.setPromptText("Comp Name");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        addPartIdText.setDisable(true);
        inHouseRb.setSelected(true);
        outsourcedOrInHouseLabel.setText("Machine ID");
        outsourcedOrInHouseText.setPromptText("Mach ID");
    }

}
