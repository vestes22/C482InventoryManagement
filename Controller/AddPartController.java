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
    
    /*
    *Asks user if they want to exit without saving. Redirects to Main Screen.
    */
    @FXML
    void addPartCancelButtonClicked(MouseEvent event) throws Exception 
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
    * Saves the new Part into Inventory and redirects to main screen.
    * Generates an alert box if entered information doesn't match constraints.
    */
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
                Alert invAlert = new Alert(Alert.AlertType.WARNING);
                invAlert.setTitle("WARNING: Invalid Values");
                invAlert.setContentText("Inventory must be between Max and Min values.");
                invAlert.showAndWait();
            }
            
            else
            {
                if (inHouseRb.isSelected())
                {
                    int machId = Integer.parseInt(outsourcedOrInHouseText.getText());
                    Part newPart = new InHouse(Part.partIdGenerator(), name, price, inv, min, max, machId);
                    Inventory.addPart(newPart);
                    
                    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                if (outsourcedRb.isSelected())
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
                        Part newPart = new Outsourced(Part.partIdGenerator(), name, price, inv, min, max, companyName);
                        Inventory.addPart(newPart);
                        
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                }
            }
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING: Empty Fields");
            alert.setContentText("All fields must be completed before saving.");
            alert.showAndWait();
        }
    }
    
    /*
    * Displays correct fields depending on RadioButton Selected (InHouse or Outsourced).
    */
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
