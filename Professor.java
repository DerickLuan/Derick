public class Professor extends Pessoa
{
	private String siape;

	public Professor(){}	
	public Professor(String nome, int idade, String cpf, String sexo)
	{
		super(nome, idade, cpf, sexo);
	}
	public Professor(String nome, int idade, String cpf, String sexo, String siape)
	{
		super(nome, idade, cpf, sexo);
		this.siape = siape;
	}
	
	public String getSiape()
	{
		return siape;
	}
	public void setSiape(String siape)
	{
		this.siape = siape;
	}
}
