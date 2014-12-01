package classes;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Corrida_ModalidadeDao;
import dao.ModalidadeDao;

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
	
	public ArrayList<Modalidade> getModalidade(Corrida_ModalidadeDao corrida_modalidadedao, ModalidadeDao modalidadedao) throws SQLException{
		
		System.out.println("teste");
		
		ArrayList<Integer> modalidades_ids = corrida_modalidadedao.findBy_idcorrida(this.id_corrida);
		ArrayList<Modalidade> saida = new ArrayList<Modalidade>();		
		
		for(int i : modalidades_ids ) {
			saida.add(modalidadedao.findBy_id(i));
		}
		
		return saida;
		
	}
	
	
	@Override
	public String toString() {
		return "Corrida [ID = " + id_corrida + ", Nome = " + nome
				+ ", Data = " + data + ", Local = " + local + "]";
	}
}
