package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtCep;
	private JTextField txtIdCli;
	private JTextField txtNome;
	private JTextField txtFone1;
	private JTextField txtCpf;
	private JTextField txtCnh;
	private JTextField txtFone2;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JLabel lblStatus;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Clientes() {
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/icon/icon.png")));
		setTitle("Cadastrar Clientes");
		setBounds(150, 150, 758, 454);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(15, 15, 200, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/icon/lupa.png")));
		lblNewLabel.setBounds(225, 13, 24, 24);
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(15, 46, 703, 114);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 703, 114);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(15, 243, 32, 14);
		getContentPane().add(lblCep);

		txtCep = new JTextField();
		txtCep.setBounds(57, 240, 93, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnBuscar.setBounds(160, 239, 89, 23);
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(15, 174, 24, 14);
		getContentPane().add(lblNewLabel_1);

		txtIdCli = new JTextField();
		txtIdCli.setEditable(false);
		txtIdCli.setColumns(10);
		txtIdCli.setBounds(49, 171, 40, 20);
		getContentPane().add(txtIdCli);

		JLabel lblNomeCli = new JLabel("* Nome");
		lblNomeCli.setBounds(105, 174, 46, 14);
		getContentPane().add(lblNomeCli);

		JLabel lblNewLabel_2 = new JLabel("* Campos obrigat\u00F3rios");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(590, 18, 128, 14);
		getContentPane().add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setBounds(161, 171, 249, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblFone = new JLabel("* Fone1");
		lblFone.setBounds(420, 174, 51, 14);
		getContentPane().add(lblFone);

		txtFone1 = new JTextField();
		txtFone1.setBounds(470, 171, 85, 20);
		getContentPane().add(txtFone1);
		txtFone1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("* CPF");
		lblNewLabel_3.setBounds(571, 174, 32, 14);
		getContentPane().add(lblNewLabel_3);

		txtCpf = new JTextField();
		txtCpf.setBounds(613, 171, 105, 20);
		getContentPane().add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("CNH");
		lblNewLabel_4.setBounds(15, 204, 32, 14);
		getContentPane().add(lblNewLabel_4);

		txtCnh = new JTextField();
		txtCnh.setBounds(49, 202, 100, 20);
		getContentPane().add(txtCnh);
		txtCnh.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Fone2");
		lblNewLabel_5.setBounds(161, 204, 40, 14);
		getContentPane().add(lblNewLabel_5);

		txtFone2 = new JTextField();
		txtFone2.setColumns(10);
		txtFone2.setBounds(211, 201, 85, 20);
		getContentPane().add(txtFone2);

		JLabel lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setBounds(306, 204, 32, 14);
		getContentPane().add(lblNewLabel_6);

		txtEmail = new JTextField();
		txtEmail.setBounds(348, 202, 370, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("* Endere\u00E7o");
		lblNewLabel_7.setBounds(15, 275, 65, 14);
		getContentPane().add(lblNewLabel_7);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(90, 272, 250, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("* N\u00FAmero");
		lblNewLabel_8.setBounds(348, 275, 62, 14);
		getContentPane().add(lblNewLabel_8);

		txtNumero = new JTextField();
		txtNumero.setBounds(420, 272, 50, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Complemento");
		lblNewLabel_9.setBounds(480, 275, 85, 14);
		getContentPane().add(lblNewLabel_9);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(571, 272, 147, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("* Bairro");
		lblNewLabel_10.setBounds(15, 300, 46, 14);
		getContentPane().add(lblNewLabel_10);

		txtBairro = new JTextField();
		txtBairro.setBounds(71, 297, 178, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("* Cidade");
		lblNewLabel_11.setBounds(262, 300, 56, 14);
		getContentPane().add(lblNewLabel_11);

		txtCidade = new JTextField();
		txtCidade.setBounds(324, 297, 178, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("* UF");
		lblNewLabel_12.setBounds(512, 300, 40, 14);
		getContentPane().add(lblNewLabel_12);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(550, 296, 51, 22);
		getContentPane().add(cboUf);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icon/create.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(15, 330, 70, 70);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icon/update.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(90, 328, 70, 70);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icon/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(170, 328, 70, 70);
		getContentPane().add(btnExcluir);

		lblStatus = new JLabel("");
		lblStatus.setBounds(259, 238, 24, 24);
		getContentPane().add(lblStatus);

		/**
		 * Uso da biblioteca Atxy2k para validacao do campo txtCep
		 */
		RestrictedTextField nome = new RestrictedTextField(txtNome);
		nome.setLimit(50);
		RestrictedTextField cnh = new RestrictedTextField(txtCnh);
		cnh.setLimit(11);
		cnh.setOnlyNums(true);
		RestrictedTextField cpf = new RestrictedTextField(txtCpf);
		cpf.setLimit(11);
		cpf.setOnlyNums(true);
		RestrictedTextField cep = new RestrictedTextField(txtCep);
		cep.setLimit(8);
		cep.setOnlyNums(true);
		RestrictedTextField endereco = new RestrictedTextField(txtEndereco);
		endereco.setLimit(50);
		RestrictedTextField numero = new RestrictedTextField(txtNumero);
		numero.setLimit(12);
		numero.setOnlyNums(true);
		RestrictedTextField complemento = new RestrictedTextField(txtComplemento);
		complemento.setLimit(30);
		RestrictedTextField bairro = new RestrictedTextField(txtBairro);
		bairro.setLimit(50);
		RestrictedTextField cidade = new RestrictedTextField(txtCidade);
		cidade.setLimit(50);
		RestrictedTextField fone1 = new RestrictedTextField(txtFone1);
		fone1.setLimit(11);
		fone1.setOnlyNums(true);
		RestrictedTextField fone2 = new RestrictedTextField(txtFone2);
		fone2.setLimit(11);
		fone2.setOnlyNums(true);
		RestrictedTextField email = new RestrictedTextField(txtEmail);
		email.setLimit(250);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(643, 360, 89, 23);
		getContentPane().add(btnLimpar);

	}// end of the constructor

	/**
	 * Método para buscar o cep no webservise: republicavirtual.com.br
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/checked.png")));
					} else {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/x-button.png")));
						JOptionPane.showMessageDialog(null, "CEP nao encontrado");
					}
				}
			}

			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do metodo buscarCep()

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnLimpar;

	/**
	 * Método responsável por fazer pesquisa avançada do cliente no bando de dados
	 */
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as Cliente, cnh as CNH, cpf as CPF, cep as CEP, endereco as Endereço, numero as Número, complemento as Complemento, bairro as Bairro, cidade as Cidade, uf as UF, fone1 as Fone1, fone2 as Fone2, email as Email from clientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do metodo pesquisarCliente()

	/**
	 * Método responsável por adicionar um cliente no banco
	 */
	private void adicionarCliente() {
		// validacao de campos obrigatorios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Nome", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone1", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtFone1.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CPF", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtCpf.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Endereco", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Número", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção !", JOptionPane.WARNING_MESSAGE);
			cboUf.requestFocus();
		} else {
			// inserir o cliente no banco
			String create = "insert into clientes(nome,cnh,cpf,cep,endereco,numero,complemento,bairro,cidade,uf,fone1,fone2,email) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCnh.getText());
				pst.setString(3, txtCpf.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtNumero.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUf.getSelectedItem().toString());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());

				// criando uma variavel que vai executar a query e receber o valor 1 em caso
				// positivo (insercao do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Cliente já cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do metodo adicionarCliente()

	/**
	 * Método responsável por editar os dados do cliente no banco de dados
	 */
	private void editarCliente() {
		// validacao de campos obrigatorios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Nome", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
		} else if (txtFone1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone1", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtFone1.requestFocus();
		} else if (txtCpf.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo CPF", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtCpf.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Endereco", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Número", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção !", JOptionPane.WARNING_MESSAGE);
			cboUf.requestFocus();
		} else {
			String create = "update clientes set nome=?,cnh=?,cpf=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=?,fone1=?,fone2=?,email=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCnh.getText());
				pst.setString(3, txtCpf.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtNumero.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUf.getSelectedItem().toString());
				pst.setString(11, txtFone1.getText());
				pst.setString(12, txtFone2.getText());
				pst.setString(13, txtEmail.getText());
				pst.setString(14, txtIdCli.getText());

				// criando uma variavel que vai executar a query e receber o valor 1 em caso
				// positivo (edicão do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Cliente já cadastrado.\nCNH, CPF ou Email já existentes", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				txtCpf.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do metodo editarCliente()

	/**
	 * Método responsável por excluir o cliente do banco de dados
	 */
	private void excluirCliente() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtIdCli.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui OS em aberto.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método excluirCliente()

	/**
	 * Método responsável por setar os campos da tabela no formulario
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] [4]....
		int setar = table.getSelectedRow();
		// setar os campos
		txtIdCli.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtCnh.setText(table.getModel().getValueAt(setar, 2).toString());
		txtCpf.setText(table.getModel().getValueAt(setar, 3).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 4).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar, 5).toString());
		txtNumero.setText(table.getModel().getValueAt(setar, 6).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar, 7).toString());
		txtBairro.setText(table.getModel().getValueAt(setar, 8).toString());
		txtCidade.setText(table.getModel().getValueAt(setar, 9).toString());
		cboUf.setSelectedItem(table.getModel().getValueAt(setar, 10).toString());
		txtFone1.setText(table.getModel().getValueAt(setar, 11).toString());
		txtFone2.setText(table.getModel().getValueAt(setar, 12).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 13).toString());
		// Gerenciar botões
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}// fim do método setarCampos()

	/**
	 * Método responsável por zerar os campos
	 */
	private void limpar() {
		// limpar campos
		txtPesquisar.setText(null);
		txtIdCli.setText(null);
		txtNome.setText(null);
		txtCnh.setText(null);
		txtCpf.setText(null);
		txtCep.setText(null);
		txtNome.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtNome.setText(null);
		cboUf.setSelectedItem("");
		txtFone1.setText(null);
		txtFone2.setText(null);
		txtEmail.setText(null);
		// limpar tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar os botões
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}// fim do metodo limpar()
}
