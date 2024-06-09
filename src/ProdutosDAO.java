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
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> produtos = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                
                produtos.add(produto);
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }
    
    public void venderProduto(int produtoID) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            stmt.setInt(2, produtoID);
            
            int linhaAfetada = stmt.executeUpdate();
            
            if (linhaAfetada == 0) {
                JOptionPane.showMessageDialog(null, "Produto com ID " + produtoID + " não encontrado!");
            }
            else {
                JOptionPane.showMessageDialog(null, "Produto com ID " + produtoID + " foi vendido com sucesso!");
            }
            
            stmt.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        }
    }
}