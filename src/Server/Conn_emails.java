package Server;

import Comunication.Mail;

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

    public void createInbox(String acc) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS {0} (messId int NOT NULL AUTO_INCREMENT, " +
                "sender varchar(30) NOT NULL , title varchar (20) NOT NULL ,date_ varchar(20) NOT NULL , " +
                "message text NOT NULL , PRIMARY KEY (messId))";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(MessageFormat.format(sql, string_converter(acc)));
    }

    public void deleteInbox(String acc) throws SQLException {
        String sql = "DROP TABLE {0}";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(MessageFormat.format(sql, string_converter(acc)));
    }

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


    public void deleteMess(String acc, int id) throws SQLException {
        String sql = "DELETE FROM {0} WHERE messId = ?";
        preparedStatement = connection.prepareStatement(MessageFormat.format(sql, string_converter(acc)));
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void insertMess(String acc, String sender, String title, String date, String messege) throws SQLException {
        String sql = "INSERT INTO {0} (sender, title, date_, message) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(MessageFormat.format(sql, string_converter(acc)));
        preparedStatement.setString(1, sender);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, date);
        preparedStatement.setString(4, messege);
        preparedStatement.executeUpdate();
    }

    private String string_converter(String str) {
        return "`" + str + "`";
    }

    public void closeConn() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Conn_emails a = new Conn_emails();
        a.createInbox("msadk");

    }
}
