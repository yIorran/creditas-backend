CREATE TABLE loan_simulation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id CHAR(36),
    rate_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (rate_id) REFERENCES creditas_taxa(id)
);