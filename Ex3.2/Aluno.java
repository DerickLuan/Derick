public class Aluno extends Pessoa
{
	private int ra;
	
	public Aluno(){}
	public Aluno(String nome, int idade, String cpf, String sexo)
	{
		super(nome, idade, cpf, sexo);
	}
	public Aluno(String nome, int idade, String cpf, String sexo, int ra)
	{
		super(nome, idade, cpf, sexo);
		this.ra = ra;
	}

	public int getRa()
	{
		return ra;
	}
	public void setRa(int ra)
	{
		this.ra = ra;
	}
}
