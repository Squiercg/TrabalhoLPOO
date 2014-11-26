package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import classes.Corrida_Modalidade;

public class Corrida_ModalidadeDao {
	private GenericDao dao;
	private String nomeClasse;
	
	public Corrida_ModalidadeDao(String caminho) throws SQLException{
		nomeClasse = "Corrida_Modalidade";
		dao = new GenericDao(caminho, nomeClasse);
	}
	
	public boolean insert(Corrida_Modalidade corrida_Modalidade) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id_relacao", corrida_Modalidade.getId_relacao());
		map.put("id_corrida", corrida_Modalidade.getId_corrida());
		map.put("id_modalidade", corrida_Modalidade.getId_modalidade());
		
		return dao.insert(map);
	}
	
	public boolean remove(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_relacao", id);
		return dao.remove(map);
	}
	
	public boolean update(Corrida_Modalidade corrida_Modalidade, int id) throws SQLException{
		Map<String,Object> updateMap = new HashMap<String,Object>();

		updateMap.put("id_corrida", corrida_Modalidade.getId_corrida());
		updateMap.put("id_modalidade", corrida_Modalidade.getId_modalidade());

		// Faz o update pelo ID
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("id_corrida", id);
		
		return dao.update(updateMap, whereMap);
	}

	public ArrayList<Corrida_Modalidade> all() throws SQLException{
		ArrayList<Corrida_Modalidade> retorno = new ArrayList<Corrida_Modalidade>();
		Corrida_Modalidade temporario;
		for(String lista : dao.all())
		{			
			String corrida_Modalidade[] = new String[3];
			corrida_Modalidade = lista.split(",");
			temporario =new Corrida_Modalidade(Integer.parseInt(corrida_Modalidade[0]),Integer.parseInt(corrida_Modalidade[1]),Integer.parseInt(corrida_Modalidade[2]));				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<String> all_chaves() throws SQLException{
		ArrayList<String> retorno = new ArrayList<String>();
		for(String lista : dao.all_atributo("id_relacao"))
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
	
	
	public ArrayList<Corrida_Modalidade> findBy(Map<String, Object> mapping) throws SQLException{
		ArrayList<Corrida_Modalidade> retorno = new ArrayList<Corrida_Modalidade>();
		Corrida_Modalidade temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String corrida_Modalidade[] = new String[3];
			corrida_Modalidade = lista.split(",");
			temporario =new Corrida_Modalidade(Integer.parseInt(corrida_Modalidade[0]),Integer.parseInt(corrida_Modalidade[1]),Integer.parseInt(corrida_Modalidade[2]));				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<Integer> findBy_idcorrida(int id) throws SQLException{
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_corrida",  id);
		
		for(String lista : dao.findBy(mapping))
		{			
			String corrida_Modalidade[] = new String[3];
			corrida_Modalidade = lista.split(",");				
			retorno.add(Integer.parseInt(corrida_Modalidade[2]));
		}
		return retorno;		
	}
	
	public boolean remover_por_modalidade(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_modalidade", id);
		return dao.remove(map);
	}
	
	public boolean remover_por_corrida(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_corrida", id);
		return dao.remove(map);
	}
	
	public Corrida_Modalidade findBy_relacao(int id) throws SQLException{
		ArrayList<Corrida_Modalidade> retorno = new ArrayList<Corrida_Modalidade>();
		
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_relacao",  id);
		
		Corrida_Modalidade temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String corrida_Modalidade[] = new String[3];
			corrida_Modalidade = lista.split(",");
			temporario =new Corrida_Modalidade(Integer.parseInt(corrida_Modalidade[0]),Integer.parseInt(corrida_Modalidade[1]),Integer.parseInt(corrida_Modalidade[2]));				
			retorno.add(temporario);
		}
		return retorno.get(0);		
	}

}
