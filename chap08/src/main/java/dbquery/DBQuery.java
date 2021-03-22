package dbquery;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    private DataSource dataSource;

    public DBQuery(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public int count(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection(); // 풀에서 구함
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("select count(*) from MEMBER")){
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(connection!=null)
                try {
                    connection.close(); // 풀에 반환
                } catch (SQLException e){
                    
                }
        }
    }

}
