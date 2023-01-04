package com.example.supply_chain_system;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    private byte[] getSHA(String input){
        try{
            MessageDigest digest=MessageDigest.getInstance("SHA-256");
            return digest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    private String getEncryptedPassword(String password){
        try{
//            System.out.println(getSHA(password));
//            byte x[]=getSHA(password);
//            for (int i=0;i< x.length;i++){
//                System.out.print(x[i]);
//            }
            BigInteger bigInteger=new BigInteger(1,getSHA(password));
//            System.out.println(bigInteger);
            StringBuilder encryptedString=new StringBuilder(bigInteger.toString(16));
            return encryptedString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    public boolean customerLogin(String email,String password){
        String query=String.format("select * from customer where email='%s' and password='%s' ",email,getEncryptedPassword(password));

        try{
            DatabaseConnection databaseConnection=new DatabaseConnection();
            ResultSet resultSet=databaseConnection.getQueryResult(query);
            if (resultSet!=null&&resultSet.next()){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        Login login=new Login();
//        System.out.println(login.customerLogin("prem@gmail.com","abc123"));
//    }
public static void main(String[] args) {
    Login login=new Login();
    System.out.println(login.getEncryptedPassword("abc123"));
}
}
