package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import classes.Corredor;
import classes.Corrida;
import classes.Inscricao;
import classes.Modalidade;
import dao.CorredorDao;
import dao.CorridaDao;
import dao.Corrida_ModalidadeDao;
import dao.InscricaoDao;
import dao.ModalidadeDao;


public class GUI_Main {
	static int contagem;
	static int id;
	static JLabel mensagem_status_inferior;
	static JPanel painel_central;
	static JFrame frame;	
	static JTable tabela_corredor;
	static DefaultTableModel modelo_tabela_corredor;
	static JTable tabela_corrida;
	static DefaultTableModel modelo_tabela_corrida;
	static JTable tabela_inscricoes;
	static DefaultTableModel modelo_tabela_inscricoes;
	static JTable tabela_modalidade;
	static DefaultTableModel modelo_tabela_modalidade;
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
		
		
		frame = new JFrame("Minha Janela" );
		frame.setLayout(new BorderLayout());
		frame.setSize(800,600);
		frame.setResizable(false);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Criando Menubar
		JMenuBar menuBar = new JMenuBar();		
		frame.setJMenuBar( menuBar );
		frame.add(menuBar,BorderLayout.PAGE_START);
		
		//Painel Central		
		painel_central = new JPanel();
		painel_central.setLayout(new BoxLayout(painel_central, BoxLayout.Y_AXIS));
		frame.add(painel_central,BorderLayout.CENTER);
		
		//Criando Mensagem_inferior
		mensagem_status_inferior = new JLabel("Inicio");
		frame.add(mensagem_status_inferior,BorderLayout.PAGE_END);	
		
		/*********************	
		***  Menu Corrida  **
		*********************/
		JMenu menu_corrida = new JMenu( "Corrida" );
		menuBar.add( menu_corrida );
		
		JMenuItem menu_corrida_listar = new JMenuItem( "Listar" );
		JMenuItem menu_corrida_adicionar = new JMenuItem( "Adicionar" );
		JMenuItem menu_corrida_remover = new JMenuItem( "Remover" );	
		JMenuItem menu_corrida_associar = new JMenuItem( "Associar Modalidade" );
		
		
		// Adicionando Botões do menu Corrida
		menu_corrida.add( menu_corrida_listar );	
		menu_corrida.add( menu_corrida_adicionar );
		menu_corrida.add( menu_corrida_remover );	
		menu_corrida.add( menu_corrida_associar );

		/****************************	
		***  Menu menu Modalidade  **
		*****************************/
		JMenu menu_modalidade = new JMenu( "Modalidade" );
		menuBar.add( menu_modalidade );
		
