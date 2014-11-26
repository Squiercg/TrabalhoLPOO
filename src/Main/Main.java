package Main;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Corredor;
import classes.Corrida;
import classes.Corrida_Modalidade;
import classes.Inscricao;
import classes.Modalidade;
import dao.CorredorDao;
import dao.CorridaDao;
import dao.Corrida_ModalidadeDao;
import dao.InscricaoDao;
import dao.ModalidadeDao;

public class Main {

	private static Scanner leitor;
	private static boolean menu_principal;
	private static boolean menu_corredores;
	private static boolean menu_corridas;
	private static CorredorDao corredordao;
	private static CorridaDao corridadao;
	private static ModalidadeDao modalidadedao;
	private static Corrida_ModalidadeDao corrida_modalidadedao;
	private static InscricaoDao inscricaodao;
	

	public static void main(String[] args) {
		String caminho;
		File file = new File("trabalho.sqlite");
		caminho = file.getAbsolutePath().replaceAll("trabalho.sqlite", "db/trabalho.sqlite").replaceAll("\\\\", "/");
		
		try {
			corredordao = new CorredorDao(caminho);
			corridadao = new CorridaDao(caminho);
			modalidadedao = new ModalidadeDao(caminho);
			corrida_modalidadedao = new Corrida_ModalidadeDao(caminho);
			inscricaodao = new InscricaoDao(caminho);
			
		} catch (SQLException e) {
			System.out.println("Problemas em criar coneção com banco de dados: " + e.getMessage());
		}
		
		
		leitor = new Scanner(System.in);
		int opcao;
		
		menu_principal=true;		
		while(menu_principal) {
			imprime_menu_principal();
			opcao=leitor.nextInt();
			leitor.nextLine();
			
			switch (opcao) {
			case 1:
				menu_corredores();
				break;
			case 2:
				menu_corridas();
				break;
			case 3:
				System.out.println("Fim do Programa! Beep. Beep.");
				menu_principal=false;
				break;
			default:
				System.out.println("Opção Inválida!");
			}			
		}		
		leitor.close();
	}
	
	public static void imprime_menu_principal(){
		System.out.printf("\n\n");
		System.out.println("Menu Principal.");
		System.out.println("Opções:");
		System.out.println("1 - Corredores");
		System.out.println("2 - Corridas");
		System.out.println("3 - Encerrar sessão");
		System.out.print("Sua Opção: ");		
	}
	
	public static void imprime_menu_corredores(){
		System.out.printf("\n\n");
		System.out.println("Menu Corredores");
		System.out.println("Opções:");
		System.out.println("1 - Listar Cadastros");
		System.out.println("2 - Cadastrar Corredor");
		System.out.println("3 - Modificar Cadastro");
		System.out.println("4 - Remover Cadastro");	
		System.out.println("5 - Listar Inscrições");	
		System.out.println("6 - Inscrever Corredor em Corrida");
		System.out.println("7 - Remover Inscricão");
		System.out.println("8 - Voltar");
		System.out.print("Sua Opção: ");		
	}
	
	public static void imprime_menu_corridas(){
		System.out.printf("\n\n");
		System.out.println("Menu Corredores");
		System.out.println("Opções:");
		System.out.println("1 - Listar Corridas");
		System.out.println("2 - Cadastrar Corrida");
		System.out.println("3 - Modificar Corrida");
		System.out.println("4 - Remover Corrida");
		System.out.println("5 - Listar Modalidade");
		System.out.println("6 - Cadastrar Modalidade");
		System.out.println("7 - Modificar Modalidade");
		System.out.println("8 - Remover Modalidade");
		System.out.println("9 - Listar relações de modalidades por corridas");		
		System.out.println("10 - Registrar modalidade em corrida");
		System.out.println("11 - Remover modalidade em corrida");
		System.out.println("12 - Voltar");
		System.out.print("Sua Opção: ");		
	}
	
