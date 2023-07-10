-- Usuarios administradores por defecto
INSERT INTO trabajofinal.usuarios (usu_admin,usu_estado, usu_estatura, usu_fecha_nacimineto, usu_id, usu_apellido, usu_email, usu_nombre, usu_sexo, usu_telefono) VALUES (1,1, 1.85, '1974-11-10', 1 , 'Valdez','vdejuan@empresa.com','Juan', 'M', '3884589546');
INSERT INTO trabajofinal.usuarios (usu_admin,usu_estado, usu_estatura, usu_fecha_nacimineto, usu_id, usu_apellido, usu_email, usu_nombre, usu_sexo, usu_telefono) VALUES (1,1, 1.65, '1986-9-9', 2 , 'Perez','mercie@empresa.com','Merci', 'F', '3781247895');
-- Ingredientes por defecto
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,1,'Lentejas');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,2,'Cordero');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,3,'Pimienta');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,4,'Cebolla');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,5,'Berenjena');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,6,'Ajo');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,7,'Sal');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,8,'Perejil');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,9,'Tomates');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,10,'Aceite');
INSERT INTO trabajofinal.ingredientes (estado_ingrediente, id, nombre) VALUES (1,11,'Limon');
-- Recetas por defecto
INSERT INTO trabajofinal.recetas (estado_receta, id, categoria_receta, imagen_receta, nombre_receta, preparacion_receta) VALUES (1,1, 'Recetas de Carnes', 'RecetaCarne1.jpg', 'Cordero Asado' , 'Salpimentar las paletillas, untarlas con la harina y ponerlas en una fuente. Cubrirlas con papel film y reservarlas toda la noche en el frigorífico junto con las lentejas puestas en remojo en agua fría. Al día siguiente, sacar la fuente de la carne y dejar que se temple. Precalentar el horno a 140°.Retirar las puntas a la berenjena y cortarla en daditos.Pelar y picar la cebolla y los ajos. Calentar el aceite en una cazuela grande y dorar a fuego vivo las paletillas. Retirarlas y reservarlas. Bajar el fuego y rehogar 3 minutos los ajos, la cebolla y la berenjena. Añadir las lentejas escurridas, la ralladura y el zumo de limón y el ras el hanout, cubrir de agua y mezclar. Colocar encima la carne, tapar y cocinar 2 h y 30 min en el horno, añadiendo un poco más de agua si fuera necesario. Lavar los tomatitos, ponerlos en la cazuela con el perejil picado y hornear 30 minutos más con la cazuela destapada. Repartir los platos.')

-- Relacion Ingredientes-Receta
INSERT INTO trabajofinal.receta_ingrediente (ingr_id, rec_id) VALUES (1,1);
INSERT INTO trabajofinal.receta_ingrediente (ingr_id, rec_id) VALUES (2,1);