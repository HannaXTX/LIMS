use LIMS;
CREATE TABLE Test (
                      Tid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      Name VARCHAR(255),
                      SCode VARCHAR(255),
                      EID INT NOT NULL,
                      price REAL,

                      foreign key (EID) REFERENCES Employees(ID),
                      foreign key (SCode) REFERENCES sample(SCode)

);


#insert some tests

SELECT * FROM Test;

-- Insert first record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test A', 'S001', 1, 100.50);

INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test A', 'S006', 1, 100.50);

-- Insert second record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test B', 'S002', 2, 200.00);

-- Insert third record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test C', 'S003', 3, 300.25);

-- Insert fourth record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test D', 'S004', 4, 150.75);

-- Insert fifth record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test E', 'S005', 5, 250.00);

#Delete
DELETE FROM Test
WHERE Tid = 2;

#Update
UPDATE Test
SET Name = 'Test D',
    SCode = 'SC126',
    EID = 4,
    price = 250.50
WHERE Tid = 1;