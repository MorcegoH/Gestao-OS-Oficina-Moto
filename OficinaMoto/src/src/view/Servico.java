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
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Servico extends JDialog {
	private JTable table;

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
				pesquisarServico();
			}
		});
		txtPesquisa.setBounds(604, 18, 123, 20);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/icon/lupa.png")));
		lblNewLabel.setBounds(737, 16, 24, 24);
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(606, 49, 254, 99);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 254, 99);
		desktopPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(771, 21, 24, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(805, 18, 55, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 20, 441, 155);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Servi\u00E7o");
		chckbxNewCheckBox.setBounds(6, 122, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxOramento = new JCheckBox("Or\u00E7amento");
		chckbxOramento.setBounds(6, 96, 97, 23);
		panel.add(chckbxOramento);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Aguardando aprova\u00E7\u00E3o", "Em reparo", "Aguardando retirada", "Retirado", "Or\u00E7amento reprovado"}));
		comboBox.setBounds(243, 122, 188, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(243, 100, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Defeito informado");
		lblNewLabel_3.setBounds(10, 20, 109, 14);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 17, 302, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Defeito constatado");
		lblNewLabel_3_1.setBounds(10, 48, 109, 14);
		panel.add(lblNewLabel_3_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(129, 45, 302, 20);
		panel.add(textField_2);
		
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
		
		textField_3 = new JTextField();
		textField_3.setBounds(76, 17, 200, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Fabricante");
		lblNewLabel_6.setBounds(286, 20, 67, 14);
		panel_1.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(363, 17, 133, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Ano");
		lblNewLabel_7.setBounds(506, 20, 29, 14);
		panel_1.add(lblNewLabel_7);
		
		textField_5 = new JTextField();
		textField_5.setBounds(545, 17, 103, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Placa");
		lblNewLabel_8.setBounds(20, 50, 46, 14);
		panel_1.add(lblNewLabel_8);
		
		textField_6 = new JTextField();
		textField_6.setBounds(76, 47, 86, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Gasolina");
		lblNewLabel_9.setBounds(172, 50, 57, 14);
		panel_1.add(lblNewLabel_9);
		
		textField_7 = new JTextField();
		textField_7.setBounds(239, 47, 114, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("T\u00E9cnico");
		lblNewLabel_10.setBounds(363, 50, 46, 14);
		panel_1.add(lblNewLabel_10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(417, 47, 57, 20);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Valor");
		lblNewLabel_11.setBounds(484, 50, 36, 14);
		panel_1.add(lblNewLabel_11);
		
		textField_9 = new JTextField();
		textField_9.setBounds(530, 48, 118, 20);
		panel_1.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Chassi");
		lblNewLabel_12.setBounds(20, 80, 46, 14);
		panel_1.add(lblNewLabel_12);
		
		textField_10 = new JTextField();
		textField_10.setBounds(76, 77, 153, 20);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Garantia");
		lblNewLabel_13.setBounds(239, 80, 57, 14);
		panel_1.add(lblNewLabel_13);
		
		textField_11 = new JTextField();
		textField_11.setBounds(306, 77, 124, 20);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Retirada");
		lblNewLabel_14.setBounds(440, 80, 57, 14);
		panel_1.add(lblNewLabel_14);
		
		textField_12 = new JTextField();
		textField_12.setBounds(506, 77, 114, 20);
		panel_1.add(textField_12);
		textField_12.setColumns(10);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setIcon(new ImageIcon(Servico.class.getResource("/icon/create.png")));
		btnAdicionar.setBounds(10, 464, 70, 70);
		getContentPane().add(btnAdicionar);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setIcon(new ImageIcon(Servico.class.getResource("/icon/read.png")));
		btnPesquisar.setBounds(90, 464, 70, 70);
		getContentPane().add(btnPesquisar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Servico.class.getResource("/icon/update.png")));
		btnEditar.setBounds(170, 464, 70, 70);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(Servico.class.getResource("/icon/delete.png")));
		btnExcluir.setBounds(250, 464, 70, 70);
		getContentPane().add(btnExcluir);

	} // fim do construtor

	DAO dao = new DAO();
	private JTextField txtPesquisa;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;

	/**
	 * Método responsável por pesquisar clientes no banco de dados
	 */
	private void pesquisarServico() {
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
	}
}
