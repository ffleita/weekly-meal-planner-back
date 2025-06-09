-- Ingrediente
INSERT INTO ingrediente (id, nombre) VALUES (1, 'Tomate');
INSERT INTO ingrediente (id, nombre) VALUES (2, 'Cebolla');
INSERT INTO ingrediente (id, nombre) VALUES (3, 'Pimiento');
INSERT INTO ingrediente (id, nombre) VALUES (4, 'Aceite de oliva');
INSERT INTO ingrediente (id, nombre) VALUES (5, 'Ajo');
INSERT INTO ingrediente (id, nombre) VALUES (6, 'Sal');
INSERT INTO ingrediente (id, nombre) VALUES (7, 'Pimienta');
INSERT INTO ingrediente (id, nombre) VALUES (8, 'Orégano');
INSERT INTO ingrediente (id, nombre) VALUES (9, 'Albahaca');
INSERT INTO ingrediente (id, nombre) VALUES (10, 'Lechuga');
INSERT INTO ingrediente (id, nombre) VALUES (11, 'Zanahoria');
INSERT INTO ingrediente (id, nombre) VALUES (12, 'Apio');
INSERT INTO ingrediente (id, nombre) VALUES (13, 'Queso');
INSERT INTO ingrediente (id, nombre) VALUES (14, 'Pan');
INSERT INTO ingrediente (id, nombre) VALUES (15, 'Carne de res');
INSERT INTO ingrediente (id, nombre) VALUES (16, 'Pollo');
INSERT INTO ingrediente (id, nombre) VALUES (17, 'Pescado');
INSERT INTO ingrediente (id, nombre) VALUES (18, 'Limón');
INSERT INTO ingrediente (id, nombre) VALUES (19, 'Vinagre');
INSERT INTO ingrediente (id, nombre) VALUES (20, 'Azúcar');

-- Medida
INSERT INTO medida (id, descripcion) VALUES (1, 'GRAMOS');
INSERT INTO medida (id, descripcion) VALUES (2, 'MILILITROS');
INSERT INTO medida (id, descripcion) VALUES (3, 'UNIDADES');
INSERT INTO medida (id, descripcion) VALUES (4, 'CUCHARADAS');
INSERT INTO medida (id, descripcion) VALUES (5, 'TAZAS');
INSERT INTO medida (id, descripcion) VALUES (6, 'LITROS');
INSERT INTO medida (id, descripcion) VALUES (7, 'PUÑADOS');
INSERT INTO medida (id, descripcion) VALUES (8, 'PIZCAS');
INSERT INTO medida (id, descripcion) VALUES (9, 'KILOGRAMOS');
INSERT INTO medida (id, descripcion) VALUES (10, 'ONZAS');

-- Receta
INSERT INTO receta (id, nombre, pasos) VALUES (1, 'Ensalada Fresca', 'Lavar y cortar los vegetales. Mezclar y servir.');
INSERT INTO receta (id, nombre, pasos) VALUES (2, 'Salsa de Tomate Casera', 'Picar tomates y ajo, cocinar con especias hasta espesar.');
INSERT INTO receta (id, nombre, pasos) VALUES (3, 'Pollo al Horno', 'Marinar el pollo, luego hornear hasta dorar.');
INSERT INTO receta (id, nombre, pasos) VALUES (4, 'Pasta Alfredo', 'Cocinar la pasta y preparar salsa con queso y ajo.');
INSERT INTO receta (id, nombre, pasos) VALUES (5, 'Tarta de Verduras', 'Preparar masa, agregar verduras y queso, hornear.');


-- IngredienteReceta para Receta 1: Ensalada Fresca
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (1, 1, 2, 3, 1);  -- Tomate - 2 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (2, 2, 1, 3, 1);  -- Cebolla - 1 UNIDAD
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (3, 10, 3, 3, 1); -- Lechuga - 3 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (4, 9, 5, 8, 1);  -- Albahaca - 5 PIZCAS
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (5, 6, 1, 3, 1);  -- Sal - 1 UNIDAD (ejemplo)

-- IngredienteReceta para Receta 2: Salsa de Tomate Casera
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (6, 1, 5, 3, 2);  -- Tomate - 5 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (7, 5, 2, 3, 2);  -- Ajo - 2 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (8, 8, 1, 3, 2);  -- Orégano - 1 UNIDAD
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (9, 6, 1, 3, 2);  -- Sal - 1 UNIDAD
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (10, 7, 1, 3, 2); -- Pimienta - 1 UNIDAD

-- IngredienteReceta para Receta 3: Pollo al Horno
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (11, 16, 1, 3, 3); -- Pollo - 1 UNIDAD
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (12, 6, 2, 3, 3);  -- Sal - 2 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (13, 7, 1, 3, 3);  -- Pimienta - 1 UNIDAD
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (14, 4, 50, 2, 3); -- Aceite de oliva - 50 MILILITROS
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (15, 18, 1, 3, 3); -- Limón - 1 UNIDAD

-- IngredienteReceta para Receta 4: Pasta Alfredo
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (16, 13, 200, 1, 4); -- Queso - 200 GRAMOS
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (17, 4, 30, 2, 4); -- Aceite de oliva - 30 MILILITROS
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (18, 5, 2, 3, 4);   -- Ajo - 2 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (19, 7, 1, 3, 4);   -- Pimienta - 1 UNIDAD
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (20, 6, 1, 3, 4);   -- Sal - 1 UNIDAD

-- IngredienteReceta para Receta 5: Tarta de Verduras
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (21, 2, 2, 3, 5);  -- Cebolla - 2 UNIDADES
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (22, 11, 3, 1, 5); -- Zanahoria - 3 GRAMOS (podés ajustar la medida si querés)
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (23, 12, 2, 1, 5); -- Apio - 2 GRAMOS
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (24, 13, 150, 1, 5); -- Queso - 150 GRAMOS
INSERT INTO ingrediente_receta (id, ingrediente_id, cantidad, medida_id, receta_id) VALUES (25, 6, 1, 3, 5);  -- Sal - 1 UNIDAD

-- PlanSemanal
INSERT INTO plan_semanal (id, descripcion) VALUES (1, 'Plan semanal vegetariano básico');
INSERT INTO plan_dia (id, almuerzo_receta_id, cena_receta_id, dia_semana, plan_semanal_id) VALUES (1, 1, 2, 'LUNES', 1);
INSERT INTO plan_dia (id, almuerzo_receta_id, cena_receta_id, dia_semana, plan_semanal_id) VALUES (2, 3, 4, 'MARTES', 1);
INSERT INTO plan_dia (id, almuerzo_receta_id, cena_receta_id, dia_semana, plan_semanal_id) VALUES (3, 2, 5, 'MIERCOLES', 1);
INSERT INTO plan_dia (id, almuerzo_receta_id, cena_receta_id, dia_semana, plan_semanal_id) VALUES (4, 4, 1, 'JUEVES', 1);
INSERT INTO plan_dia (id, almuerzo_receta_id, cena_receta_id, dia_semana, plan_semanal_id) VALUES (5, 5, 3, 'VIERNES', 1);
