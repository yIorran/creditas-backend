CREATE TABLE installment (
    id int AUTO_INCREMENT PRIMARY KEY,
    parcela VARCHAR(255),
    valor VARCHAR(255),
    installment_plan_id INT,
    FOREIGN KEY (installment_plan_id) REFERENCES installment_plan(id)
);