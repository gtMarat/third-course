--liquibase formatted sql

--changeset marat:1
CREATE INDEX students_name ON student (name);

--changeset marat:2
CREATE INDEX faculties_name_color ON faculty (name, color);

