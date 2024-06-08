use LIMS;



DROP TABLE Employees;
CREATE TABLE Employees (
                           ID INT NOT NULL AUTO_INCREMENT,
                           Name VARCHAR(255) NOT NULL,
                           SSN VARCHAR(255) NOT NULL,
                           Address VARCHAR(255) NOT NULL,
                           DateOfBirth DATE NOT NULL,
                           Major VARCHAR(255) NOT NULL,
                           PhoneNumber VARCHAR(255) NOT NULL,
                           Email VARCHAR(255) NOT NULL,

                           PRIMARY KEY (ID)
);



SELECT  * FROM Employees;

DELETE FROM Employees E WHERE E.ID>8;


INSERT INTO Employees (Name, SSN, Address, DateOfBirth, Major, PhoneNumber, Email)
VALUES
    ('David Miller', '234-56-7890', '222 Maple St', '1980-09-25', 'Finance', '555-222-3333', 'david.miller@example.com'),
    ('Lisa Wilson', '543-21-0987', '777 Willow St', '1991-04-18', 'Accounting', '555-777-8888', 'lisa.wilson@example.com'),
    ('Kevin Brown', '678-90-1234', '888 Birch St', '1987-11-30', 'Economics', '555-888-9999', 'kevin.brown@example.com'),
    ('Amanda Lee', '345-67-8901', '999 Spruce St', '1994-06-08', 'Communications', '555-999-0000', 'amanda.lee@example.com'),
    ('Brian Davis', '901-23-4567', '333 Oakwood St', '1983-02-14', 'Human Resources', '555-333-4444', 'brian.davis@example.com'),
    ('Rachel Martinez', '432-10-9876', '444 Elmwood St', '1990-10-05', 'Sociology', '555-444-5555', 'rachel.martinez@example.com'),
    ('Jason Garcia', '789-12-3456', '555 Pinecone St', '1986-07-12', 'History', '555-555-6666', 'jason.garcia@example.com'),
    ('Michelle Lopez', '567-89-0123', '666 Cedarwood St', '1995-03-22', 'English Literature', '555-666-7777', 'michelle.lopez@example.com');



DELETE FROM Employees
WHERE Name = 'David Miller';

UPDATE Employees
SET Name = 'New Name'
WHERE Name = 'Current Name';

DROP table Employees;