use LIMS;
CREATE TABLE Sample (
                        SCode VARCHAR(255) PRIMARY KEY,
                        Name VARCHAR(255),
                        Cid INT,
                        ProductionDate DATE,
                        ExpirationDate DATE,
                        Storage VARCHAR(255),
                        Temperature VARCHAR(255),
                        IS_A VARCHAR(255),
                        Tid INT,
                        FOREIGN KEY (Cid) REFERENCES Customer(Cid),
                        FOREIGN KEY (Tid) REFERENCES Test(Tid)
);

INSERT INTO Sample (SCode, Name, ProductionDate, ExpirationDate, Storage, Temperature, IS_A) VALUES
                   ('S001', 'Sample 1',  '2024-01-01', '2025-01-01', 'Cool', '10C', 'Animal feed'),
                   ('S002', 'Sample 2',  '2023-02-15', '2024-02-15', 'Dry', '20C', 'Drinking Water'),
                   ('S003', 'Sample 3',  '2023-03-10', '2024-03-10', 'Cool', '5C', 'Human Food'),
                   ('S004', 'Sample 4',  '2023-04-05', '2024-04-05', 'Frozen', '-18C', 'Animal feed'),
                   ('S005', 'Sample 5',  '2024-05-10', '2025-05-10', 'Cool', '15C', 'Human Food'),
                   ('S006', 'Sample 6',  '2024-06-20', '2025-06-20', 'Dry', '22C', 'Drinking Water'),
                   ('S007', 'Sample 7', '2024-07-30', '2025-07-30', 'Frozen', '-20C', 'Animal feed'),
                   ('S008', 'Sample 8', '2024-08-15', '2025-08-15', 'Cool', '12C', 'Human Food');

SELECT * FROM Sample;

DELETE FROM Sample WHERE SCode = 'S001';

UPDATE Sample
SET Name = 'Updated Sample 2', Temperature = '25C'
WHERE SCode = 'S002';

