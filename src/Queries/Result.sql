use LIMS;
CREATE TABLE Result (
                         Rid INT PRIMARY KEY AUTO_INCREMENT,
                         Tid INT NOT NULL,
                         Status VARCHAR(255),
                         Description TEXT,
                         Date DATE,
                         SCode VARCHAR(255),
                         Unit VARCHAR(255),

                         foreign key (Tid) REFERENCES Test (Tid)
);
# insert some results
INSERT INTO Result (Tid, Status, Description, Date, SCode, Unit)
VALUES (1, 'Approved', 'All tests passed', '2024-06-01', 'SC123', 'UnitA');

INSERT INTO Result (Tid, Status, Description, Date, SCode, Unit)
VALUES (2, 'Pending', 'Initial testing phase', '2024-06-02', 'SC124', 'UnitB');

INSERT INTO Result (Tid, Status, Description, Date, SCode, Unit)
VALUES (3, 'Rejected', 'Encountered errors', '2024-06-03', 'SC125', 'UnitC');

INSERT INTO Result (Tid, Status, Description, Date, SCode, Unit)
VALUES (4, 'Approved', 'Successfully completed', '2024-06-04', 'SC126', 'UnitD');

INSERT INTO Result (Tid, Status, Description, Date, SCode, Unit)
VALUES (5, 'Pending', 'Awaiting resources', '2024-06-05', 'SC127', 'UnitE');


#Delete
DELETE FROM Result
WHERE Rid = 1;

#Update
UPDATE Result
SET Status = 'Pending',
    Description = 'Awaiting results',
    Date = '2024-06-02',
    SCode = 'SC456',
    Unit = 'UnitB'
WHERE Rid = 1;




