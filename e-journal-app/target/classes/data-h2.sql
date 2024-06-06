INSERT INTO users (id, username, password, first_name, last_name, email, phone_number, role)
VALUES (0, 'teacher', '{noop}teacher', 'Teacher', 'Teacher',
        'teacher@example.com', '+380975555555', 'ROLE_TEACHER');
INSERT INTO teacher (id) VALUES (0);