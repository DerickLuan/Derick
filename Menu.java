import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Menu 
{
	public static boolean op = true;
	public static Conexao c = new Conexao();

	public static void main(String args[]) throws SQLException, Exception
	{
		DAOBD daobd = new DAOBD(c.conecta());
		daobd.inserir();
		while(op)
		{
			String opcao = JOptionPane.showInputDialog(null, "[1] - Salvar\n[2] - Exibir\n[3] - Exibir Todos\n[4] - Remover\n[5] - Alterar\n[0] - Sair", "Menu Principal", JOptionPane.INFORMATION_MESSAGE);

			switch(opcao)
			{
				case "1":
				{
					int o = op();
					if(o > 0 && o < 3)
					{
						daobd.salvarBd(o);
					}
					else{JOptionPane.showMessageDialog(null, "Opção invalida!");}
				}
				break;
				case "2":
				{
					op = true;
					int o = op();
					if(o > 0 && o < 3)
					{
						daobd.buscarBd(o);
					}
					else{JOptionPane.showMessageDialog(null, "Opção invalida!");}
				}
				break;
				case "3":
				{
					int o = op();
					if(o >= 0 && o < 3){daobd.buscarTBd(o);}
					else{JOptionPane.showMessageDialog(null, "Opção invalida!");}
				}
				break;
				case "4":
				{
					int o = op();
					if(o > 0 && o < 3){daobd.remover(o);}
				}
				break;
				case "5":
				{
					int o = op();
					if(o > 0 && o < 3){daobd.alterar(o);}
				}
				break;
				case "0":
				{
					JOptionPane.showMessageDialog(null, "Saindo...");
					op = false;
					daobd.gravar();
				}
				break;
				default: JOptionPane.showMessageDialog(null, "Opção invalida!");
			}//switch
		}//while
	}//main

	public static int op()
	{
		int op = Integer.parseInt(JOptionPane.showInputDialog("[1] - Professor\n[2] - Aluno\n[0] - Sair"));
		return op;
	}
}
