use LIMS;
CREATE TABLE Test (
                      Tid INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                      Name VARCHAR(255),
                      SCode VARCHAR(255),
                      EID INT NOT NULL,
                      price REAL,

                      foreign key (EID) REFERENCES Employees(EID),
                      foreign key (SCode) REFERENCES Employees(SCode)

);

#insert some tests

-- Insert first record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test A', 'SC123', 1, 100.50);

-- Insert second record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test B', 'SC124', 2, 200.00);

-- Insert third record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test C', 'SC125', 3, 300.25);

-- Insert fourth record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test D', 'SC126', 4, 150.75);

-- Insert fifth record
INSERT INTO Test (Name, SCode, EID, price)
VALUES ('Test E', 'SC127', 5, 250.00);

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