package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.Corredor;
import dao.*;

public class Teste {

	public static void main(String[] args) {
		String caminho;
		File file = new File("trabalho.sqlite");
		caminho = file.getAbsolutePath().replaceAll("trabalho.sqlite", "db/trabalho.sqlite").replaceAll("\\\\", "/");
		
		try{						
			GenericDao dao = new GenericDao(caminho,"Corredor");
			ArrayList<String> teste;
			
			teste = dao.all();
			System.out.println("Dao.all: "+teste);
			
			Map<String, Object> mapping = new HashMap<>();
			mapping.put( "id_corredor",  "2");			
			
			teste = dao.findBy(mapping);			
			System.out.println("Dao.findby: "+teste);
			

			mapping.clear();
			mapping.put( "id_corredor",  "2");
			mapping.put( "nome",  "Thiago Machado");
			mapping.put( "data_de_nascimento",  "10/10/1995");
			mapping.put( "cpf",  "654321");
			
			dao.insert(mapping);

			teste = dao.all();
			System.out.println("Dao.all depois insert: "+teste);
			
			mapping.clear();
			mapping.put( "id_corredor",  "2");			
			Map<String, Object> mapping2 = new HashMap<>();			
			mapping2.put( "cpf",  "999999");			
			dao.update(mapping2,mapping);
			teste = dao.all();
			System.out.println("Dao.all depois update: "+teste);
			
			
			mapping.clear();
			mapping.put( "id_corredor",  "2");			
			dao.remove(mapping);
			
			teste = dao.all();
			System.out.println("Dao.all depois remove: "+teste);
			
			
		}catch(Exception e){
			System.out.println("Caught IOException: " + e.getMessage());						
		}	
		
		try{
			CorredorDao corredordao = new CorredorDao(caminho);
			
			ArrayList<Corredor> lista_corredores;
			
			lista_corredores = corredordao.all();			
			for (Corredor pessoa : lista_corredores){
				System.out.println(pessoa);				
			}
				
			Corredor cor_in = new Corredor(2,"Thiago Machado","10/10/1995","654321");
			
			corredordao.insert(cor_in);
			
			lista_corredores = corredordao.all();			
			for (Corredor pessoa : lista_corredores){
				System.out.println(pessoa);				
			}
			
			System.out.println(corredordao.all_chaves());
			System.out.println(corredordao.descobre_chave_livre());
			
			
			
			Corredor cor_up = new Corredor();
			
			cor_up.setCpf("999999");
			corredordao.update(cor_up,2);
			
			lista_corredores = corredordao.all();			
			for (Corredor pessoa : lista_corredores){
				System.out.println(pessoa);				
			}			
			
			corredordao.remove(2);
			
			lista_corredores = corredordao.all();			
			for (Corredor pessoa : lista_corredores){
				System.out.println(pessoa);				
			}
			
			System.out.println(corredordao.descobre_chave_livre());

			
		}catch(Exception e){
			System.out.println("Caught IOException: " + e.getMessage());						
		}
		

		System.out.println("Hello World!");
	}

}
