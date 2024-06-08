use LIMS;
CREATE TABLE Result
(
    Rid         INT PRIMARY KEY AUTO_INCREMENT,
    SCode       INT NOT NULL,
    Status      VARCHAR(255),
    Description TEXT,
    Date        DATE,
    Unit        VARCHAR(255)

#     FOREIGN KEY (Tid) REFERENCES Test (Tid),
#     FOREIGN KEY (SCode) REFERENCES Sample (SCode)
);

# insert some results
INSERT INTO Result ( Status, Description, Date, SCode, Unit)
VALUES ( 'Approved', 'All tests passed', '2024-06-01', 1, 'UnitA'),
       ( 'Pending', 'Initial testing phase', '2024-06-02', 2, 'UnitB'),
       ( 'Rejected', 'Encountered errors', '2024-06-03', 3, 'UnitC'),
       ( 'Approved', 'Successfully completed', '2024-06-04', 4, 'UnitD'),
       ( 'Pending', 'Awaiting resources', '2024-06-05', 5, 'UnitE');

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



ALTER TABLE result
    DROP FOREIGN KEY test_ibfk_2,
    DROP FOREIGN KEY sample_ibfk_3;

DROP TABLE Result;


ALTER TABLE result
    ADD CONSTRAINT  test_ibfk_2 FOREIGN KEY (Tid) REFERENCES Test (Tid),
    ADD CONSTRAINT  sample_ibfk_3   FOREIGN KEY (SCode) REFERENCES Sample (SCode);








