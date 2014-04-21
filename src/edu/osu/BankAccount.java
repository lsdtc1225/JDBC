package edu.osu;

//import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.math.BigDecimal;


public class BankAccount {
    public static void main(String[] args) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/youtube", "root", "root");
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            BigDecimal withdrawAmount = new BigDecimal(100);

            withdrawFromChecking(connection, statement, withdrawAmount, 1);
            depositIntoSaving(connection, statement, withdrawAmount, 1);

            statement.executeBatch();
            connection.commit();
            System.out.println("Account Modified");
        }
        /*catch(ClassNotFoundException error)
        {
            System.out.println("Error: " + error.getMessage());
        }
        catch(SQLException error)
        {
            System.out.println("Error: " + error.getMessage());
        }*/
        catch(Exception error){
            
            System.out.println("Error: " + error.getMessage());
            connection.rollback();
        }
        finally
        {
            if(connection != null) try{connection.close();} catch(SQLException ignore){}
            if(statement != null) try{statement.close();} catch(SQLException ignore){}
        }
    }

    public static void withdrawFromChecking(Connection connection, Statement statement, BigDecimal amount, int id) throws SQLException{
        statement.addBatch("UPDATE bankAccount SET checkingBalance = checkingBalance - " + amount +" WHERE id=" + id);
    }

    public static void depositIntoSaving(Connection connection, Statement statement, BigDecimal amount, int id) throws SQLException{
        statement.addBatch("UPDATE bankAccount SET savingBalance = savingBalance + " + amount +" WHERE id=" + id);
    }
}
