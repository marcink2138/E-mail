package Server;

import Comunication.Mail;
import Comunication.Message;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    public void createAccount(String acc, String password) throws SQLException {
        String sql = "INSERT INTO Accounts (Email_addr,password) VALUES (?,?)";
        Conn_emails inbox = new Conn_emails();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, acc);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        inbox.createInbox(acc);
        System.out.println("Konto o danym emailu znajduje sie w bazie danych");
        System.out.println("Konto zostalo zalozone");
        inbox.closeConn();

    }

    public void deleteAccount(String acc) throws SQLException {
        String sql = "DELETE FROM Accounts WHERE Email_addr = ?";
        Conn_emails inbox = new Conn_emails();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, acc);
        preparedStatement.executeUpdate();
        inbox.deleteInbox(acc);
        inbox.closeConn();
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
        //Conn_acc a = new Conn_acc();
        //Conn_emails b = new Conn_emails();
        //a.deleteAccount("marcin@kercz@jd.pl");
        //b.deleteMess("marcin@karcz@jd.pl", 1);
        //ArrayList<Mail> t = new ArrayList<>();
        //Message w = new Mail("mess","ab","ab",2,"cf","ag","sdadsa");
        //w=(Mail)w;
        //t = b.getListMess("marcin@karcz@jd.pl");
        //for (int i =0; i<t.size(); i++)
        //    System.out.println((Message)t.get(i).getComand());
    }
}
