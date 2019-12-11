package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ModifyProductController implements Initializable
{
    Stage stage;
    Parent scene;
    private ObservableList<Part> searchedParts = FXCollections.observableArrayList();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    @FXML
    private TextField modifyProdIdText;

    @FXML
    private TextField modifyProdNameText;

    @FXML
    private TextField modifyProdInvText;

    @FXML
    private TextField modifyProdPriceText;

    @FXML
    private TextField modifyProdMinText;

    @FXML
    private TextField modifyProdMaxText;

    @FXML
    private Button modifyProdSearchButton;

    @FXML
    private Button modifyProdAddButton;

    @FXML
    private Button modifyProdDeleteButton;

    @FXML
    private Button modifyProdCancelButton;

    @FXML
    private Button modifyProdSaveButton;

    @FXML
    private TextField modifyProdSearchText;

    @FXML
    private TableView<Part> modifyProdTopTable;

    @FXML
    private TableColumn<Part, Integer> topModifyProdIdCol;

    @FXML
    private TableColumn<Part, String> topModifyProdNameCol;

    @FXML
    private TableColumn<Part, Integer> topModifyProdInvCol;

    @FXML
    private TableColumn<Part, Double> topModifyProdPriceCol;

    @FXML
    private TableView<Part> modifyProdBottomTable;

    @FXML
    private TableColumn<Part, Integer> bottomModifyProdIdCol;

    @FXML
    private TableColumn<Part, String> bottomModifyProdNameCol;

    @FXML
    private TableColumn<Part, Integer> bottomModifyProdInvCol;

    @FXML
    private TableColumn<Part, Double> bottomModifyProdPriceCol;
    
    @FXML
    private Label productWarningLabel;

    @FXML
    void modifyProdAddButtonClicked(MouseEvent event) 
    {
        productWarningLabel.setText("");
        Part addedPart = modifyProdTopTable.getSelectionModel().getSelectedItem();
        if (isPresent(addedPart.getId(), associatedParts))
        {
            productWarningLabel.setText("That part is already associated with this product.");
        }
        else 
        {
            associatedParts.add(addedPart);
        }
        modifyProdBottomTable.setItems(associatedParts);
        bottomModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    //Searches whether a Part exists based on ID.
    private boolean isPresent(int id, ObservableList<Part> parts)
    {
       for (Part part : parts)
       {
           if (part.getId() == id)
           {
               return true;
           }
       }
       return false;
    }

    @FXML
    void modifyProdCancelButtonClicked(MouseEvent event) throws Exception 
    {
       stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void modifyProdDeleteButtonClicked(MouseEvent event) 
    {
        int index = modifyProdBottomTable.getSelectionModel().getSelectedIndex();
        associatedParts.remove(index);
        modifyProdBottomTable.setItems(associatedParts);
        bottomModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void modifyProdSaveButtonClicked(MouseEvent event) 
    {
        try 
        {
            int index = -1;
            Product newProduct;
            int id = Integer.parseInt(modifyProdIdText.getText());
            String name = modifyProdNameText.getText();
            int inv = Integer.parseInt(modifyProdInvText.getText());
            double price = Double.parseDouble(modifyProdPriceText.getText());
            int max = Integer.parseInt(modifyProdMaxText.getText());
            int min = Integer.parseInt(modifyProdMinText.getText());
        
            //Finds the ObservableArray index of the part being modified by matching the Part ID, so we can replace it.
            for (int i = 0; i < Inventory.getAllProducts().size(); i++)
            {
                if (Inventory.getAllProducts().get(i).getId() == id)
                {
                    index = i;
                }
            }
        
            newProduct = new Product(id, name, price, inv, max, min);
        
            for (Part part : associatedParts)
            {
                newProduct.addAssociatedPart(part);
            }
            
            if(min > max)
            {
                productWarningLabel.setText("*Min values cannot be greater than Max values.");
            }
            
            else if (name.equals(""))
            {
                productWarningLabel.setText("*All fields must be completed before saving.");
            }
            
            else if (inv > max || inv < min)
            {
                productWarningLabel.setText("*Inventory must be between Max and Min values.");
            }
            
            else
            {
                Inventory.updateProduct(index, newProduct);
                associatedParts.clear();
          
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainScreenFXML.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch(Exception e)
        {
            productWarningLabel.setText("*All fields must be completed before saving.");
        }      
    }

    @FXML
    void modifyProdSearchButtonClicked(MouseEvent event) 
    {
        System.out.println("Search Button Clicked!");
    }
    
    //Selects the product to be modified and populates TextFields with selected data.
    public void selectModifiedProduct(Product product)
    {
        modifyProdIdText.setText(String.valueOf(product.getId()));
        modifyProdNameText.setText(product.getName());
        modifyProdInvText.setText(String.valueOf(product.getStock()));
        modifyProdPriceText.setText(String.valueOf(product.getPrice()));
        modifyProdMaxText.setText(String.valueOf(product.getMax()));
        modifyProdMinText.setText(String.valueOf(product.getMin()));
        
        for (Part part : product.getAllAssociatedParts())
        {
            associatedParts.add(part);
        }
        
        modifyProdBottomTable.setItems(associatedParts);
        bottomModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bottomModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bottomModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bottomModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        modifyProdIdText.setDisable(true);
        modifyProdTopTable.setItems(Inventory.getAllParts());
        topModifyProdIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        topModifyProdNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        topModifyProdInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topModifyProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
}
