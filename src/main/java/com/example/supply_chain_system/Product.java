package com.example.supply_chain_system;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public Product(int id,String name,Double price){
        this.id=new SimpleIntegerProperty(id);
        this.name=new SimpleStringProperty(name);
        this.price=new SimpleDoubleProperty(price);

    }

    public int getId() {
        return id.get();
    }




    public String getName() {
        return name.get();
    }



    public double getPrice() {
        return price.get();
    }

    //to get all products details
    public static ObservableList<Product> getAllProducts(){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ObservableList<Product> productsList= FXCollections.observableArrayList();
        String query="select * from product";
        try{
            ResultSet rs=databaseConnection.getQueryResult(query);
            while (rs!=null&& rs.next()){
                productsList.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getDouble("price")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productsList;
    }

    //to get only product details whose name matches
    public static ObservableList<Product> getProductsByName(String productName){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ObservableList<Product> productsList= FXCollections.observableArrayList();
        String query=String.format("select * from product where lower(name) like '%%%s%%' ",productName.toLowerCase());
        try{
            ResultSet rs=databaseConnection.getQueryResult(query);
            while (rs!=null&& rs.next()){
                productsList.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getDouble("price")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productsList;
    }

    public static ObservableList<Product> getPreviousOrders(String customerEmail){
        DatabaseConnection databaseConnection=new DatabaseConnection();
        ObservableList<Product> productsList= FXCollections.observableArrayList();
        String query=String.format("SELECT p.product_id,p.name,p.price FROM (orders o join customer c on o.customer_id=c.customer_id and c.email='%s' ) join product p on p.product_id=o.product_id",customerEmail);
        try{
            ResultSet rs=databaseConnection.getQueryResult(query);
            while (rs!=null&& rs.next()){
                productsList.add(new Product(rs.getInt("product_id"),rs.getString("name"),rs.getDouble("price")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productsList;
    }

}
