package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import classes.Inscricao;

public class InscricaoDao {
	private GenericDao dao;
	private String nomeClasse;
	
	public InscricaoDao(String caminho) throws SQLException{
		nomeClasse = "Inscricao";
		dao = new GenericDao(caminho, nomeClasse);
	}
	
	public boolean insert(Inscricao inscricao) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id_inscricao", inscricao.getId_inscricao());
		map.put("id_relacao", inscricao.getId_relacao());
		map.put("id_corredor", inscricao.getId_corredor());
		map.put("data", inscricao.getData());
		map.put("tamanho_camiseta", inscricao.getTamanho_camiseta());
		
		return dao.insert(map);
	}
	
	public boolean remove(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_inscricao", id);
		return dao.remove(map);
	}
	
	public boolean update(Inscricao inscricao, int id) throws SQLException{
		Map<String,Object> updateMap = new HashMap<String,Object>();
		
		// Verifica se os campos foram preenchidos para fazer o update nos valores
		
		if(inscricao.getId_relacao()!=0)
			updateMap.put("id_relacao", inscricao.getId_relacao());
		if(inscricao.getId_corredor()!=0)
			updateMap.put("id_corredor", inscricao.getId_corredor());
		if(inscricao.getData() != null)
			updateMap.put("data", inscricao.getData());
		if(inscricao.getTamanho_camiseta() != null)
			updateMap.put("tamanho_camiseta", inscricao.getTamanho_camiseta());		

		// Faz o update pelo ID
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("id_inscricao", id);
		
		return dao.update(updateMap, whereMap);
	}

	public ArrayList<Inscricao> all() throws SQLException{
		ArrayList<Inscricao> retorno = new ArrayList<Inscricao>();
		Inscricao temporario;
		for(String lista : dao.all())
		{			
			String inscricao[] = new String[5];
			inscricao = lista.split(",");
			temporario =new Inscricao(Integer.parseInt(inscricao[1]),Integer.parseInt(inscricao[4]),Integer.parseInt(inscricao[0]),inscricao[3],inscricao[2]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<String> all_chaves() throws SQLException{
		ArrayList<String> retorno = new ArrayList<String>();
		for(String lista : dao.all_atributo("id_inscricao"))
		{			
			retorno.add(lista);
		}
		return retorno;		
	}
	
	public int descobre_chave_livre() throws SQLException{
		int retorno;
		
		ArrayList<String> lista = this.all_chaves();		
		ArrayList<Integer> inteiros = new ArrayList<Integer>();
		
		if(lista.size()==0){
			retorno=1;
		}else {
			for(int i = 0; i < lista.size(); i++) {
				inteiros.add(Integer.parseInt(lista.get(i)));   
			}			
			Collections.sort(inteiros);			
			retorno=inteiros.get(inteiros.size()-1)+1;			
			for(int i = 1; i < inteiros.size(); i++) {
				if((inteiros.get(i-1)+1)!=inteiros.get(i)){
					retorno=inteiros.get(i);
					i=inteiros.size();
				} 
			}
		}		
		return retorno;		
	}
	
	public boolean chave_existe(int n) throws SQLException{

		ArrayList<String> lista = this.all_chaves();		
		ArrayList<Integer> inteiros = new ArrayList<Integer>();
		for(int i = 0; i < lista.size(); i++) {
			inteiros.add(Integer.parseInt(lista.get(i)));   
		}
		
		if(inteiros.contains(n)){
			return true;
		} else {
			return false;
		}
	}	
	
	public ArrayList<Inscricao> findBy(Map<String, Object> mapping) throws SQLException{
		ArrayList<Inscricao> retorno = new ArrayList<Inscricao>();
		Inscricao temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String inscricao[] = new String[4];
			inscricao = lista.split(",");			
			temporario =new Inscricao(Integer.parseInt(inscricao[1]),Integer.parseInt(inscricao[4]),Integer.parseInt(inscricao[0]),inscricao[3],inscricao[2]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<Integer> todos_corredores() throws SQLException{
		ArrayList<Integer> retorno = new ArrayList<Integer>();

		for(String lista : dao.all())
		{			
			String inscricao[] = new String[5];
			inscricao = lista.split(",");			
			retorno.add(Integer.parseInt(inscricao[0]));
		}
		
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.addAll(retorno);
		retorno.clear();
		retorno.addAll(hs);
		
		
		return retorno;		
	}
	
	public ArrayList<Integer> relacoes_de_um_corredor(int corredor) throws SQLException{
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_corredor",  corredor);
		
		for(String lista : dao.findBy(mapping))
		{			
			String inscricao[] = new String[4];
			inscricao = lista.split(",");					
			retorno.add(Integer.parseInt(inscricao[4]));
		}
		return retorno;		
	}
	
	public boolean existe_inscricao(int corredor,int relacao) throws SQLException{
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_corredor",  corredor);
		mapping.put( "id_relacao",  relacao);
		
		for(String lista : dao.findBy(mapping))
		{			
			String inscricao[] = new String[4];
			inscricao = lista.split(",");					
			retorno.add(Integer.parseInt(inscricao[4]));
		}
		
		if(retorno.size()==0){
			return false;
		} else {
			return true;			
		}				
	}
	
	public ArrayList<Inscricao> inscricoes_de_um_corredor(int corredor) throws SQLException{
		ArrayList<Inscricao> retorno = new ArrayList<Inscricao>();
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_corredor",  corredor);
		
		for(String item : dao.findBy(mapping))
		{			
			String inscricao[] = new String[5];
			inscricao = item.split(",");
			retorno.add(new Inscricao(Integer.parseInt(inscricao[1]),Integer.parseInt(inscricao[4]),Integer.parseInt(inscricao[0]),inscricao[3],inscricao[2]));
		}
		return retorno;		
	}
	
}
