CREATE TABLE problem (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         active TINYINT(1) NOT NULL,
                         create_date DATETIME NOT NULL
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         active TINYINT(1) NOT NULL,
                         product_father_id BIGINT,
                         create_date DATETIME NOT NULL
);

CREATE TABLE product_problem (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 problem_id BIGINT NOT NULL,
                                 product_id BIGINT NOT NULL,
                                 activation_date DATETIME,
                                 is_active TINYINT(1) NOT NULL,
                                 FOREIGN KEY (problem_id) REFERENCES problem (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO problem (name, active)
VALUES ('Problem 1', true),
       ('Problem 2', false);

INSERT INTO product (name, active)
VALUES ('Product 1', true),
       ('Product 2', true),
       ('Product 3', false);

INSERT INTO problem_product (problem_id, product_id, activation_date, is_active)
VALUES (1, 1, '2023-06-21 10:00:00', true),
       (1, 2, '2023-06-21 11:00:00', false),
       (2, 2, '2023-06-21 12:00:00', true),
       (2, 3, '2023-06-21 13:00:00', false);
