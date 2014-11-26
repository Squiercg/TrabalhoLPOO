package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import classes.Modalidade;

public class ModalidadeDao {
	private GenericDao dao;
	private String nomeClasse;
	
	public ModalidadeDao(String caminho) throws SQLException{
		nomeClasse = "Modalidade";
		dao = new GenericDao(caminho, nomeClasse);
	}
	
	public boolean insert(Modalidade modalidade) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("id_modalidade", modalidade.getId_modalidade());
		map.put("quantidade_km", modalidade.getQuantidade_km());
		
		return dao.insert(map);
	}
	
	public boolean remove(int id) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_modalidade", id);
		return dao.remove(map);
	}
	
	public boolean update(Modalidade modalidade, int id) throws SQLException{
		Map<String,Object> updateMap = new HashMap<String,Object>();

		updateMap.put("quantidade_km", modalidade.getQuantidade_km());

		// Faz o update pelo ID
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("id_modalidade", id);
		
		return dao.update(updateMap, whereMap);
	}

	public ArrayList<Modalidade> all() throws SQLException{
		ArrayList<Modalidade> retorno = new ArrayList<Modalidade>();
		Modalidade temporario;
		for(String lista : dao.all())
		{			
			String modalidade[] = new String[2];
			modalidade = lista.split(",");
			temporario =new Modalidade(Integer.parseInt(modalidade[0]),Integer.parseInt(modalidade[1]));				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public ArrayList<String> all_chaves() throws SQLException{
		ArrayList<String> retorno = new ArrayList<String>();
		for(String lista : dao.all_atributo("id_modalidade"))
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
	
	public ArrayList<Modalidade> findBy(Map<String, Object> mapping) throws SQLException{
		ArrayList<Modalidade> retorno = new ArrayList<Modalidade>();
		Modalidade temporario;
		for(String lista : dao.findBy(mapping))
		{			
			String modalidade[] = new String[2];
			modalidade = lista.split(",");
			temporario =new Modalidade(Integer.parseInt(modalidade[0]),Integer.parseInt(modalidade[1]));				
			retorno.add(temporario);
		}
		return retorno;		
	}
	
	public boolean existe_quantidade_km(int n) throws SQLException{
		ArrayList<String> lista_km = new ArrayList<String>();
		for(String lista : dao.all_atributo("quantidade_km"))
		{			
			lista_km.add(lista);
		}
		
		ArrayList<Integer> inteiros = new ArrayList<Integer>();
		
		for(int i = 0; i < lista_km.size(); i++) {
			inteiros.add(Integer.parseInt(lista_km.get(i)));   
		}
		
		if(inteiros.contains(n)){
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<String> lista_modalidade_por_id_corrida(ArrayList<Integer> entrada) throws SQLException{		
		ArrayList<String> retorno = new ArrayList<String>();
		
		Map<String, Object> mapping = new HashMap<>();
		
		Modalidade temporario;
		for(int i=0; i < entrada.size(); i++) {
			mapping.clear();
			
			mapping.put( "id_modalidade",  entrada.get(i));

			temporario=this.findBy(mapping).get(0);
			
			retorno.add(temporario.getQuantidade_km()+ "km");
		}
		
		return retorno;		
	}
	
	public Modalidade findBy_id(int id) throws SQLException{
		ArrayList<Modalidade> retorno = new ArrayList<Modalidade>();
		Modalidade temporario;
		
		Map<String, Object> mapping = new HashMap<>();
		mapping.put( "id_modalidade",  id);
		
		for(String lista : dao.findBy(mapping))
		{			
			String modalidade[] = new String[2];
			modalidade = lista.split(",");
			temporario =new Modalidade(Integer.parseInt(modalidade[0]),Integer.parseInt(modalidade[1]));				
			retorno.add(temporario);
		}
		return retorno.get(0);		
	}


}
