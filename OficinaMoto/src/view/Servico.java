package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servico extends JDialog {
	private JTable table;
	private String tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servico dialog = new Servico();
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
	public Servico() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Servico.class.getResource("/icon/icon.png")));
		setResizable(false);
		setBounds(150, 150, 886, 605);
		getContentPane().setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisa.setBounds(471, 18, 256, 20);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/icon/lupa.png")));
		lblNewLabel.setBounds(737, 16, 24, 24);
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(471, 49, 389, 126);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 389, 126);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(771, 21, 24, 14);
		getContentPane().add(lblNewLabel_1);

		txtIdCli = new JTextField();
		txtIdCli.setEnabled(false);
		txtIdCli.setBounds(805, 18, 55, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 20, 441, 155);
		getContentPane().add(panel);
		panel.setLayout(null);

		chkServico = new JCheckBox("Servi\u00E7o");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		buttonGroup.add(chkServico);
		chkServico.setBounds(6, 122, 97, 23);
		panel.add(chkServico);

		chkOrcamento = new JCheckBox("Or\u00E7amento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		buttonGroup.add(chkOrcamento);
		chkOrcamento.setBounds(6, 96, 97, 23);
		panel.add(chkOrcamento);

		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] {"", "Aguardando aprova\u00E7\u00E3o", "Em reparo", "Aguardando retirada", "Retirado", "Or\u00E7amento reprovado"}));
		cboStatus.setBounds(243, 122, 188, 22);
		panel.add(cboStatus);

		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(243, 100, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Defeito informado");
		lblNewLabel_3.setBounds(10, 46, 109, 14);
		panel.add(lblNewLabel_3);

		txtDefeitoInfo = new JTextField();
		txtDefeitoInfo.setBounds(129, 40, 302, 20);
		panel.add(txtDefeitoInfo);
		txtDefeitoInfo.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("Defeito constatado");
		lblNewLabel_3_1.setBounds(10, 75, 109, 14);
		panel.add(lblNewLabel_3_1);

		txtDefeitoConst = new JTextField();
		txtDefeitoConst.setColumns(10);
		txtDefeitoConst.setBounds(129, 69, 302, 20);
		panel.add(txtDefeitoConst);

		txtOs = new JTextField();
		txtOs.setEnabled(false);
		txtOs.setBounds(10, 15, 86, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Data");
		lblNewLabel_15.setBounds(129, 18, 37, 14);
		panel.add(lblNewLabel_15);

		txtDataOs = new JTextField();
		txtDataOs.setEnabled(false);
		txtDataOs.setBounds(176, 15, 255, 20);
		panel.add(txtDataOs);
		txtDataOs.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Servico.class.getResource("/icon/motorcycle.png")));
		lblNewLabel_4.setBounds(20, 215, 128, 128);
		getContentPane().add(lblNewLabel_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Moto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(203, 215, 657, 128);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Modelo");
		lblNewLabel_5.setBounds(20, 20, 46, 14);
		panel_1.add(lblNewLabel_5);

		txtModelo = new JTextField();
		txtModelo.setBounds(76, 17, 200, 20);
		panel_1.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Fabricante");
		lblNewLabel_6.setBounds(286, 20, 67, 14);
		panel_1.add(lblNewLabel_6);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(363, 17, 133, 20);
		panel_1.add(txtFabricante);
		txtFabricante.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Ano");
		lblNewLabel_7.setBounds(506, 20, 29, 14);
		panel_1.add(lblNewLabel_7);

		txtAno = new JTextField();
		txtAno.setBounds(545, 17, 103, 20);
		panel_1.add(txtAno);
		txtAno.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Placa");
		lblNewLabel_8.setBounds(20, 50, 46, 14);
		panel_1.add(lblNewLabel_8);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(76, 47, 86, 20);
		panel_1.add(txtPlaca);
		txtPlaca.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Combust\u00EDvel");
		lblNewLabel_9.setBounds(172, 50, 78, 14);
		panel_1.add(lblNewLabel_9);

		txtCombustivel = new JTextField();
		txtCombustivel.setBounds(260, 48, 103, 20);
		panel_1.add(txtCombustivel);
		txtCombustivel.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("T\u00E9cnico");
		lblNewLabel_10.setBounds(373, 50, 46, 14);
		panel_1.add(lblNewLabel_10);

		txtTecnico = new JTextField();
		txtTecnico.setBounds(429, 47, 57, 20);
		panel_1.add(txtTecnico);
		txtTecnico.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Valor");
		lblNewLabel_11.setBounds(499, 50, 36, 14);
		panel_1.add(lblNewLabel_11);

		txtValor = new JTextField();
		txtValor.setBounds(545, 48, 103, 20);
		panel_1.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Chassi");
		lblNewLabel_12.setBounds(20, 80, 46, 14);
		panel_1.add(lblNewLabel_12);

		txtChassi = new JTextField();
		txtChassi.setBounds(76, 77, 153, 20);
		panel_1.add(txtChassi);
		txtChassi.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Garantia");
		lblNewLabel_13.setBounds(239, 80, 57, 14);
		panel_1.add(lblNewLabel_13);

		txtGarantia = new JTextField();
		txtGarantia.setBounds(306, 77, 124, 20);
		panel_1.add(txtGarantia);
		txtGarantia.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Retirada");
		lblNewLabel_14.setBounds(440, 80, 57, 14);
		panel_1.add(lblNewLabel_14);

		txtRetirada = new JTextField();
		txtRetirada.setBounds(506, 77, 114, 20);
		panel_1.add(txtRetirada);
		txtRetirada.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOs();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Servico.class.getResource("/icon/create.png")));
		btnAdicionar.setBounds(20, 475, 70, 70);
		getContentPane().add(btnAdicionar);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(Servico.class.getResource("/icon/read.png")));
		btnPesquisar.setBounds(100, 475, 70, 70);
		getContentPane().add(btnPesquisar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOs();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Servico.class.getResource("/icon/update.png")));
		btnEditar.setBounds(180, 475, 70, 70);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOs();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Servico.class.getResource("/icon/delete.png")));
		btnExcluir.setBounds(260, 475, 70, 70);
		getContentPane().add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(761, 522, 89, 23);
		getContentPane().add(btnLimpar);
		
		RestrictedTextField defeitoInfo = new RestrictedTextField(txtDefeitoInfo);
		defeitoInfo.setLimit(200);
		RestrictedTextField defeitoConst = new RestrictedTextField(txtDefeitoConst);
		defeitoConst.setLimit(200);
		RestrictedTextField modelo = new RestrictedTextField(txtModelo);
		modelo.setLimit(50);
		RestrictedTextField fabricante = new RestrictedTextField(txtFabricante);
		fabricante.setLimit(50);
		RestrictedTextField ano = new RestrictedTextField(txtAno);
		ano.setLimit(4);
		ano.setOnlyNums(true);
		RestrictedTextField placa = new RestrictedTextField(txtPlaca);
		placa.setLimit(7);
		RestrictedTextField combustivel = new RestrictedTextField(txtCombustivel);
		combustivel.setLimit(20);
		RestrictedTextField tecnico = new RestrictedTextField(txtTecnico);
		tecnico.setOnlyNums(true);
		RestrictedTextField chassi = new RestrictedTextField(txtChassi);
		chassi.setLimit(17);
		RestrictedTextField garantia = new RestrictedTextField(txtGarantia);
		garantia.setLimit(8);
		garantia.setOnlyNums(true);
		RestrictedTextField retirada = new RestrictedTextField(txtRetirada);
		retirada.setLimit(8);
		retirada.setOnlyNums(true);
	} // fim do construtor

	DAO dao = new DAO();
	private JTextField txtPesquisa;
	private JTextField txtIdCli;
	private JTextField txtDefeitoInfo;
	private JTextField txtDefeitoConst;
	private JTextField txtModelo;
	private JTextField txtFabricante;
	private JTextField txtAno;
	private JTextField txtPlaca;
	private JTextField txtCombustivel;
	private JTextField txtTecnico;
	private JTextField txtValor;
	private JTextField txtChassi;
	private JTextField txtGarantia;
	private JTextField txtRetirada;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtOs;
	private JTextField txtDataOs;
	private JCheckBox chkOrcamento;
	private JCheckBox chkServico;
	private JComboBox cboStatus;
	private JButton btnAdicionar;
	private JButton btnPesquisar;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Método responsável por pesquisar clientes no banco de dados
	 */
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as Nome, cpf as CPF, fone1 as Fone1,"
				+ "email as Email from clientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtPesquisa.getText() + "%");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método pesquisarCliente()

	/**
	 * Método e responsável por setar os campos da tabela no formulario
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] [4]....
		int setar = table.getSelectedRow();
		// setar os campos
		txtIdCli.setText(table.getModel().getValueAt(setar, 0).toString());
	}// fim do metodo setarCampos()

	/**
	 * Método responsável pela pesquisa da OS
	 */
	private void pesquisarOs() {
		// técnica usada para capturar o número da OS
		String numOs = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from tbos where os=" + numOs;
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtDataOs.setText(rs.getString(2));
				if (rs.getString(3).equals("Orçamento")) {
					chkOrcamento.setSelected(true);
					tipo = "Orçamento";
				} else {
					chkServico.setSelected(true);
					tipo = "Serviço";
				}
				cboStatus.setSelectedItem(rs.getString(4));
				txtDefeitoInfo.setText(rs.getString(5));
				txtDefeitoConst.setText(rs.getString(6));
				txtModelo.setText(rs.getString(7));
				txtFabricante.setText(rs.getString(8));
				txtAno.setText(rs.getString(9));
				txtPlaca.setText(rs.getString(10));
				txtCombustivel.setText(rs.getString(11));
				txtTecnico.setText(rs.getString(12));
				txtValor.setText(rs.getString(13));
				txtChassi.setText(rs.getString(14));
				txtIdCli.setText(rs.getString(15));
				txtGarantia.setText(rs.getString(16));
				txtRetirada.setText(rs.getString(17));
				btnAdicionar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Número de OS não existe", "Atenção !",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método pesquisarOs()

	/*
	 * Método responsável por emitir uma nova OS(inserir OS)
	 */
	private void emitirOs() {
		// validação
		if (txtIdCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha ID do cliente", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtPesquisa.requestFocus();
		} else if (txtDefeitoInfo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito Informado", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtDefeitoInfo.requestFocus();
		} else if (tipo == null) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione qual o status da OS", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			cboStatus.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Modelo", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtModelo.requestFocus();
		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fabricante", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtFabricante.requestFocus();
		} else if (txtAno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Ano", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtAno.requestFocus();
		} else if (txtCombustivel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Combustível", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtCombustivel.requestFocus();
		} else if (txtTecnico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Técnico", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtTecnico.requestFocus();
		} else if (txtChassi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Chassi", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtChassi.requestFocus();
		} else {
			String create = "insert into tbos (tipo,statusos,defeitocli,defeitotec,modelo,fabricante,ano,placa,combustivel,tecnico,valor,chassi,idcli,garantia,datasaida) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, tipo);
				pst.setString(2, cboStatus.getSelectedItem().toString());
				pst.setString(3, txtDefeitoInfo.getText());
				pst.setString(4, txtDefeitoConst.getText());
				pst.setString(5, txtModelo.getText());
				pst.setString(6, txtFabricante.getText());
				pst.setString(7, txtAno.getText());
				pst.setString(8, txtPlaca.getText());
				pst.setString(9, txtCombustivel.getText());
				pst.setString(10, txtTecnico.getText());
				pst.setString(11, txtValor.getText());
				pst.setString(12, txtChassi.getText());
				pst.setString(13, txtIdCli.getText());
				pst.setString(14, txtGarantia.getText());	
				pst.setString(15, txtRetirada.getText());
		
				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método emitirOs()
	
	/*
	 * Método responsável por emitir uma nova OS(inserir OS)
	 */
	private void editarOs() {
		// validação
		if (txtIdCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha ID do cliente", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtPesquisa.requestFocus();
		} else if (tipo == null) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione qual o status da OS", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			cboStatus.requestFocus();
		} else if (txtDefeitoInfo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito Informado", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtDefeitoInfo.requestFocus();
		} else if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Modelo", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtModelo.requestFocus();
		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fabricante", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtFabricante.requestFocus();
		} else if (txtAno.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Ano", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtAno.requestFocus();
		} else if (txtCombustivel.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Combustível", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtCombustivel.requestFocus();
		} else if (txtTecnico.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Técnico", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtTecnico.requestFocus();
		} else if (txtChassi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Chassi", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtChassi.requestFocus();
		} else {
			String create = "update tbos set tipo = ?,statusos = ?,defeitocli = ?,defeitotec = ?,modelo = ?,fabricante = ?,ano = ?,placa = ?,combustivel = ?,tecnico = ?,valor = ?,chassi = ?,garantia = ?,datasaida = ? where os = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, tipo);
				pst.setString(2, cboStatus.getSelectedItem().toString());
				pst.setString(3, txtDefeitoInfo.getText());
				pst.setString(4, txtDefeitoConst.getText());
				pst.setString(5, txtModelo.getText());
				pst.setString(6, txtFabricante.getText());
				pst.setString(7, txtAno.getText());
				pst.setString(8, txtPlaca.getText());
				pst.setString(9, txtCombustivel.getText());
				pst.setString(10, txtTecnico.getText());
				pst.setString(11, txtValor.getText());
				pst.setString(12, txtChassi.getText());
				pst.setString(13, txtGarantia.getText());	
				pst.setString(14, txtRetirada.getText());
				pst.setString(15, txtOs.getText());
		
				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "OS editada com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método editarOs()
	
	/**
	 * Método responsável por excluir a OS do banco de dados
	 */
	private void excluirOs() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desta OS?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbos where os = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtOs.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "OS excluída com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método excluirOs()
	
	/**
	 * Método responsável por zerar todos os campos
	 */
	private void limpar() {
		// limpar campos
		txtPesquisa.setText(null);
		txtIdCli.setText(null);
		txtOs.setText(null);
		txtDataOs.setText(null);
		txtDefeitoConst.setText(null);
		txtDefeitoInfo.setText(null);
		// tirar seleção do checkbox
		buttonGroup.clearSelection();
		cboStatus.setSelectedItem("");
		txtModelo.setText(null);
		txtFabricante.setText(null);
		txtAno.setText(null);
		txtPlaca.setText(null);
		txtCombustivel.setText(null);
		txtTecnico.setText(null);
		txtValor.setText(null);
		txtChassi.setText(null);
		txtGarantia.setText(null);
		txtRetirada.setText(null);
		// limpar tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		tipo = null;
	}// fim do método limpar()

}
