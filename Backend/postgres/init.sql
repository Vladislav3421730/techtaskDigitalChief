CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          created_at DATE DEFAULT CURRENT_DATE,
                          is_active BOOLEAN NOT NULL,
                          description VARCHAR(255),
                          name VARCHAR(255)
);
CREATE TABLE sku (
                     id SERIAL PRIMARY KEY,
                     size VARCHAR(50) NOT NULL,
                     color VARCHAR(50) NOT NULL,
                     cost NUMERIC(10, 2) NOT NULL,
                     product_id INTEGER REFERENCES products(id) ON DELETE CASCADE
);
INSERT INTO products (name, description, is_active,created_at) VALUES
                                                                   ('Кроссовки', 'Привезены из Узбекистана', TRUE, CURRENT_DATE - INTERVAL '0 days'),
                                                                   ('Куртка', 'Теплая куртка для зимы', FALSE, CURRENT_DATE - INTERVAL '1 days'),
                                                                   ('Обувь', 'Летняя обувь для прогулок', TRUE, CURRENT_DATE - INTERVAL '2 days'),
                                                                   ('Футболка', 'Классическая белая футболка', TRUE, CURRENT_DATE - INTERVAL '3 days'),
                                                                   ('Шорты', 'Удобные шорты для отдыха', FALSE, CURRENT_DATE - INTERVAL '4 days'),
                                                                   ('Сумка', 'Стильная сумка через плечо', TRUE, CURRENT_DATE - INTERVAL '5 days'),
                                                                   ('Рюкзак', 'Прочный рюкзак для учебы', TRUE, CURRENT_DATE - INTERVAL '6 days'),
                                                                   ('Часы', 'Спортивные часы с GPS', FALSE, CURRENT_DATE - INTERVAL '7 days'),
                                                                   ('Платье', 'Элегантное вечернее платье', TRUE, CURRENT_DATE - INTERVAL '8 days'),
                                                                   ('Джинсы', 'Классические джинсы с высокой талией', TRUE, CURRENT_DATE - INTERVAL '9 days'),
                                                                   ('Блузка', 'Легкая блузка для офиса', FALSE, CURRENT_DATE - INTERVAL '10 days'),
                                                                   ('Шапка', 'Модная шапка для холодной погоды', TRUE, CURRENT_DATE - INTERVAL '11 days'),
                                                                   ('Перчатки', 'Уютные перчатки для зимы', FALSE, CURRENT_DATE - INTERVAL '12 days'),
                                                                   ('Куртка-пуховик', 'Теплый пуховик для зимних прогулок', TRUE, CURRENT_DATE - INTERVAL '13 days'),
                                                                   ('Сандалии', 'Удобные сандалии для пляжа', TRUE, CURRENT_DATE - INTERVAL '14 days'),
                                                                   ('Кроссовки для бега', 'Легкие кроссовки для бега', TRUE, CURRENT_DATE - INTERVAL '15 days'),
                                                                   ('Туфли', 'Элегантные туфли на каблуке', FALSE, CURRENT_DATE - INTERVAL '16 days'),
                                                                   ('Ботинки', 'Стильные ботинки для осени', TRUE, CURRENT_DATE - INTERVAL '17 days'),
                                                                   ('Спортивные штаны', 'Удобные спортивные штаны', TRUE, CURRENT_DATE - INTERVAL '18 days'),
                                                                   ('Купальник', 'Яркий купальник для лета', TRUE, CURRENT_DATE - INTERVAL '19 days');
INSERT INTO sku (size, color, cost, product_id) VALUES
                                                    ('XS', 'Красный', 1999.99, 1),
                                                    ('M', 'Голубой', 2299.99, 1),
                                                    ('XL', 'Зелёный', 2499.99, 1),

                                                    ('S', 'Белый', 1499.99, 2),
                                                    ('M', 'Чёрный', 1599.99, 2),
                                                    ('XXL', 'Розовый', 1699.99, 2),

                                                    ('S', 'Коричневый', 2999.99, 3),
                                                    ('M', 'Малиновый', 3199.99, 3),
                                                    ('L', 'Серый', 3499.99, 3),

                                                    ('S', 'Синий', 1999.99, 4),
                                                    ('M', 'Белый', 2199.99, 4),
                                                    ('L', 'Чёрный', 2399.99, 4),

                                                    ('S', 'Красный', 999.99, 5),
                                                    ('M', 'Жёлтый', 1099.99, 5),
                                                    ('L', 'Синий', 1199.99, 5),

                                                    ('XS', 'Голубой', 1499.99, 6),
                                                    ('M', 'Белый', 1599.99, 6),
                                                    ('XL', 'Чёрный', 1699.99, 6),

                                                    ('S', 'Чёрный', 3499.99, 7),
                                                    ('M', 'Коричневый', 3699.99, 7),
                                                    ('L', 'Бежевый', 3899.99, 7),

                                                    ('S', 'Синий', 1599.99, 8),
                                                    ('M', 'Чёрный', 1699.99, 8),
                                                    ('XXL', 'Бежевый', 1799.99, 8),

                                                    ('S', 'Фиолетовый', 2499.99, 9),
                                                    ('M', 'Зелёный', 2699.99, 9),
                                                    ('L', 'Чёрный', 2899.99, 9),

                                                    ('S', 'Розовый', 1999.99, 10),
                                                    ('M', 'Синий', 2199.99, 10),
                                                    ('L', 'Чёрный', 2399.99, 10),

                                                    ('S', 'Серый', 2499.99, 11),
                                                    ('M', 'Чёрный', 2699.99, 11),
                                                    ('L', 'Белый', 2899.99, 11),

                                                    ('S', 'Красный', 1999.99, 12),
                                                    ('M', 'Синий', 2299.99, 12),
                                                    ('L', 'Зелёный', 2499.99, 12),

                                                    ('S', 'Синий', 1999.99, 13),
                                                    ('M', 'Чёрный', 2299.99, 13),
                                                    ('L', 'Зелёный', 2499.99, 13),

                                                    ('S', 'Фиолетовый', 2999.99, 14),
                                                    ('M', 'Серый', 3199.99, 14),
                                                    ('L', 'Чёрный', 3399.99, 14),

                                                    ('S', 'Чёрный', 1999.99, 15),
                                                    ('M', 'Синий', 2199.99, 15),
                                                    ('L', 'Белый', 2399.99, 15),

                                                    ('S', 'Красный', 1799.99, 16),
                                                    ('M', 'Синий', 1999.99, 16),
                                                    ('L', 'Зелёный', 2199.99, 16),

                                                    ('S', 'Коричневый', 1599.99, 17),
                                                    ('M', 'Чёрный', 1699.99, 17),
                                                    ('L', 'Серый', 1799.99, 17),

                                                    ('S', 'Синий', 1999.99, 18),
                                                    ('M', 'Чёрный', 2299.99, 18),
                                                    ('L', 'Зелёный', 2499.99, 18),

                                                    ('S', 'Розовый', 1499.99, 19),
                                                    ('M', 'Синий', 1599.99, 19),
                                                    ('L', 'Чёрный', 1699.99, 19),

                                                    ('XS', 'Серый', 2999.99, 20),
                                                    ('M', 'Красный', 3199.99, 20),
                                                    ('XXL', 'Синий', 3399.99, 20);

