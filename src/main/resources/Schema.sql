CREATE TABLE residency(
    id uuid UNIQUE PRIMARY KEY,
    street varchar(100),
    bt_st_1 varchar(100),
    bt_st_2 varchar(100),
    numeration int,
    orientation varchar(10),
    extra_comment varchar (100)
);

CREATE TABLE people(
    id_people uuid UNIQUE PRIMARY KEY,
    dni varchar(12) not null,
    name varchar(50) not null,
    firstlastname varchar(50) not null,
    secondlastname varchar(50),
    residency_id uuid,
    FOREIGN KEY (residency_id) REFERENCES residency(id)
);

CREATE TABLE job(
    id_job uuid UNIQUE PRIMARY KEY,
    role varchar(100) not null,
    enterprise varchar(100) not null,
    start_date date not null,
    end_date date,
    id_people uuid not null,

    FOREIGN KEY (id_people) REFERENCES people(id_people)
);