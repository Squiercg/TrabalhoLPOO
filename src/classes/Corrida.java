package classes;

public class Corrida {
	private int id_corrida;	
	private String nome;
	private String data;
	private String local;
	
	public Corrida() {
	}	
	public Corrida(int id_corrida, String nome, String data, String local) {
		this.id_corrida = id_corrida;
		this.nome = nome;
		this.data = data;
		this.local = local;
	}
	
	public int getId_corrida() {
		return id_corrida;
	}
	public void setId_corrida(int id_corrida) {
		this.id_corrida = id_corrida;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	@Override
	public String toString() {
		return "Corrida [ID = " + id_corrida + ", Nome = " + nome
				+ ", Data = " + data + ", Local = " + local + "]";
	}
}
