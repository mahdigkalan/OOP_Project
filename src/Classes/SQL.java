package Classes;
import java.sql.* ;
public class SQL {
    public static void writeInDataBase() throws SQLException {
//        String jdbcUrlAdminDb = "jdbc:sqlite:Admin.db" ;
//        Connection connection = DriverManager.getConnection(jdbcUrlAdminDb) ;
//        String adminId = Functions.Functions.setID("admin") ;
//        String sql = "insert into adminTable values ('mahdisattari', 'mahdisattari4444', '"+adminId+"')" ;
//        Statement statement = connection.createStatement() ;
//        statement.executeUpdate(sql) ;
    }
    public static void readFromAdminDateBase() throws SQLException {
        String jdbcUrlAdminDb = "jdbc:sqlite:Admin.db" ;
        Connection connection = DriverManager.getConnection(jdbcUrlAdminDb) ;
        String sql = "select * from adminTable" ;
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery(sql) ;
        while (resultSet.next()){
            String username = resultSet.getString("username") ;
            String password = resultSet.getString("password") ;
            String adminid = resultSet.getString("id") ;
            Admin admin = new Admin(username,password,adminid) ;
            Admin.adminArrayList.add(admin) ;
            Admin.counterIDAdmin++ ;
        }
    }
    public static void readFromUserDateBase() throws SQLException {
        String jdbcUrlUserDb = "jdbc:sqlite:User.db" ;
        Connection connection = DriverManager.getConnection(jdbcUrlUserDb) ;
        String sql = "select * from userTable" ;
        Statement statement = connection.createStatement() ;
        ResultSet resultSet = statement.executeQuery(sql) ;
        while (resultSet.next()){
            String username = resultSet.getString("username") ;
            String password = resultSet.getString("password") ;
            String userid = resultSet.getString("id") ;
            User user = new User(username,password,userid) ;
            User.userArrayList.add(user) ;
            User.counterIDUser++ ;
        }
    }
    public static void disposable() throws SQLException {
//        String jdbcUrlAdminDb = "jdbc:sqlite:Admin.db" ;
//        Connection connection = DriverManager.getConnection(jdbcUrlAdminDb) ;
//        String sql = "delete from adminTable" ;
//        Statement statement = connection.createStatement() ;
//        statement.executeUpdate(sql) ;
    }
}
