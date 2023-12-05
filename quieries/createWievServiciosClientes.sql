CREATE VIEW dbsoporte.serviciosClientes AS
SELECT 	SC.idcliente, 
		SC.idservicio,
        SE.nombreservicio
FROM dbsoporte.serviciocontratado SC
INNER JOIN dbsoporte.servicio SE
ON SC.idservicio = SE.idservicio
;