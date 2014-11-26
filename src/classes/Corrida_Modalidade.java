package classes;

public class Corrida_Modalidade {
	private int id_relacao;	
	private int id_corrida;
	private int id_modalidade;
	
	public Corrida_Modalidade() {
	}	
	
	public Corrida_Modalidade(int id_relacao, int id_corrida, int id_modalidade) {
		this.id_relacao = id_relacao;
		this.id_corrida = id_corrida;
		this.id_modalidade = id_modalidade;
	}
	
	public int getId_relacao() {
		return id_relacao;
	}
	public void setId_relacao(int id_relacao) {
		this.id_relacao = id_relacao;
	}
	public int getId_corrida() {
		return id_corrida;
	}
	public void setId_corrida(int id_corrida) {
		this.id_corrida = id_corrida;
	}
	public int getId_modalidade() {
		return id_modalidade;
	}
	public void setId_modalidade(int id_modalidade) {
		this.id_modalidade = id_modalidade;
	}
	@Override
	public String toString() {
		return "Corrida_Modalidade [Id = " + id_relacao + ", Id da corrida = "
				+ id_corrida + ", Id da modalidade = " + id_modalidade + "]";
	}
}
