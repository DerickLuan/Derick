import javax.swing.JOptionPane;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;
import java.util.ArrayList;

public class DAOBD
{
	public Leitura ler = new Leitura();
	public DAO d = new DAO();
	public Connection conexao;
	public DAOBD(Connection conexao)
	{
		this.conexao = conexao;
	}
	public void inserir(){d.inserir();}
	public void gravar(){d.gravar();}

	public void salvarBd(int op) throws SQLException, Exception
	{
		try
		{
			Professor p = new Professor();
			Aluno a = new Aluno();
			if(op == 1)
			{
				d.salvar(op, p, a);
				String sql = "insert into Professor(nome, idade, cpf, sexo, siape) values(?,?,?,?,?)";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, p.getNome());
				ps.setInt(2, p.getIdade());
				ps.setString(3, p.getCpf());
				ps.setString(4, p.getSexo());
				ps.setInt(5, Integer.parseInt(p.getSiape()));
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
			}
			else if(op == 2)
			{
				d.salvar(op, p, a);
				String sql = "insert into Aluno(nome, idade, cpf, sexo, ra) values(?,?,?,?,?)";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, a.getNome());
				ps.setInt(2, a.getIdade());
				ps.setString(3, a.getCpf());
				ps.setString(4, a.getSexo());
				ps.setInt(5, Integer.parseInt(a.getRa()));
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
		ResultSet rs = null;
		String cpf = ler.cpf();
		boolean b = checar(op, cpf);
		try
		{
			if(op == 1 && b == true)
			{
				d.remover(cpf);
				String sql = "delete from Professor where cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
			}
			else if(op == 2 && b == true)
			{
				d.remover(cpf);
				String sql = "delete from Aluno where cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, cpf);
				ps.executeUpdate();				
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
		try
		{
			Professor p = new Professor();
			Aluno a = new Aluno();
			String cpf = ler.cpf();
			d.remover(cpf);
			if(op == 1)
			{	
				d.salvar(op, p, a);
				String sql = "UPDATE Professor SET siape = ?, nome = ?, idade = ?, cpf = ?, sexo = ? WHERE cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(6, cpf);
				ps.setInt(1, Integer.parseInt(p.getSiape()));
				ps.setString(2, p.getNome());
				ps.setInt(3, p.getIdade());
				ps.setString(4, p.getCpf());
				ps.setString(5, p.getSexo());
				ps.executeUpdate();
			}
			else if(op == 2)
			{
				d.salvar(op, p, a);
				String sql = "UPDATE Aluno SET ra = ?, nome = ?, idade = ?, cpf = ?, sexo = ? WHERE cpf = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(6, cpf);
				ps.setInt(1, Integer.parseInt(a.getRa()));
				ps.setString(2, a.getNome());
				ps.setInt(3, a.getIdade());
				ps.setString(4, a.getCpf());
				ps.setString(5, a.getSexo());
				ps.executeUpdate();
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
