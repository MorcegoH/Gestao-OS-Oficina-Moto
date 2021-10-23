package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JTextField;

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

		JLabel lblNewLabel_12 = new JLabel("UF");
		lblNewLabel_12.setBounds(512, 300, 46, 14);
		getContentPane().add(lblNewLabel_12);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(550, 296, 51, 22);
		getContentPane().add(cboUf);

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icon/create.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(15, 330, 70, 70);
		getContentPane().add(btnAdicionar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icon/update.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(90, 328, 70, 70);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/icon/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(170, 328, 70, 70);
		getContentPane().add(btnExcluir);

		lblStatus = new JLabel("");
		lblStatus.setBounds(259, 238, 24, 24);
		getContentPane().add(lblStatus);

		/**
		 * Uso da biblioteca Atxy2k para validação do campo txtCep
		 */
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		validar.setOnlyNums(true);
		validar.setLimit(8);

	}// end of the constructor

	/**
	 * buscarCep
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
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}

			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	DAO dao = new DAO();
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as Cliente, cnh as CNH, cpf as CPF, cep as CEP, endereco as Endereço, numero as Número, complemento as Complemento, bairro as Bairro, cidade as Cidade, uf as UF, fone as Telefone, cel as Celular, email as Email from clientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
