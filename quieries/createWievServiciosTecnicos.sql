CREATE VIEW dbsoporte.serviciosTecnicos AS
SELECT 	ST.idtecnico, 
		ST.idservicio,
        SE.nombreservicio
FROM dbsoporte.especialidadtecnico ST
INNER JOIN dbsoporte.servicio SE
ON ST.idservicio = SE.idservicio
;