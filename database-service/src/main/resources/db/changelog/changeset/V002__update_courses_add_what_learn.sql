--liquibase formatted sql
--changeset albert:2

ALTER TABLE courses
    ADD what_learn VARCHAR(4096) NOT NULL;