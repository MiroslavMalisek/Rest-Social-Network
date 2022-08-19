package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clase.datos.*;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AplicacionMovil extends JFrame {
	
	private Profile profile;

	private JPanel contentPane;
	private final JTextArea textArea;
	private JTextField usuario;

	/**
	 * Create the frame.
	 */
	public AplicacionMovil(Profile p) {
		
		profile = p;
		
		setTitle("Aplicaci\u00F3n m\u00F3vil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Incluir un bloque de texto que contiene scrolls
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea = new JTextArea();
		textArea.setBounds(10, 93, 416, 120);
		textArea.setEditable(false);
		scroll.setBounds(10, 93, 416, 120);
		scroll.setViewportView(textArea);
		getContentPane().add(scroll);
		
		
		JLabel lblNewLabel = new JLabel("Introduzca el nombre del usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 34, 195, 14);
		contentPane.add(lblNewLabel);
		
		usuario = new JTextField();
		usuario.setBounds(215, 32, 96, 20);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JButton mostrarBtn = new JButton("Mostrar");
		mostrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = usuario.getText();
				List<String> datos = obtenerDatos(user);
			}
		});
		mostrarBtn.setBounds(337, 31, 89, 23);
		contentPane.add(mostrarBtn);
		
		
		JLabel lblNewLabel_1 = new JLabel("Informaci\u00F3n obtenida");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 68, 159, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton borrarBtn = new JButton("Borrar");
		borrarBtn.setBounds(337, 65, 89, 23);
		contentPane.add(borrarBtn);
		
		JLabel lblCambiarElUsuario = new JLabel("Cambiar el usuario (retorno a la p\u00E1gina principal):");
		lblCambiarElUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCambiarElUsuario.setBounds(10, 232, 261, 14);
		contentPane.add(lblCambiarElUsuario);
		
		JButton btnCambiaruser = new JButton("Cambiar usuario");
		btnCambiaruser.setBounds(281, 229, 113, 23);
		contentPane.add(btnCambiaruser);
	}
	
	private List<String> obtenerDatos(String username){
		//Establecer una coneccion con DB
		ConnectDB connectionDB = ConnectDB.getInstanceProfiles();
		//recibir una lista de resultados
		List<String> datos = connectionDB.funtionsProfiles.getDatos(username);
		return datos;
	}
}