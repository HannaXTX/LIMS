use LIMS;
CREATE TABLE Customer (
                          Cid INT NOT NULL AUTO_INCREMENT,
                          Name VARCHAR(255) NOT NULL,
                          PhoneNumber VARCHAR(20),
                          Job VARCHAR(255),
                          Email VARCHAR(255) NOT NULL,

                          PRIMARY KEY (Cid)
);

# insert some Customers
INSERT INTO Customer (Name, PhoneNumber, Job, Email)
VALUES ('John Doe', '123-456-7890', 'Engineer', 'john.doe@example.com');

INSERT INTO Customer (Name, PhoneNumber, Job, Email)
VALUES ('George Khair', '123-465-7890', 'Programmer', 'goerge.doe@example.com');

INSERT INTO Customer (Name, PhoneNumber, Job, Email)
VALUES ('Hanna Omar','123-654-7890', 'Engineer', 'hanna.doe@example.com');

INSERT INTO Customer (Name, PhoneNumber, Job, Email)
VALUES ('Rakan Kaibni', '123-954-7890', 'Doctor', 'rakan.doe@example.com');

INSERT INTO Customer (Name, PhoneNumber, Job, Email)
VALUES ('Mazen Ahmed', '123-442-7890', 'Engineer', 'mazen.doe@example.com');

SELECT * FROM Customer;

DELETE FROM Customer WHERE Cid> 5;



#Delete
DELETE FROM Customer
WHERE Name = 'George';

#Update
UPDATE Customer
SET Name = 'Jane Doe',
    PhoneNumber = '098-765-4321',
    Job = 'Manager',
    Email = 'jane.doe@example.com'
WHERE Cid = 1;






