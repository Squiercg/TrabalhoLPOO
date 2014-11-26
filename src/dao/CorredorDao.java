package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import classes.Corredor;



public class CorredorDao {
	private GenericDao dao;
	private String nomeClasse;
	
	public CorredorDao(String caminho) throws SQLException{
		nomeClasse = "Corredor";
		dao = new GenericDao(caminho, nomeClasse);
	}
	
	public boolean insert(Corredor corredor) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id_corredor", corredor.getId_corredor());
		map.put("nome", corredor.getNome());
		map.put("data_de_nascimento", corredor.getData_de_nascimento());
		map.put("cpf", corredor.getCpf());
		
		return dao.insert(map);
	}
	
	public boolean remove(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_corredor", id);
		return dao.remove(map);
	}
	
	public boolean update(Corredor corredor, int id) throws SQLException{
		Map<String,Object> updateMap = new HashMap<String,Object>();
		
		// Verifica se os campos foram preenchidos para fazer o update nos valores
		if(corredor.getNome() != null)
			updateMap.put("nome", corredor.getNome());
		
		if(corredor.getData_de_nascimento() != null )
			updateMap.put("data_de_nascimento", corredor.getData_de_nascimento());
		
		if(corredor.getCpf() != null)
			updateMap.put("cpf", corredor.getCpf());
		

		// Faz o update pelo ID
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("id_corredor", id);
		
		return dao.update(updateMap, whereMap);
	}

	public ArrayList<Corredor> all() throws SQLException{
		ArrayList<Corredor> retorno = new ArrayList<Corredor>();
		Corredor temporario;
		for(String lista : dao.all())
		{			
			String corredor[] = new String[4];
			corredor = lista.split(",");
			temporario =new Corredor(Integer.parseInt(corredor[0]),corredor[1],corredor[2],corredor[3]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<String> all_chaves() throws SQLException{
		ArrayList<String> retorno = new ArrayList<String>();
		for(String lista : dao.all_atributo("id_corredor"))
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
	
	
	public ArrayList<Corredor> findBy(Map<String, Object> mapping) throws SQLException{
		ArrayList<Corredor> retorno = new ArrayList<Corredor>();
		Corredor temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String corredor[] = new String[4];
			corredor = lista.split(",");			
			temporario =new Corredor(Integer.parseInt(corredor[0]),corredor[1],corredor[2],corredor[3]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<Corredor> findBy(int id) throws SQLException{
		ArrayList<Corredor> retorno = new ArrayList<Corredor>();
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_corredor",  id);		
		Corredor temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String corredor[] = new String[4];
			corredor = lista.split(",");			
			temporario =new Corredor(Integer.parseInt(corredor[0]),corredor[1],corredor[2],corredor[3]);				
			retorno.add(temporario);
		}
		return retorno;		
	}
	

}
