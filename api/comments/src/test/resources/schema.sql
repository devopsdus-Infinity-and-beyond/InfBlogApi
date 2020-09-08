CREATE TABLE comment(id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255), text VARCHAR(255), post_Id BIGINT);
create sequence hibernate_sequence;