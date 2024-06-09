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

DELETE FROM Employees;
ALTER TABLE Employees AUTO_INCREMENT = 1;


INSERT INTO Employees (Name, SSN, Address, DateOfBirth, Major, PhoneNumber, Email)
VALUES
    ('Rakan', '1221334', '222 Maple St', '1980-09-25', 'Finance', '555-222-3333', 'Rakan@gmail.com'),
    ('Hanna', '1220214', '777 Willow St', '1991-04-18', 'Accounting', '555-777-8888', 'Hanna@gmail.com'),
    ('Adel',  '122084', '888 Birch St', '1987-11-30', 'Economics', '555-888-9999', 'Adel@gmail.com'),
    ('Basem', '678-90-1234', '888 Birch St', '1987-11-30', 'Economics', '555-888-9999', 'Dioldo@gmail.com');




    DELETE FROM Employees
WHERE Name = 'Kevin Brown';

UPDATE Employees
SET Name = 'New Name'
WHERE Name = 'Current Name';

DROP table Employees;