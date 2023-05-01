CREATE DATABASE IF NOT EXISTS national_library_system
DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;

USE mysql;

DELETE FROM USER WHERE USER = 'nls_root' AND HOST = 'localhost';

FLUSH PRIVILEGES;

CREATE USER 'nls_root'@'localhost' IDENTIFIED BY 'nls_password';

GRANT ALL PRIVILEGES ON *.* TO 'nls_root'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;