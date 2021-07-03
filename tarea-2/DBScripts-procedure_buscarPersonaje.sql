DROP FUNCTION buscarpersonaje;
CREATE OR REPLACE FUNCTION buscarPersonaje(personaje varchar)

RETURNS TABLE(nombre VARCHAR(50), codigo VARCHAR(50)) AS
$BODY$
DECLARE
	reg RECORD;
BEGIN
	FOR REG IN SELECT "name", people_id
FROM starwars.people WHERE LOWER("name") LIKE LOWER('%'||personaje||'%')  LOOP
		codigo := reg.people_id;
		nombre := reg.name;
		RETURN NEXT;
	END LOOP;
	RETURN;
END
$BODY$ LANGUAGE 'plpgsql'