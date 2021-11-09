package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Usuarios extends JDialog {
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JTextField txtPesquisar;
	private JButton btnEditar_1;
	private JButton btnExcluir_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				chkSenha.setVisible(false);
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/icon/icon.png")));
		setResizable(false);
		setModal(true);
		setTitle("Usu\u00E1rios");
		setBounds(150, 150, 682, 435);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtPesquisar.setBounds(10, 11, 169, 24);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/lupa.png")));
		lblNewLabel.setBounds(189, 11, 24, 24);
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 46, 646, 86);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 646, 86);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 146, 32, 14);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(38, 143, 100, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Usu\u00E1rio");
		lblNewLabel_2.setBounds(148, 146, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(204, 143, 452, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setBounds(10, 196, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtLogin = new JTextField();
		txtLogin.setBounds(52, 193, 183, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(245, 196, 46, 14);
		getContentPane().add(lblNewLabel_4);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "administrador", "operador", "atendente" }));
		cboPerfil.setBounds(52, 242, 142, 22);
		getContentPane().add(cboPerfil);

		JLabel lblNewLabel_5 = new JLabel("Perfil");
		lblNewLabel_5.setBounds(10, 246, 40, 14);
		getContentPane().add(lblNewLabel_5);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// tratamento do checkbox(senha do usuário)
				if (chkSenha.isSelected()) {
					editarUsuario();
				} else {
					editarUsuarioPersonalizado();
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/update.png")));
		btnEditar.setBounds(274, 305, 80, 80);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/delete.png")));
		btnExcluir.setBounds(364, 305, 80, 80);
		getContentPane().add(btnExcluir);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/icon/create.png")));
		btnAdicionar.setBounds(184, 305, 80, 80);
		getContentPane().add(btnAdicionar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(294, 193, 362, 20);
		getContentPane().add(txtSenha);

		// uso da biblioteca atxy2k para validações
		RestrictedTextField usuario = new RestrictedTextField(this.txtUsuario);
		usuario.setLimit(50);
		RestrictedTextField login = new RestrictedTextField(this.txtLogin);
		login.setLimit(50);
		RestrictedTextField senha = new RestrictedTextField(this.txtSenha);

		chkSenha = new JCheckBox("Confirma a mudan\u00E7a da senha?");
		chkSenha.setBounds(294, 220, 211, 23);
		getContentPane().add(chkSenha);
		senha.setLimit(250);

	} // fim do construtor

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTable table;
	private JTextField txtId;
	private JComboBox cboPerfil;
	private JPasswordField txtSenha;
	private JCheckBox chkSenha;

	/**
	 * Método responsável por fazer pesquisa avançada do cliente no bando de dados
	 */
	private void pesquisarUsuario() {
		// ? -> parâmetro
		String read = "select id as ID, usuario as Usuário, login as Login, perfil as Perfil from usuarios where usuario like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			// 1 -> parâmetro
			pst.setString(1, txtPesquisar.getText() + "%");
			// executar a query e obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável por setar os campos da tabela no formulario
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteúdo da linha da tabela
		// int (índice) [0], [1], [2],...
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table.getModel().getValueAt(setar, 2).toString());
		cboPerfil.setSelectedItem(table.getModel().getValueAt(setar, 3).toString());
		// gerenciar os botões
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		chkSenha.setVisible(true);
	}

	/**
	 * Método responsável por setar a senha no campo txtSenha
	 */
	public void setarSenha() {
		String read2 = "select senha from usuarios where id = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			// a linha abaixo executa a instrução sql e armazena o resultado no objeto rs
			ResultSet rs = pst.executeQuery();
			// a linha abaixo verifica se existe uma senha para o id
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável por adicionar um usuário no banco de dados
	 */
	private void adicionarUsuario() {
		// validação de campos obrigatórios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o usuário", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a UF", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			cboPerfil.requestFocus();
		} else {
			// inserir o cliente no banco
			String create = "insert into usuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// criando uma variável que irá executar a query e receber o valor1 em caso
				// positivo (inserção do cliente no banco de dados)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					limpar();
				}
				con.close();
				// o catch abaixo se refere ao valor duplicado de email(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já existente\nCadastre outro Login", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao adicionar o usuário", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Método responsável por editar um usuário no banco de dados
	 */
	private void editarUsuario() {
		// validação de campos obrigatórios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o usuário", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha a UF", "Atenção!", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			cboPerfil.requestFocus();
		} else {
			// inserir o cliente no banco
			String update = "update usuarios set usuario = ?, login = ?, senha = md5(?), perfil = ? where id = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				// criando uma variável que irá executar a query e receber o valor1 em caso
				// positivo (inserção do cliente no banco de dados)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do usuário editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					limpar();
				}
				con.close();
				// o catch abaixo se refere ao valor duplicado de email(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login já existente\nCadastre outro Login", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do usuário", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}// fim do método editarUsuario()

	/**
	 * Método responsável por editar os dados do cliente no banco de dados exceto a
	 * senha
	 */
	private void editarUsuarioPersonalizado() {
		// validação de campos obrigatórios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuário", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Aviso", JOptionPane.WARNING_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			// editar o usuario do banco
			String update = "update usuarios set usuario=?,login=?,perfil=? where id=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edicao do usuario no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Edição não realizada.\nLogin já existente", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}// fim do metodo editarUsuarioPersonalizado()

	/**
	 * Método responsável por excluir o usuário do banco de dados
	 */
	private void excluirUsuario() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where id = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir\nCliente possui pedido em aberto", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Método responsável por limpar os campos e gereniar os botões
	 */
	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem("");
		// limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}
