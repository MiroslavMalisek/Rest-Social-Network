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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConsultarLibros extends JFrame {

	private JPanel contentPane;
	
	
	private Profile profile;
	private final JTextArea textArea;
	private JTextField nombreLibro;
	private JTextField autorLibro;
	private JTextField catLibro;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JButton publicarLibroBtn;
	private JLabel lblAadaUnaLectura;
	private JTextField nombreLibroLectura;
	private JTextField calificacion;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton publicarLecturaBtn;
	private JLabel lblEditeUnLibro;
	private JTextField nombreLibroEditar;
	private JTextField autorEditar;
	private JTextField categoriaEditar;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton EditeBtn;
	private JLabel lblElimineUnaLectura;
	private JTextField nombreEliminar;
	private JLabel lblNewLabel_5;
	private JButton EliminarBtn;
	private JLabel lblMostrarltimosLibros;
	private JTextField usuarioMostrar;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JTextField fecha;
	private JLabel lblNewLabel_13;
	private JTextField rangoDesde;
	private JLabel lblNewLabel_14;
	private JTextField rangoHasta;
	private JButton MostrarBtn;
	private JLabel lblCambiarElUsuario;
	private JButton cambioUsuario;
	private JLabel lblNewLabel_15;
	
	private ClientConfig config;
	private Client client;
	private WebTarget target;

	/**
	 * Create the frame.
	 */
	public ConsultarLibros(Profile p) {
		
		config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(getBaseURI());
        
        profile = p;
        
		setTitle("Consultad de libros");
		
		setTitle("Consultas de libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1166, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Seleccione una de las acciones");
		lblNewLabel_3.setBounds(10, 11, 289, 31);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel_3);
		
		
		//Incluir un bloque de texto que contiene scrolls
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea = new JTextArea();
		textArea.setBounds(668, 53, 463, 585);
		textArea.setEditable(false);
		scroll.setBounds(668, 53, 463, 585);
		scroll.setViewportView(textArea);
		getContentPane().add(scroll);
		
		
		JLabel lblNewLabel = new JLabel("A\u0148ada un libro:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 73, 119, 26);
		contentPane.add(lblNewLabel);
		
		nombreLibro = new JTextField();
		nombreLibro.setBounds(139, 78, 142, 20);
		contentPane.add(nombreLibro);
		nombreLibro.setColumns(10);
		
		autorLibro = new JTextField();
		autorLibro.setColumns(10);
		autorLibro.setBounds(291, 78, 142, 20);
		contentPane.add(autorLibro);
		
		catLibro = new JTextField();
		catLibro.setColumns(10);
		catLibro.setBounds(443, 78, 142, 20);
		contentPane.add(catLibro);
		
		lblNewLabel_1 = new JLabel("Nombre del libro");
		lblNewLabel_1.setBounds(139, 53, 119, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Autor");
		lblNewLabel_2.setBounds(291, 53, 49, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_4 = new JLabel("Categor\u00EDa");
		lblNewLabel_4.setBounds(443, 53, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		publicarLibroBtn = new JButton("Publicar");
		publicarLibroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nombreLibro.getText();
				String autor = autorLibro.getText();
				String category = catLibro.getText();
				//comprobar si se han introducido todos los datos
				if(name.isEmpty() || autor.isEmpty() || category.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "No se han introducido todos los datos");
				}
				else {
					//llamar la funcion para Insert libro y comprobar funcionalidad
					int error = InsertLibro(name, autor, category);
					
					if (error == 201){
						JOptionPane.showMessageDialog(contentPane, "Libro ha sido publicado correctamente");
					}
					else if (error == 409){
						JOptionPane.showMessageDialog(contentPane, "Libro ya existe");

					}
					else {
						JOptionPane.showMessageDialog(contentPane, "No se han podido publicar el libro");
					}
					
					//borrar los textFields
					nombreLibro.setText("");
					autorLibro.setText("");
					catLibro.setText("");
				}
			}
		});
		publicarLibroBtn.setBounds(139, 109, 89, 23);
		contentPane.add(publicarLibroBtn);
		
		
		
		
		lblEditeUnLibro = new JLabel("Edite un libro:");
		lblEditeUnLibro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEditeUnLibro.setBounds(10, 163, 119, 26);
		contentPane.add(lblEditeUnLibro);
		
		nombreLibroEditar = new JTextField();
		nombreLibroEditar.setColumns(10);
		nombreLibroEditar.setBounds(139, 168, 142, 20);
		contentPane.add(nombreLibroEditar);
		
		autorEditar = new JTextField();
		autorEditar.setColumns(10);
		autorEditar.setBounds(291, 168, 142, 20);
		contentPane.add(autorEditar);
		
		categoriaEditar = new JTextField();
		categoriaEditar.setColumns(10);
		categoriaEditar.setBounds(443, 168, 142, 20);
		contentPane.add(categoriaEditar);
		
		lblNewLabel_8 = new JLabel("Nombre del libro");
		lblNewLabel_8.setBounds(139, 143, 133, 14);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Autor (no obligatorio)");
		lblNewLabel_9.setBounds(291, 143, 142, 14);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Categor\u00EDa (no obl)");
		lblNewLabel_10.setBounds(443, 143, 142, 14);
		contentPane.add(lblNewLabel_10);
		
		EditeBtn = new JButton("Editar");
		EditeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nombreLibroEditar.getText();
				String autor = autorEditar.getText();
				String category = categoriaEditar.getText();
				int error = 1;
				
				//comprobar combinaciones
				if(name.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Introduzca el nombre del libro a editar");
				}
				else if(autor.isEmpty() && !category.isEmpty()) {
					error = EditLibroCategory(name, category);
				}
				else if(!autor.isEmpty() && category.isEmpty()) {
					error = EditLibroAutor(name, autor);
				}
				else if(!autor.isEmpty() && !category.isEmpty()) {
					error = EditLibroCompleto(name, autor, category);
				}
				else if(autor.isEmpty() && category.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Introduzca al menos un dato para editar");
				}
				
				System.out.println(error);
				//Comprobar el error
				if (error == 201){
					JOptionPane.showMessageDialog(contentPane, "Libro ha sido editado correctamente");
					//borrar los textFields
					nombreLibroEditar.setText("");
					autorEditar.setText("");
					categoriaEditar.setText("");
				}
				else if (error  != 1) {
					JOptionPane.showMessageDialog(contentPane, "No se ha podido editar el libro");
				}
			}
		});
		EditeBtn.setBounds(139, 199, 89, 23);
		contentPane.add(EditeBtn);
		
		
		
		
		
		
		lblAadaUnaLectura = new JLabel("A\u0148ada una lectura:");
		lblAadaUnaLectura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAadaUnaLectura.setBounds(10, 253, 142, 26);
		contentPane.add(lblAadaUnaLectura);
		
		nombreLibroLectura = new JTextField();
		nombreLibroLectura.setColumns(10);
		nombreLibroLectura.setBounds(149, 258, 142, 20);
		contentPane.add(nombreLibroLectura);
		
		calificacion = new JTextField();
		calificacion.setColumns(10);
		calificacion.setBounds(301, 258, 142, 20);
		contentPane.add(calificacion);
		
		lblNewLabel_6 = new JLabel("Nombre del libro");
		lblNewLabel_6.setBounds(149, 233, 133, 14);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Calificaci\u00F3n (x/10)");
		lblNewLabel_7.setBounds(301, 233, 119, 14);
		contentPane.add(lblNewLabel_7);
		
		publicarLecturaBtn = new JButton("Publicar");
		publicarLecturaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nombreLibroLectura.getText();
				String calif = calificacion.getText();
				String user = profile.getUsername();
				int error = 1;
				if (!name.isEmpty() && !calif.isEmpty()) {
					float califFloat = Float.parseFloat(calif);
					error = anadirLectura(user, name, califFloat);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Introduzca todos los datos");
				}
				
				//Comprobar error
				if (error == 201){
					JOptionPane.showMessageDialog(contentPane, "Lectura ha sido publicado correctamente");
					//borrar los textFields
					nombreLibroLectura.setText("");
					calificacion.setText("");
				}
				else if (error == 409){
					JOptionPane.showMessageDialog(contentPane, "Esta lectura ya existe");

				}
				else if (error == 404) {
					JOptionPane.showMessageDialog(contentPane, "El libro no existe. Cree lo primero");
				}
				else if (error != 1) {
					JOptionPane.showMessageDialog(contentPane, "No se ha podido publicar la lectura");
				}
			}
		});
		publicarLecturaBtn.setBounds(149, 289, 89, 23);
		contentPane.add(publicarLecturaBtn);
		
		
		
		
		lblElimineUnaLectura = new JLabel("Elimine una lectura:");
		lblElimineUnaLectura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblElimineUnaLectura.setBounds(10, 343, 142, 26);
		contentPane.add(lblElimineUnaLectura);
		
		nombreEliminar = new JTextField();
		nombreEliminar.setColumns(10);
		nombreEliminar.setBounds(149, 348, 142, 20);
		contentPane.add(nombreEliminar);
		
		lblNewLabel_5 = new JLabel("Nombre del libro");
		lblNewLabel_5.setBounds(149, 323, 132, 14);
		contentPane.add(lblNewLabel_5);
		
		EliminarBtn = new JButton("Eliminar");
		EliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nombreEliminar.getText();
				int error = eliminarLectura(name);
				//comprobar error
				if(error == 200) {
					JOptionPane.showMessageDialog(contentPane, "Lectura ha sido eliminada correctamente");
					//borrar los textFields
					nombreEliminar.setText("");
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Lectura no se ha podido eliminar");
				}
			}
		});
		EliminarBtn.setBounds(149, 379, 89, 23);
		contentPane.add(EliminarBtn);
		
		
		
		
		
		lblMostrarltimosLibros = new JLabel("Mostrar los libros le\u00EDdos por un usuario:");
		lblMostrarltimosLibros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMostrarltimosLibros.setBounds(10, 441, 271, 26);
		contentPane.add(lblMostrarltimosLibros);
		
		usuarioMostrar = new JTextField();
		usuarioMostrar.setColumns(10);
		usuarioMostrar.setBounds(20, 503, 142, 20);
		contentPane.add(usuarioMostrar);
		
		lblNewLabel_11 = new JLabel("Usuario");
		lblNewLabel_11.setBounds(20, 478, 89, 14);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Desde la fecha (no obl)");
		lblNewLabel_12.setBounds(172, 478, 142, 14);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_15 = new JLabel("(YYYY-MM-DD)");
		lblNewLabel_15.setBounds(172, 534, 133, 14);
		contentPane.add(lblNewLabel_15);
		
		fecha = new JTextField();
		fecha.setColumns(10);
		fecha.setBounds(172, 503, 142, 20);
		contentPane.add(fecha);
		
		lblNewLabel_13 = new JLabel("Rango desde (no obl)");
		lblNewLabel_13.setBounds(324, 478, 142, 14);
		contentPane.add(lblNewLabel_13);
		
		rangoDesde = new JTextField();
		rangoDesde.setColumns(10);
		rangoDesde.setBounds(324, 503, 142, 20);
		contentPane.add(rangoDesde);
		
		lblNewLabel_14 = new JLabel("Rango hasta (no obl)");
		lblNewLabel_14.setBounds(476, 478, 142, 14);
		contentPane.add(lblNewLabel_14);
		
		rangoHasta = new JTextField();
		rangoHasta.setColumns(10);
		rangoHasta.setBounds(476, 503, 142, 20);
		contentPane.add(rangoHasta);
		
		MostrarBtn = new JButton("Mostrar");
		MostrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuarioConsultar = usuarioMostrar.getText();
				String date = fecha.getText();
				String desde = rangoDesde.getText();
				String hasta = rangoHasta.getText();
				//anadir a la fecha el tiempo de medianoche de ese d\EDa
				if (!date.isEmpty()) {
					date = date.concat(" 00:00:00");
				}
				//Combrobar todas las opciones
				if(usuarioConsultar.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Introduzca el nombre del usuario a consultar");
				}
				else if(!date.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
					int desdeInt = Integer.parseInt(desde);
					int hastaInt = Integer.parseInt(hasta);
					LecturasUsuarioList lecturas = getLecturasDateDesdeHasta(usuarioConsultar, date, desdeInt, hastaInt);
					printLecturas(lecturas);

				}
				else if(!date.isEmpty() && !desde.isEmpty() && hasta.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Combinacion invalilida, introduzca hasta");
				}
				else if(!date.isEmpty() && desde.isEmpty() && !hasta.isEmpty()) {
					int desdeInt = 0;
					int hastaInt = Integer.parseInt(hasta);
					LecturasUsuarioList lecturas = getLecturasDateDesdeHasta(usuarioConsultar, date, desdeInt, hastaInt);
					printLecturas(lecturas);

				}
				else if(!date.isEmpty() && desde.isEmpty() && hasta.isEmpty()) {
					//cambiar desde y hasta a -1 que es un numero que no se puede introducir a SQL como LIMIT y se puede usar para identificar que hay que devolver toda las lecturas
					int desdeInt = -1;
					int hastaInt = -1;
					LecturasUsuarioList lecturas = getLecturasDateDesdeHasta(usuarioConsultar, date, desdeInt, hastaInt);
					printLecturas(lecturas);

				}
				else if(date.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
					//cambiar date a -1 que consideramos un formato no v\E1lido y se puede usar para identificar que hay que devolver las lecturas sin restringir fecha
					date = "-1";
					int desdeInt = Integer.parseInt(desde);
					int hastaInt = Integer.parseInt(hasta);
					LecturasUsuarioList lecturas = getLecturasDateDesdeHasta(usuarioConsultar, date, desdeInt, hastaInt);
					printLecturas(lecturas);

				}
				else if(date.isEmpty() && !desde.isEmpty() && hasta.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Combinacion invalida, introduzca hasta");
				}
				else if(date.isEmpty() && desde.isEmpty() && !hasta.isEmpty()) {
					//cambiar date a -1 que consideramos un formato no v\E1lido y se puede usar para identificar que hay que devolver las lecturas sin restringir fecha, solo hasta
					date = "-1";
					int desdeInt = 0;
					int hastaInt = Integer.parseInt(hasta);
					LecturasUsuarioList lecturas = getLecturasDateDesdeHasta(usuarioConsultar, date, desdeInt, hastaInt);
					printLecturas(lecturas);
				}
				else if(date.isEmpty() && desde.isEmpty() && hasta.isEmpty()) {
					//cambiar date a -1 que es un numero que no se puede introducir a SQL y se puede usar para identificar que hay que devolver las lecturas sin restringir nada
					date = "-1";
					int desdeInt = -1;
					int hastaInt = -1;
					LecturasUsuarioList lecturas = getLecturasDateDesdeHasta(usuarioConsultar, date, desdeInt, hastaInt);
					printLecturas(lecturas);
				}
			}
		});
		MostrarBtn.setBounds(30, 534, 89, 23);
		contentPane.add(MostrarBtn);
		
		
		
		
		lblCambiarElUsuario = new JLabel("Cambiar el usuario (retorno a la p\u00E1gina principal):");
		lblCambiarElUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCambiarElUsuario.setBounds(10, 607, 335, 31);
		contentPane.add(lblCambiarElUsuario);
		
		cambioUsuario = new JButton("Cambiar usuario");
		cambioUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ir a la pagina principal
				contentPane.setVisible(false);
				dispose();
				LoginFormStart.main(null);
			}
		});
		cambioUsuario.setBounds(358, 613, 133, 23);
		contentPane.add(cambioUsuario);
		
		
		
		
		JLabel lblNewLabel_4_1 = new JLabel("Resultados");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(668, 17, 112, 22);
		contentPane.add(lblNewLabel_4_1);
		
		JButton borrarRstBtn = new JButton("Borrar resultados");
		borrarRstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Simplemente borrar el bloque de texto
				textArea.setText("");
			}
		});
		borrarRstBtn.setBounds(790, 18, 142, 23);
		contentPane.add(borrarRstBtn);
		
		
	}

	
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/RESTSocialNetwork/api/").build();
    }
	
	//M\E9todo para insertar un nuevo libro
	private int InsertLibro(String name, String autor, String category) {
		
		LecturaUsuario l = new LecturaUsuario();
		l.setNameLibro(name);
		l.setAutorLibro(autor);
		l.setCategoriaLibro(category);
		Response response = target.path(profile.getUsername()).path("consultar_libros")
		        .request()
		        .post(Entity.json(l));
		return response.getStatus();
	}
	
	//Metodo para editar la categoria de un libro
	private int EditLibroCategory(String name, String category) {
		LecturaUsuario l = new LecturaUsuario();
		l.setCategoriaLibro(category);
		Response response = target.path(profile.getUsername()).path("consultar_libros").path(name)
		        .request()
		        .put(Entity.json(l));
		return response.getStatus();
		
	}
	
	//M\E9todo para editar el autor de un libro
	private int EditLibroAutor(String name, String autor) {
		LecturaUsuario l = new LecturaUsuario();
		l.setAutorLibro(autor);
		Response response = target.path(profile.getUsername()).path("consultar_libros").path(name)
		        .request()
		        .put(Entity.json(l));
		return response.getStatus();
	}
	
	//M\E9todo para editar todo el libro
	private int EditLibroCompleto(String name, String autor, String category) {
		LecturaUsuario l = new LecturaUsuario();
		l.setCategoriaLibro(category);
		l.setAutorLibro(autor);
		Response response = target.path(profile.getUsername()).path("consultar_libros").path(name)
		        .request()
		        .put(Entity.json(l));
		return response.getStatus();
	}
	
	//M\E9todo para anadir una lectura de un usuario
	private int anadirLectura(String user, String name, float calif) {
		LecturaUsuario l = new LecturaUsuario();
		l.setNameLibro(name);
		l.setCalificacion(calif);
		Response response = target.path(profile.getUsername()).path("consultar_libros").path("lectura")
		        .request()
		        .post(Entity.json(l));
		return response.getStatus();
	}
	
	//M\E9todo para anadir una lectura de un usuario
	private int eliminarLectura(String name) {
		Response response = target.path(profile.getUsername()).path("consultar_libros").path("lectura").path(name)
		        .request()
		        .delete();
		return response.getStatus();
	}
	
	//M\E9todo para recibir una lectura del usuario (completa)
	private LecturasUsuarioList getLecturasDateDesdeHasta(String usuario, String date, int desde, int hasta) {
		LecturasUsuarioList lu = target.path(profile.getUsername()).path("consultar_libros").path(usuario)
				.queryParam("date", date).queryParam("limit", desde).queryParam("offset", hasta)
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(LecturasUsuarioList.class);
		return lu;
	}
	
	//M\E9todo que imprime las lecturas de un usuario
	private void printLecturas(LecturasUsuarioList lecturas) {
		Iterator<LecturaUsuario> i = lecturas.getLecturasList().iterator();
		if(i.hasNext()) {
			while(i.hasNext()) {
				LecturaUsuario l = i.next();
				textArea.append(l.getNameLibro() + "\t" + l.getAutorLibro() + "\t " + l.getCategoriaLibro() + "\t " + l.getCalificacion() + "\n");
			}
		}
		else {
			textArea.append("No se ha encontrado ningua lectura..." + "\n");
		}
	}
	
	
}