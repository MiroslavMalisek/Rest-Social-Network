package gui;


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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ConsultarUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioDatos;
	private JTextField correoCambio;
	private JTextField edadCambio;
	private JTextField filtroPatron;
	
	private Profile profile;
	private ClientConfig config;
	private Client client;
	private WebTarget target;

	/**
	 * Create the frame.
	 */
	public ConsultarUsuarios(Profile p) {
		
		profile = p;
		config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(getBaseURI());
		
		setTitle("Consultas del usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 665, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_3 = new JLabel("Seleccione una de las acciones");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 11, 289, 31);
		contentPane.add(lblNewLabel_3);
		
		
		//Incluir un bloque de texto que contiene scrolls
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 345, 630, 256);
		textArea.setEditable(false);
		scroll.setBounds(10, 345, 630, 256);
		scroll.setViewportView(textArea);
		getContentPane().add(scroll);
		
		
		
		JLabel lblNewLabel = new JLabel("Ver datos del usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 63, 147, 31);
		contentPane.add(lblNewLabel);
		
		usuarioDatos = new JTextField();
		usuarioDatos.setForeground(Color.BLACK);
		usuarioDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuarioDatos.setBounds(167, 70, 157, 20);
		contentPane.add(usuarioDatos);
		usuarioDatos.setColumns(10);
		
		JButton verUsuarioBtn = new JButton("Ver");
		verUsuarioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = usuarioDatos.getText();
				Response r = target.path(profile.getUsername()).path("consultar_usuarios").path(name)
				        .request().accept(MediaType.APPLICATION_XML)
				        .get(Response.class);
				Profile p = target.path(profile.getUsername()).path("consultar_usuarios").path(name)
				        .request().accept(MediaType.APPLICATION_XML)
				        .get(Profile.class);
				
				//Check si el usuario deseado existe
				if(r.getStatus() == 200){
					textArea.append(p.getUsername() + " " + p.getEmail() + " " + p.getAge() + "\n");
				}
				else {
						textArea.append("No se ha encontrado este usuario..." + "\n");
				}
			}
		});
		verUsuarioBtn.setBounds(334, 69, 89, 23);
		contentPane.add(verUsuarioBtn);
		
		
		
		
		JLabel lblCambiarMisDatos = new JLabel("Cambiar mis datos:");
		lblCambiarMisDatos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCambiarMisDatos.setBounds(10, 139, 147, 31);
		contentPane.add(lblCambiarMisDatos);
		
		JLabel lblNewLabel_1 = new JLabel("Correo (no obligatorio)");
		lblNewLabel_1.setBounds(167, 131, 157, 14);
		contentPane.add(lblNewLabel_1);
		
		correoCambio = new JTextField();
		correoCambio.setForeground(Color.BLACK);
		correoCambio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		correoCambio.setColumns(10);
		correoCambio.setBounds(167, 146, 157, 20);
		contentPane.add(correoCambio);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edad (no obligatorio)");
		lblNewLabel_1_1.setBounds(334, 131, 148, 14);
		contentPane.add(lblNewLabel_1_1);
		
		edadCambio = new JTextField();
		edadCambio.setForeground(Color.BLACK);
		edadCambio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		edadCambio.setColumns(10);
		edadCambio.setBounds(334, 146, 157, 20);
		contentPane.add(edadCambio);
		
		JButton cambioDatosBtn = new JButton("Cambiar");
		cambioDatosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = profile.getUsername();
				String email = correoCambio.getText();
				String age = edadCambio.getText();
				int ageInt;
				//check los datos de entrada
				if(email.isEmpty() && age.isEmpty()) {
					email = profile.getEmail();
					ageInt = profile.getAge();
				}
				else if(email.isEmpty()) {
					email = profile.getEmail();
					ageInt = Integer.parseInt(age);
				}
				else if(age.isEmpty()) {
					ageInt = profile.getAge();
				}
				else {
					ageInt = Integer.parseInt(age);
				}
				
				//llamar la funcion para Update profile y comprobar funcionalidad
				
				
				int error = updateProfile(username, email, ageInt);
				System.out.println(error);
				if (error == 201){
					JOptionPane.showMessageDialog(contentPane, "Datos se han cambiado correctamente");
					profile.setUsername(username);
					profile.setEmail(email);
					profile.setAge(ageInt);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No se han podido cambiar los datos");
				}
				
				//borrar los textFields
				correoCambio.setText("");
				edadCambio.setText("");
			}
		});
		cambioDatosBtn.setBounds(501, 145, 89, 23);
		contentPane.add(cambioDatosBtn);
		
		
		
		
		JLabel lblBorrarMiPerfil = new JLabel("Borrar mi perfil:");
		lblBorrarMiPerfil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBorrarMiPerfil.setBounds(10, 193, 147, 31);
		contentPane.add(lblBorrarMiPerfil);
		
		JButton borrarBtn = new JButton("Borrar");
		borrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error = deleteProfile();
				
				if (error == 204){
					JOptionPane.showMessageDialog(contentPane, "Perfil se ha borrado correctamente");
					//ir a la pagina principal ya que el usuario ya no existe
					contentPane.setVisible(false);
					dispose();
					LoginFormStart.main(null);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No se ha podido borrar el perfil");
				}
				
			}
		});
		borrarBtn.setBounds(167, 199, 89, 23);
		contentPane.add(borrarBtn);
		
		
		
		
		JLabel lblObtenerTodosLos = new JLabel("Ver todos los usuarios:");
		lblObtenerTodosLos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblObtenerTodosLos.setBounds(10, 257, 193, 31);
		contentPane.add(lblObtenerTodosLos);
		
		filtroPatron = new JTextField();
		filtroPatron.setBounds(213, 264, 198, 20);
		contentPane.add(filtroPatron);
		filtroPatron.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Filtrar por patr\u00F3n (no obligatorio)");
		lblNewLabel_2.setBounds(213, 246, 198, 14);
		contentPane.add(lblNewLabel_2);

		JButton getUsuariosBtn = new JButton("Obtener");
		getUsuariosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String patron = filtroPatron.getText();
				ProfilesList profiles;
				//chceck si hay que buscar por un patron
				if(patron.isEmpty()) {
					profiles = getTodosProfiles();
				}
				else {
					profiles = getTodosProfilesPatron(patron);
				}
				
				Iterator<Profile> i = profiles.getProfilesList().iterator();
				if(i.hasNext()) {
					while(i.hasNext()) {
						Profile p = i.next();
						textArea.append(p.getUsername() + "\n");
					}
				}
				else {
					textArea.append("No se ha encontrado ningun usuario..." + "\n");
				}
				
				
				
			}
		});
		getUsuariosBtn.setBounds(421, 263, 89, 23);
		contentPane.add(getUsuariosBtn);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Resultados");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 312, 100, 22);
		contentPane.add(lblNewLabel_4);
		
		JButton borrarRstBtn = new JButton("Borrar resultados");
		borrarRstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Simplemente borrar el bloque de texto
				textArea.setText("");
			}
		});
		borrarRstBtn.setBounds(114, 314, 142, 23);
		contentPane.add(borrarRstBtn);
		
		
		
		
		JLabel lblCambiarElUsuario = new JLabel("Cambiar el usuario (retorno a la p\u00E1gina principal):");
		lblCambiarElUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCambiarElUsuario.setBounds(10, 624, 335, 31);
		contentPane.add(lblCambiarElUsuario);
		
		JButton cambioUsuario = new JButton("Cambiar usuario");
		cambioUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ir a la pagina principal
				contentPane.setVisible(false);
				dispose();
				LoginFormStart.main(null);
			}
		});
		cambioUsuario.setBounds(358, 630, 133, 23);
		contentPane.add(cambioUsuario);
		
	}
	
	
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/RESTSocialNetwork/api/").build();
    }
	
	
	
	
	//metodo para update el perfil
	private int updateProfile(String name, String email, int age) {
		
		Profile p = new Profile();
		p.setUsername(name);
		p.setEmail(email);
		p.setAge(age);
		Response response = target.path(p.getUsername()).path("consultar_usuarios")
		        .request()
		        .put(Entity.json(p));
		return response.getStatus();
	}
	
	//metodo para Delete el perfil
	private int deleteProfile() {
		Response response = target.path(profile.getUsername()).path("consultar_usuarios")
		        .request()
		        .delete();
		return response.getStatus();
	}
	
	//metodo para get todos los usuarios, sin patron
	private ProfilesList getTodosProfiles(){
		ProfilesList pl = target.path(profile.getUsername()).path("consultar_usuarios")
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(ProfilesList.class);
		return pl;
	}
	
	//metodo para get todos los usuarios con patron
	private ProfilesList getTodosProfilesPatron(String patron){
		
		ProfilesList pl = target.path(profile.getUsername()).path("consultar_usuarios").queryParam("patron", patron)
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(ProfilesList.class);
		return pl;
	}
	
}