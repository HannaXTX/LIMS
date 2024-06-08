use LIMS;
CREATE TABLE Result
(
    Rid         INT PRIMARY KEY AUTO_INCREMENT,
    Tid         INT NOT NULL,
    SCode       INT NOT NULL,
    Status      VARCHAR(255),
    Description TEXT,
    Date        DATE,
    Unit        VARCHAR(255)

#     FOREIGN KEY (Tid) REFERENCES Test (Tid),
#     FOREIGN KEY (SCode) REFERENCES Sample (SCode)
);

# insert some results
INSERT INTO Result (Tid, Status, Description, Date, SCode, Unit)
VALUES (1, 'Approved', 'All tests passed', '2024-06-01', 1, 'UnitA'),
       (2, 'Pending', 'Initial testing phase', '2024-06-02', 2, 'UnitB'),
       (3, 'Rejected', 'Encountered errors', '2024-06-03', 3, 'UnitC'),
       (4, 'Approved', 'Successfully completed', '2024-06-04', 4, 'UnitD'),
       (5, 'Pending', 'Awaiting resources', '2024-06-05', 5, 'UnitE');

SELECT * FROM Result;

DROP TABLE Result;


#Delete
DELETE
FROM Result
WHERE Rid = 1;

#Update
UPDATE Result
SET Status      = 'Pending',
    Description = 'Awaiting results',
    Date        = '2024-06-02',
    SCode       = 'SC456',
    Unit        = 'UnitB'
WHERE Rid = 1;




ALTER TABLE test
    DROP FOREIGN KEY test_ibfk_2;


ALTER TABLE Result
    DROP FOREIGN KEY result_ibfk_2;

ALTER TABLE Result
    DROP FOREIGN KEY result_ibfk_1;





