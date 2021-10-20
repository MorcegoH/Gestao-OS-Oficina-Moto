package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class CEP extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox cboUF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CEP frame = new CEP();
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
	public CEP() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CEP.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1112, 654);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 29, 40, 13);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(72, 28, 96, 19);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endere\u00E7o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 75, 68, 19);
		contentPane.add(lblNewLabel_1);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(72, 77, 555, 19);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 141, 45, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("UF");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(491, 198, 25, 13);
		contentPane.add(lblNewLabel_3);

		txtBairro = new JTextField();
		txtBairro.setBounds(72, 140, 406, 19);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		cboUF = new JComboBox();
		cboUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUF.setBounds(523, 196, 104, 21);
		contentPane.add(cboUF);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLimpar.setBounds(10, 281, 109, 32);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("Pesquisar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCep.setBounds(349, 22, 129, 27);
		contentPane.add(btnCep);

		JButton txtSobre = new JButton("");
		txtSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		txtSobre.setToolTipText("Sobre");
		txtSobre.setIcon(new ImageIcon(CEP.class.getResource("/img/help.png")));
		txtSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtSobre.setBorder(null);
		txtSobre.setBackground(SystemColor.control);
		txtSobre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSobre.setBounds(716, 29, 128, 128);
		contentPane.add(txtSobre);

		JLabel lblNewLabel_2_1 = new JLabel("Cidade");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 198, 45, 13);
		contentPane.add(lblNewLabel_2_1);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(72, 197, 406, 19);
		contentPane.add(txtCidade);

		/* Uso da Biblioteca Atxy2k para validação do campo txtCep */
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(195, 15, 32, 32);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	} // Fim do Construtor
	
		private void buscarCep() {
			String logradouro="";
			String	tipoLogradouro="";
			String resultado=null;
			String cep=txtCep.getText();
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
				        	cboUF.setSelectedItem(element.getText());
				        }
				        if (element.getQualifiedName().equals("tipo_logradouro")) {
				        	tipoLogradouro = element.getText();
				        }
				        if (element.getQualifiedName().equals("logradouro")) {
				        	logradouro = element.getText();
				        if (element.getQualifiedName().equals("resultado")) {
				        		resultado = element.getText();
				        	if (resultado.equals("1")) {
				        		lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked.png")));
				        		
				        	} else {
				        		JOptionPane.showMessageDialog(null, "CEP não encontrado!!");
				        	}
				        		
				        	}
				 }
				 }
				 //Setar o campo endereco
				 txtEndereco.setText(tipoLogradouro + " " + logradouro);
			} catch (Exception e) {
				System.out.println(e);
			}
			
			private void limpar() {
				txtCep.setText(null);
				txtEndereco.setText(null);
				txtBairro.setText(null);
				txtCidade.setText(null);
				cboUF.setSelectedItem(null);
				txtCep.requestFocus();
				lblStatus.setIcon(null);
			}
		}
}
