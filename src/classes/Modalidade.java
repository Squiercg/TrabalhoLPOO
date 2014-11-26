package classes;

public class Modalidade {
	private int id_modalidade;	
	private int quantidade_km;
	
	public Modalidade() {
	}
	public Modalidade(int id_modalidade, int quantidade_km) {
		this.id_modalidade = id_modalidade;
		this.quantidade_km = quantidade_km;
	}	
	
	public int getId_modalidade() {
		return id_modalidade;
	}
	public void setId_modalidade(int id_modalidade) {
		this.id_modalidade = id_modalidade;
	}
	public int getQuantidade_km() {
		return quantidade_km;
	}
	public void setQuantidade_km(int quantidade_km) {
		this.quantidade_km = quantidade_km;
	}
	@Override
	public String toString() {
		return "Modalidade [Id = " + id_modalidade
				+ ", Distancia = " + quantidade_km + " km ]";
	}	

}
