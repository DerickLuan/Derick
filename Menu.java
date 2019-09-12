import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

public class Menu
{
	public static boolean op = true;

	public static	Leitura ler = new Leitura();
	public static DAO dao = new DAO();
	public static String log;

	public static void main(String args[])
	{
		dao.inserir();
		while(op)
		{
			String opcao = JOptionPane.showInputDialog("[1] - Salvar\n[2] - Exibir Todos\n[3] - Remover\n[0] - Sair");

			switch(opcao)
			{
				case "1":
				{
					dao.salvarBd();
					op = true;
				}
				break;
				case "2":
				{
					buscar();
					op = true;
				}
				break;
				case "3":
				{
					dao.remover();
				}
				break;
				case "0":
				{
					JOptionPane.showMessageDialog(null, "Saindo...");
					op = false;
					dao.gravar();
				}
				break;
				default: JOptionPane.showMessageDialog(null, "Opção invalida!");
			}//switch
		}//while
	}//main

	public static void salvar()
	{
		while(op)
		{
			String opcao = JOptionPane.showInputDialog("[1] - Professor\n[2] - Aluno\n[0] - Sair");
			switch(Integer.parseInt(opcao))
			{
				case 1:
				{
					dao.salvar(Integer.parseInt(opcao));
					log = "Professor cadastrado.";
					log(log);
				}
				break;
				case 2:
				{
					dao.salvar(Integer.parseInt(opcao));
					log = "Aluno cadastrado.";
					log(log);
				}
				break;
				case 0:
				{
					op = false;
				}
				break;
				default: JOptionPane.showMessageDialog(null, "Opção invalida!");
			}
		}
	}//salvar

	public static void buscar()
	{
		while(op)
		{
			String opcao = JOptionPane.showInputDialog("[1] - Professor\n[2] - Aluno\n[0] - Sair");
			switch(Integer.parseInt(opcao))
			{
				case 1:
				{
					dao.buscarTodos(Integer.parseInt(opcao));
					log = "Exibido lista de professores.";
					log(log);
				}
				break;
				case 2:
				{
					dao.buscarTodos(Integer.parseInt(opcao));
					log = "Exibido lista de alunos.";
					log(log);
				}
				break;
				case 0:
				{
					op = false;
				}
				break;
				default: JOptionPane.showMessageDialog(null, "Opção invalida!");
			}
		}
	}//Buscar

	public static void log(String log)
	{
		try 
		{													
			FileWriter arquivo = new FileWriter("log.txt", true);
			PrintWriter gravarArquivo = new PrintWriter(arquivo);
			gravarArquivo.println(horaAtual() + ": " + log);
			arquivo.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static String horaAtual() 
	{
		Calendar data = Calendar.getInstance();
		int hora = data.get(Calendar.HOUR_OF_DAY); 
		int min = data.get(Calendar.MINUTE);
		int seg = data.get(Calendar.SECOND);
		String horaAtual = hora + ":" + min + ":" + seg;
		return horaAtual;
	}//horaAtual
}//Menu
