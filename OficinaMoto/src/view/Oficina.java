package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;

public class Oficina extends JFrame {

	private JPanel bcg;

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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Oficina.class.getResource("/icon/dbof.png")));
		lblNewLabel.setBounds(10, 10, 32, 32);
		panel.add(lblNewLabel);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setBounds(455, 11, 299, 28);
		panel.add(lblTime);
		
		JButton btnUser = new JButton("");
		btnUser.setBackground(SystemColor.scrollbar);
		btnUser.setIcon(new ImageIcon(Oficina.class.getResource("/icon/usuarios.png")));
		btnUser.setToolTipText("Usu\u00E1rio");
		btnUser.setBounds(10, 21, 128, 128);
		bcg.add(btnUser);
		
		JButton btnClient = new JButton("");
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
		btnSobre.setIcon(new ImageIcon(Oficina.class.getResource("/icon/sobre.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBackground(SystemColor.scrollbar);
		btnSobre.setBounds(605, 11, 128, 128);
		bcg.add(btnSobre);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Oficina.class.getResource("/icon/iconmoto1.png")));
		lblNewLabel_1.setBounds(332, 11, 64, 64);
		bcg.add(lblNewLabel_1);
	}
}
