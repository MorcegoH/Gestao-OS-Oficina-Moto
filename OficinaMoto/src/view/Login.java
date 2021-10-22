package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icon/icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(88, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(144, 57, 200, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(88, 85, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(88, 171, 89, 23);
		contentPane.add(btnEntrar);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/icon/dbof.png")));
		lblStatus.setBounds(312, 162, 32, 32);
		contentPane.add(lblStatus);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(144, 88, 200, 20);
		contentPane.add(txtSenha);
	} // fim do construtor
	
	/**
	 * M�todo respons�vel pela exibi��o do status de conex�o
	 */
	private void status() {
		// criar um objeto de nome dao para acessar o m�todo de conex�o
		DAO dao = new DAO();
		try {
			// abrir a conex�o com o banco
			Connection con = dao.conectar();

			// a linha abaixo exibe o retorno da conex�o
			System.out.println(con);

			// mudando o �cone do rodap� no caso do banco de dados estar dispon�vel
			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dbon.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dbof.png")));
			}

			// IMPORTANTE! Sempre encerrar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
