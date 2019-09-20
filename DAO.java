import javax.swing.JOptionPane;
import java.util.LinkedList;

public class DAO
{
	public LinkedList<Pessoa> pessoas = new LinkedList<>();
	public Leitura ler = new Leitura();

	public void inserir()
	{
		ler.ler(pessoas);
	}

	public void salvar(int op, Professor p, Aluno a)
	{
		if(op == 1)
		{
			ler.pro(p);
			pessoas.add(p);
		}
		else if(op == 2)
		{
			ler.alu(a);
			pessoas.add(a);
		}
	}

	public void remover(String cpf)
	{
		for(int i=0; i < pessoas.size(); i++)
		{
			if(cpf.compareTo(pessoas.get(i).getCpf()) == 0)
			{
				if(pessoas.get(i) instanceof Professor)
				{
					pessoas.remove(i);
				}
				else if(pessoas.get(i) instanceof Aluno)
				{
					pessoas.remove(i);
				}
			}
		}//for
	}
	
	public void gravar()
	{
		ler.gravar(pessoas);
	}
}
