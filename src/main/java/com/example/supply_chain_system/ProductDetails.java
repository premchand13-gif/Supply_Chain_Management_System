package com.example.supply_chain_system;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {
    private TableView<Product> productTable;
    public static ObservableList<Product> addToCart_list= FXCollections.observableArrayList();

    public void setAddToCart_list(){
        Product selectedProduct=getProductDetails();
        addToCart_list.add(selectedProduct);
    }

    public Product getProductDetails(){
        try{
            Product selectedProduct=productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Display whole product table
    public Pane getProductPane(){

        // creating columns
        TableColumn id=new TableColumn<>("Product Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        // adding data to table
//        ObservableList<Product> list= FXCollections.observableArrayList();
//        list.add(new Product(1,"Lenovo",49999.99));
//        list.add(new Product(2,"Hp",59999.99));
//        list.add(new Product(3,"Apple",98999.99));

        ObservableList<Product> list=Product.getAllProducts();
        productTable=new TableView<>();
        productTable.setItems(list);

        //adding all columns to tableView
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.w,SupplyChain.h);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //adding tableView to pane

        Pane productPane=new Pane();
        productPane.setPrefSize(SupplyChain.w,SupplyChain.h);
//        productPane.setTranslateY(SupplyChain.b);
//        productPane.setTranslateX(SupplyChain.b);
        productPane.getChildren().add(productTable);
        return productPane;
    }

    // display product table based on name in searchbar
    public Pane getProductPaneByName(String productName){

        // creating columns
        TableColumn id=new TableColumn<>("Product Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        ObservableList<Product> list=Product.getProductsByName(productName);
        productTable=new TableView<>();
        productTable.setItems(list);

        //adding all columns to tableView
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.w,SupplyChain.h);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //adding tableView to pane

        Pane productPane=new Pane();
        productPane.setPrefSize(SupplyChain.w,SupplyChain.h);
        productPane.getChildren().add(productTable);
        return productPane;
    }
    // display previous product orders
    public Pane getProductPaneOfPreviousOrders(String customerEmail){

        // creating columns
        TableColumn id=new TableColumn<>("Product Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        ObservableList<Product> list=Product.getPreviousOrders(customerEmail);
        productTable=new TableView<>();
        productTable.setItems(list);

        //adding all columns to tableView
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.w,SupplyChain.h);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //adding tableView to pane

        Pane productPane=new Pane();
        productPane.setPrefSize(SupplyChain.w,SupplyChain.h);
        productPane.getChildren().add(productTable);
        return productPane;
    }
   //display the add to cart product list
    public Pane getAddToCartPane(){

        // creating columns
        TableColumn id=new TableColumn<>("Product Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



//        ObservableList<Product> list=Product.getPreviousOrders(customerEmail);
        productTable=new TableView<>();
        productTable.setItems(addToCart_list);

        //adding all columns to tableView
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.w,SupplyChain.h);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //adding tableView to pane

        Pane productPane=new Pane();
        productPane.setPrefSize(SupplyChain.w,SupplyChain.h);
        productPane.getChildren().add(productTable);
        return productPane;
    }
}
