CREATE TABLE people(
    id uuid UNIQUE PRIMARY KEY,
    dni varchar(12) not null,
    name varchar(50) not null,
    firstlastname varchar(50) not null,
    secondlastname varchar(50)
);
