
import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDAO {
  public Connection getConexao() {
      try {
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root", "jp140405");
          return conn;
      }
      catch (Exception e) {
          System.out.println("Erro ao conectar: " + e.getMessage());
          return null;
      }
  }
}
