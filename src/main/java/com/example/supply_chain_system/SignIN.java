package com.example.supply_chain_system;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SignIN {
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
            BigInteger bigInteger=new BigInteger(1,getSHA(password));
            StringBuilder encryptedString=new StringBuilder(bigInteger.toString(16));
            return encryptedString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    public boolean getSignIn(String fName,String lName,String email,String password,String mobile,String address){
        password=getEncryptedPassword(password);
        int rowCount=0;
        try{
//
                DatabaseConnection databaseConnection = new DatabaseConnection();
                rowCount=databaseConnection.signInUpdate(fName,lName, email, password, mobile,address);
//
//
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowCount!=0;
    }
}