	public static void menu_corredores(){
		int opcao;
		
		menu_corredores=true;
		while(menu_corredores) {
			imprime_menu_corredores();
			opcao=leitor.nextInt();
			leitor.nextLine();
			
			switch (opcao) {
			case 1: //Listar Cadastros
				ArrayList<Corredor> lista_corredores;				
				try {
					System.out.printf("\n\n");
					lista_corredores = corredordao.all();
					System.out.println("Lista de corredores cadastrados:");
					for (Corredor pessoa : lista_corredores){
						System.out.println(pessoa);				
					}
					System.out.printf("\n\n");
					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 2: //Cadastrar Corredor
				String nome, data_de_nascimento, cpf;
				
				System.out.println("Cadastro de Corredor.");
				System.out.printf("Nome: ");
				nome=leitor.nextLine();
				System.out.printf("Data de Nascimento (dd/mm/aaaa):");
				data_de_nascimento=leitor.nextLine();
				System.out.printf("CPF: ");
				cpf=leitor.nextLine();
				
				try {
					Corredor cadastro = new Corredor(corredordao.descobre_chave_livre(),nome,data_de_nascimento,cpf);
					corredordao.insert(cadastro);					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				
				break;
			case 3://Modificar Cadastro
				int mod, identificador;			
				
				System.out.println("O que deseja Modificar?");
				System.out.println("1 - Nome");
				System.out.println("2 - Data de nascimento");
				System.out.println("3 - CPF");
				System.out.println("4 - Voltar");
				
				mod=leitor.nextInt();
				leitor.nextLine();
				
				Corredor modificar = new Corredor();
				
				System.out.printf("Qual id do cadastro que deseja modificar?");
				identificador=leitor.nextInt();
				leitor.nextLine();
				
				
				switch (mod) {
				case 1:
					System.out.printf("Novo Nome: ");
					modificar.setNome(leitor.nextLine());					
					try {
						if(corredordao.chave_existe(identificador)) {
							corredordao.update(modificar,identificador);
						} else {
							System.out.println("Falha na modificação, chave inexistente!");
						}
					} catch (SQLException e) {
						System.out.println("Problemas com o banco de dados!" + e.getMessage());
					}					
					break;
				case 2:
					System.out.printf("Nova Data de nascimento (dd/mm/aaaa): ");
					modificar.setData_de_nascimento(leitor.nextLine());					
					try {
						if(corredordao.chave_existe(identificador)) {
							corredordao.update(modificar,identificador);
						} else {
							System.out.println("Falha na modificação, chave inexistente!");
						}
					} catch (SQLException e) {
						System.out.println("Problemas com o banco de dados!" + e.getMessage());
					}
					break;
				case 3:
					System.out.printf("Novo cpf: ");
					modificar.setCpf(leitor.nextLine());					
					try {
						if(corredordao.chave_existe(identificador)) {
							corredordao.update(modificar,identificador);
						} else {
							System.out.println("Falha na modificação, chave inexistente!");
						}
					} catch (SQLException e) {
						System.out.println("Problemas com o banco de dados!" + e.getMessage());
					}
					break;
				case 4:
					System.out.println("Voltando ao menu corredor");
					break;
				default:
					System.out.println("Opção Inválida!");
				}
				break;
			case 4://Remover Cadastro
				int id_remover;
				System.out.printf("Qual id do cadastro que deseja remover?");
				id_remover=leitor.nextInt();
				leitor.nextLine();
				try {
					if(corredordao.chave_existe(id_remover)) {
						corredordao.remove(id_remover);
					} else {
						System.out.println("Falha na remoção, chave inexistente!");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 5://Listar Inscricões	
				try {
					System.out.printf("\n\n");
					System.out.println("Lista de Incrições:");
					for (int corredor : inscricaodao.todos_corredores()){
						Corredor corredor_auxiliar = corredordao.findBy(corredor).get(0);
						System.out.println();
						System.out.println(corredor_auxiliar.getNome()+ ": ");
						for(int relacoes : inscricaodao.relacoes_de_um_corredor(corredor_auxiliar.getId_corredor())) {
							Corrida_Modalidade temporario = corrida_modalidadedao.findBy_relacao(relacoes);
							System.out.println(corridadao.findBy_id(temporario.getId_corrida()).getNome() + 
									" na modalidade " + modalidadedao.findBy_id(temporario.getId_modalidade()).getQuantidade_km() + " km");							
						}
					}
					System.out.printf("\n\n");
					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 6://Inscrever Corredor em Corrida
				int id_corredor, id_relacao;
				String data, tamanho_camiseta;
				
				System.out.println("Inscrever Corredor em Corrida");
				
				System.out.printf("Entre com o id do corredor: ");				
				id_corredor=leitor.nextInt();
				leitor.nextLine();
				System.out.printf("Entre com o id da corrida/modalidade: ");				
				id_relacao=leitor.nextInt();
				leitor.nextLine();				
				System.out.printf("Entre com a data da inscricao (dd/mm/aaaa): ");
				data=leitor.nextLine();
				System.out.printf("Entre com o tamanho da camiseta:");
				tamanho_camiseta=leitor.nextLine();

				
				try {
					if(corredordao.chave_existe(id_corredor) && corrida_modalidadedao.chave_existe(id_relacao)){
						if(inscricaodao.existe_inscricao(id_corredor,id_relacao)==false){
							Inscricao cadastro_inscricao = new Inscricao(inscricaodao.descobre_chave_livre(),id_relacao,id_corredor,data,tamanho_camiseta);
							inscricaodao.insert(cadastro_inscricao);
						} else {
							System.out.println("Esse corredor ja está inscrito nessa modalidade");
						}
					} else {
						System.out.println("Chave inválida");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}				

				break;
			case 7://Remover inscricão
				int id_remover_inscricao;
				System.out.printf("Qual id da inscricao que deseja remover?");
				id_remover_inscricao=leitor.nextInt();
				leitor.nextLine();
				try {
					if(inscricaodao.chave_existe(id_remover_inscricao)) {
						inscricaodao.remove(id_remover_inscricao);
					} else {
						System.out.println("Falha na remoção, chave inexistente!");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;	
			case 8:
				System.out.println("Voltando ao menu principal");
				menu_corredores=false;
				break;
			default:
				System.out.println("Opção Inválida!");
			}			
		}		
	}

	public static void menu_corridas(){
		int opcao;
		
		menu_corridas=true;
		while(menu_corridas) {
			imprime_menu_corridas();
			opcao=leitor.nextInt();
			leitor.nextLine();
			
			switch (opcao) {
			case 1: //Listar Corridas
				ArrayList<Corrida> lista_corridas;				
				try {
					System.out.printf("\n\n");
					lista_corridas = corridadao.all();
					System.out.println("Lista de corridas cadastrados:");
					for (Corrida corrida : lista_corridas){
						System.out.println(corrida);
						System.out.printf("Modalidades: ");						
						System.out.println(modalidadedao.lista_modalidade_por_id_corrida(corrida_modalidadedao.findBy_idcorrida(corrida.getId_corrida())));

					}
					System.out.printf("\n\n");
					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 2: //Cadastrar Corrida
				String nome, data, local;
				
				System.out.println("Cadastro de corrida.");
				System.out.printf("Nome: ");
				nome=leitor.nextLine();
				System.out.printf("Data (dd/mm/aaaa) :");
				data=leitor.nextLine();
				System.out.printf("Local: ");
				local=leitor.nextLine();
				
				try {
					Corrida cadastro_corrida = new Corrida(corridadao.descobre_chave_livre(),nome,data,local);
					corridadao.insert(cadastro_corrida);					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				
				break;
			case 3: //Modificar Corrida
				int mod, identificador;			
				
				System.out.println("O que deseja Modificar?");
				System.out.println("1 - Nome");
				System.out.println("2 - Data");
				System.out.println("3 - Local");
				System.out.println("4 - Voltar");
				
				mod=leitor.nextInt();
				leitor.nextLine();
				
				Corrida modificar_corrida = new Corrida();
				
				System.out.printf("Qual id da corrida que deseja modificar?");
				identificador=leitor.nextInt();
				leitor.nextLine();
				
				
				switch (mod) {
				case 1:
					System.out.printf("Novo Nome: ");
					modificar_corrida.setNome(leitor.nextLine());					
					try {
						if(corridadao.chave_existe(identificador)) {
							corridadao.update(modificar_corrida,identificador);
						} else {
							System.out.println("Falha na modificação, chave inexistente!");
						}
					} catch (SQLException e) {
						System.out.println("Problemas com o banco de dados!" + e.getMessage());
					}					
					break;
				case 2:
					System.out.printf("Nova data (dd/mm/aaaa): ");
					modificar_corrida.setData(leitor.nextLine());					
					try {
						if(corridadao.chave_existe(identificador)) {
							corridadao.update(modificar_corrida,identificador);
						} else {
							System.out.println("Falha na modificação, chave inexistente!");
						}
					} catch (SQLException e) {
						System.out.println("Problemas com o banco de dados!" + e.getMessage());
					}
					break;
				case 3:
					System.out.printf("Novo local: ");
					modificar_corrida.setLocal(leitor.nextLine());					
					try {
						if(corridadao.chave_existe(identificador)) {
							corridadao.update(modificar_corrida,identificador);
						} else {
							System.out.println("Falha na modificação, chave inexistente!");
						}
					} catch (SQLException e) {
						System.out.println("Problemas com o banco de dados!" + e.getMessage());
					}
					break;
				case 4:
					System.out.println("Voltando ao menu corrida");
					break;
				default:
					System.out.println("Opção Inválida!");
				}
				break;
			case 4: //Remover Corrida
				int id_remover_corrida;
				System.out.printf("Qual id da corrida que deseja remover?");
				id_remover_corrida=leitor.nextInt();
				leitor.nextLine();
				try {
					if(corridadao.chave_existe(id_remover_corrida)) {
						corridadao.remove(id_remover_corrida);
						corrida_modalidadedao.remover_por_corrida(id_remover_corrida);
					} else {
						System.out.println("Falha na remoção, chave inexistente!");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 5: //Listar Modalidade
				ArrayList<Modalidade> lista_modalidades;				
				try {
					System.out.printf("\n\n");
					lista_modalidades = modalidadedao.all();
					System.out.println("Lista de modalidades cadastrados:");
					for (Modalidade modalidade : lista_modalidades){
						System.out.println(modalidade);				
					}
					System.out.printf("\n\n");
					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 6: //Cadastrar Modalidade
				int quantidade_km;
				
				System.out.println("Cadastro de modalidade.");
				System.out.printf("Quantidade de Kilometros: ");
				quantidade_km=leitor.nextInt();
				leitor.nextLine();
				
				try {
					if(modalidadedao.existe_quantidade_km(quantidade_km)) {
						System.out.println("Modalidade ja existe!");
					}else {
						Modalidade cadastro_modalidade = new Modalidade(modalidadedao.descobre_chave_livre(),quantidade_km);
						modalidadedao.insert(cadastro_modalidade);
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 7: //Modificar Modalidade
				int identificador_modalidade, novo_km;
				Modalidade modificar_modalidade = new Modalidade();
				
				System.out.printf("Qual id da modalidade que deseja modificar?");
				identificador_modalidade=leitor.nextInt();
				leitor.nextLine();
				
				System.out.printf("Nova quantidade de kilometros: ");
				novo_km=leitor.nextInt();
				leitor.nextLine();
				modificar_modalidade.setQuantidade_km(novo_km);					
				try {
					if(modalidadedao.chave_existe(identificador_modalidade)) {
						modalidadedao.update(modificar_modalidade,identificador_modalidade);
					} else {
						System.out.println("Falha na modificação, chave inexistente!");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}					
				
				break;
			case 8: //Remover Modalidade
				int id_remover_modalidade;
				System.out.printf("Qual id da modalidade que deseja remover?");
				id_remover_modalidade=leitor.nextInt();
				leitor.nextLine();
				try {
					if(modalidadedao.chave_existe(id_remover_modalidade)) {
						modalidadedao.remove(id_remover_modalidade);
						corrida_modalidadedao.remover_por_modalidade(id_remover_modalidade);
					} else {
						System.out.println("Falha na remoção, chave inexistente!");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 9: //Lista de relações entre corridas e modalidades
				ArrayList<Corrida_Modalidade> lista_Corrida_Modalidade;				
				try {
					System.out.printf("\n\n");
					lista_Corrida_Modalidade = corrida_modalidadedao.all();
					System.out.println("Lista de relações entre corridas e modalidades");
					for (Corrida_Modalidade relacao : lista_Corrida_Modalidade){
						System.out.println(relacao);				
					}
					System.out.printf("\n\n");
					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}

				break;
			case 10: //Registrar modalidade em corrida
				int id_corrida, id_modalidade;
				
				System.out.printf("Qual o id da corrida que vai para receber uma modalidade: ");
				id_corrida=leitor.nextInt();
				leitor.nextLine();
				
				System.out.printf("Qual o id da modalidade a ser adicionada: ");
				id_modalidade=leitor.nextInt();
				leitor.nextLine();
				
				try {
					if(corridadao.chave_existe(id_corrida) && modalidadedao.chave_existe(id_modalidade)){
						Corrida_Modalidade insere_Corrida_modalidade = new Corrida_Modalidade(corrida_modalidadedao.descobre_chave_livre(),id_corrida,id_modalidade);
						corrida_modalidadedao.insert(insere_Corrida_modalidade);
					}
					
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}

				break;
			case 11:
				int id_remover_relacao;
				System.out.printf("Qual id da relacao entre corrida e modalidade que deseja remover?");
				id_remover_relacao=leitor.nextInt();
				leitor.nextLine();
				try {
					if(corrida_modalidadedao.chave_existe(id_remover_relacao)) {
						corrida_modalidadedao.remove(id_remover_relacao);
					} else {
						System.out.println("Falha na remoção, chave inexistente!");
					}
				} catch (SQLException e) {
					System.out.println("Problemas com o banco de dados!" + e.getMessage());
				}
				break;
			case 12:
				System.out.println("Voltando ao menu principal");
				menu_corridas=false;
				break;
			default:
				System.out.println("Opção Inválida!");
			}			
		}
	}	
}
