package com.example.supply_chain_system;

public class Order {
    public static boolean getOrderUpdated(String customerEmail,Product product){
        int rowCount=0;
        try{
//
            DatabaseConnection databaseConnection = new DatabaseConnection();
            rowCount=databaseConnection.orderUpdate(customerEmail,product);
//
//
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowCount!=0;
    }
}
