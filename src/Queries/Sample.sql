use LIMS;
CREATE TABLE Sample
(
    SCode          INT PRIMARY KEY,
    Name           VARCHAR(255),
    Cid            INT,
    ProductionDate DATE,
    ExpirationDate DATE,
    Storage        VARCHAR(255),
    Temperature    VARCHAR(255),
    IS_A           VARCHAR(255),
    Tid            INT,
    FOREIGN KEY (Cid) REFERENCES Customer (Cid),
    FOREIGN KEY (Tid) REFERENCES Test (Tid)
);

INSERT INTO Sample (SCode, Name, Cid, ProductionDate, ExpirationDate, Storage, Temperature, IS_A)
VALUES (0001, 'Sample 1', 1, '2024-01-01', '2025-01-01', 'Cool', '10C', 'Animal feed'),
       (0002, 'Sample 2', 2, '2023-02-15', '2024-02-15', 'Dry', '20C', 'Drinking Water'),
       (0003, 'Sample 3', 3, '2023-03-10', '2024-03-10', 'Cool', '5C', 'Human Food'),
       (0004, 'Sample 4', 4,'2023-04-05', '2024-04-05', 'Frozen', '-18C', 'Animal feed'),
       (0005, 'Sample 5', 5,'2024-05-10', '2025-05-10', 'Cool', '15C', 'Human Food'),
       (0006, 'Sample 6', 1,'2024-06-20', '2025-06-20', 'Dry', '22C', 'Drinking Water'),
       (0007, 'Sample 7', 1,'2024-07-30', '2025-07-30', 'Frozen', '-20C', 'Animal feed'),
       (0008, 'Sample 8', 1,'2024-08-15', '2025-08-15', 'Cool', '12C', 'Human Food');

SELECT * FROM Sample;

DROP TABLE Sample;

DROP TABLE IF EXISTS Sample;

DELETE
FROM Sample
WHERE SCode = 'S001';

DELETE FROM Sample
WHERE SCode='S001';

UPDATE Sample
SET Name        = 'Updated Sample 2',
    Temperature = '25C'
WHERE SCode = 'S002';




SHOW CREATE TABLE sample;



-- Step 2: Drop the foreign key constraint
ALTER TABLE test DROP FOREIGN KEY test_ibfk_2;


DROP TABLE sample;

ALTER TABLE sample ADD CONSTRAINT test_ibfk_2 FOREIGN KEY (sample_id) REFERENCES sample (id);

