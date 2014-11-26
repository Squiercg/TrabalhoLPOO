package classes;

public class Corredor {
	private int id_corredor;	
	private String nome;
	private String data_de_nascimento;
	private String cpf;
	
	public Corredor(){
		
	}
	
	public Corredor(
			int id_corredor, 
			String nome, 
			String data_de_nascimento,
			String cpf) {
		this.id_corredor = id_corredor;
		this.nome = nome;
		this.data_de_nascimento = data_de_nascimento;
		this.cpf = cpf;
	}
	
	public int getId_corredor() {
		return id_corredor;
	}
	public void setId_corredor(int id_corredor) {
		this.id_corredor = id_corredor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_de_nascimento() {
		return data_de_nascimento;
	}
	public void setData_de_nascimento(String data_de_nascimento) {
		this.data_de_nascimento = data_de_nascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "[ID = " + id_corredor + ", "
				+ "Nome = " + nome
				+ ", Data de nascimento = " + data_de_nascimento + ", "
				+ "CPF = " + cpf + "]";
	}	

}
