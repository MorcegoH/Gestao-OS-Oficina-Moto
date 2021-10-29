package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

import java.awt.Font;

@SuppressWarnings("serial")
public class Oficina extends JFrame {

	private JPanel bcg;
	private JLabel lblTime;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Oficina frame = new Oficina();
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
	public Oficina() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// evento disparado ao ativar o JFrame
				setarData();
				status();
			}
		});
		setResizable(false);
		setTitle("Sistema de Gest\u00E3o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Oficina.class.getResource("/icon/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 422);
		bcg = new JPanel();
		bcg.setBackground(Color.LIGHT_GRAY);
		bcg.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bcg);
		bcg.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 333, 764, 50);
		bcg.add(panel);
		panel.setLayout(null);

		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Oficina.class.getResource("/icon/dbof.png")));
		lblStatus.setBounds(10, 10, 32, 32);
		panel.add(lblStatus);

		lblTime = new JLabel("");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(401, 11, 353, 28);
		panel.add(lblTime);

		JButton btnUser = new JButton("");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUser.setBackground(SystemColor.scrollbar);
		btnUser.setIcon(new ImageIcon(Oficina.class.getResource("/icon/usuarios.png")));
		btnUser.setToolTipText("Usu\u00E1rio");
		btnUser.setBounds(10, 21, 128, 128);
		bcg.add(btnUser);

		JButton btnClient = new JButton("");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
			}
		});
		btnClient.setBackground(SystemColor.scrollbar);
		btnClient.setIcon(new ImageIcon(Oficina.class.getResource("/icon/client.png")));
		btnClient.setToolTipText("Clientes");
		btnClient.setBounds(10, 179, 128, 128);
		bcg.add(btnClient);

		JButton btnRelat = new JButton("");
		btnRelat.setIcon(new ImageIcon(Oficina.class.getResource("/icon/relatorio.png")));
		btnRelat.setBackground(SystemColor.scrollbar);
		btnRelat.setToolTipText("Relat\u00F3rios");
		btnRelat.setBounds(615, 179, 128, 128);
		bcg.add(btnRelat);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clicar no botao
				Sobre sobre = new Sobre(); // criar objeto
				sobre.setVisible(true); // exibit o JDialog Sobre
			}
		});
		btnSobre.setIcon(new ImageIcon(Oficina.class.getResource("/icon/sobre.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBackground(SystemColor.scrollbar);
		btnSobre.setBounds(605, 11, 128, 128);
		bcg.add(btnSobre);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Oficina.class.getResource("/icon/iconmoto1.png")));
		lblNewLabel_1.setBounds(332, 11, 64, 64);
		bcg.add(lblNewLabel_1);

		JButton btnOs = new JButton("");
		btnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servico servico = new Servico();
				servico.setVisible(true);
			}
		});
		btnOs.setBackground(SystemColor.scrollbar);
		btnOs.setIcon(new ImageIcon(Oficina.class.getResource("/icon/os.png")));
		btnOs.setBounds(300, 179, 128, 128);
		bcg.add(btnOs);
	} // fim do construtor

	/**
	 * Método responsável por setar a data e hora no rodapé
	 */
	private void setarData() {
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblTime.setText(formatador.format(dataLabel));
	}

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
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
