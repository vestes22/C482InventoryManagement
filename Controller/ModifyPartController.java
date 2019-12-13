package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    private RadioButton modifyPartInHouseRb;

    @FXML
    private RadioButton modifyPartOutsourcedRb;
    
    @FXML
    private Label outsourcedOrInHouseLabel;
    
    @FXML
    private TextField outsourcedOrInHouseText; 
    
    /*
    * Displays correct fields depending on which RadioButton is selected (InHouse or Outsourced).
    */
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

    /*
    * Asks user if they want to exit without saving.
    * Redirects to the Main Screen.
    */
    @FXML
    void modifyPartCancelButtonClicked(MouseEvent event) throws Exception
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to exit without saving?");
        Optional<ButtonType> answer = alert.showAndWait();
        
        if(answer.isPresent() && answer.get() == ButtonType.OK)
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } 
    }

    /*
    * Updates and saves changes to the Part in Inventory.
    * Generates an alert box if changes do not conform to constraints.
    */
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Invalid Values");
                alert.setContentText("Min values cannot be greater than Max values.");
                alert.showAndWait();
            }
            
            else if (name.equals(""))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Empty Fields");
                alert.setContentText("All fields must be completed before saving.");
                alert.showAndWait();
            }
            
            else if (inv > max || inv < min)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Invalid Values");
                alert.setContentText("Inventory must be between Max and Min values.");
                alert.showAndWait();
            }
            
            else
            { 
                if (modifyPartInHouseRb.isSelected())
                {
                    int machId = Integer.parseInt(outsourcedOrInHouseText.getText());
                    newPart = new InHouse(id, name, price, inv, min, max, machId);
                    Inventory.updatePart(index, newPart);
                    
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                if (modifyPartOutsourcedRb.isSelected())
                {
                    String companyName = outsourcedOrInHouseText.getText();
                    if (companyName.equals(""))
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("WARNING: Empty Fields");
                        alert.setContentText("All fields must be completed before saving.");
                        alert.showAndWait();
                    }
                    else
                    {
                        newPart = new Outsourced(Part.partIdGenerator(), name, price, inv, min, max, companyName);
                        Inventory.updatePart(index, newPart);
                        
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        }
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING: Empty Fields");
            alert.setContentText("All fields must be completed before saving.");
            alert.showAndWait();
        }
    }
    
    /*
    * Allows us to use an instance of ModifyPartController to pass information from the Main Screen to the Modify Part screen.
    * Allows us to parse and pre-fill data for the Part to be modified.
    */
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        modifyPartIdText.setDisable(true);
    }

}
