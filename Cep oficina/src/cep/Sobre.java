package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
	public Sobre() {
		setModal(true);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 400);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP Oficina - Version 1.0");
		lblNewLabel.setBounds(60, 39, 206, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor: Aluno Heder Santos");
		lblNewLabel_1.setBounds(60, 81, 206, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("WEB Service:");
		lblNewLabel_1_1.setBounds(60, 130, 92, 22);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblWebService = new JLabel("republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://www.republicavirtual.com.br/");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setBounds(152, 130, 206, 22);
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblWebService);
		
		JButton btnYouTube = new JButton("");
		btnYouTube.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnYouTube.setToolTipText("Playlist");
		btnYouTube.setBounds(60, 242, 64, 64);
		btnYouTube.setIcon(new ImageIcon(Sobre.class.getResource("/img/youtube.png")));
		btnYouTube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.youtube.com/watch?v=sKO2C58yf28&list=PLbEOwbQR9lqxVuDWNIrG57_JGcbIL3FWP&index=5");
			}
		});
		getContentPane().add(btnYouTube);
		
		JButton btnGitHub = new JButton("");
		btnGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/MorcegoH/Gestao-OS-Oficina-Moto");
			}
		});
		btnGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGitHub.setToolTipText("Projeto");
		btnGitHub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		btnGitHub.setBounds(223, 242, 64, 64);
		getContentPane().add(btnGitHub);

	} // Fim do Construtor
	
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
