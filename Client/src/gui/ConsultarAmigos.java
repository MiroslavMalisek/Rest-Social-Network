package gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultarAmigos extends JFrame {
	
	private Profile profile;


	private JPanel contentPane;
	private final JTextArea textArea;
	private JTextField amigoAnadir;
	private JTextField eliminarAmigo;
	private JTextField verAmigos;
	private JTextField nombreAmigo;
	private JTextField fecha;
	private JTextField desdeRango;
	private JTextField hastaRango;
	private JTextField buscarCal;
	private JTextField buscarAutor;
	private JTextField buscarCat;
	
	private ClientConfig config;
	private Client client;
	private WebTarget target;

	/**
	 * Create the frame.
	 */
	public ConsultarAmigos(Profile p) {
		setTitle("Consultas de amigos");
		
		config = new ClientConfig();
        client = ClientBuilder.newClient(config);
        target = client.target(getBaseURI());
		
		profile = p;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1166, 730);
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
		textArea = new JTextArea();
		textArea.setBounds(668, 53, 463, 585);
		textArea.setEditable(false);
		scroll.setBounds(668, 53, 463, 585);
		scroll.setViewportView(textArea);
		getContentPane().add(scroll);
		
		
		
		JLabel lblAadaUnAmigo = new JLabel("A\u0148ada un amigo:");
		lblAadaUnAmigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAadaUnAmigo.setBounds(10, 73, 119, 26);
		contentPane.add(lblAadaUnAmigo);
		
		amigoAnadir = new JTextField();
		amigoAnadir.setColumns(10);
		amigoAnadir.setBounds(149, 78, 142, 20);
		contentPane.add(amigoAnadir);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre del usuario");
		lblNewLabel_1.setBounds(149, 53, 119, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton anadirBtn = new JButton("A\u0148adir");
		anadirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amigo = amigoAnadir.getText();
				String user = profile.getUsername();
				if(amigo.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Introduzca el nombre del usuario");
				}
				else {
					//llamar la funcion para Insert amigo y comprobar funcionalidad
					int error = anadirAmigo(user, amigo);
					if (error == 201){
						JOptionPane.showMessageDialog(contentPane, "Amigo se ha anadido correctamente");
					}
					else if (error == 409){
						JOptionPane.showMessageDialog(contentPane, "Este usuario ya es su amigo");

					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Este usuario no existe");
					}
					
					//borrar los textFields
					amigoAnadir.setText("");
				}
			}
		});
		anadirBtn.setBounds(311, 77, 89, 23);
		contentPane.add(anadirBtn);
		
		
		
		
		JLabel lblElimineUnAmigo = new JLabel("Elimine un amigo:");
		lblElimineUnAmigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblElimineUnAmigo.setBounds(10, 141, 142, 26);
		contentPane.add(lblElimineUnAmigo);
		
		eliminarAmigo = new JTextField();
		eliminarAmigo.setColumns(10);
		eliminarAmigo.setBounds(149, 146, 142, 20);
		contentPane.add(eliminarAmigo);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del amigo");
		lblNewLabel_1_1.setBounds(149, 121, 119, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton elimineBtn = new JButton("Elimine");
		elimineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amigo = eliminarAmigo.getText();
				if(amigo.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Introduzca el nombre del amigo");
				}
				else {
					//llamar la funcion para eliminar amigo y comprobar funcionalidad
					int error = eliminarAmigo(amigo);
					if(error == 200) {
						JOptionPane.showMessageDialog(contentPane, "Amigo ha sido eliminada correctamente");
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Amigo no se ha podido eliminar");
					}
					
					//borrar los textFields
					eliminarAmigo.setText("");
				}
			}
		});
		elimineBtn.setBounds(311, 145, 89, 23);
		contentPane.add(elimineBtn);
		
		
		
		
		JLabel lblObtenerTodosMis = new JLabel("Ver todos mis amigos:");
		lblObtenerTodosMis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblObtenerTodosMis.setBounds(10, 206, 193, 31);
		contentPane.add(lblObtenerTodosMis);
		
		verAmigos = new JTextField();
		verAmigos.setColumns(10);
		verAmigos.setBounds(213, 213, 198, 20);
		contentPane.add(verAmigos);
		
		JLabel lblNewLabel_2 = new JLabel("Filtrar por patr\u00F3n (no obligatorio)");
		lblNewLabel_2.setBounds(213, 195, 198, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton verBtn = new JButton("Ver");
		verBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String patron = verAmigos.getText();
				LecturasAmigoList amigos;
				//chceck si hay que buscar por un patron
				if(patron.isEmpty()) {
					amigos = verTodosAmigos();
				}
				else {
					amigos = verTodosAmigosPatron(patron);
				}
				
				//Imprimir la lista de los usuarios devueltos
				Iterator<LecturaAmigo> i = amigos.getLecturasList().iterator();
				if(i.hasNext()) {
					while(i.hasNext()) {
						LecturaAmigo p = i.next();
						textArea.append(p.getNameAmigo() + "\n");
					}
				}
				else {
					textArea.append("No se ha encontrado ningun usuario..." + "\n");
				}
				
			}
		});
		verBtn.setBounds(421, 212, 89, 23);
		contentPane.add(verBtn);
		
		
		
		
		
		JLabel lblMostrarltimosLibros = new JLabel("Mostrar los libros le\u00EDdos por amigos:");
		lblMostrarltimosLibros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMostrarltimosLibros.setBounds(10, 266, 271, 26);
		contentPane.add(lblMostrarltimosLibros);
		
		nombreAmigo = new JTextField();
		nombreAmigo.setColumns(10);
		nombreAmigo.setBounds(20, 328, 142, 20);
		contentPane.add(nombreAmigo);
		
		JLabel lblNewLabel_11 = new JLabel("Nombre del amigo");
		lblNewLabel_11.setBounds(20, 303, 109, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_11_1 = new JLabel("<html>* Dejar vacio para<br/>mostrar todos</html>");
		lblNewLabel_11_1.setBounds(20, 352, 132, 43);
		contentPane.add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_12 = new JLabel("Desde la fecha (no obl)");
		lblNewLabel_12.setBounds(172, 303, 142, 14);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_15 = new JLabel("(YYYY-MM-DD)");
		lblNewLabel_15.setBounds(172, 359, 133, 14);
		contentPane.add(lblNewLabel_15);
		
		fecha = new JTextField();
		fecha.setColumns(10);
		fecha.setBounds(172, 328, 142, 20);
		contentPane.add(fecha);
		
		JLabel lblNewLabel_13 = new JLabel("Rango desde (no obl)");
		lblNewLabel_13.setBounds(324, 303, 142, 14);
		contentPane.add(lblNewLabel_13);
		
		desdeRango = new JTextField();
		desdeRango.setColumns(10);
		desdeRango.setBounds(324, 328, 142, 20);
		contentPane.add(desdeRango);
		
		JLabel lblNewLabel_14 = new JLabel("Rango hasta (no obl)");
		lblNewLabel_14.setBounds(476, 303, 142, 14);
		contentPane.add(lblNewLabel_14);
		
		hastaRango = new JTextField();
		hastaRango.setColumns(10);
		hastaRango.setBounds(476, 328, 142, 20);
		contentPane.add(hastaRango);
		
		JButton mostrarBtn = new JButton("Mostrar");
		mostrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amigoConsultar = nombreAmigo.getText();
				String date = fecha.getText();
				String desde = desdeRango.getText();
				String hasta = hastaRango.getText();
				//anadir a la fecha el tiempo de medianoche de ese día
				if (!date.isEmpty()) {
					date = date.concat(" 00:00:00");
				}
				LecturasAmigoList lecturas = decidirAmigoLectura(amigoConsultar, date, desde, hasta);
				printLecturas(lecturas);
			}
		});
		mostrarBtn.setBounds(20, 407, 89, 23);
		contentPane.add(mostrarBtn);
		
		
		
		
		
		JLabel lblBuscar = new JLabel("Buscar libros recomendados por mis amigos");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBuscar.setBounds(10, 464, 345, 26);
		contentPane.add(lblBuscar);
		
		JLabel lblNewLabel_12_1_1 = new JLabel("Calificaci\u00F3n minimal");
		lblNewLabel_12_1_1.setBounds(20, 501, 142, 14);
		contentPane.add(lblNewLabel_12_1_1);
		
		buscarCal = new JTextField();
		buscarCal.setColumns(10);
		buscarCal.setBounds(20, 526, 142, 20);
		contentPane.add(buscarCal);
		
		JLabel lblNewLabel_12_1 = new JLabel("Autor");
		lblNewLabel_12_1.setBounds(172, 501, 142, 14);
		contentPane.add(lblNewLabel_12_1);
		
		buscarAutor = new JTextField();
		buscarAutor.setColumns(10);
		buscarAutor.setBounds(172, 526, 142, 20);
		contentPane.add(buscarAutor);
		
		JLabel lblNewLabel_13_1 = new JLabel("Categor\u00EDa");
		lblNewLabel_13_1.setBounds(324, 501, 142, 14);
		contentPane.add(lblNewLabel_13_1);
		
		buscarCat = new JTextField();
		buscarCat.setColumns(10);
		buscarCat.setBounds(324, 526, 142, 20);
		contentPane.add(buscarCat);
		
		JLabel lblNewLabel = new JLabel("*Al menos un campo obligatorio");
		lblNewLabel.setBounds(20, 557, 198, 14);
		contentPane.add(lblNewLabel);
		
		JButton BuscarBtn = new JButton("Buscar");
		BuscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String calificacion = buscarCal.getText();
				String autor = buscarAutor.getText();
				String categoria = buscarCat.getText();
				
				LecturasAmigoList lectRecomendaciones = decidirRecomendaciones(calificacion, autor, categoria);
				printLecturas(lectRecomendaciones);
				
			}
		});
		BuscarBtn.setBounds(476, 525, 89, 23);
		contentPane.add(BuscarBtn);
		
		
		
		
		JLabel lblCambiarElUsuario = new JLabel("Cambiar el usuario (retorno a la p\u00E1gina principal):");
		lblCambiarElUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCambiarElUsuario.setBounds(10, 607, 335, 31);
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
		cambioUsuario.setBounds(358, 613, 133, 23);
		contentPane.add(cambioUsuario);
		
		
		
		
		
		JLabel lblNewLabel_4_1 = new JLabel("Resultados");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(668, 18, 112, 22);
		contentPane.add(lblNewLabel_4_1);
		
		JButton borrarRstBtn = new JButton("Borrar resultados");
		borrarRstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Simplemente borrar el bloque de texto
				textArea.setText("");
			}
		});
		borrarRstBtn.setBounds(790, 19, 142, 23);
		contentPane.add(borrarRstBtn);
		
		
	}
	
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/RESTSocialNetwork/api/").build();
    }
	
	
	
	//Método para anadir un nuevo amigo
	private int anadirAmigo(String user, String amigo) {
		LecturaAmigo l = new LecturaAmigo();
		l.setNameAmigo(amigo);
		Response response = target.path(profile.getUsername()).path("consultar_amigos")
		        .request()
		        .post(Entity.json(l));
		return response.getStatus();
	}
	
	//Método para eliminar un amigo
	private int eliminarAmigo(String amigo) {
		Response response = target.path(profile.getUsername()).path("consultar_amigos").path("amigos").path(amigo)
		        .request()
		        .delete();
		return response.getStatus();
	}
	
	//método para get todos los usuarios, sin patron
	private LecturasAmigoList verTodosAmigos(){
		LecturasAmigoList pl = target.path(profile.getUsername()).path("consultar_amigos")
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(LecturasAmigoList.class);
		return pl;
	}
	
	//método para get todos los usuarios con patron
	private LecturasAmigoList verTodosAmigosPatron(String patron){
		LecturasAmigoList pl = target.path(profile.getUsername()).path("consultar_amigos").queryParam("patron", patron)
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(LecturasAmigoList.class);
		return pl;
	}
	
	//Método para recibir las lecturas de todos los amigos (completa)
	private LecturasAmigoList getLecturasTodasDateDesdeHasta(String date, int desde, int hasta) {
		LecturasAmigoList la = target.path(profile.getUsername()).path("consultar_amigos").path("lecturas")
				.queryParam("date", date).queryParam("limit", desde).queryParam("offset", hasta)
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(LecturasAmigoList.class);
		return la;
	}
	
	//Método para recibir las lecturas de uno de los amigos (completa)
	
	private LecturasAmigoList getLecturasAmigoDateDesdeHasta(String amigo, String date, int desde, int hasta) {
		LecturasAmigoList la = target.path(profile.getUsername()).path("consultar_amigos").path("lecturas").path(amigo)
				.queryParam("date", date).queryParam("limit", desde).queryParam("offset", hasta)
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(LecturasAmigoList.class);
		return la;
	}
	//Método para obtener las recomendaciones
	private LecturasAmigoList getRecomendaciones(String calificacion, String autor, String categoria) {
		LecturasAmigoList la = target.path(profile.getUsername()).path("consultar_amigos").path("recomendations")
				.queryParam("calificacion", calificacion).queryParam("autor", autor).queryParam("category", categoria)
				.request()
				.accept(MediaType.APPLICATION_XML)
		        .get(LecturasAmigoList.class);
		return la;
	}
	
	//método que decide la accion al querer consultar los libros leidos por nuestros amigos
	private LecturasAmigoList decidirAmigoLectura(String amigo, String date, String desde, String hasta){
		
		int desdeInt = 0;
		int hastaInt = 0;
		
		if(amigo.isEmpty()) {
			//Comprobar todas las posibilidades cuando no se ha introducido ningun amigo
			
			if(!date.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
				desdeInt = Integer.parseInt(desde);
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(!date.isEmpty() && !desde.isEmpty() && hasta.isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Combinacion inválida, introduzca hasta");
			}
			
			else if(!date.isEmpty() && desde.isEmpty() && !hasta.isEmpty()) {
				desdeInt = 0;
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(!date.isEmpty() && desde.isEmpty() && hasta.isEmpty()) {
				//cambiar desde y hasta a -1 que es un numero que no se puede introducir a SQL como LIMIT y se puede usar para identificar que hay que devolver todas las lecturas
				desdeInt = -1;
				hastaInt = -1;
			}
			
			else if(date.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
				//cambiar date a -1 que consideramos un formato no válido y se puede usar para identificar que hay que devolver las lecturas sin restringir fecha
				date = "-1";
				desdeInt = Integer.parseInt(desde);
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(date.isEmpty() && !desde.isEmpty() && hasta.isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Combinacion inválida, introduzca hasta");
			}
			
			else if(date.isEmpty() && desde.isEmpty() && !hasta.isEmpty()) {
				//cambiar date a -1 que consideramos un formato no válido y se puede usar para identificar que hay que devolver las lecturas sin restringir fecha, solo hasta
				date = "-1";
				desdeInt = 0;
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(date.isEmpty() && desde.isEmpty() && hasta.isEmpty()) {
				//cambiar date a -1 que es un numero que no se puede introducir a SQL y se puede usar para identificar que hay que devolver las lecturas sin restringir nada
				date = "-1";
				desdeInt = -1;
				hastaInt = -1;
			}
			
			LecturasAmigoList lecturas = getLecturasTodasDateDesdeHasta(date, desdeInt, hastaInt);
			return lecturas;
			
		}
		else {
			//comprobar todas las posibilidades si se ha introduciodo un amigo
			
			if(!date.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
				desdeInt = Integer.parseInt(desde);
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(!date.isEmpty() && !desde.isEmpty() && hasta.isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Combinacion inválida, introduzca hasta");
			}
			
			else if(!date.isEmpty() && desde.isEmpty() && !hasta.isEmpty()) {
				desdeInt = 0;
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(!date.isEmpty() && desde.isEmpty() && hasta.isEmpty()) {
				//cambiar desde y hasta a -1 que es un numero que no se puede introducir a SQL como LIMIT y se puede usar para identificar que hay que devolver todas las lecturas
				desdeInt = -1;
				hastaInt = -1;
			}
			
			else if(date.isEmpty() && !desde.isEmpty() && !hasta.isEmpty()) {
				//cambiar date a -1 que consideramos un formato no válido y se puede usar para identificar que hay que devolver las lecturas sin restringir fecha
				date = "-1";
				desdeInt = Integer.parseInt(desde);
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(date.isEmpty() && !desde.isEmpty() && hasta.isEmpty()) {
				JOptionPane.showMessageDialog(contentPane, "Combinacion inválida, introduzca hasta");
			}
			
			else if(date.isEmpty() && desde.isEmpty() && !hasta.isEmpty()) {
				//cambiar date a -1 que consideramos un formato no válido y se puede usar para identificar que hay que devolver las lecturas sin restringir fecha, solo hasta
				date = "-1";
				desdeInt = 0;
				hastaInt = Integer.parseInt(hasta);
			}
			
			else if(date.isEmpty() && desde.isEmpty() && hasta.isEmpty()) {
				//cambiar date a -1 que es un numero que no se puede introducir a SQL y se puede usar para identificar que hay que devolver las lecturas sin restringir nada
				date = "-1";
				desdeInt = -1;
				hastaInt = -1;
			}
			
			LecturasAmigoList lecturas = getLecturasAmigoDateDesdeHasta(amigo, date, desdeInt, hastaInt);
			return lecturas;
		}
	}
	
	
	//método que decide la acción al querer obtener recomendaciones por nuestros amigos
	LecturasAmigoList decidirRecomendaciones(String calificacion, String autor, String categoria){
		
		//cuando no se ha intorducido una calificacion
		if(calificacion.isEmpty() && autor.isEmpty() && categoria.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "Introduzca al menos criterio");
		}
		else if(calificacion.isEmpty() && autor.isEmpty() && !categoria.isEmpty()) {
			//convertir calificacion y autor a -1 (que no se han introducido)
			calificacion = "-1";
			autor = "-1";
		}
		else if(calificacion.isEmpty() && !autor.isEmpty() && categoria.isEmpty()) {
			//convertir calificacion y categoria a -1 (que no se han introducido)
			calificacion = "-1";
			categoria = "-1";
		}
		else if(calificacion.isEmpty() && !autor.isEmpty() && !categoria.isEmpty()) {
			//convertir calificacion a -1 (que no se han introducido)
			calificacion = "-1";
		}
		
		//posibilidades cuando se ha intorducido una calificacion
		else if(!calificacion.isEmpty() && autor.isEmpty() && categoria.isEmpty()) {
			//convertir autor y categoria a -1 (que no se han introducido)
			autor = "-1";
			categoria = "-1";
		}
		else if(!calificacion.isEmpty() && !autor.isEmpty() && categoria.isEmpty()) {
			//convertir categoria a -1 (que no se han introducido)
			categoria = "-1";
		}
		else if(!calificacion.isEmpty() && autor.isEmpty() && !categoria.isEmpty()) {
			//convertir categoria a -1 (que no se han introducido)
			autor = "-1";
		}
		
		LecturasAmigoList lecturas = getRecomendaciones(calificacion, autor, categoria);
		return lecturas;
		
		
	}
	
	//M\E9todo que imprime las lecturas amigos
	private void printLecturas(LecturasAmigoList lecturas) {
		Iterator<LecturaAmigo> i = lecturas.getLecturasList().iterator();
		if(i.hasNext()) {
			while(i.hasNext()) {
				LecturaAmigo l = i.next();
				textArea.append(l.getNameAmigo() + "\t" + l.getNameLibro() + "\t " + l.getAutorLibro() + "\t " + l.getCategoriaLibro() + "\t" + l.getCalificacion() + "\n");
			}
		}
		else {
			textArea.append("No se ha encontrado ningua lectura..." + "\n");
		}
	}


}