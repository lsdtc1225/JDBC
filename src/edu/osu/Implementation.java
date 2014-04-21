package edu.osu;

import java.lang.ClassNotFoundException;
import java.sql.SQLException;
//import java.sql.ResultSet;
import java.sql.Connection;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;


public class Implementation {

    public static void addBook(Connection connection, PreparedStatement preparedStatement, String name, String author, String publisher) throws SQLException{
        preparedStatement = connection.prepareStatement("INSERT INTO books (name, author, publisher) VALUES (?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, author);
        preparedStatement.setString(3, publisher);
        preparedStatement.executeUpdate();
    }



    public static void main (String[] args){
        Connection connection = null;
        //Statement statement = null;
        //ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try{

            System.out.println("Connection To Database");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/youtube", "root", "root");
            //statement = connection.createStatement();
            //statement.execute("CREATE TABLE books (id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), author VARCHAR(100), publisher VARCHAR(100))");
            //System.out.println("Connection Successful");
            //statement.execute("INSERT INTO books(name, author, publisher) VALUES ('Harry Potter and deathly', 'J.K Rowlling', 'Apple Coporation')");
            //System.out.println("Book Inserted");
            //statement = connection.createStatement();
            //resultSet = statement.executeQuery("SELECT id, name FROM books");
            /*while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                System.out.println(id + "\t" + name);
            }*/
            addBook(connection, preparedStatement, "The Matrix", "Tim Burton", "Apple");
            System.out.println("Statement Successfully Entered");
        }
        catch(ClassNotFoundException error){
            System.out.println("Error: " + error.getMessage());
        }
        catch(SQLException error){
            System.out.println("Error: " + error.getMessage());
        }
        finally{
            if(connection != null) try{connection.close();} catch(SQLException ignore){}
            
            //if(statement != null) try{statement.close();} catch(SQLException ignore){}
            
            //if(resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
            if(preparedStatement != null) try{preparedStatement.close();} catch(SQLException ignore){}
        }
    }
}
