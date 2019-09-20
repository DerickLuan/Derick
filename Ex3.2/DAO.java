import javax.swing.JOptionPane;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class DAO
{
	public Leitura ler = new Leitura();
	public Connection conexao;
	
	public DAO(Connection conexao)
	{
		this.conexao = conexao;
	}
		
	public void salvarBd(int op) throws SQLException, Exception
	{
		try
		{
			if(op == 1)
			{
				String sql = "insert into Professor(nome, idade, cpf, sexo, siape) values(?,?,?,?,?)";
				Professor p = new Professor();
				ler.pro(p);
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(1, p.getNome());
				prepare.setInt(2, p.getIdade());
				prepare.setString(3, p.getCpf());
				prepare.setString(4, p.getSexo());
				prepare.setInt(5, p.getSiape());
				prepare.executeUpdate();
				JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
			}
			else if(op == 2)
			{
				String sql = "insert into Aluno(nome, idade, cpf, sexo, ra) values(?,?,?,?,?)";
				Aluno a = new Aluno();
				ler.alu(a);
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(1, a.getNome());
				prepare.setInt(2, a.getIdade());
				prepare.setString(3, a.getCpf());
				prepare.setString(4, a.getSexo());
				prepare.setInt(5, a.getRa());
				prepare.executeUpdate();
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
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(1, ler.nome());

				rs = prepare.executeQuery();
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
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(1, ler.nome());

				rs = prepare.executeQuery();
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
		ResultSet rs = null;
		try
		{
			String cpf = ler.cpf();
			boolean b = checar(op, cpf);
			if(op == 1 && b == true)
			{		
				String sql = "delete from Professor where cpf = ?";
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(1, ler.cpf());
				prepare.executeUpdate();
				JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
			}
			else if(op == 2 && b == true)
			{
				String sql = "delete from Aluno where cpf = ?";
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(1, ler.cpf());
				prepare.executeUpdate();				
				JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
			}		
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
				PreparedStatement prepare = conexao.prepareStatement(sql);
				rs = prepare.executeQuery();
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
				PreparedStatement prepare = conexao.prepareStatement(sql);
				rs = prepare.executeQuery();
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
		try
		{
			String cpf = ler.cpf();
			boolean b = checar(op, cpf);
			if(op == 1 && b == true)
			{	
				Professor p = new Professor();
				ler.pro(p);
				String sql = "UPDATE Professor SET siape = ?, nome = ?, idade = ?, cpf = ?, sexo = ? WHERE cpf = ?";
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(6, cpf);
				prepare.setInt(1, p.getSiape());
				prepare.setString(2, p.getNome());
				prepare.setInt(3, p.getIdade());
				prepare.setString(4, p.getCpf());
				prepare.setString(5, p.getSexo());
				prepare.executeUpdate();
			}
			else if(op == 2 && b == true)
			{
				Aluno a = new Aluno();
				ler.alu(a);
				String sql = "UPDATE Aluno SET ra = ?, nome = ?, idade = ?, cpf = ?, sexo = ? WHERE cpf = ?";
				PreparedStatement prepare = conexao.prepareStatement(sql);
				prepare.setString(6, cpf);
				prepare.setInt(1, a.getRa());
				prepare.setString(2, a.getNome());
				prepare.setInt(3, a.getIdade());
				prepare.setString(4, a.getCpf());
				prepare.setString(5, a.getSexo());
				prepare.executeUpdate();
			}		
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