		JMenuItem menu_modalidade_listar = new JMenuItem( "Listar" );		
		menu_modalidade_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_modalidade_listar");
				mensagem_status_inferior.setText("Clicou menu_modalidade_listar");
			}
		}
		);		
		JMenuItem menu_modalidade_adicionar = new JMenuItem( "Adicionar" );
		menu_modalidade_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_modalidade_adicionar");
				mensagem_status_inferior.setText("Clicou menu_modalidade_adicionar");
			}
		}
		);		
		JMenuItem menu_modalidade_remover = new JMenuItem( "Remover" );		
		menu_modalidade_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_modalidade_remover");
				mensagem_status_inferior.setText("Clicou menu_modalidade_remover");
			}
		}
		);		
		JMenuItem menu_modalidade_associar = new JMenuItem( "Associar Corrida" );
		menu_modalidade_associar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_modalidade_associar");
				mensagem_status_inferior.setText("Clicou menu_modalidade_associar");
			}
		}
		);
		
		// Adicionando Botões do menu Modalidade
		menu_modalidade.add( menu_modalidade_listar );	
		menu_modalidade.add( menu_modalidade_adicionar );
		menu_modalidade.add( menu_modalidade_remover );	
		menu_modalidade.add( menu_modalidade_associar );
		
		

		/****************************	
		**  Menu menu Corredor     **
		*****************************/
		JMenu menu_corredor = new JMenu( "Corredor" );
		menuBar.add( menu_corredor );
		
		JMenuItem menu_corredor_listar = new JMenuItem( "Listar" );			
		JMenuItem menu_corredor_adicionar = new JMenuItem( "Adicionar" );				
		JMenuItem menu_corredor_remover = new JMenuItem( "Remover" );		
				

		
		// Adicionando Botões do menu Modalidade
		menu_corredor.add( menu_corredor_listar );	
		menu_corredor.add( menu_corredor_adicionar );
		menu_corredor.add( menu_corredor_remover );	
		
		/****************************	
		***  Menu menu Inscrição  **
		*****************************/
		JMenu menu_inscricao = new JMenu( "Inscrição" );
		menuBar.add( menu_inscricao );
		
		JMenuItem menu_inscricao_listar = new JMenuItem( "Listar" );		
		menu_inscricao_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_inscricao_listar");
				mensagem_status_inferior.setText("Clicou menu_inscricao_listar");
			}
		}
		);		
		JMenuItem menu_inscricao_adicionar = new JMenuItem( "Adicionar" );
		menu_inscricao_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_inscricao_adicionar");
				mensagem_status_inferior.setText("Clicou menu_inscricao_adicionar");
			}
		}
		);		
		JMenuItem menu_inscricao_remover = new JMenuItem( "Remover" );		
		menu_inscricao_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_inscricao_remover");
				mensagem_status_inferior.setText("Clicou menu_inscricao_remover");
			}
		}
		);		

		
		// Adicionando Botões do menu Modalidade
		menu_inscricao.add( menu_inscricao_listar );	
		menu_inscricao.add( menu_inscricao_adicionar );
		menu_inscricao.add( menu_inscricao_remover );
		
		
		/**************************************	
		**  Menu menu Corredor Listeners    **
		**************************************/		
		
		//listar	
		menu_corredor_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_listar");				
				mensagem_status_inferior.setText("Clicou menu_corredor_listar");
				
				String [] colunas_corredor = {"Id", "Nome", "Data de Nascimento","Cpf"};
				modelo_tabela_corredor = new DefaultTableModel(colunas_corredor, 0);				
				tabela_corredor = new JTable(modelo_tabela_corredor);
				
				String [] colunas_inscricoes = {"Id Inscricao","Id Relacao", "Id Corredor","Data inscrição","Tamanho da camiseta"};
				modelo_tabela_inscricoes = new DefaultTableModel(colunas_inscricoes, 0);				
				tabela_inscricoes = new JTable(modelo_tabela_inscricoes);
				
				tabela_corredor.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()){
							System.out.println(tabela_corredor.getValueAt(tabela_corredor.getSelectedRow(), 0).toString());
							int id_corredor = Integer.parseInt(tabela_corredor.getValueAt(tabela_corredor.getSelectedRow(), 0).toString());
							Corredor corredor_selecionado;
							try {
								corredor_selecionado = corredordao.findBy(id_corredor).get(0);
								modelo_tabela_inscricoes.setRowCount(0);
								for(Inscricao caso : corredor_selecionado.getInscricoes(inscricaodao)) {
									Object[] data = {caso.getId_inscricao(),caso.getId_relacao(),caso.getId_corredor(),caso.getData(),caso.getTamanho_camiseta()};
									modelo_tabela_inscricoes.addRow(data);						
								}
							} catch (SQLException e1) {
								mensagem_status_inferior.setText("Problemas com o banco de dados!" + e1.getMessage());
								System.out.println("Problemas com o banco de dados!" + e1.getMessage());
							}
							
							painel_central.removeAll();								
							painel_central.add(new JScrollPane(tabela_corredor),BorderLayout.CENTER);
							painel_central.add(new JScrollPane(tabela_inscricoes),BorderLayout.CENTER);
							frame.setVisible(true);
						}
				    }					
				});				
				
				ArrayList<Corredor> lista_corredores;				
				try {
					lista_corredores = corredordao.all();
					for(Corredor caso : lista_corredores) {
						Object[] data = {caso.getId_corredor(),caso.getNome(),caso.getData_de_nascimento(),caso.getCpf()};
						modelo_tabela_corredor.addRow(data);						
					}
					
				} catch (SQLException e1) {
					mensagem_status_inferior.setText("Problemas com o banco de dados!" + e1.getMessage());
					System.out.println("Problemas com o banco de dados!" + e1.getMessage());
				}
				
				painel_central.removeAll();								
				painel_central.add(new JScrollPane(tabela_corredor),BorderLayout.CENTER);
				painel_central.add(new JScrollPane(tabela_inscricoes),BorderLayout.CENTER);
				frame.setVisible(true);				
			}
		}
		);
		
		//adicionar		
		menu_corredor_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_adicionar");
				mensagem_status_inferior.setText("Clicou menu_corredor_adicionar");
				painel_central.removeAll();				
				final Corredor corredor_cadastrar = new Corredor();				
				try {
					corredor_cadastrar.setId_corredor(corredordao.descobre_chave_livre());
				} catch (SQLException e1) {
					mensagem_status_inferior.setText("Erro ao acessar o banco de dados: " + e1.getMessage());
				}
				JLabel label_textField_nome = new JLabel("Nome");
				final JTextField textField_nome = new JTextField(30);
				textField_nome.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						corredor_cadastrar.setNome(textField_nome.getText());
						mensagem_status_inferior.setText("Nome Registrado");
					}
				}
				);				
				JLabel label_textField_data_nascimento = new JLabel("Data de Nascimento");
				final JTextField textField_data_nascimento = new JTextField(11);
				textField_data_nascimento.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						corredor_cadastrar.setData_de_nascimento(textField_data_nascimento.getText());
						mensagem_status_inferior.setText("Data de Nascimento registrada");
					}
				}
				);				
				JLabel label_textField_cpf = new JLabel("CPF");				
				final JTextField textField_cpf = new JTextField(30);
				textField_cpf.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						corredor_cadastrar.setCpf(textField_cpf.getText());
						mensagem_status_inferior.setText("CPF registrado");
					}
				}
				);				
				JPanel panel_nome = new JPanel(new FlowLayout());
				panel_nome.add(label_textField_nome);
				panel_nome.add(textField_nome);
				JPanel panel_data_nascimento = new JPanel(new FlowLayout());
				panel_data_nascimento.add(label_textField_data_nascimento);
				panel_data_nascimento.add(textField_data_nascimento);
				JPanel panel_cpf = new JPanel(new FlowLayout());
				panel_cpf.add(label_textField_cpf);
				panel_cpf.add(textField_cpf);				
				JButton botao_corredor_adicionar = new JButton("Salvar");
				botao_corredor_adicionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(corredor_cadastrar.getNome()!=null && corredor_cadastrar.getData_de_nascimento()!=null && corredor_cadastrar.getCpf()!=null){
							try {
								corredordao.insert(corredor_cadastrar);
								mensagem_status_inferior.setText("Corredor cadastrado com sucesso");
								try {
									corredor_cadastrar.setId_corredor(corredordao.descobre_chave_livre());
								} catch (SQLException e1) {
									mensagem_status_inferior.setText("Erro ao acessar o banco de dados: " + e1.getMessage());
								}
							} catch (SQLException e1) {
								mensagem_status_inferior.setText("Erro ao acessar o banco de dados: " + e1.getMessage());
							} finally {
								corredor_cadastrar.setNome(null);
								corredor_cadastrar.setData_de_nascimento(null);
								corredor_cadastrar.setCpf(null);
								textField_nome.setText("");
								textField_data_nascimento.setText("");
								textField_cpf.setText("");								
							}							
						} else {
							mensagem_status_inferior.setText("Informações faltando (Você deu enter no que digitou no campo?)");	
						}
					}
				}
				);				
				painel_central.add(panel_nome,BorderLayout.CENTER);
				painel_central.add(panel_data_nascimento,BorderLayout.CENTER);
				painel_central.add(panel_cpf,BorderLayout.CENTER);
				painel_central.add(botao_corredor_adicionar,BorderLayout.CENTER);				
				frame.setVisible(true);	
			}
		}
		);
		
		
		//Remover
		menu_corredor_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_remover");
				mensagem_status_inferior.setText("Clicou menu_corredor_remover");
				
				final String [] colunas_corredor = {"Id", "Nome", "Data de Nascimento","Cpf"};
				modelo_tabela_corredor = new DefaultTableModel(colunas_corredor, 0);				
				tabela_corredor = new JTable(modelo_tabela_corredor);				
				tabela_corredor.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()){
							System.out.println(tabela_corredor.getValueAt(tabela_corredor.getSelectedRow(), 0).toString());
							id = Integer.parseInt(tabela_corredor.getValueAt(tabela_corredor.getSelectedRow(), 0).toString());							
						}
				    }					
				});
				ArrayList<Corredor> lista_corredores;				
				try {
					lista_corredores = corredordao.all();
					for(Corredor caso : lista_corredores) {
						Object[] data = {caso.getId_corredor(),caso.getNome(),caso.getData_de_nascimento(),caso.getCpf()};
						modelo_tabela_corredor.addRow(data);						
					}					
				} catch (SQLException e1) {
					mensagem_status_inferior.setText("Problemas com o banco de dados!" + e1.getMessage());
					System.out.println("Problemas com o banco de dados!" + e1.getMessage());
				}
				
				final JButton botao_corredor_remover = new JButton("Remover");
				botao_corredor_remover.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							corredordao.remove(id);
							mensagem_status_inferior.setText("Remoção com sucesso");							
						} catch (SQLException e1) {
							mensagem_status_inferior.setText("Problemas com o banco de dados!" + e1.getMessage());
						} finally{
							//Atualizar Lista Falhando
						}						
					}
				}
				);				
				painel_central.removeAll();								
				painel_central.add(new JScrollPane(tabela_corredor),BorderLayout.CENTER);
				painel_central.add(botao_corredor_remover,BorderLayout.CENTER);
				frame.setVisible(true);				
			}
			}
		);
		
		/**************************************	
		**  Menu menu Corredor Listeners    **
		**************************************/	
		
		//Listar		
		menu_corrida_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_listar");
				mensagem_status_inferior.setText("Clicou menu_corrida_listar");
				
				String [] colunas_corrida = {"Id Corrida", "Nome", "Data","Local"};
				modelo_tabela_corrida = new DefaultTableModel(colunas_corrida, 0);				
				tabela_corrida = new JTable(modelo_tabela_corrida);
				
				String [] colunas_modalidade = {"Id modalidade","Quantidade de KM"};
				modelo_tabela_modalidade = new DefaultTableModel(colunas_modalidade, 0);				
				tabela_modalidade = new JTable(modelo_tabela_modalidade);
				
				tabela_corrida.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()){
							System.out.println(tabela_corrida.getValueAt(tabela_corrida.getSelectedRow(), 0).toString());
							int id_corrida = Integer.parseInt(tabela_corrida.getValueAt(tabela_corrida.getSelectedRow(), 0).toString());
							Corrida corrida_selecionado;
							try {
								corrida_selecionado = corridadao.findBy_id(id_corrida);
								modelo_tabela_modalidade.setRowCount(0);
								for(Modalidade caso : corrida_selecionado.getModalidade(corrida_modalidadedao,modalidadedao)) {
									Object[] data = {caso.getId_modalidade(),caso.getQuantidade_km()};
									modelo_tabela_modalidade.addRow(data);						
								}
								painel_central.removeAll();								
								painel_central.add(new JScrollPane(tabela_corrida),BorderLayout.CENTER);
								painel_central.add(new JScrollPane(tabela_modalidade),BorderLayout.CENTER);
								frame.setVisible(true);	
							} catch (SQLException e1) {
								mensagem_status_inferior.setText("Problemas com o banco de dados!" + e1.getMessage());
								System.out.println("Problemas com o banco de dados!" + e1.getMessage());
							} 								

						}
				    }					
				});				
				
				ArrayList<Corrida> lista_corrida;				
				try {
					lista_corrida = corridadao.all();
					for(Corrida caso : lista_corrida) {
						Object[] data = {caso.getId_corrida(),caso.getNome(),caso.getData(),caso.getLocal()};
						modelo_tabela_corrida.addRow(data);						
					}
					
				} catch (SQLException e1) {
					mensagem_status_inferior.setText("Problemas com o banco de dados!" + e1.getMessage());
					System.out.println("Problemas com o banco de dados!" + e1.getMessage());
				}
				
				painel_central.removeAll();								
				painel_central.add(new JScrollPane(tabela_corrida),BorderLayout.CENTER);
				painel_central.add(new JScrollPane(tabela_modalidade),BorderLayout.CENTER);
				frame.setVisible(true);				
			}
		}
		);;
		
		//Adicionar
		menu_corrida_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_adicionar");
				mensagem_status_inferior.setText("Clicou menu_corrida_adicionar");
			}
		}
		);
		
		//Remover
		menu_corrida_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_remover");
				mensagem_status_inferior.setText("Clicou menu_corrida_remover");
			}
		}
		);
		
		//Associar
		menu_corrida_associar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_associar");
				mensagem_status_inferior.setText("Clicou menu_corrida_associar");
			}
		}
		);


	
		frame.setVisible(true);
	}

}


