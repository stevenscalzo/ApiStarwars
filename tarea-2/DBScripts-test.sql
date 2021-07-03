-- Crear tabla en base de datos
CREATE TABLE personas (
 nombre varchar(20),
 direccion varchar(40),
 edad numeric(3)
);

-- Insertar valores en la base de datos
INSERT INTO personas VALUES ('juan', 'su casa', 25);
INSERT INTO personas VALUES ('pedro', 'su casa', 25);
INSERT INTO personas VALUES ('jose', 'su casa', 25);
INSERT INTO personas VALUES ('raul', 'su casa', 25);
INSERT INTO personas VALUES ('miguel', 'su casa', 25);

-- Seleccionar toda la base de datos
select * from personas;

-- Obtener los valores y ordenarlos por nombre
SELECT * FROM personas ORDER BY nombre;
