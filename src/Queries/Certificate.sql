use LIMS;
create table Certificate(

    CertificateCode INT PRIMARY KEY AUTO_INCREMENT,
    Cid INT NOT NULL,
    Rid INT Not Null,
    Total_Price REAL NOT NULL,

    foreign key (Cid) REFERENCES Customer (Cid),
    foreign key (Rid) REFERENCES Result(Rid)
);

#Insert
-- Insert first record
INSERT INTO Certificate (Total_Price)
VALUES (50);

-- Insert second record
INSERT INTO Certificate (Total_Price)
VALUES (200);

-- Insert third record
INSERT INTO Certificate (Cid, Rid, Total_Price)
VALUES (3, 3, 175.25);

-- Insert fourth record
INSERT INTO Certificate (Cid, Rid, Total_Price)
VALUES (4, 4, 300.75);

-- Insert fifth record
INSERT INTO Certificate (Cid, Rid, Total_Price)
VALUES (5, 5, 250.00);



#Delete
DELETE FROM Certificate
WHERE CertificateCode = 1;



#Update
UPDATE Certificate
SET Total_Price = 175.50
WHERE CertificateCode = 2;




