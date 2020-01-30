create table todos (
    id int unsigned primary key auto_increment,
    text VARCHAR(150) not null,
    done BIT
);
