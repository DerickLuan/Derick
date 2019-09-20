public class Professor extends Pessoa
{
	private int siape;

	public Professor(){}	
	public Professor(String nome, int idade, String cpf, String sexo)
	{
		super(nome, idade, cpf, sexo);
	}
	public Professor(String nome, int idade, String cpf, String sexo, int siape)
	{
		super(nome, idade, cpf, sexo);
		this.siape = siape;
	}
	
	public int getSiape()
	{
		return siape;
	}
	public void setSiape(int siape)
	{
		this.siape = siape;
	}
}
