CREATE TABLE installment_plan (
    id int AUTO_INCREMENT PRIMARY KEY,
    simulacao_id INT,
    FOREIGN KEY (simulacao_id) REFERENCES loan_simulation(id)
);