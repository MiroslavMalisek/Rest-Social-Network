-- usuario = restNetwork
-- password = restNetwork
-- gestor: MySQL Workbench instalado en la maquina virtual

CREATE DATABASE IF NOT EXISTS social_network;
USE social_network;

CREATE TABLE IF NOT exists perfiles (
    username VARCHAR(30) UNIQUE NOT NULL,
    email VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS libros (
    nombre VARCHAR(50) UNIQUE NOT NULL,
    autor VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    PRIMARY KEY (nombre)
);

CREATE TABLE IF NOT EXISTS amistad (
    usuario VARCHAR(30) NOT NULL,
    amigo VARCHAR(30) NOT NULL,
    PRIMARY KEY (usuario , amigo),
    FOREIGN KEY (usuario)
        REFERENCES perfiles (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (amigo)
        REFERENCES perfiles (username)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lecturas (
    usuario VARCHAR(30) NOT NULL,
    nombre_libro VARCHAR(50) NOT NULL,
    calificacion FLOAT NOT NULL,
    fecha DATETIME NOT NULL,
    PRIMARY KEY (usuario , nombre_libro),
    FOREIGN KEY (usuario)
        REFERENCES perfiles (username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nombre_libro)
        REFERENCES libros (nombre)
        ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO perfiles 
VALUES ('Miroslav', 'miroslav@mm.com', 20);

INSERT INTO perfiles 
VALUES ('Jose', 'jose@example.com', 23);

INSERT INTO perfiles 
VALUES ('Sergio', 'sergio@example.com', 10);

INSERT INTO perfiles 
VALUES ('Joe', 'joe@example.com', 40);

INSERT INTO perfiles 
VALUES ('Mario', 'mario@mm.com', 37);

INSERT INTO perfiles 
VALUES ('Maria', 'maria@example.com', 14);

INSERT INTO perfiles 
VALUES ('Amariano', 'amariano@example.com', 10);

INSERT INTO perfiles 
VALUES ('Abad', 'abad@example.com', 40);



INSERT INTO libros
VALUES ('Biblia', 'Dios', 'fantasy');

INSERT INTO libros
VALUES ('Harry Potter', 'Rowling', 'fantasy');

INSERT INTO libros
VALUES ('El Senor de los Anillos', 'Tolkien', 'novela');

INSERT INTO libros
VALUES ('Narnia', 'Lewis', 'fantasy');

INSERT INTO libros
VALUES ('El Alquimista', 'Coelho', 'novela');

INSERT INTO libros
VALUES ('El Código da Vinci', 'Brown', 'fantasy');

INSERT INTO libros
VALUES ('Quijote', 'Cervantes', 'novela');

INSERT INTO libros
VALUES ('Hobbit', 'Tolkien', 'novela');

INSERT INTO libros
VALUES ('Bodas de Sangre', 'Lorca', 'drama');

INSERT INTO libros
VALUES ('Fuente Ovejuna', 'Lope de Vega', 'drama');



INSERT INTO lecturas
VALUES ("Miroslav", "Narnia", 9.0, NOW());
INSERT INTO lecturas
VALUES ("Miroslav", "Quijote", 10.0, NOW());
INSERT INTO lecturas
VALUES ("Miroslav", "Fuente Ovejuna", 6.5, NOW());
INSERT INTO lecturas
VALUES ("Jose", "Bodas de Sangre", 8.5, NOW());
INSERT INTO lecturas
VALUES ("Sergio", "Quijote", 9.0, NOW());
INSERT INTO lecturas
VALUES ("Sergio", "Harry Potter", 7.5, NOW());
INSERT INTO lecturas
VALUES ("Joe", "Hobbit", 8.3, NOW());
INSERT INTO lecturas
VALUES ("Joe", "El Senor de los Anillos", 10.0, NOW());
INSERT INTO lecturas
VALUES ("Mario", "Biblia", 5.0, NOW());
INSERT INTO lecturas
VALUES ("Mario", "El Alquimista", 9.3, NOW());
INSERT INTO lecturas
VALUES ("Maria", "Bodas de Sangre", 7.4, NOW());
INSERT INTO lecturas
VALUES ("Amariano", "Quijote", 9.8, NOW());
INSERT INTO lecturas
VALUES ("Amariano", "El Codigo da Vinci", 10.0, NOW());
INSERT INTO lecturas
VALUES ("Abad", "Biblia", 8.2, NOW());


INSERT INTO amistad
VALUES ('Miroslav', 'Jose');
INSERT INTO amistad
VALUES ('Miroslav', 'Sergio');
INSERT INTO amistad
VALUES ('Miroslav', 'Abad');
INSERT INTO amistad
VALUES ('Sergio', 'Miroslav');
INSERT INTO amistad
VALUES ('Sergio', 'Mario');
INSERT INTO amistad
VALUES ('Jose', 'Joe');
INSERT INTO amistad
VALUES ('Jose', 'Amariano');
INSERT INTO amistad
VALUES ('Joe', 'Sergio');
INSERT INTO amistad
VALUES ('Mario', 'Maria');
INSERT INTO amistad
VALUES ('Mario', 'Miroslav');
INSERT INTO amistad
VALUES ('Maria', 'Mario');
INSERT INTO amistad
VALUES ('Maria', 'Sergio');
INSERT INTO amistad
VALUES ('Mario', 'Jose');
INSERT INTO amistad
VALUES ('Maria', 'Joe');
INSERT INTO amistad
VALUES ('Maria', 'Miroslav');
INSERT INTO amistad
VALUES ('Maria', 'Amariano');
INSERT INTO amistad
VALUES ('Maria', 'Abad');
INSERT INTO amistad
VALUES ('Abad', 'Sergio');
INSERT INTO amistad
VALUES ('Abad', 'Miroslav');
INSERT INTO amistad
VALUES ('Abad', 'Maria');


