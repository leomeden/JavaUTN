CREATE VIEW dbsoporte.serviciosTecnicosNombres AS
SELECT 	ET.idtecnico, 
		ET.idservicio,
        TE.nombreapellido
FROM dbsoporte.especialidadtecnico ET
INNER JOIN dbsoporte.tecnico TE
ON ET.idtecnico = TE.idtecnico
;