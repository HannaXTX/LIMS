use LIMS;

SHOW GRANTS;

SHOW GRANTS FOR 'root'@'localhost';


GRANT ALL PRIVILEGES ON dbname.* TO 'root'@'localhost';
FLUSH PRIVILEGES ;

Select @@version


