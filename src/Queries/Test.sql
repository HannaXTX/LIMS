use LIMS;
CREATE TABLE Test
(
    Tid   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name  VARCHAR(255),
    EID   INT NOT NULL,
    price REAL

#                       foreign key (EID) REFERENCES Employees(ID),
#                       foreign key (SCode) REFERENCES Sample(SCode)

);

DROP TABLE Test;


#insert some tests

SELECT *
FROM Test;

-- Insert first record
INSERT INTO Test (Name, EID, price)
VALUES ('Protein', 1, 100),
       ('Fat', 2, 150),
       ('Ash', 2, 80),
       ('Moisture', 1, 50);


#Delete
DELETE
FROM Test
WHERE Tid = 2;

#Update
UPDATE Test
SET Name  = 'Test D',
    SCode = 'SC126',
    EID   = 4,
    price = 250.50
WHERE Tid = 1;



ALTER TABLE test
    DROP FOREIGN KEY employee_ibfk_2;

DROP TABLE Test;

ALTER TABLE test
    ADD CONSTRAINT employee_ibfk_2 foreign key (EID) REFERENCES Employees (ID);
