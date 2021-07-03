CREATE PROCEDURE buscarPersonajesSinEspecies()
LANGUAGE plpgsql
AS $$
BEGIN

    SELECT * FROM starwars.people WHERE species = NULL;

    COMMIT;
END;$$

