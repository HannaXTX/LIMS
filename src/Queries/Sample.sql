use LIMS;
CREATE TABLE Sample
(
    SCode          INT PRIMARY KEY AUTO_INCREMENT,
    Name           VARCHAR(255),
    Cid            INT,
    ProductionDate DATE,
    ExpirationDate DATE,
    Storage        VARCHAR(255),
    Temperature    VARCHAR(255),
    IS_A           VARCHAR(255),
    Tid            INT

#     FOREIGN KEY (Cid) REFERENCES Customer (Cid),
#     FOREIGN KEY (Tid) REFERENCES Test (Tid)
);

INSERT INTO Sample (Name, Cid, ProductionDate, ExpirationDate, Storage, Temperature, IS_A, Tid)
VALUES ('Sample 1', 1, '2024-01-01', '2025-01-01', 'Cool', '10C', 'Animal feed', 1),
       ('Sample 2', 2, '2023-02-15', '2024-02-15', 'Dry', '20C', 'Drinking Water', 2),
       ('Sample 3', 3, '2023-03-10', '2024-03-10', 'Cool', '5C', 'Human Food', 3),
       ('Sample 4', 4, '2023-04-05', '2024-04-05', 'Frozen', '-18C', 'Animal feed', 4),
       ('Sample 5', 5, '2024-05-10', '2025-05-10', 'Cool', '15C', 'Human Food', 5);


SELECT *
FROM Sample;

DROP TABLE Sample;

DROP TABLE IF EXISTS Sample;

DELETE
FROM Sample
WHERE SCode = 'S001';

DELETE
FROM Sample
WHERE SCode = 'S001';

UPDATE Sample
SET Name        = 'Updated Sample 2',
    Temperature = '25C'
WHERE SCode = 'S002';



SHOW CREATE TABLE sample;


-- Step 2: Drop the foreign key constraint
ALTER TABLE test
    DROP FOREIGN KEY test_ibfk_2;


DROP TABLE sample;

ALTER TABLE sample
    ADD CONSTRAINT customer_ibfk_1 FOREIGN KEY (Cid) REFERENCES Test (Cid);

ALTER TABLE test
    ADD CONSTRAINT test_ibfk_2 FOREIGN KEY (Tid) REFERENCES Test (Tid);





#     FOREIGN KEY (Cid) REFERENCES Customer (Cid),
#     FOREIGN KEY (Tid) REFERENCES Test (Tid)