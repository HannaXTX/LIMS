use LIMS;
CREATE TABLE Test
(
    Tid   INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name  VARCHAR(255),
    SCode INT,
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
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test A', 1, 1, 100.50);

INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test A', 2, 1, 100.50);

-- Insert second record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test B', 5, 2, 200.00);

-- Insert third record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test C', 3, 3, 300.25);

-- Insert fourth record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test D', 4, 4, 150.75);

-- Insert fifth record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test E', 2, 5, 250.00);

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
    DROP FOREIGN KEY sample_ibfk_2,
    DROP FOREIGN KEY employee_ibfk_2;


DROP TABLE Test;

ALTER TABLE test
    ADD CONSTRAINT sample_ibfk_2 foreign key (SCode) REFERENCES Sample(SCode),
    ADD CONSTRAINT  employee_ibfk_2    foreign key (EID) REFERENCES Employees(ID);



