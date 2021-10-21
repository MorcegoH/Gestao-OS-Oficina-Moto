package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Sobre extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/icon/icon.png")));
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 435, 266);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Sistema de Gerenciamento Oficina Moto - Version 1.0");
			lblNewLabel.setBounds(30, 29, 356, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Autores: Aryon, Heder, Ismael");
			lblNewLabel_1.setBounds(30, 54, 356, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Sob Licen\u00E7a MIT");
			lblNewLabel_2.setBounds(30, 79, 308, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblMit = new JLabel("");
			lblMit.setIcon(new ImageIcon(Sobre.class.getResource("/icon/mit.png")));
			lblMit.setBounds(304, 140, 64, 64);
			contentPanel.add(lblMit);
		}
	}

}
