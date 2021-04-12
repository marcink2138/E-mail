package Server;

import java.sql.*;

// "jdbc:mysql://localhost:3306/Inbox" -> Inbox
// "jdbc:mysql://localhost:3306/Email accounts" -> konta

public class Conn_emails {
    private static Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public Conn_emails() throws SQLException {
        //URL bazy
        String URL = "jdbc:mysql://localhost:3306/Email accounts";
        //haslo do bazy
        String passwd = "admin";
        //login do bazy
        String login = "root";
        connection = DriverManager.getConnection(URL, login, passwd);              //Laczenie z baza
    }

    public void createInbox(String acc) {

    }

    public void deleteInbox(String acc) {

    }

    public void getListMess(String acc) {

    }

    public void getMess(String acc, int id) {

    }

    public void deleteMess(String acc, int id) {

    }

}
