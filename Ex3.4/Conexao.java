import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import javax.swing.JOptionPane;

public class Conexao
{
	public Conexao(){}
	
	public Leitura ler = new Leitura();

	public Connection conecta() throws SQLException, Exception
	{
		Connection conexao = null;
		String login, senha;
		login = ler.login();
		senha = ler.senha();
		try
		{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/Curso", login, senha);
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
		if(conexao == null)
		{
			JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos!");
		}
		else{JOptionPane.showMessageDialog(null, "Bem-Vindo!");}
		return conexao;
	}
}
