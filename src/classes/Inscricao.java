package classes;

public class Inscricao {
	private int id_inscricao;
	private int id_relacao;
	private int id_corredor;
	private String data;
	private String tamanho_camiseta;
	
	public Inscricao() {		
	}	
	public Inscricao(int id_inscricao, int id_relacao, int id_corredor,
			String data, String tamanho_camiseta) {
		super();
		this.id_inscricao = id_inscricao;
		this.id_relacao = id_relacao;
		this.id_corredor = id_corredor;
		this.data = data;
		this.tamanho_camiseta = tamanho_camiseta;
	}
	
	public int getId_inscricao() {
		return id_inscricao;
	}
	public void setId_inscricao(int id_inscricao) {
		this.id_inscricao = id_inscricao;
	}
	public int getId_relacao() {
		return id_relacao;
	}
	public void setId_relacao(int id_relacao) {
		this.id_relacao = id_relacao;
	}
	public int getId_corredor() {
		return id_corredor;
	}
	public void setId_corredor(int id_corredor) {
		this.id_corredor = id_corredor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTamanho_camiseta() {
		return tamanho_camiseta;
	}
	public void setTamanho_camiseta(String tamanho_camiseta) {
		this.tamanho_camiseta = tamanho_camiseta;
	}
	@Override
	public String toString() {
		return "Inscricao [Id = " + id_inscricao + ", Relacao Corrida - Modalidade = "
				+ id_relacao + ", Id do corredor=" + id_corredor + ", Data da inscrição = "
				+ data + ", Tamanho da Camiseta = " + tamanho_camiseta + "]";
	}
}
