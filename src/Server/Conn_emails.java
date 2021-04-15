package Server;

//import Comunication.Mail;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

// "jdbc:mysql://localhost:3306/Inbox" -> Inbox
// "jdbc:mysql://localhost:3306/Email accounts" -> email acc

public class Conn_emails {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public Conn_emails() throws SQLException {
        //URL of database
        String URL = "jdbc:mysql://localhost:3306/Inbox";
        //password to database
        String passwd = "admin";
        //login to database
        String login = "root";
        connection = DriverManager.getConnection(URL, login, passwd);              //Establishing connection with DB
    }

    public boolean createInbox(String acc) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS {0} (messId int NOT NULL AUTO_INCREMENT, " +
                "sender varchar(30) NOT NULL , title varchar (20) NOT NULL ,date_ varchar(20) NOT NULL , " +
                "message text NOT NULL , PRIMARY KEY (messId))";
        preparedStatement = connection.prepareStatement(sql);

        if (preparedStatement.executeUpdate(MessageFormat.format(sql, string_converter(acc))) == 1) {
            System.out.println("Utworzony skrzynke odbiorcza");
            return true;
        } else {
            System.out.println("Istnieje juz taka skrzynka odbiorcza");
            return false;
        }

    }

    public boolean deleteInbox(String acc) throws SQLException {
        try {
            String sql = "DROP TABLE {0}";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate(MessageFormat.format(sql, string_converter(acc)));
            System.out.println("Udalo usunac sie skrzynke odbiorcza");
            return true;
        } catch (Exception e) {
            System.out.println("Brak skrzynki odbiorczej dla danego konta");
            return false;
        }

    }
/*
    public ArrayList<Mail> getListMess(String acc) throws SQLException {
        ArrayList<Mail> messages = new ArrayList<>();
        String sql = "SELECT * FROM {0}";
        preparedStatement = connection.prepareStatement(MessageFormat.format(sql, string_converter(acc)));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            messages.add(new Mail("mess", acc, resultSet.getString("sender"),
                    resultSet.getInt("messId"), resultSet.getString("title"),
                    resultSet.getString("date_"), resultSet.getString("message")));
        return messages;
    }
*/

    public boolean deleteMess(String acc, int id) throws SQLException {
        try {
            String sql = "DELETE FROM {0} WHERE messId = ?";
            preparedStatement = connection.prepareStatement(MessageFormat.format(sql, string_converter(acc)));
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Udane usuniecie maila");
            return true;
        } catch (Exception e) {
            System.out.println("Brak rekordu lub tabeli");
            return false;
        }

    }

    public boolean insertMess(String acc, String sender, String title, String date, String messege) throws SQLException {
        try {
            String sql = "INSERT INTO {0} (sender, title, date_, message) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(MessageFormat.format(sql, string_converter(acc)));
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, messege);
            preparedStatement.executeUpdate();
            System.out.println("Zapisano wiadomosc");
            return true;
        } catch (Exception e) {
            System.out.println("Brak skrzynki odbiorczej");
            return false;
        }

    }

    private String string_converter(String str) {
        return "`" + str + "`";
    }

    public void closeConn() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Conn_emails a = new Conn_emails();
        a.insertMess("lpsad","dsa","dsa","cafsasf","asdasdasd");


    }
}
