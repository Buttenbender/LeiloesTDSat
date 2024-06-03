import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
    private conectaDAO conexao;
    private Connection conn;
    
    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.getConexao();
    }
    
    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            stmt.execute();
        }
        catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }
}