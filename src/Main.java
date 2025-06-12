import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "password";


    public static void main(String[] args) {

        //Connect IDE with database.
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //Load necessary drivers.
        try{

            //Using Statement interface not recommended because compiler and you have to run this same piece again and again
//            Connection connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();



            // -------------------To Insert Data into the table-----------------------------------//
            //YOU HAVE TO USE FORMAT SPECIFIER FOR THE SPECIFIC DATATYPE                🔻
//            String query = String.format("INSERT INTO students(name,age,marks) VALUES('%s',%o,%f)","Aashika",22,98.2);
//
//            //since we are inserting into the table so we don't need to print the loop we create a int var and store the table effected in the process.
//            var rowsAffected = statement.executeUpdate(query);
//            if(rowsAffected>0) System.out.println("Successfully INSERTED and "+rowsAffected+" rows are affected.");
//            else System.out.println("Insertion unsuccessful.");


            //Updating the contents of the table.
//
//            String updateQuery = String.format("UPDATE students SET marks = %f WHERE id = %o",99.7,2);
//
//            int updateInt = statement.executeUpdate(updateQuery);
//
//
//            if(updateInt > 0) System.out.println("Updated Successfully!!!");
//            else System.out.println("Failed to Update!!!");


            // Deleting the data from the table
//
//            String deleteQuery = String.format("DELETE FROM students WHERE id = %d",1);
//            int deleteInt = statement.executeUpdate(deleteQuery);
//
//            if(deleteInt > 0) System.out.println("Deleted Successfully!");
//            else System.out.println("Deletion Failed!");

//          // Just checking with my own data
//            String updateID = String.format("UPDATE students SET id = %d WHERE id = %d",1,2);
//            int updateIDCommand = statement.executeUpdate(updateID);
//            if(updateIDCommand>0) System.out.println("Successful!");
//            else System.out.println("Failed");


//            //Using PreparedStatement
//            Connection connection = DriverManager.getConnection(url,username,password);
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students(name,age,marks) VALUES(?,?,?)");
//
//            preparedStatement.setString(1,"Janarjan Rajbhandari");
//            preparedStatement.setInt(2,22);
//            preparedStatement.setDouble(3,95.2);
//
//            int returnedVal = preparedStatement.executeUpdate();
//            if(returnedVal>0) System.out.println("Inserted Successfully!");
//            else System.out.println("Insertion failed!");

            // Trying to drop
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            preparedStatement1.setInt(1,2);
            int dropVal = preparedStatement1.executeUpdate();
            if(dropVal > 0) System.out.println("Dropped successfully!");
            else System.out.println("Failed to drop");



            //--------Viewing the contents of the table
            String query = "SELECT * FROM students";
            ResultSet resultSet = preparedStatement1.executeQuery(query);

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double marks = resultSet.getDouble("marks");

                System.out.println("ID: " + id + "\nNAME: " + name + "\nMarks: "+ marks +"\n");

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
