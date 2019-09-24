import javax.swing.JOptionPane;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import java.util.ArrayList;

public class DAO
{
	private Connection conexao;

	public DAO(Connection con)
	{
		this.conexao = con;
	}

	public Leitura ler = new Leitura();

	public void salvarBd(int op) throws SQLException, Exception
	{		
		try
		{
			ArrayList<String> p = new ArrayList();
			ler.salvar(p, op);
			if(op == 1)
			{
				String sql = "insert into Professor(nome, idade, cpf, sexo, siape) values(?,?,?,?,?)";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, p.get(0));
				ps.setInt(2, Integer.parseInt(p.get(1)));
				ps.setString(3, p.get(2));
				ps.setString(4, p.get(3));
				ps.setInt(5, Integer.parseInt(p.get(4)));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
			}
			else if(op == 2)
			{
				String sql = "insert into Aluno(nome, idade, cpf, sexo, ra) values(?,?,?,?,?)";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, p.get(0));
				ps.setInt(2, Integer.parseInt(p.get(1)));
				ps.setString(3, p.get(2));
				ps.setString(4, p.get(3));
				ps.setInt(5, Integer.parseInt(p.get(4)));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
			}
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
	}
	
	public void buscarBd(int op) throws SQLException, Exception
	{
		ResultSet rs = null;
		try
		{
			if(op == 1)
			{		
				String sql = "select * from Professor where nome = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, ler.nome());
				rs = ps.executeQuery();
				while(rs.next())
				{
					int siape = rs.getInt("siape");
					String nome = rs.getString("nome");
					int idade = rs.getInt("idade");
					String cpf = rs.getString("cpf");
					String sexo = rs.getString("sexo");
					JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nIdade: " + idade + "\nCPF: " + cpf + "\nSexo: " + sexo + "\nSiape: " + siape);
				}
			}
			else if(op == 2)
			{
				String sql = "select * from Aluno where nome = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, ler.nome());
				rs = ps.executeQuery();
				while(rs.next())
				{
					int ra = rs.getInt("ra");
					String nome = rs.getString("nome");
					int idade = rs.getInt("idade");
					String cpf = rs.getString("cpf");
					String sexo = rs.getString("sexo");
					JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nIdade: " + idade + "\nCPF: " + cpf + "\nSexo: " + sexo + "\nRA: " + ra);
				}
			}		
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
	}

	public void remover(int op) throws SQLException, Exception
	{
		String cpf = ler.cpf(); 
		boolean b = checar(op, cpf);
		ResultSet rs = null;
		try
		{
			if(op == 1 && b == true)
			{		
				String sql = "delete from Professor where cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
			}
			else if(op == 2 && b == true)
			{
				String sql = "delete from Aluno where cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				ps.executeUpdate();				
				JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
			}	
			else{JOptionPane.showMessageDialog(null, "Pessoa não cadastrada!");}	
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
	}

	public void buscarTBd(int op) throws SQLException, Exception
	{
		ResultSet rs = null;
		try
		{
			if(op == 1)
			{	
				String info = "\t\t\tProfessores\n";
				String sql = "select * from Professor";
				PreparedStatement ps = conexao.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
				{
					int siape = rs.getInt("siape");
					String nome = rs.getString("nome");
					int idade = rs.getInt("idade");
					String cpf = rs.getString("cpf");
					String sexo = rs.getString("sexo");
					String in = "Nome: " + nome + " | Idade: " + idade + " | CPF: " + cpf + " | Sexo: " + sexo + " | Siape: " + siape + "\n";
					info = info + in;
				}
				JOptionPane.showMessageDialog(null, info);
			}
			else if(op == 2)
			{
				String info = "\t\t\tAlunos\n";
				String sql = "select * from Aluno";
				PreparedStatement ps = conexao.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
				{
					int ra = rs.getInt("ra");
					String nome = rs.getString("nome");
					int idade = rs.getInt("idade");
					String cpf = rs.getString("cpf");
					String sexo = rs.getString("sexo");
					String in = "Nome: " + nome + " | Idade: " + idade + " | CPF: " + cpf + " | Sexo: " + sexo + " | RA: " + ra + "\n";
					info = info + in;
				}
				JOptionPane.showMessageDialog(null, info);
			}		
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
	}

	public void alterar(int op) throws SQLException, Exception
	{
		ResultSet rs = null;
		String cpf = ler.cpf(); 
		boolean b = checar(op, cpf);
		try
		{
			ArrayList<String> p = new ArrayList();
			if(op == 1 && b == true)
			{	
				ler.salvar(p, op);
				String sql = "UPDATE Professor SET siape = ?, nome = ?, idade = ?, cpf = ?, sexo = ? WHERE cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(6, cpf);
				ps.setInt(1, Integer.parseInt(p.get(4)));
				ps.setString(2, p.get(0));
				ps.setInt(3, Integer.parseInt(p.get(1)));
				ps.setString(4, p.get(2));
				ps.setString(5, p.get(3));
				ps.executeUpdate();
			}
			else if(op == 2 && b == true)
			{
				ler.salvar(p, op);
				String sql = "UPDATE Aluno SET ra = ?, nome = ?, idade = ?, cpf = ?, sexo = ? WHERE cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(6, cpf);
				ps.setInt(1, Integer.parseInt(p.get(4)));
				ps.setString(2, p.get(0));
				ps.setInt(3, Integer.parseInt(p.get(1)));
				ps.setString(4, p.get(2));
				ps.setString(5, p.get(3));
				ps.executeUpdate();
			}
			else{JOptionPane.showMessageDialog(null, "Pessoa não cadastrada!");}
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
	}

	public boolean checar(int op, String cpf)
	{
		ResultSet rs = null;
		boolean b = false;
		try
		{
			if(op == 1)
			{		
				String sql = "select * from Professor where cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				rs = ps.executeQuery();
				while(rs.next()){b = true;}
			}
			else if(op == 2)
			{
				String sql = "select * from Aluno where cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				rs = ps.executeQuery();
				while(rs.next()){b = true;}
			}		
		}
		catch(SQLException ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
		return b;
	}
}
