import javax.swing.JOptionPane;
import java.io.*;
import java.util.LinkedList;

public class Leitura
{
	public Professor pro(Professor p)
	{
		p.setNome(JOptionPane.showInputDialog("Nome: "));
		p.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Idade: ")));
		p.setCpf(JOptionPane.showInputDialog("CPF: "));
		p.setSexo(JOptionPane.showInputDialog("Sexo: "));
		p.setSiape(JOptionPane.showInputDialog("Siape: "));
		
		return p;
	}
	
	public Aluno alu(Aluno a)
	{
		a.setNome(JOptionPane.showInputDialog("Nome: "));
		a.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Idade: ")));
		a.setCpf(JOptionPane.showInputDialog("CPF: "));
		a.setSexo(JOptionPane.showInputDialog("Sexo: "));
		a.setRa(JOptionPane.showInputDialog("RA: "));

		return a;
	}

	public String nome()
	{
		String nome = JOptionPane.showInputDialog("Nome: ");
		return nome;
	}

	public void ler(LinkedList<Pessoa> pes)
	{
		try 
		{
			FileReader arquivo = new FileReader("pessoas.txt");
			BufferedReader lerArquivo = new BufferedReader(arquivo);
			
			String linha = lerArquivo.readLine();
			while(linha != null) 
			{
				String pessoa[] = linha.split(":");
				Professor p = new Professor();
				Aluno a = new Aluno();
				if(pessoa[0].compareTo("Professor") == 0)
				{
					p.setNome(pessoa[1]);
					p.setIdade(Integer.parseInt(pessoa[2]));
					p.setCpf(pessoa[3]);
					p.setSexo(pessoa[4]);
					p.setSiape(pessoa[5]);
					pes.add(p);
				}
				else if(pessoa[0].compareTo("Aluno") == 0)
				{
					a.setNome(pessoa[1]);
					a.setIdade(Integer.parseInt(pessoa[2]));
					a.setCpf(pessoa[3]);
					a.setSexo(pessoa[4]);
					a.setRa(pessoa[5]);
					pes.add(a);
				}
				linha = lerArquivo.readLine();
			}
			lerArquivo.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
		
	public void gravar(LinkedList<Pessoa> p)
	{
		try 
		{													
			FileWriter arquivo = new FileWriter("pessoas.txt");
			PrintWriter g = new PrintWriter(arquivo);
			for(int i = 0; i < p.size(); i++)
			{
				if(p.get(i) instanceof Professor)
				{
					Professor pro = (Professor) p.get(i);
					g.println("Professor" + ":" + pro.getNome() + ":" + pro.getIdade() + ":" + pro.getCpf() + ":" + pro.getSexo() + ":" + pro.getSiape());
				}
				else if(p.get(i) instanceof Aluno)
				{
					Aluno a = (Aluno) p.get(i);
					g.println("Aluno" + ":" + a.getNome() + ":" + a.getIdade() + ":" + a.getCpf() + ":" + a.getSexo() + ":" + a.getRa());
				}
			}
			arquivo.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public boolean verifica(LinkedList<Pessoa> p, String cpf)
	{
		int j = 0;
		for(int i = 0; i < p.size(); i++)
		{
			if(cpf.compareTo(p.get(i).getCpf()) == 0)
			{
				j++;
			}
		}
		if(j == 0){return false;}
		else{return true;}
	}
}
