import javax.swing.JOptionPane;
import com.mysql.cj.jdbc.Driver;
import java.util.ArrayList;

public class Leitura
{
	public ArrayList<String> salvar(ArrayList<String> p, int op)
	{
		p.add(0, JOptionPane.showInputDialog(null, "Nome: ", "Salvar", JOptionPane.QUESTION_MESSAGE));
		p.add(1, JOptionPane.showInputDialog(null, "Idade: ", "Salvar", JOptionPane.QUESTION_MESSAGE));
		p.add(2, JOptionPane.showInputDialog(null, "CPF: ", "Salvar", JOptionPane.QUESTION_MESSAGE));
		p.add(3, JOptionPane.showInputDialog(null, "Sexo: ", "Salvar", JOptionPane.QUESTION_MESSAGE));
		if(op == 1)
		{
			p.add(4, JOptionPane.showInputDialog(null, "Siape: ", "Salvar", JOptionPane.QUESTION_MESSAGE));
		}
		else if(op == 2)
		{
			p.add(4, JOptionPane.showInputDialog(null, "RA: ", "Salvar", JOptionPane.QUESTION_MESSAGE));
		}
		return p;
	}

	public String nome()
	{
		String nome = JOptionPane.showInputDialog(null, "Nome: ", "Nome", JOptionPane.QUESTION_MESSAGE);
		return nome;
	}
	public String cpf()
	{
		String cpf = JOptionPane.showInputDialog(null, "CPF: ", "CPF", JOptionPane.QUESTION_MESSAGE);
		return cpf;
	}
	public String login()
	{
		String usuario = JOptionPane.showInputDialog(null, "Usuario: ", "Usuario", JOptionPane.QUESTION_MESSAGE);
		return usuario;
	}
	public String senha()
	{
		String senha = JOptionPane.showInputDialog(null, "Senha: ", "Senha", JOptionPane.QUESTION_MESSAGE);
		return senha;
	}
}
