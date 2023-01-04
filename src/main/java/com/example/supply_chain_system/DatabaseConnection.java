package com.example.supply_chain_system;
import java.sql.*;
public class DatabaseConnection {
    private static final String databaseUrl="jdbc:mysql://localhost:3306/supply_chain_system";
    private static final String userName="root";
    private static final String password="Prem1301";
    public Statement getStatement(){
        Statement statement=null;
        Connection conn;
        try{
            conn=DriverManager.getConnection(databaseUrl,userName,password);
            statement= conn.createStatement();

        }
        catch (Exception e){
            e.printStackTrace();;
        }
        return statement;
    }
    public String getCustomerName(String customerEmail){
        Statement statement=getStatement();
        String query=String.format("select first_name from customer where email='%s'",customerEmail);
        try{
            ResultSet rs=statement.executeQuery(query);
            if(rs.next()){
                return rs.getString("first_name");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryResult(String query){
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int orderUpdate(String customerEmail,Product product){
        Statement statement=getStatement();
        String query=String.format("insert into orders (customer_id,product_id) values((select customer_id from customer where email='%s'),'%s') ",customerEmail,product.getId());
        try{
            return statement.executeUpdate(query);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int signInUpdate(String fName,String lName,String email,String password,String mobile,String address){
          Statement statement=getStatement();

          String query=String.format("insert into customer (first_name,last_name,email,password,phone_number,address) values('%s','%s','%s','%s','%s','%s')",fName,lName,email,password,mobile,address);
        try {
            return  statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        DatabaseConnection databaseConnection=new DatabaseConnection();
//        ResultSet rs= databaseConnection.getQueryResult("select email,first_name from customer");
//        try {
//            while (rs.next()) {
//                System.out.println(rs.getString("email") + " " + rs.getString("first_name"));
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
