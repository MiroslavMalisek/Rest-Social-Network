package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

import clase.datos.*;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFormStart {

	private JFrame frame;
	private JTextField username;
	
	private ClientConfig config;
	private Client client;
	private WebTarget target;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFormStart window = new LoginFormStart();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFormStart() {
		config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(getBaseURI());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduzca su nombre de usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(97, 69, 258, 33);
		frame.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(143, 113, 149, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				Response r = target.path("login").path(name)
				        .request().accept(MediaType.APPLICATION_JSON)
				        .get(Response.class);
				
				Profile p = target.path("login").path(name)
				        .request().accept(MediaType.APPLICATION_XML)
				        .get(Profile.class);
				if(r.getStatus() == 200) {
					//Abrir la ventana con el menu del usuario
					UserMenu um = new UserMenu(p);
					frame.dispose();
					um.setVisible(true);
				}
				else if(r.getStatus() == 404) {
					JOptionPane.showMessageDialog(frame, "User not found...");
					username.setText("");
				}
				else {
					JOptionPane.showMessageDialog(frame, "No se ha podido realizar login");
					username.setText("");
				}
				
				
			}
		});
		btnNewButton.setBounds(159, 144, 119, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reg\u00EDstrate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abrir la ventana con el formulario para registrarse
				RegisterForm rs = new RegisterForm();
				frame.dispose();
				rs.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(298, 217, 108, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/RESTSocialNetwork/api/").build();
    }
}
