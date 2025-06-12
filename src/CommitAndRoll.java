import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommitAndRoll {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "password";


    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");

        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException();
            e.printStackTrace();
        }

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String debit_query = "UPDATE accounts SET balance = balance - ? where account_num = ?";
            String credit_query = "UPDATE accounts SET balance = balance - ? where account_num = ?";

            PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query);

            debitPreparedStatement.setDouble(1,500);
            debitPreparedStatement.setInt(2,101);

            creditPreparedStatement.setDouble(1,500);
            creditPreparedStatement.setInt(2,102);

            int affected_rows1 = debitPreparedStatement.executeUpdate();
            int affected_rows2 = creditPreparedStatement.executeUpdate();
        }
        catch (SQLException sq){
            System.out.println(sq.getMessage());
        }
    }
}
