package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.datos.*;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UserMenu extends JFrame {

	private JPanel contentPane;
	private Profile profile;

	/**
	 * Create the frame.
	 */
	public UserMenu(Profile p) {
		setTitle("User Menu");
		profile = p;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Seleccione la acci\u00F3n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 416, 35);
		contentPane.add(lblNewLabel);
		
		
		JButton cons_usu = new JButton("Consultar los usuarios");
		cons_usu.setFont(new Font("Tahoma", Font.BOLD, 12));
		cons_usu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir la ventana con los acciones de consultar los usuarios
				ConsultarUsuarios cu = new ConsultarUsuarios(profile);
				contentPane.setVisible(false);
				dispose();
				cu.setVisible(true);
			}
		});
		cons_usu.setBounds(10, 71, 171, 44);
		contentPane.add(cons_usu);
		
		
		
		JButton cons_libr = new JButton("Consultar los libros");
		cons_libr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir la ventana con los acciones de consultar los libros
				ConsultarLibros cl = new ConsultarLibros(profile);
				contentPane.setVisible(false);
				dispose();
				cl.setVisible(true);
			}
		});
		cons_libr.setFont(new Font("Tahoma", Font.BOLD, 12));
		cons_libr.setBounds(255, 71, 171, 44);
		contentPane.add(cons_libr);
		
		
		
		JButton cons_amg = new JButton("Consultar los amigos");
		cons_amg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir la ventana con los acciones de consultar los amigos
				ConsultarAmigos ca = new ConsultarAmigos(profile);
				contentPane.setVisible(false);
				dispose();
				ca.setVisible(true);
			}
		});
		cons_amg.setFont(new Font("Tahoma", Font.BOLD, 12));
		cons_amg.setBounds(10, 148, 171, 44);
		contentPane.add(cons_amg);
		
		
		JButton cons_amg_1 = new JButton("Aplicaci\u00F3n m\u00F3vil");
		cons_amg_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//abrir la ventana con los acciones de aplicacion movil
				AplicacionMovil am = new AplicacionMovil(profile);
				contentPane.setVisible(false);
				dispose();
				am.setVisible(true);
			}
		});
		cons_amg_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		cons_amg_1.setBounds(255, 148, 171, 44);
		contentPane.add(cons_amg_1);
		
		
		
		JButton btnNewButton = new JButton("Cambiar usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ir a la pagina principal
				contentPane.setVisible(false);
				dispose();
				LoginFormStart.main(null);
			}
		});
		btnNewButton.setBounds(155, 222, 133, 23);
		contentPane.add(btnNewButton);
	}
}
