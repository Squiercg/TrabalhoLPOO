package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import classes.Corrida;

public class CorridaDao {
	private GenericDao dao;
	private String nomeClasse;
	
	public CorridaDao(String caminho) throws SQLException{
		nomeClasse = "Corrida";
		dao = new GenericDao(caminho, nomeClasse);
	}
	
	public boolean insert(Corrida corrida) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id_corrida", corrida.getId_corrida());
		map.put("nome", corrida.getNome());
		map.put("data", corrida.getData());
		map.put("local", corrida.getLocal());
		
		return dao.insert(map);
	}
	
	public boolean remove(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_corrida", id);
		return dao.remove(map);
	}
	
	public boolean update(Corrida corrida, int id) throws SQLException{
		Map<String,Object> updateMap = new HashMap<String,Object>();
		
		// Verifica se os campos foram preenchidos para fazer o update nos valores
		if(corrida.getNome() != null)
			updateMap.put("nome", corrida.getNome());
		
		if(corrida.getData() != null )
			updateMap.put("data", corrida.getData());
		
		if(corrida.getLocal() != null)
			updateMap.put("local", corrida.getLocal());		

		// Faz o update pelo ID
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("id_corrida", id);
		
		return dao.update(updateMap, whereMap);
	}

	public ArrayList<Corrida> all() throws SQLException{
		ArrayList<Corrida> retorno = new ArrayList<Corrida>();
		Corrida temporario;
		for(String lista : dao.all())
		{			
			String corrida[] = new String[4];
			corrida = lista.split(",");
			temporario =new Corrida(Integer.parseInt(corrida[0]),corrida[1],corrida[2],corrida[3]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<String> all_chaves() throws SQLException{
		ArrayList<String> retorno = new ArrayList<String>();
		for(String lista : dao.all_atributo("id_corrida"))
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
			for(int i = 0; i < inteiros.size(); i++) {
				if((inteiros.get(i))!=(i+1)){
					retorno=i+1;
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
	
	
	public ArrayList<Corrida> findBy(Map<String, Object> mapping) throws SQLException{
		ArrayList<Corrida> retorno = new ArrayList<Corrida>();
		Corrida temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String corrida[] = new String[4];
			corrida = lista.split(",");			
			temporario =new Corrida(Integer.parseInt(corrida[0]),corrida[1],corrida[2],corrida[3]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public Corrida findBy_id(int id) throws SQLException{
		ArrayList<Corrida> retorno = new ArrayList<Corrida>();
		
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_corrida",  id);		
		
		Corrida temporario;		
		for(String lista : dao.findBy(mapping))
		{			
			String corrida[] = new String[4];
			corrida = lista.split(",");			
			temporario =new Corrida(Integer.parseInt(corrida[0]),corrida[1],corrida[2],corrida[3]);				
			retorno.add(temporario);
		}
		return retorno.get(0);		
	}

}
