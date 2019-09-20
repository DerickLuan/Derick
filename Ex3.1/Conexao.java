import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import javax.swing.JOptionPane;

public class Conexao
{
	public Conexao(){}

	public static Connection conecta() throws SQLException, Exception
	{
		Connection conexao = null;
		try
		{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/Curso","root", "");
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
		return conexao;
	}

}
