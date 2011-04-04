
/*
 * CREATE TABLES
 */

CREATE TABLE Empleado(
NIF VARCHAR(9) PRIMARY KEY,
pass VARCHAR(15) NOT NULL,
pila VARCHAR(16) NOT NULL,
ap1 VARCHAR(20),
ap2 VARCHAR(20),
dir VARCHAR(50),
cod_postal INT(5),
tlf INT(9),
poblacion VARCHAR(20),
provincia VARCHAR(20),
num_cuenta BIGINT,
esAdmin BIT NOT NULL)
ENGINE = InnoDB;

CREATE TABLE Cliente(
NIF VARCHAR(9) PRIMARY KEY,
loginName VARCHAR(15) NOT NULL,
pass VARCHAR(15) NOT NULL,
pila VARCHAR(16) NOT NULL,
ap1 VARCHAR(20) NOT NULL,
ap2 VARCHAR(20) NOT NULL,
email VARCHAR(60) NOT NULL,
dir VARCHAR(50) NOT NULL,
cod_postal INT(5) NOT NULL,
tlf INT(9),
poblacion VARCHAR(20),
provincia VARCHAR(20),
dir_facturacion VARCHAR(50) NOT NULL,
VIP BIT NOT NULL)
ENGINE = InnoDB;

CREATE TABLE Categoria(
idCategoria INT(2) PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
padre INT(2))
ENGINE = InnoDB;

CREATE TABLE Producto(
idProducto INT(10) PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(20) NOT NULL,
descripcion VARCHAR(200) NOT NULL,
precio DOUBLE PRECISION NOT NULL,
categoria INT(2) NOT NULL,
CONSTRAINT ProductoCategoriaFK FOREIGN KEY(categoria)
			REFERENCES Categoria(idCategoria))
ENGINE = InnoDB;

CREATE TABLE Factura(
idFactura INT(10) PRIMARY KEY AUTO_INCREMENT,
fecha TIMESTAMP NOT NULL,
pagada BIT NOT NULL,
cliente VARCHAR(9) NOT NULL,
CONSTRAINT FacturaClienteFK FOREIGN KEY(cliente)
			REFERENCES Cliente(NIF))
ENGINE = InnoDB;

CREATE TABLE Linea_Factura(
num_linea INT(3),
factura INT(10),
producto INT(10) NOT NULL,
cantidad INT(3) NOT NULL,
descuento DOUBLE PRECISION,
total DOUBLE PRECISION NOT NULL,
PRIMARY KEY (factura, num_linea),
CONSTRAINT Linea_FacturaFacturaFK FOREIGN KEY(factura)
			REFERENCES Factura(idFactura) ON DELETE CASCADE,
CONSTRAINT Linea_FacturaProductoFK FOREIGN KEY(producto)
			REFERENCES Producto(idProducto))
ENGINE = InnoDB;

CREATE TABLE Mantenimiento(
idMantenimiento INT(10) PRIMARY KEY AUTO_INCREMENT,
lugar VARCHAR(50) NOT NULL,
descripcion VARCHAR(200) NOT NULL,
fecha TIMESTAMP NOT NULL,
dia VARCHAR(16) NOT NULL,
cliente VARCHAR(9) NOT NULL,
CONSTRAINT MantenimientoClienteFK FOREIGN KEY(cliente)
			REFERENCES Cliente(NIF))
ENGINE = InnoDB;

CREATE TABLE Mantenimiento_Emp(
mantenimiento INT(10),
empleado VARCHAR(9),
PRIMARY KEY (mantenimiento, empleado),
CONSTRAINT Mantenimiento_EmpMantenimientoFK FOREIGN KEY(mantenimiento)
			REFERENCES Mantenimiento(idMantenimiento) ON DELETE CASCADE,
CONSTRAINT Mantenimiento_EmpEmpleadoFK FOREIGN KEY(empleado)
			REFERENCES Empleado(NIF) ON DELETE CASCADE)
ENGINE = InnoDB;

INSERT INTO Empleado (NIF, pass, pila, ap1, ap2, dir, cod_postal, 
		tlf, poblacion, provincia, num_cuenta, esAdmin) 
		VALUES ('admin', 'admin', 'Admin', null, null, null, null, null,
		null, null, null, true);

