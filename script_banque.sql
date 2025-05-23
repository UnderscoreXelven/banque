CREATE DATABASE Bank;
USE Bank;

CREATE TABLE Customer(
	id_customer INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(150) NOT NULL,
    prenom VARCHAR(150) NOT NULL,
    tel VARCHAR(15)
);

CREATE TABLE BankAccount(
	id_account INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_customer INT NOT NULL,
    FOREIGN KEY (id_customer) REFERENCES Customer(id_customer)
);

CREATE TABLE Operation(
	id_operation INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    status ENUM('DEPOSIT', 'WITHDRAWAL') NOT NULL,
    id_account INT NOT NULL,
    FOREIGN KEY(id_account) REFERENCES BankAccount(id_account)
);