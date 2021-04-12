package Server;

import java.sql.*;

// "jdbc:mysql://localhost:3306/Inbox" -> Inbox
// "jdbc:mysql://localhost:3306/Email accounts" -> konta
public class Conn_acc {
    private static Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public Conn_acc() throws SQLException {
        //URL bazy
        String URL = "jdbc:mysql://localhost:3306/Email accounts";
        //haslo do bazy
        String passwd = "admin";
        //login do bazy
        String login = "root";
        connection = DriverManager.getConnection(URL, login, passwd);              //Laczenie z baza
    }

    public boolean createAccount(String acc, String password) throws SQLException {
        String sql = "INSERT INTO Accounts (Email_addr,password) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, acc);
        preparedStatement.setString(2, password);
        try {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Konto o danym emailu znajduje sie w bazie danych");
            return false;
        }
        System.out.println("Konto zostalo zalozone");
        return true;

    }

    public void deleteAccount(String acc) throws SQLException {
        String sql = "DELETE FROM Accounts WHERE Email_addr = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, acc);
        preparedStatement.executeUpdate();
    }

    public void closeConn() throws SQLException {
        connection.close();
    }

    public boolean getAcc(String acc, String password) throws SQLException {
        String sql = "SELECT * FROM Accounts WHERE Email_addr = ? AND password = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, acc);
        preparedStatement.setString(2, password);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Konto znajduje sie w bazie danych");
            return true;
        } else {
            System.out.println("Brak konta");
            return false;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Conn_acc a = new Conn_acc();
        //a.createAccount("JESTEMBOGIEM", "polska");
        a.getAcc("JESTEMBOGIEM1", "polska");
        //a.deleteAccount("marianczello@pl.com");
        a.closeConn();
    }
}
