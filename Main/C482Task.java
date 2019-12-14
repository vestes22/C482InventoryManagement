package virginiaestesc482;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import Model.Product;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VirginiaEstesC482 extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreenFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        /*
        * Loads the application with sample data.
        */
        Part part1 = new InHouse(1, "Part 1", 1.11, 5, 2, 10, 10);
        Part part2 = new InHouse(2, "Part 2", 2.22, 6, 2, 10, 20);
        Part part3 = new InHouse(3, "Part 3", 3.33, 7, 5, 15, 10);
        Part part4 = new InHouse(4, "Part 4", 4.44, 8, 5, 15, 20);
        Part part5 = new InHouse(5, "Part 5", 5.55, 9, 8, 18, 30);
        
        Part part6 = new Outsourced(6, "Part 6", 6.66, 10, 5, 15, "Comp 1");
        Part part7 = new Outsourced(7, "Part 7", 7.77, 11, 5, 15, "Comp 2");
        Part part8 = new Outsourced(8, "Part 8", 8.88, 15, 10, 20, "Comp 1");
        Part part9 = new Outsourced(9, "Part 9", 9.99, 12, 10, 20, "Comp 3");
        Part part10 = new Outsourced(10, "Part 10", 10.01, 10, 5, 15, "Comp 3");
        
        Product product1 = new Product(10, "Product 1", 10.01, 4, 3, 5);
        product1.addAssociatedPart(part9);
        product1.addAssociatedPart(part3);
        
        Product product2 = new Product(20, "Product 2", 20.01, 5, 4, 6);
        product2.addAssociatedPart(part1);
        product2.addAssociatedPart(part5);
        
        Product product3 = new Product(30, "Product 3", 30.01, 6, 5, 7);
        product3.addAssociatedPart(part7);
        product3.addAssociatedPart(part4);
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);
        Inventory.addPart(part7);
        Inventory.addPart(part8);
        Inventory.addPart(part9);
        Inventory.addPart(part10);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        
        launch(args);
    }
}

