import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DAO
{
	public LinkedList<Pessoa> pessoas = new LinkedList<>();
	public Leitura ler = new Leitura();

	public void inserir()
	{
		ler.ler(pessoas);
	}

	public void salvar(int op)
	{
		if(op == 1)
		{
			Professor p = new Professor();
			ler.pro(p);
			if(ler.verifica(pessoas, p.getCpf()) == false)
			{
				pessoas.add(p);
				JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
			}
			else{JOptionPane.showMessageDialog(null, "Pessoa já cadastrada!");}
		}
		else if(op == 2)
		{
			Aluno a = new Aluno();
			ler.alu(a);
			if(ler.verifica(pessoas, a.getCpf()) == false)
			{
				pessoas.add(a);
				JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
			}
			else{JOptionPane.showMessageDialog(null, "Pessoa já cadastrada!");}
		}
	}
	
	public void buscarTodos(int op)
	{
		String barra = "=================================\n";
		String info = barra;
		int v = 0;
		if(op == 1)
		{
			for(int i = 0; i < pessoas.size(); i++)
			{
				if(pessoas.get(i) instanceof Professor)
				{
					Professor p = (Professor) pessoas.get(i);
					String in = "Nome: " + p.getNome() + "\nIdade: " + p.getIdade() + "\nCPF: " + p.getCpf() + "\nSexo: " + p.getSexo() + "\nSiape: " + p.getSiape() + "\n";
					info = info + in + barra;
					v++;
				}
			}
			if(v > 0)
			{
				JOptionPane.showMessageDialog(null, info);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Lista de professores vazia.");
			}
		}

		else if(op == 2)
		{
			for(int i = 0; i < pessoas.size(); i++)
			{
				if(pessoas.get(i) instanceof Aluno)
				{
					Aluno a = (Aluno) pessoas.get(i);
					String in = "Nome: " + a.getNome() + "\nIdade: " + a.getIdade() + "\nCPF: " + a.getCpf() + "\nSexo: " + a.getSexo() + "\nRA: " + a.getRa() + "\n";
					info = info + in + barra;
					v++;
				}
			}
			if(v > 0)
			{
				JOptionPane.showMessageDialog(null, info);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Lista de alunos vazia.");
			}
		}
	}

	public void remover()
	{
		String nome = ler.nome();
		int v = 0;
		for(int i=0; i < pessoas.size(); i++)
		{
			if(nome.compareTo(pessoas.get(i).getNome()) == 0)
			{
				if(pessoas.get(i) instanceof Professor)
				{
					pessoas.remove(i);
					String info = "Professor " + nome + " removido com sucesso!";
					JOptionPane.showMessageDialog(null, info);
					v++;
				}
				else if(pessoas.get(i) instanceof Aluno)
				{
					pessoas.remove(i);
					String info = "Aluno " + nome + " removido com sucesso!";
					JOptionPane.showMessageDialog(null, info);
					v++;
				}
			}
		}
		if(v == 0)
		{
			JOptionPane.showMessageDialog(null, "Não se encontra na lista.");
		}
	}
	
	public void gravar()
	{
		ler.gravar(pessoas);
	}

	public void salvarBd() throws SQLException
	{
		Aluno a = new Aluno("Derick", 19, "123.456.789-12", "Masculino", "123");
		Connection conexao = null;
		try
		{
			conexao = DriverManager.getConnection("jbdc:mysql://localhost/mysql","root", "");
			String sql = "insert into alunos(nome, idade, cpf, sexo, ra) values('Derick','19','123','M','123')";

			PreparedStatement prepare = conexao.preparedStatement(sql);
			prepare.executeUpdate();
		}
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		finally
		{
			conexao.close();
		}
	}
}
