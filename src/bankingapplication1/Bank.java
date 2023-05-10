/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapplication1;
import bankingapplication1.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Student
 */
public class Bank {
    private String name;
    
    public Bank(){
    
    }
    public Bank(String name){
    this.name = name;
    }
    
    public void listAccounts(){
        Connection connection= BankingConnection.connect();

        Statement statement;
        try {
            statement = connection.createStatement();        
            String sql = "SELECT * FROM account";
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                System.out.println(results.getString(1)+" " +results.getString(2)+" "+results.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void openAccount (Account account){
      Connection connection= BankingConnection.connect();
      String sql = "Insert into student (accNumber,accName,accBalance)" + "VALUES (?,?,?)";
      PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setString(1,"651114");
            preparedStatement.setString(2,"Porama");
            preparedStatement.setDouble(3,100000); 
            preparedStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }
    public void closeAccount (int number){
    Connection connection= BankingConnection.connect();
    String sql = "DELETE from account WHERE accNumber = ?";
      PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setString(1,"651114");
            preparedStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void depositMoney(int number , double amount){
    Account account = getAccount(number);
    account.deposit(amount);
    Connection connection= BankingConnection.connect();
    String sql = "UPDATE account SET accbalance=? WHERE StudentID = ?";
     PreparedStatement preparedStatement;
     try {
            preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setDouble(1,account.getBalance());
            preparedStatement.setInt(2,account.getNumber());
            preparedStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        System.out.println("Balance: "+ account.getBalance());
    
    }
    public void withdrawMoney(int number , double amount){
    Account account = getAccount(number);
     account.withdraw(amount);
     System.out.println("Balance: "+ account.getBalance());
}
    public Account getAccount(int number){
        Connection connection= BankingConnection.connect();
        String sql = "SELECT * FROM account Where accNumber =?";
        PreparedStatement preparedStatement;
        Account account= null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,number);
            ResultSet result = preparedStatement.executeQuery(sql);
            result.next();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            account = new Account(number, accName, balance);
           
             
        } catch (SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    return account;
    }
    
    
}

