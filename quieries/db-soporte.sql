CREATE DATABASE dbsoporte
CHARSET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE dbsoporte;


CREATE TABLE `tipo_servicio` (
	`idtiposervicio` INT(10) NOT NULL AUTO_INCREMENT,
	`nomtiposervicio` varchar(20) NOT NULL,
	PRIMARY KEY (`idtiposervicio`)
);

CREATE TABLE `servicio` (
	`idservicio` INT(10) NOT NULL AUTO_INCREMENT,
	`idtiposervicio` INT(10) NOT NULL,
	`nombreservicio` varchar(40) NOT NULL,
	`tiempomaxres` INT(10) NOT NULL,
	PRIMARY KEY (`idservicio`)
);

CREATE TABLE `cliente` (
	`idcliente` INT(10) NOT NULL AUTO_INCREMENT,
	`cuit` bigint(11) NOT NULL,
	`razonsocial` varchar(40) NOT NULL,
	`idcondiva` INT(10) NOT NULL,
	`direccion` varchar(40) NOT NULL,
	`telefono` bigint(20) NOT NULL,
	`email` varchar(40) NOT NULL,
	`fechaalta` DATE NOT NULL,
	`activo` bool NOT NULL,
	`fechabaja` DATE,
	PRIMARY KEY (`idcliente`)
);

CREATE TABLE `serviciocontratado` (
	`idservcontr` INT(10) NOT NULL AUTO_INCREMENT,
	`idcliente` INT(10) NOT NULL,
	`idservicio` INT(10) NOT NULL,
	`fechaaltaserv` DATE NOT NULL,
	`servactivo` bool NOT NULL,
	`fechafincontrato` DATE,
	PRIMARY KEY (`idservcontr`)
);

CREATE TABLE `condiva` (
	`idcondiva` INT(10) NOT NULL AUTO_INCREMENT,
	`condiva` varchar(60) NOT NULL,
	PRIMARY KEY (`idcondiva`)
);

CREATE TABLE `especialidadtecnico` (
	`idespecialidad` INT(10) NOT NULL AUTO_INCREMENT,
	`idtecnico` INT(10) NOT NULL,
	`idservicio` INT(10) NOT NULL,
	`tiempoestimadotec` INT(10) NOT NULL,
	PRIMARY KEY (`idespecialidad`)
);

CREATE TABLE `tecnico` (
	`idtecnico` INT(10) NOT NULL AUTO_INCREMENT,
	`dni` INT(10) NOT NULL,
	`cuilcuit` bigint(11) NOT NULL,
	`nombreapellido` varchar(40) NOT NULL,
	`fechaalta` DATE NOT NULL,
	`activo` bool NOT NULL,
	`fechabaja` DATE,
	`horasdiarias` int(6) NOT NULL,
	`telefono` bigint(20) NOT NULL,
	`email` varchar(40) NOT NULL,
	`idmedionotif` INT(10) NOT NULL,
	PRIMARY KEY (`idtecnico`)
);

CREATE TABLE `medionotif` (
	`idnotif` INT(10) NOT NULL AUTO_INCREMENT,
	`nommedionotif` varchar(40) NOT NULL,
	PRIMARY KEY (`idnotif`)
);

CREATE TABLE `incidente` (
	`idincidente` INT(20) NOT NULL AUTO_INCREMENT,
	`idservicio` INT(10) NOT NULL,
	`fechaalta` DATE NOT NULL,
	`resuelto` bool NOT NULL,
	`fecharesuelto` DATE,
	`idtecnico` INT(10) NOT NULL,
	`idcliente` INT(10) NOT NULL,
	`descrproblema` varchar(400),
	`fechaestimada` DATE NOT NULL,
	`descrresolucion` varchar(400),
	PRIMARY KEY (`idincidente`)
);

ALTER TABLE `servicio` ADD CONSTRAINT `servicio_fk0` FOREIGN KEY (`idtiposervicio`) REFERENCES `tipo_servicio`(`idtiposervicio`);

ALTER TABLE `cliente` ADD CONSTRAINT `cliente_fk0` FOREIGN KEY (`idcondiva`) REFERENCES `condiva`(`idcondiva`);

ALTER TABLE `serviciocontratado` ADD CONSTRAINT `serviciocontratado_fk0` FOREIGN KEY (`idcliente`) REFERENCES `cliente`(`idcliente`);

ALTER TABLE `serviciocontratado` ADD CONSTRAINT `serviciocontratado_fk1` FOREIGN KEY (`idservicio`) REFERENCES `servicio`(`idservicio`);

ALTER TABLE `especialidadtecnico` ADD CONSTRAINT `especialidadtecnico_fk0` FOREIGN KEY (`idtecnico`) REFERENCES `tecnico`(`idtecnico`);

ALTER TABLE `especialidadtecnico` ADD CONSTRAINT `especialidadtecnico_fk1` FOREIGN KEY (`idservicio`) REFERENCES `servicio`(`idservicio`);

ALTER TABLE `tecnico` ADD CONSTRAINT `tecnico_fk0` FOREIGN KEY (`idmedionotif`) REFERENCES `medionotif`(`idnotif`);

ALTER TABLE `incidente` ADD CONSTRAINT `incidente_fk0` FOREIGN KEY (`idservicio`) REFERENCES `servicio`(`idservicio`);

ALTER TABLE `incidente` ADD CONSTRAINT `incidente_fk1` FOREIGN KEY (`idtecnico`) REFERENCES `tecnico`(`idtecnico`);

ALTER TABLE `incidente` ADD CONSTRAINT `incidente_fk2` FOREIGN KEY (`idcliente`) REFERENCES `cliente`(`idcliente`);










