INSERT INTO problem (name, description, active, create_date)
VALUES ('Inativo', 'Produto Inativo', 1, '2023-06-21 22:30:59');

INSERT INTO product (name, description, active, product_father_id, create_date)
VALUES ('Seguro Vida', 'Seguro Vida', 1, true, '2023-06-21 22:30:59');

INSERT INTO product_problem (problem_id, product_id, activation_date, is_active)
VALUES (1, 1, '2023-06-21 22:30:59', 1);