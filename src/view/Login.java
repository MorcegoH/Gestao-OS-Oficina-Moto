package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "unused", "serial" })
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
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

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(88, 60, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(144, 57, 200, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(88, 85, 46, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnEntrar.setBounds(88, 162, 75, 32);
		contentPane.add(btnEntrar);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/icon/dbof.png")));
		lblStatus.setBounds(312, 162, 32, 32);
		contentPane.add(lblStatus);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(144, 88, 200, 20);
		contentPane.add(txtSenha);
	} // end of the constructor

	/**
	 * Método responsável pela exibição do status de conexão
	 */
	private void status() {
		DAO dao = new DAO();
		try {
			Connection con = dao.conectar();
			System.out.println(con);

			if (con != null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dbon.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dbof.png")));
			}
			// IMPORTANTE! Sempre encerrar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método status()

	DAO dao = new DAO();

	/**
	 * Método responsável pela autenticação do usuário
	 */
	private void logar() {
		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			try {
				String read = "select * from usuarios where login=? and senha=md5(?)";
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtLogin.getText());
				pst.setString(2, txtSenha.getText());
				// a linha abaixo executa a query(instrução SQL) armazenando o resultado no
				// objeto rs
				ResultSet rs = pst.executeQuery();
				// se existir o login e senha correspondente
				// se existir o login e senha correspondente
				if (rs.next()) {
					// capturar o perfil do usuário
					String perfil = rs.getString(5);
					// System.out.println(perfil);

					// tratamento de perfil de usuário
					if (perfil.equals("administrador")) {
						Oficina oficina = new Oficina();
						oficina.setVisible(true);
						// liberar os botões
						oficina.btnRelatorios.setEnabled(true);
						oficina.btnUser.setEnabled(true);
						// finalizar o JFrame
						this.dispose();
					} else if (perfil.equals("operador")) {
						Oficina oficina = new Oficina();
						oficina.setVisible(true);
						// finalizar o JFrame
						this.dispose();
					} else {
						Oficina oficina = new Oficina();
						oficina.setVisible(true);
						// finalizar o JFrame
						this.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inválido(s)", "Atenção!",
							JOptionPane.WARNING_MESSAGE);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}
}
