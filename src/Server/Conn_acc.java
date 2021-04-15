package Server;

import java.sql.*;

// "jdbc:mysql://localhost:3306/Inbox" -> Inbox
// "jdbc:mysql://localhost:3306/Email accounts" -> konta
public class Conn_acc {
    private Connection connection;
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
        try {
            String sql = "INSERT INTO Accounts (Email_addr,password) VALUES (?,?)";
            Conn_emails inbox = new Conn_emails();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, acc);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            inbox.createInbox(acc);
            inbox.closeConn();
            System.out.println("Konto zostalo zalozone");
            return true;
        } catch (Exception e) {
            System.out.println("Konto o danym emailu znajduje sie w bazie danych");
            return false;
        }
    }

    public boolean deleteAccount(String acc) throws SQLException {
        try {
            String sql = "DELETE FROM Accounts WHERE Email_addr = ?";
            Conn_emails inbox = new Conn_emails();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, acc);
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate()==1) {
                inbox.deleteInbox(acc);
                inbox.closeConn();
                System.out.println("Konto zostalo usuniete");
                return true;
            }else {
                System.out.println("Brak takiego konta");
                return false;
            }
        }catch (Exception e){
            System.out.println("Brak takiego konta");
            return false;
        }
    }

    public void closeConn() throws SQLException {
        connection.close();
    }

    public boolean isAcc(String acc, String password) throws SQLException {
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

    public boolean changePassword(String acc, String password, String new_password) throws SQLException {
        if (isAcc(acc, password)) {
            String sql = "UPDATE Accounts SET password = ? WHERE Email_addr = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, new_password);
            preparedStatement.setString(2, acc);
            preparedStatement.executeUpdate();
            return true;
        } else
            return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Conn_acc a = new Conn_acc();

        a.createAccount("Abc", "jfjfjfj");
    }
}
