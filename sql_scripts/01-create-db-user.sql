
CREATE USER 'atos'@'localhost' IDENTIFIED BY 'atos';

GRANT ALL PRIVILEGES ON *.* TO 'atos'@'localhost';


ALTER USER 'atos'@'localhost' IDENTIFIED WITH mysql_native_password BY 'atos';