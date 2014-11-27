package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GUI_Main {
	static int contagem;
	static JLabel mensagem_status_inferior;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Minha Janela" );
		frame.setLayout(new BorderLayout());
		frame.setSize(800,600);
		frame.setResizable(false);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Criando Menubar
		JMenuBar menuBar = new JMenuBar();		
		frame.setJMenuBar( menuBar );
		frame.add(menuBar,BorderLayout.PAGE_START);
		
		//Painel Central		
		JPanel painel_central = new JPanel();
		painel_central.setLayout(new BoxLayout(painel_central, BoxLayout.Y_AXIS));
		frame.add(painel_central,BorderLayout.CENTER);
		
		//Criando Mensagem_inferior
		mensagem_status_inferior = new JLabel("Inicio");
		frame.add(mensagem_status_inferior,BorderLayout.PAGE_END);	
		
		//  Menu Corrida
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
