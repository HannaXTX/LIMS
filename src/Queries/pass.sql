CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE,
                       password VARCHAR(100)
                   
);

-- Sign up operation
INSERT INTO users (username, password) VALUES ('Rakan', '0000');


INSERT INTO users (username, password) VALUES ('Admin', '0000');

-- Login operation
SELECT * FROM users WHERE username = 'Rakan' AND password = '0000';

-- Get all users
SELECT * FROM users;

delete from users
where username = 'wqwq' OR username = 'Ali' OR username = 'www' or username= 'qqq';