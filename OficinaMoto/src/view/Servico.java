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
		setBounds(150, 150, 675, 253);
		getContentPane().setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarServico();
			}
		});
		txtPesquisa.setBounds(10, 11, 150, 20);
		getContentPane().add(txtPesquisa);
		txtPesquisa.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/icon/lupa.png")));
		lblNewLabel.setBounds(170, 9, 24, 24);
		getContentPane().add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 42, 639, 99);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 639, 99);
		desktopPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	} // fim do construtor

	DAO dao = new DAO();
	private JTextField txtPesquisa;

	private void pesquisarServico() {
		String read = "select idcli as ID, nome as Nome, cnh as CNH, cpf as CPF, cep as Cep, endereco as Endereço, numero as Nº,"
				+ "complemento as Complemento, bairro as Bairro, cidade as Cidade, uf as UF, fone1 as Cel, fone2 as Fone,"
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
