package Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import classes.Corredor;
import dao.CorredorDao;
import dao.CorridaDao;
import dao.Corrida_ModalidadeDao;
import dao.InscricaoDao;
import dao.ModalidadeDao;


public class GUI_Main {
	static int contagem;
	static JLabel mensagem_status_inferior;
	static JPanel painel_central;
	static JFrame frame;
	static JTable tabela;
	static DefaultTableModel modelo_tabela;
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
		menu_corrida_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_listar");
				mensagem_status_inferior.setText("Clicou menu_corrida_listar");
			}
		}
		);		
		JMenuItem menu_corrida_adicionar = new JMenuItem( "Adicionar" );
		menu_corrida_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_adicionar");
				mensagem_status_inferior.setText("Clicou menu_corrida_adicionar");
			}
		}
		);		
		JMenuItem menu_corrida_remover = new JMenuItem( "Remover" );		
		menu_corrida_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_remover");
				mensagem_status_inferior.setText("Clicou menu_corrida_remover");
			}
		}
		);		
		JMenuItem menu_corrida_associar = new JMenuItem( "Associar Modalidade" );
		menu_corrida_associar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corrida_associar");
				mensagem_status_inferior.setText("Clicou menu_corrida_associar");
			}
		}
		);
		
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
		menu_corredor_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_listar");
				String [] colunas = {"Id", "Nome", "Data de Nascimento","Cpf"};
				DefaultTableModel modelo_tabela = new DefaultTableModel(colunas, 0);
				JTable tabela = new JTable(modelo_tabela);
				
				
				mensagem_status_inferior.setText("Clicou menu_corredor_listar");
				ArrayList<Corredor> lista_corredores;				
				try {
					lista_corredores = corredordao.all();
					for(Corredor caso : lista_corredores) {
						Object[] data = {caso.getId_corredor(),caso.getNome(),caso.getData_de_nascimento(),caso.getCpf()};
						modelo_tabela.addRow(data);						
					}
					
				} catch (SQLException e1) {
					System.out.println("Problemas com o banco de dados!" + e1.getMessage());
				}
				painel_central.removeAll();
				painel_central.add(new JScrollPane(tabela),BorderLayout.CENTER);				
				frame.setVisible(true);				
			}
		}
		);	
		
		
		
		JMenuItem menu_corredor_adicionar = new JMenuItem( "Adicionar" );
		menu_corredor_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_adicionar");
				mensagem_status_inferior.setText("Clicou menu_corredor_adicionar");
			}
		}
		);		
		JMenuItem menu_corredor_remover = new JMenuItem( "Remover" );		
		menu_corredor_remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_remover");
				mensagem_status_inferior.setText("Clicou menu_corredor_remover");
			}
		}
		);		

		
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
		
		menu_corredor_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou menu_corredor_listar");				
				mensagem_status_inferior.setText("Clicou menu_corredor_listar");
				
				String [] colunas = {"Id", "Nome", "Data de Nascimento","Cpf"};
				modelo_tabela = new DefaultTableModel(colunas, 0);	
				
				tabela = new JTable(modelo_tabela);	
				
				
				ArrayList<Corredor> lista_corredores;				
				try {
					lista_corredores = corredordao.all();
					for(Corredor caso : lista_corredores) {
						Object[] data = {caso.getId_corredor(),caso.getNome(),caso.getData_de_nascimento(),caso.getCpf()};
						modelo_tabela.addRow(data);						
					}
					
				} catch (SQLException e1) {
					System.out.println("Problemas com o banco de dados!" + e1.getMessage());
				}
				painel_central.removeAll();
				
				tabela.addMouseListener(new MouseListener() {
					

					@Override
					public void mouseEntered(MouseEvent arg0) {
						System.out.println(tabela.getSelectedRow());
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						System.out.println(tabela.getSelectedRow());
						
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						System.out.println(tabela.getSelectedRow());						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						System.out.println(tabela.getSelectedRow());
						
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println(tabela.getSelectedRow());
						
					}
					});
				
				painel_central.add(tabela,BorderLayout.CENTER);
				
				
				frame.setVisible(true);				
			}
		}
		);
		
		
		
		


		
		
		
		
		
		

		JButton botao = new JButton("botão");
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contagem++;
				mensagem_status_inferior.setText(Integer.toString(contagem));	
			}
		}
		);
		
		
		
		JButton botao2 = new JButton("botão222");
		
		
		painel_central.add(botao2,BorderLayout.CENTER);
		painel_central.add(botao,BorderLayout.CENTER);

	
		frame.setVisible(true);
	}

}
