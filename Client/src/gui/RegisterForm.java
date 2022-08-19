package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.datos.*;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterForm extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField age;
	
	private ClientConfig config;
	private Client client;
	private WebTarget target;

	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		
		config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(getBaseURI());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduzca tus datos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 35, 399, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 73, 124, 27);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		name.setColumns(10);
		name.setBounds(144, 77, 134, 20);
		contentPane.add(name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Correo");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 109, 124, 27);
		contentPane.add(lblNewLabel_1_1);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 12));
		email.setColumns(10);
		email.setBounds(144, 113, 134, 20);
		contentPane.add(email);
		
		JLabel lblNewLabel_1_2 = new JLabel("Edad");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(10, 147, 124, 27);
		contentPane.add(lblNewLabel_1_2);
		
		age = new JTextField();
		age.setFont(new Font("Tahoma", Font.PLAIN, 12));
		age.setColumns(10);
		age.setBounds(144, 151, 134, 20);
		contentPane.add(age);
		
		JButton enviar = new JButton("Enviar");
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = name.getText();
				String correo = email.getText();
				String edad = age.getText();
				
				//comprobar si se han introducido todos los datos
				if (username.isEmpty() || correo.isEmpty() || edad.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Introduzca todos los datos");
				}
				else {
					int edadInt = Integer.parseInt(edad);
					//Establecer una coneccion con DB para crear nuevo usuario
					Profile p = new Profile();
					p.setUsername(username);
					p.setEmail(correo);
					p.setAge(edadInt);
					Response response = target.path("register")
					        .request()
					        .post(Entity.json(p));
					
					//comprobar si se ha creado correctamente
					if (response.getStatus() == 201){
						JOptionPane.showMessageDialog(contentPane, "Registracion se ha creado correctamente");
						//ir a la pagina principal
						contentPane.setVisible(false);
						dispose();
						LoginFormStart.main(null);
					}
					else if (response.getStatus() == 204){
						JOptionPane.showMessageDialog(contentPane, "Este username ya existe");
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "No se ha podido completar la registracion");
					}
				}
			}
		});
		enviar.setFont(new Font("Tahoma", Font.BOLD, 12));
		enviar.setBounds(167, 197, 89, 23);
		contentPane.add(enviar);
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/RESTSocialNetwork/api/").build();
    }

}