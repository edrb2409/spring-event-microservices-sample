DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
    id integer  NOT NULL,
    name VARCHAR(100)  NOT NULL,
    primary key (id)
);

CREATE TABLE employees (
    id VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    fullname VARCHAR(200) NOT NULL,
    birthday date NOT NULL,
    departmentId integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE employees ADD FOREIGN KEY (departmentId)
REFERENCES departments(id);