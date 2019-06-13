package c482project0;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class C482Project0 extends Application
{
   Stage window;
   Scene main, addPart, modifyPart, addProduct, modifyProduct;
   TextField partIdText, partNameText, partInvText, partPriceText, partMaxText, partMinText, machineIdText, companyNameText;
   TableView<Parts> parts;
   TableView<Products> products;
   //TableView<Parts> addProductPartsTable;
   TableView<Parts> addedPartsTable;
   TableView<Parts> modifyProductPartsTable;
   TableView<Parts> modifiedPartsTable;
   ObservableList<Parts> allParts;
   
   public static void main(String[] args)
   {
       launch(args);
   }
   
   @Override
   public void start(Stage primaryStage)
   {
       window = primaryStage;
       window.setTitle("Inventory Management");
       
       //-------------------------------------------------------------------------------------------------------------
       //Grids and Layouts:
       
       //Creates outer grid for "Main" screen. 
       GridPane outerGrid = new GridPane();
       outerGrid.setPadding(new Insets(15,15,15,15));
       outerGrid.setVgap(10);
       outerGrid.setHgap(10);
       outerGrid.setStyle("-fx-background-color: GAINSBORO; -fx-padding: 10;");
       
       //Creates "Parts" half of "Main" screen.
       GridPane partsGrid = new GridPane();
       partsGrid.setPadding(new Insets(10,10,10,10));
       partsGrid.setVgap(10);
       partsGrid.setHgap(10);
       partsGrid.setStyle("-fx-background-color: WHITESMOKE; -fx-padding: 10;");
       
       //Creates "Products" half of "Main" screen.
       GridPane productsGrid = new GridPane();
       productsGrid.setPadding(new Insets(10,10,10,10));
       productsGrid.setVgap(10);
       productsGrid.setHgap(10);
       productsGrid.setStyle("-fx-background-color: WHITESMOKE; -fx-padding: 10;");
       
       //Creates layout for "Add Part" screen
       GridPane addPartsGrid = new GridPane();
       addPartsGrid.setPadding(new Insets(10, 10, 10, 10));
       addPartsGrid.setVgap(10);
       addPartsGrid.setHgap(10);
       addPartsGrid.setStyle("-fx-background-color: LIGHTGRAY; -fx-padding: 10;"); 
       
       //Creates layout for "Modify Part" screen.
       GridPane modifyPartsGrid = new GridPane();
       modifyPartsGrid.setPadding(new Insets(10, 10, 10, 10));
       modifyPartsGrid.setVgap(10);
       modifyPartsGrid.setHgap(10);
       modifyPartsGrid.setStyle( "-fx-background-color: LIGHTGRAY; -fx-padding: 10;");
       
       //Creates overall layout for "Add Product" screen.
       GridPane outerProductsGrid = new GridPane();
       outerProductsGrid.setPadding(new Insets(10, 10, 10, 10));
       outerProductsGrid.setVgap(10);
       outerProductsGrid.setHgap(15);
       outerProductsGrid.setStyle("-fx-background-color: LIGHTGRAY; -fx-padding: 10;"); 
       
       //Creates grid for left half of "Add Product" screen.
       GridPane leftAddProduct = new GridPane();
       leftAddProduct.setPadding(new Insets(10, 10, 10, 10));
       leftAddProduct.setVgap(10);
       leftAddProduct.setHgap(10);
       
       //Creates grid for right half of "Add Product" screen.
       GridPane rightAddProduct = new GridPane();
       rightAddProduct.setPadding(new Insets(10, 10, 10, 10));
       rightAddProduct.setVgap(10);
       rightAddProduct.setHgap(10);
       
       //Creates overall layout for "Modify Product" screen.
       GridPane outerModifyProductsGrid = new GridPane();
       outerModifyProductsGrid.setPadding(new Insets(10, 10, 10, 10));
       outerModifyProductsGrid.setVgap(10);
       outerModifyProductsGrid.setHgap(15);
       outerModifyProductsGrid.setStyle("-fx-background-color: LIGHTGRAY; -fx-padding: 10;"); 
       
       //Creates grid for left half of "Modify Product" screen.
       GridPane leftModifyProduct = new GridPane();
       leftModifyProduct.setPadding(new Insets(10, 10, 10, 10));
       leftModifyProduct.setVgap(10);
       leftModifyProduct.setHgap(10);
       
       //Creates grid for right half of "Modify Product" screen.
       GridPane rightModifyProduct = new GridPane();
       rightModifyProduct.setPadding(new Insets(10, 10, 10, 10));
       rightModifyProduct.setVgap(10);
       rightModifyProduct.setHgap(10);
 
       //Creates the different screens.
       Scene main = new Scene(outerGrid, 1000, 400);
       Scene addPart = new Scene(addPartsGrid, 400, 300);
       Scene modifyPart = new Scene(modifyPartsGrid, 450, 300);
       Scene addProduct = new Scene(outerProductsGrid, 800, 400);
       Scene modifyProduct = new Scene(outerModifyProductsGrid, 800, 400);
       
       //------------------------------------------------------------------------------------------------------------
       //Main Screen
       
       //Creates title label for "Main" screen.
       Text mainLabel = new Text("Inventory Management System");
       mainLabel.setFill(Color.DARKSLATEGREY);
       mainLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
       GridPane.setConstraints(mainLabel, 0, 0);
       
       //Creates search text boxes for "Main" screen.
       TextField partsTextField = new TextField();
       GridPane.setConstraints(partsTextField, 2, 0);
       
       TextField productsTextField = new TextField();
       GridPane.setConstraints(productsTextField, 2, 0);
       
       //Creates title label for "Parts" half of "Main" screen.
       Text partsLabel = new Text("Parts");
       partsLabel.setFont(Font.font("null", FontWeight.BOLD, 14));
       GridPane.setConstraints(partsLabel, 0, 0);
       
       //Creates title label for "Products" half of "Main" screen.
       Text productsLabel = new Text("Products");
       productsLabel.setFont(Font.font("null", FontWeight.BOLD, 14));
       GridPane.setConstraints(productsLabel, 0, 0);
       
       //Creates the table for the Parts List
       //Part ID column
       TableColumn<Parts, Integer> partIdColumn = new TableColumn<>("Part ID");
       partIdColumn.setMinWidth(75);
       partIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
       
       //Part Name Column
       TableColumn<Parts, String> partNameColumn = new TableColumn<>("Part Name");
       partNameColumn.setMinWidth(100);
       partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
       
       //Inventory Column
       TableColumn<Parts, Integer> partInventoryColumn = new TableColumn<>("Inventory Level");
       partInventoryColumn.setMinWidth(100);
       partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partInventory"));
       
       //Price Column
       TableColumn<Parts, Double> partPriceColumn = new TableColumn<>("Price/Cost per Unit");
       partPriceColumn.setMinWidth(175);
       partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
       
       //Max Column
       TableColumn<Parts, Integer> partMaxColumn = new TableColumn<>("Max");
       partMaxColumn.setCellValueFactory(new PropertyValueFactory<>("partMax"));
       
       //Min Column
       TableColumn<Parts, Integer> partMinColumn = new TableColumn<>("Min");
       partMinColumn.setCellValueFactory(new PropertyValueFactory<>("partMin"));
       
       //Machine ID Column
       TableColumn<Parts, Integer> machineIdColumn = new TableColumn<>("Machine ID");
       machineIdColumn.setCellValueFactory(new PropertyValueFactory<>("machineId"));
       
       //Company Name Column
       TableColumn<Parts, String> companyNameColumn = new TableColumn<>("Company Name");
       companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
               
       parts = new TableView<>();
       //parts.setItems(getParts());
       parts.getColumns().addAll
       (
           partIdColumn,
           partNameColumn,
           partInventoryColumn, 
           partPriceColumn, 
           partMaxColumn, 
           partMinColumn,
           machineIdColumn,
           companyNameColumn
       );
       partMaxColumn.setVisible(false);
       partMinColumn.setVisible(false);
       machineIdColumn.setVisible(false);
       companyNameColumn.setVisible(false);
       partsGrid.add(parts, 0, 1, 3, 1);
       
      
       /*
       //Creates the table for the Products List
       //Product ID column
       TableColumn productIdColumn = new TableColumn("Product ID");
       productIdColumn.setMinWidth(75);
       productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
       
       //Product Name column
       TableColumn productNameColumn = new TableColumn("Product Name");
       productNameColumn.setMinWidth(100);
       productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
       
       //Product Inventory column
       TableColumn productInvCol = new TableColumn("Inventory Level");
       productInvCol.setMinWidth(100);
       productInvCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));
       
       //Product Price Column
       TableColumn productPriceCol = new TableColumn("Price/Cost per Unit");
       productPriceCol.setMinWidth(175);
       productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
       
       //Product Max Column
       TableColumn productMaxColumn = new TableColumn("Max");
       productMaxColumn.setCellValueFactory(new PropertyValueFactory<>("productMax"));
       
       //Product Min Column
       TableColumn productMinColumn = new TableColumn("Min");
       productMinColumn.setCellValueFactory(new PropertyValueFactory<>("productMin"));
       
       products = new TableView<>();
       products.getColumns().addAll
       (
           productIdColumn, 
           productNameColumn, 
           productInvCol, 
           productPriceCol, 
           productMaxColumn, 
           productMinColumn
       );
       productMaxColumn.setVisible(false);
       productMinColumn.setVisible(false);
       productsGrid.add(products, 0, 1, 3, 1);
       */
       
       //Creates "Main" screen buttons.
       Button exit = new Button("Exit");
       exit.setMinWidth(65);
       exit.setMaxWidth(65);
       GridPane.setHalignment(exit, HPos.RIGHT);
       GridPane.setConstraints(exit, 1, 2);
       exit.setOnAction(e -> window.close());
       
       //Creates buttons for "Parts" half of "Main" screen.
       Button searchParts = new Button("Search");
       searchParts.setMinWidth(65);
       searchParts.setMaxWidth(65);
       GridPane.setConstraints(searchParts, 1, 0);
       
       Button addParts = new Button("Add");
       addParts.setMinWidth(65);
       addParts.setMaxWidth(65);
       GridPane.setConstraints(addParts, 0, 2);
       addParts.setOnAction(e -> window.setScene(addPart));
       
       Button modifyParts = new Button("Modify");
       modifyParts.setMinWidth(65);
       modifyParts.setMaxWidth(65);
       GridPane.setConstraints(modifyParts, 1, 2);
       modifyParts.setOnAction(e -> window.setScene(modifyPart));
       
       Button deleteParts = new Button("Delete");
       deleteParts.setMinWidth(65);
       deleteParts.setMaxWidth(65);
       GridPane.setConstraints(deleteParts, 2, 2);
       
       //Creates buttons for "Products" half of "Main" screen.
       Button searchProducts = new Button("Search");
       searchProducts.setMinWidth(65);
       searchProducts.setMaxWidth(65);
       GridPane.setConstraints(searchProducts, 1, 0);
       
       Button addProducts = new Button("Add");
       addProducts.setMinWidth(65);
       addProducts.setMaxWidth(65);
       GridPane.setConstraints(addProducts, 0, 2);
       addProducts.setOnAction(e -> window.setScene(addProduct));
       
       Button modifyProducts = new Button("Modify");
       modifyProducts.setMinWidth(65);
       modifyProducts.setMaxWidth(65);
       GridPane.setConstraints(modifyProducts, 1, 2);
       modifyProducts.setOnAction(e -> window.setScene(modifyProduct));
       
       Button deleteProducts = new Button("Delete");
       deleteProducts.setMinWidth(65);
       deleteProducts.setMaxWidth(65);
       GridPane.setConstraints(deleteProducts, 2, 2);
       
       //Aligns and embeds grids for "Main" screen.
       GridPane.setConstraints(partsGrid,0,1);
       GridPane.setConstraints(productsGrid,1,1);
       
       //Adds layout to "Main" screen.
       partsGrid.getChildren().addAll
       (
            partsLabel, 
            searchParts, 
            partsTextField,
            addParts, 
            modifyParts, 
            deleteParts
       );
       productsGrid.getChildren().addAll
        (
            productsLabel,
            searchProducts, 
            productsTextField, 
            addProducts, 
            modifyProducts, 
            deleteProducts
        );
       outerGrid.getChildren().addAll(mainLabel, partsGrid, productsGrid, exit);
      
       //------------------------------------------------------------------------------------------------------------------
       //Add Part Screen
        
       //Creates title label for "Add Part" screen.
       Text addPartLabel = new Text("Add Part  ");
       addPartLabel.setFont(Font.font("null", FontWeight.BOLD, 14));
       //addPartsGrid.add(addPartLabel, 0, 0, 1, 1);
       
       //Creates Radio Buttons used on "Add Part" screen. 
       ToggleGroup addPartsToggle = new ToggleGroup();
      
       RadioButton inHouse = new RadioButton("In-House");
       inHouse.setToggleGroup(addPartsToggle);
       //inHouse.setSelected(true);
       
       RadioButton outsourced = new RadioButton("Outsourced");
       outsourced.setToggleGroup(addPartsToggle);
      
       //Creates "ID" label and textbox.
       Label partId = new Label("    ID");
       addPartsGrid.add(partId, 0, 1, 1, 1);
       
       TextField partIdText = new TextField();
       partIdText.setPromptText("Auto Gen - Disabled");
       //partIdText.setDisable(true);
       addPartsGrid.add(partIdText, 1, 1, 1, 1);
      
       //Creates "Name" label and textbox.
       Label partName = new Label("    Name");
       addPartsGrid.add(partName, 0, 2, 1, 1);
       
       TextField partNameText = new TextField();
       partNameText.setPromptText("Part Name");
       addPartsGrid.add(partNameText, 1, 2, 1, 1);
       
       //Creates "Inventory" label and textbox.
       Label partInv = new Label("    Inv");
       addPartsGrid.add(partInv, 0, 3, 1, 1);
       
       TextField partInvText = new TextField();
       partInvText.setPromptText("Inv");
       addPartsGrid.add(partInvText, 1, 3, 1, 1);
       
       //Creates "Price/Cost" label and textbox.
       Label partPrice = new Label("    Price/Cost");
       addPartsGrid.add(partPrice, 0, 4, 1, 1);
       
       TextField partPriceText = new TextField();
       partPriceText.setPromptText("Price/Cost");
       addPartsGrid.add(partPriceText, 1, 4, 1, 1);
       
       //Creates "Max" and "Min" labels and textboxes.
       Label partMax = new Label("    Max");
       addPartsGrid.add(partMax, 0, 5, 1, 1);
       
       Label partMin = new Label("Min");
       
       TextField partMaxText = new TextField();
       partMaxText.setPromptText("Max");
       partMaxText.setMinWidth(80);
       partMaxText.setMaxWidth(80);
       
       TextField partMinText = new TextField();
       partMinText.setPromptText("Min");
       partMinText.setMinWidth(80);
       partMinText.setMaxWidth(80);
       
       //Creates "Company Name" label and textbox
       Label companyName = new Label("    Company Name");
           
       TextField companyNameText = new TextField();
       companyNameText.setPromptText("Comp Nm");
       
       //Creates "Machine ID" label and textbox
       Label machineId = new Label("     Machine ID");
           
       TextField machineIdText = new TextField();
       machineIdText.setPromptText("Mach ID");
       
       outsourced.setOnAction(e -> 
       {
           addPartsGrid.getChildren().removeAll(machineId, machineIdText);
           addPartsGrid.add(companyName, 0, 6);
           addPartsGrid.add(companyNameText, 1, 6);
           
       });
       
       inHouse.setOnAction(e -> 
       {
           addPartsGrid.getChildren().removeAll(companyName, companyNameText);
           addPartsGrid.add(machineId, 0, 6);
           addPartsGrid.add(machineIdText, 1, 6);
       });
       
       inHouse.setSelected(true);
       
       //Creates Save and Cancel buttons used on "Add Part" screen.
       Button saveAddPart = new Button("Save");
       saveAddPart.setMinWidth(65);
       saveAddPart.setMaxWidth(65);
       
       //Save Button functionality:
       saveAddPart.setOnAction(e -> 
       {
           addPart();
           //window.setScene(main);
       });
       
       Button cancelAddPart = new Button("Cancel");
       cancelAddPart.setMinWidth(65);
       cancelAddPart.setMaxWidth(65);
       cancelAddPart.setOnAction(e -> window.setScene(main));

       //Adds layout to "Add Part" screen
       HBox addPartHbox1 = new HBox(10, partMaxText, partMin, partMinText);
       addPartsGrid.add(addPartHbox1, 1, 5, 3, 1);
       
       HBox addPartHBox2 = new HBox(10, saveAddPart, cancelAddPart);
       addPartsGrid.add(addPartHBox2, 1, 7, 1, 1);
       
       HBox addPartHBox3 = new HBox(20, addPartLabel, inHouse, outsourced);
       addPartsGrid.add(addPartHBox3, 0, 0, 2, 1);
       
       //----------------------------------------------------------------------------------------------------------------------
       //Modify Part Screen
       
       //Creates title label for "Modify Part" screen.
       Text modifyPartLabel = new Text("Modify Part");
       modifyPartLabel.setFont(Font.font("null", FontWeight.BOLD, 14));
       
       //Creates Radio Buttons for "Modify Part" screen
       ToggleGroup modifyPartsToggle = new ToggleGroup();
       
       RadioButton mInHouse = new RadioButton("In-House");
       mInHouse.setToggleGroup(modifyPartsToggle);
       mInHouse.setSelected(true);
       
       RadioButton mOutsourced = new RadioButton("Outsourced");
       mOutsourced.setToggleGroup(modifyPartsToggle);
 
       //Creates "ID" label and textbox.
       Label mPartId = new Label("    ID");
       modifyPartsGrid.add(mPartId, 1, 1, 1, 1);
       
       TextField mPartIdText = new TextField();
       mPartIdText.setPromptText("Auto Gen - Disabled");
       mPartIdText.setDisable(true);
       modifyPartsGrid.add(mPartIdText, 2, 1, 1, 1);
      
       //Creates "Name" label and textbox.
       Label mPartName = new Label("    Name");
       modifyPartsGrid.add(mPartName, 1, 2, 1, 1);
       
       TextField mPartNameText = new TextField("Part 1");
       modifyPartsGrid.add(mPartNameText, 2, 2, 1, 1);
       
       //Creates "Inventory" label and textbox.
       Label mPartInv = new Label("    Inv");
       modifyPartsGrid.add(mPartInv, 1, 3, 1, 1);
       
       TextField mPartInvText = new TextField("200");
       modifyPartsGrid.add(mPartInvText, 2, 3, 1, 1);
       
       //Creates "Price/Cost" label and textbox.
       Label mPartPrice = new Label("    Price/Cost");
       modifyPartsGrid.add(mPartPrice, 1, 4, 1, 1);
       
       TextField mPartPriceText = new TextField("$5.00");
       modifyPartsGrid.add(mPartPriceText, 2, 4, 1, 1);
       
       //Creates "Max" and "Min" labels and textboxes.
       Label mPartMax = new Label("    Max");
       modifyPartsGrid.add(mPartMax, 1, 5, 1, 1);
       
       TextField mPartMaxText = new TextField("300");
       mPartMaxText.setMinWidth(80);
       mPartMaxText.setMaxWidth(80);
       
       Label mPartMin = new Label("Min");
       
       TextField mPartMinText = new TextField("5");
       mPartMinText.setMinWidth(80);
       mPartMinText.setMaxWidth(80);
       
       //Creates "Company Name" label and textbox.
       Label mPartCompName = new Label("    Company Name");
       modifyPartsGrid.add(mPartCompName, 1, 6, 1, 1);
       
       TextField mPartCompText = new TextField("Company A");
       modifyPartsGrid.add(mPartCompText, 2, 6, 1, 1);
       
       //Creates Save and Cancel buttons used on "Modify Part" screen.
       Button saveModifyPart = new Button("Save");
       saveModifyPart.setMinWidth(65);
       saveModifyPart.setMaxWidth(65);
       
       Button cancelModifyPart = new Button("Cancel");
       cancelModifyPart.setMinWidth(65);
       cancelModifyPart.setMaxWidth(65);
       cancelModifyPart.setOnAction(e -> window.setScene(main));
       
       //Adds layout to "Modify Part" screen.
       HBox modifyPartHBox1 = new HBox(10, mPartMaxText, mPartMin, mPartMinText);
       modifyPartsGrid.add(modifyPartHBox1, 2, 5, 3, 1);
       
       HBox modifyPartHBox2 = new HBox(20, modifyPartLabel, mInHouse, mOutsourced);
       modifyPartsGrid.add(modifyPartHBox2, 0, 0, 3, 1);
       
       HBox modifyPartHBox3 = new HBox(10, saveModifyPart, cancelModifyPart);
       modifyPartsGrid.add(modifyPartHBox3, 2, 7, 2, 1);
       
       //----------------------------------------------------------------------------------------------------------------------
       //Add Products Screen
       
       //Creates title label for "Add Product" screen.
       Label addProductLabel = new Label("Add Product");
       addProductLabel.setFont(Font.font("null", FontWeight.BOLD, 14));
       leftAddProduct.add(addProductLabel,0, 0, 2, 1);
       
       //Creates "ID" label and textbox.
       Label addProductId = new Label("ID");
       leftAddProduct.add(addProductId, 0, 1, 1, 1);
       
       TextField addProductIdText = new TextField();
       addProductIdText.setPromptText("Auto Gen - Disabled");
       addProductIdText.setDisable(true);
       leftAddProduct.add(addProductIdText, 1, 1, 1, 1);
       
       //Creates "Name" label and textbox.
       Label addProductName = new Label("Name");
       leftAddProduct.add(addProductName, 0, 2, 1, 1);
       
       TextField addProductNameText = new TextField();
       addProductNameText.setPromptText("Product Name");
       leftAddProduct.add(addProductNameText, 1, 2, 1, 1);
       
       //Creates "Inventory" label and textbox.
       Label addProductInv = new Label("Inv");
       leftAddProduct.add(addProductInv, 0, 3, 1, 1);
       
       TextField addProductInvText = new TextField();
       addProductInvText.setPromptText("Inv");
       leftAddProduct.add(addProductInvText, 1, 3, 1, 1);
       
       //Creates "Price" label and textbox. 
       Label addProductPrice = new Label("Price");
       leftAddProduct.add(addProductPrice, 0, 4, 1, 1);
       
       TextField addProductPriceText = new TextField();
       addProductPriceText.setPromptText("Price");
       leftAddProduct.add(addProductPriceText, 1, 4, 1, 1);
       
       //Creates "Max" and "Min" labels and textboxes.
       Label addProductMax = new Label("Max");
       leftAddProduct.add(addProductMax, 0, 5, 1, 1);
       
       TextField addProductMaxText = new TextField();
       addProductMaxText.setPromptText("Max");
       addProductMaxText.setMinWidth(65);
       addProductMaxText.setMaxWidth(65);
       
       Label addProductMin = new Label("Min");
       
       TextField addProductMinText = new TextField();
       addProductMinText.setPromptText("Min");
       addProductMinText.setMinWidth(65);
       addProductMinText.setMaxWidth(65);
       
       TextField searchAddProductText = new TextField();
       rightAddProduct.add(searchAddProductText, 1, 0, 1, 1);
       
       //Creates buttons used in "Add Product" screen.
       Button searchAddProduct = new Button("Search");
       searchAddProduct.setMinWidth(65);
       searchAddProduct.setMaxWidth(65);
       rightAddProduct.add(searchAddProduct, 0, 0, 1, 1);
       
       Button addAddProduct = new Button("Add");
       addAddProduct.setMinWidth(65);
       addAddProduct.setMaxWidth(65);
       GridPane.setHalignment(addAddProduct, HPos.RIGHT);
       rightAddProduct.add(addAddProduct, 3, 2, 1, 1);
       
       Button deleteAddProduct = new Button("Delete");
       deleteAddProduct.setMinWidth(65);
       deleteAddProduct.setMaxWidth(65);
       GridPane.setHalignment(deleteAddProduct, HPos.RIGHT);
       rightAddProduct.add(deleteAddProduct, 3, 4, 1, 1);
       
       Button saveAddProduct = new Button("Save");
       saveAddProduct.setMinWidth(65);
       saveAddProduct.setMaxWidth(65);
       
       Button cancelAddProduct = new Button("Cancel");
       cancelAddProduct.setMinWidth(65);
       cancelAddProduct.setMaxWidth(65);
       cancelAddProduct.setOnAction(e -> window.setScene(main));
       
       //Creates and adds parts table to upper half of "Add Product" screen.
       //Part ID column
       TableColumn<Parts, Integer> partIdCol = new TableColumn<>("Part ID");
       partIdCol.setMinWidth(75);
       partIdCol.setCellValueFactory(new PropertyValueFactory<>("partId"));
       
       //Part Name Column
       TableColumn<Parts, String> partNameCol = new TableColumn<>("Part Name");
       partNameCol.setMinWidth(100);
       partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
       
       //Inventory Column
       TableColumn<Parts, Integer> partInventoryCol = new TableColumn<>("Inventory Level");
       partInventoryCol.setMinWidth(100);
       partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("partInventory"));
     
       //Price Column
       TableColumn<Parts, Double> partPriceCol = new TableColumn<>("Price/Cost per Unit");
       partPriceCol.setMinWidth(175);
       partPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
       
       //Max Column
       TableColumn<Parts, Integer> partMaxCol = new TableColumn<>("Max");
       partMaxCol.setCellValueFactory(new PropertyValueFactory<>("partMax"));
       
       //Min Column
       TableColumn<Parts, Integer> partMinCol = new TableColumn<>("Min");
       partMinCol.setCellValueFactory(new PropertyValueFactory<>("partMin"));
       
       //Machine ID Column
       TableColumn<Parts, Integer> machineIdCol = new TableColumn<>("Machine ID");
       machineIdCol.setCellValueFactory(new PropertyValueFactory<>("machineId"));
       
       //Company Name Column
       TableColumn<Parts, String> companyNameCol = new TableColumn<>("Company Name");
       companyNameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
               
       TableView<Parts> addProductPartsTable = new TableView<>();
       addProductPartsTable.getColumns().addAll
       (
           partIdCol,
           partNameCol,
           partInventoryCol, 
           partPriceCol, 
           partMaxCol, 
           partMinCol,
           machineIdCol,
           companyNameCol
       );
       partMaxCol.setVisible(false);
       partMinCol.setVisible(false);
       machineIdCol.setVisible(false);
       companyNameCol.setVisible(false);
       rightAddProduct.add(addProductPartsTable, 0, 1, 4, 1);
       
       
    
       //Adds parts table to lower half of "Add Product" screen.
       addedPartsTable = new TableView<>();
       //addedPartsTable.setItems(data);
       addedPartsTable.getColumns().addAll
       (
           partIdColumn, 
           partNameColumn,
           partInventoryColumn,
           partPriceColumn,
           partMaxColumn,
           partMinColumn
       );
       rightAddProduct.add(addedPartsTable, 0, 3, 4, 1);
       
       
       //Adds layout to "Add Product" screen
       HBox addProductHBox2 = new HBox(10, addProductMaxText, addProductMin, addProductMinText);
       leftAddProduct.add(addProductHBox2, 1, 5, 4, 1);
       
       HBox addProductHBox = new HBox(10, saveAddProduct, cancelAddProduct);
       rightAddProduct.add(addProductHBox, 3, 5, 1, 1);
       GridPane.setHalignment(addProductHBox, HPos.RIGHT);
       addProductHBox.setAlignment(Pos.BASELINE_RIGHT);
       
       //Aligns and embeds grids for "Add Product" screen layout.
       GridPane.setConstraints(leftAddProduct,0,1);
       GridPane.setConstraints(rightAddProduct,1,1);
       GridPane.setHalignment(rightAddProduct, HPos.RIGHT);
       outerProductsGrid.getChildren().addAll(leftAddProduct, rightAddProduct);
       
       //----------------------------------------------------------------------------------------------------------------------
       //Modify Product Screen
       
       //Creates title label for "Modify Product" screen.
       Label modifyProductLabel = new Label("Modify Product");
       modifyProductLabel.setFont(Font.font("null", FontWeight.BOLD, 14));
       leftModifyProduct.add(modifyProductLabel,0, 0, 2, 1);
       
       //Creates "ID" label and textbox.
       Label modifyProductId = new Label("ID");
       leftModifyProduct.add(modifyProductId, 0, 1, 1, 1);
       
       TextField modifyProductIdText = new TextField();
       modifyProductIdText.setPromptText("Auto Gen - Disabled");
       modifyProductIdText.setDisable(true);
       leftModifyProduct.add(modifyProductIdText, 1, 1, 1, 1);
       
       //Creates "Name" label and textbox.
       Label modifyProductName = new Label("Name");
       leftModifyProduct.add(modifyProductName, 0, 2, 1, 1);
       
       TextField modifyProductNameText = new TextField();
       modifyProductNameText.setPromptText("Product Name");
       leftModifyProduct.add(modifyProductNameText, 1, 2, 1, 1);
       
       //Creates "Inventory" label and textbox.
       Label modifyProductInv = new Label("Inv");
       leftModifyProduct.add(modifyProductInv, 0, 3, 1, 1);
       
       TextField modifyProductInvText = new TextField();
       modifyProductInvText.setPromptText("Inv");
       leftModifyProduct.add(modifyProductInvText, 1, 3, 1, 1);
       
       //Creates "Price" label and textbox.
       Label modifyProductPrice = new Label("Price");
       leftModifyProduct.add(modifyProductPrice, 0, 4, 1, 1);
       
       TextField modifyProductPriceText = new TextField();
       modifyProductPriceText.setPromptText("Price");
       leftModifyProduct.add(modifyProductPriceText, 1, 4, 1, 1);
       
       //Creates "Max" and "Min" labels and textboxes.
       Label modifyProductMax = new Label("Max");
       leftModifyProduct.add(modifyProductMax, 0, 5, 1, 1);
       
       TextField modifyProductMaxText = new TextField();
       modifyProductMaxText.setPromptText("Max");
       modifyProductMaxText.setMinWidth(65);
       modifyProductMaxText.setMaxWidth(65);
       
       Label modifyProductMin = new Label("Min");
       
       TextField modifyProductMinText = new TextField();
       modifyProductMinText.setPromptText("Min");
       modifyProductMinText.setMinWidth(65);
       modifyProductMinText.setMaxWidth(65);
       
       TextField searchModifyProductText = new TextField();
       rightModifyProduct.add(searchModifyProductText, 1, 0, 1, 1);
       
       //Creates buttons used on "Modify Product" screen.
       Button searchModifyProduct = new Button("Search");
       searchModifyProduct.setMinWidth(65);
       searchModifyProduct.setMaxWidth(65);
       rightModifyProduct.add(searchModifyProduct, 0, 0, 1, 1);
       
       Button addModifyProduct = new Button("Add");
       addModifyProduct.setMinWidth(65);
       addModifyProduct.setMaxWidth(65);
       GridPane.setHalignment(addModifyProduct, HPos.RIGHT);
       rightModifyProduct.add(addModifyProduct, 3, 2, 1, 1);
       
       Button deleteModifyProduct = new Button("Delete");
       deleteModifyProduct.setMinWidth(65);
       deleteModifyProduct.setMaxWidth(65);
       GridPane.setHalignment(deleteModifyProduct, HPos.RIGHT);
       rightModifyProduct.add(deleteModifyProduct, 3, 4, 1, 1);
       
       Button saveModifyProduct = new Button("Save");
       saveModifyProduct.setMinWidth(65);
       saveModifyProduct.setMaxWidth(65);
       
       Button cancelModifyProduct = new Button("Cancel");
       cancelModifyProduct.setMinWidth(65);
       cancelModifyProduct.setMaxWidth(65);
       cancelModifyProduct.setOnAction(e -> window.setScene(main));
       
       
        /*
       //Adds the parts table to upper half of "Modify Product" screen.
       modifyProductPartsTable = new TableView<>();
       //modifyProductPartsTable.setItems(data);
       modifyProductPartsTable.getColumns().addAll
       (
           partIdColumn, 
           partNameColumn, 
           partInventoryColumn, 
           partPriceColumn,
           partMaxColumn,
           partMinColumn
       );
       rightModifyProduct.add(modifyProductPartsTable, 0, 1, 4, 1);
       
       //Adds parts table to lower half of "Modify Product" screen.
       modifiedPartsTable = new TableView<>();
      // modifiedPartsTable.setItems(data);
       modifiedPartsTable.getColumns().addAll
       (
           partIdColumn, 
           partNameColumn, 
           partInventoryColumn,
           partPriceColumn,
           partMaxColumn,
           partMinColumn
       );
       rightModifyProduct.add(modifiedPartsTable, 0, 3, 4, 1);
       */
       
       //Adds layout to "Modify Product" screen
       HBox modifyProductHBox2 = new HBox(10, modifyProductMaxText, modifyProductMin, modifyProductMinText);
       leftModifyProduct.add(modifyProductHBox2, 1, 5, 4, 1);
       
       HBox modifyProductHBox = new HBox(10, saveModifyProduct, cancelModifyProduct);
       rightModifyProduct.add(modifyProductHBox, 3, 5, 1, 1);
       GridPane.setHalignment(modifyProductHBox, HPos.RIGHT);
       modifyProductHBox.setAlignment(Pos.BASELINE_RIGHT);
       
       //Aligns and embeds grids for "Modify Product" layout.
       GridPane.setConstraints(leftModifyProduct,0,1);
       GridPane.setConstraints(rightModifyProduct,1,1);
       GridPane.setHalignment(rightModifyProduct, HPos.RIGHT);
       outerModifyProductsGrid.getChildren().addAll(leftModifyProduct, rightModifyProduct);
       
       //----------------------------------------------------------------------------------------------------------------------
       window.setScene(main);
       window.show();
   }
   
       public void addPart()                 
    {
       InHouse part = new InHouse();
       part.setPartId(Integer.parseInt(partIdText.getText()));
       part.setPartName(partNameText.getText());
       part.setPartInventory(Integer.parseInt(partInvText.getText()));
       part.setPartPrice(Double.parseDouble(partPriceText.getText()));
       part.setPartMax(Integer.parseInt(partMaxText.getText()));
       part.setPartMin(Integer.parseInt(partMinText.getText()));
       part.setMachineId(Integer.parseInt(machineIdText.getText()));
       parts.getItems().add(part);
       partIdText.clear();
       partNameText.clear();
       partInvText.clear();
       partPriceText.clear();
       partMaxText.clear();
       partMinText.clear();
       machineIdText.clear();
    }
   
        public void deletePart()
    {
        ObservableList<Parts> partSelected, allPart;
        allPart = parts.getItems();
        partSelected = parts.getSelectionModel().getSelectedItems();
        
        partSelected.forEach(allPart::remove);
    }
        
        
    /*
    public ObservableList<Parts> getMachineParts()
    {
        ObservableList<Parts> partsTable = FXCollections.observableArrayList();
        partsTable.add(new InHouse(, "Chocolate Chip", 5, 10.00, 5, 1));
        return partsTable;
    }*/
}
