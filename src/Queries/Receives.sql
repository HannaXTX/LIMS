use LIMS;
create table Receives
(
                          SCode VARCHAR(255),
                          ID INT NOT NULL,
                          ReceiveDate Date,
                          primary key (SCode,ID),

                          FOREIGN KEY (SCode) REFERENCES Sample(SCode),
                          FOREIGN KEY (ID) REFERENCES Employee(CourseID)

);
#insert

-- Insert first record
INSERT INTO Receives (SCode, ID, ReceiveDate)
VALUES ('SC001', 1, '2024-06-01');

-- Insert second record
INSERT INTO Receives (SCode, ID, ReceiveDate)
VALUES ('SC002', 2, '2024-06-02');

-- Insert third record
INSERT INTO Receives (SCode, ID, ReceiveDate)
VALUES ('SC003', 3, '2024-06-03');

-- Insert fourth record
INSERT INTO Receives (SCode, ID, ReceiveDate)
VALUES ('SC004', 4, '2024-06-04');

-- Insert fifth record
INSERT INTO Receives (SCode, ID, ReceiveDate)
VALUES ('SC005', 5, '2024-06-05');

#Delete
DELETE FROM Receives
WHERE SCode = 'SC001' AND ID = 1;

#Update
UPDATE Receives
SET ReceiveDate = '2024-07-01'
WHERE SCode = 'SC001' AND ID = 1;



