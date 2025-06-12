import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

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

            // Trying to delete (we use drop to delete the table or database itself.)
//            Connection connection = DriverManager.getConnection(url,username,password);
//            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM students WHERE id = ?");
//            preparedStatement1.setInt(1,2);
//            int dropVal = preparedStatement1.executeUpdate();
//            if(dropVal > 0) System.out.println("Dropped successfully!");
//            else System.out.println("Failed to drop");



            // Batch processing

            Connection connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
            String query = "INSERT INTO students(name, age, marks) VALUES(?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner sc = new Scanner(System.in);
            while(true) {
                System.out.print("Enter Name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                System.out.print("Enter Marks: ");
                double marks = sc.nextDouble();

                sc.nextLine();
                /*The "leftover" refers to the newline character (\n) that remains in the input buffer after using methods
                like nextInt() or nextDouble() from Scanner. These methods read only the number, not the newline you press
                after entering the value. When you call nextLine() next, it immediately reads this leftover newline, resulting
                in skipped input.*/
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);
                preparedStatement.addBatch();

                System.out.print("\nAdd next? (Y/N): ");
                String choice = sc.next().toUpperCase();
//                String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s',%d,%f)",name,age,marks);
                if (choice.equals("N")) {
                    break;
                }
            }
                // So what happens in batch processing is when you execute the batch each statement is going to run independently meaning in the array
                // each pocket or index '| |' is going to store the binary/bool value that is returned from the process success or failure
                /* so if the process succeeds then 1 will be stored and vice versa like this --> |1|0|1|1|0|*/
                int[] rowsAffected = preparedStatement.executeBatch();

                for(int i = 0; i<rowsAffected.length; i++){
                        if(rowsAffected[i] == 0){
                            System.out.println(i+" query failed to execute.");
                        }
                    }



            //--------Viewing the contents of the table
            String queryToView = "SELECT * FROM students";
            ResultSet resultSet = preparedStatement.executeQuery(queryToView);

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double marks = resultSet.getDouble("marks");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + "\nNAME: " + name + "\nAge: "+age+"\nMarks: "+ marks +"\n");

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
