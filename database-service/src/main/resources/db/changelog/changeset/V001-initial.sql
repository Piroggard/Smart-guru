--liquibase formatted sql
--changeset albert:1

CREATE TABLE organizers
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    role        VARCHAR      NOT NULL,
    date_create TIMESTAMP,
    date_delete TIMESTAMP,
    delete      BOOLEAN
);

CREATE TABLE courses
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name               VARCHAR(100)                 NOT NULL,
    url                VARCHAR UNIQUE,
    type               VARCHAR                      NOT NULL,
    number_seats       INT CHECK (number_seats > 0) NOT NULL,
    price              INT                          NOT NULL,
    photo_profile      VARCHAR UNIQUE,
    description        VARCHAR                      NOT NULL,
    direction          VARCHAR                      NOT NULL,
    duration           VARCHAR,
    organizer_id       BIGINT                       NOT NULL REFERENCES organizers (id) ON DELETE CASCADE,
    certificate        BOOLEAN,
    about_technology   VARCHAR                      NOT NULL,
    date_create        TIMESTAMP,
    date_start_course  DATE,
    date_finish_course DATE,
    delete             BOOLEAN,
    date_delete        TIMESTAMP,
    date_update        TIMESTAMP,
    status             VARCHAR                      NOT NULL
--    address_id          UUID                         NOT NULL UNIQUE REFERENCES address_course (id) ON DELETE CASCADE
);

CREATE TABLE news
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id        BIGINT       NOT NULL REFERENCES courses (id) ON DELETE CASCADE,
    direction        VARCHAR      NOT NULL,
    date_create      TIMESTAMP,
    date_publication TIMESTAMP,
    date_update      TIMESTAMP,
    image            VARCHAR,
    title            VARCHAR(100) NOT NULL,
    description      VARCHAR      NOT NULL,
    delete           BOOLEAN
);

CREATE TABLE technologies
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    photo     VARCHAR      NOT NULL,
    course_id BIGINT       NOT NULL REFERENCES courses (id) ON DELETE SET NULL
);

CREATE TABLE address_courses
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id   BIGINT       NOT NULL UNIQUE REFERENCES courses (id) ON DELETE CASCADE,
    country     VARCHAR(100) NOT NULL,
    city        VARCHAR(100) NOT NULL,
    street      VARCHAR(100),
    house       INT CHECK (house > 0),
    district    VARCHAR,
    date_create TIMESTAMP,
    date_update TIMESTAMP,
    delete      BOOLEAN
);

CREATE TABLE photos_courses
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    photos    VARCHAR NOT NULL,
    course_id BIGINT  NOT NULL REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE address_organizations
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    organization_id BIGINT       NOT NULL REFERENCES organizers (id) ON DELETE CASCADE,
    country         VARCHAR(100) NOT NULL,
    city            VARCHAR(100) NOT NULL,
    street          VARCHAR(100),
    house           INT CHECK (house > 0),
    district        VARCHAR,
    date_create     TIMESTAMP,
    date_update     TIMESTAMP,
    delete          BOOLEAN
);

CREATE TABLE "users"
(
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    firstname      VARCHAR(100) NOT NULL,
    lastname       VARCHAR(100) NOT NULL,
    middlename     VARCHAR(100),
    photo          VARCHAR,
    email          VARCHAR(100) NOT NULL UNIQUE,
    role           VARCHAR      NOT NULL,
    password       VARCHAR      NOT NULL,
    date_create    TIMESTAMP,
    date_update    TIMESTAMP,
    date_regcourse TIMESTAMP,
    date_delete    TIMESTAMP,
    delete         BOOLEAN
);

CREATE TABLE users_courses
(
--    id                UUID GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id         BIGINT REFERENCES courses (id) ON DELETE SET NULL,
    user_id           BIGINT REFERENCES "users" (id) ON DELETE SET NULL,
    PRIMARY KEY (course_id, user_id),
    date_registration TIMESTAMP,
    user_status       INT
);

CREATE TABLE reviews
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title            VARCHAR(100) NOT NULL,
    description      VARCHAR      NOT NULL,
    date_publication TIMESTAMP,
    date_update      TIMESTAMP,
    course_id        BIGINT       NOT NULL REFERENCES courses (id) ON DELETE CASCADE,
    user_id          BIGINT       NOT NULL REFERENCES "users" (id) ON DELETE SET NULL,
    date_delete      TIMESTAMP,
    date_moderation  TIMESTAMP,
    delete           BOOLEAN,
    moderation       BOOLEAN,
    raiting          INT
);

CREATE TABLE certificates
(
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id         BIGINT                    NOT NULL REFERENCES courses (id) ON DELETE SET NULL,
    organizer_id      BIGINT                    NOT NULL REFERENCES organizers (id) ON DELETE SET NULL,
    user_id           BIGINT                    NOT NULL REFERENCES "users" (id) ON DELETE CASCADE,
    name              VARCHAR(100)              NOT NULL,
    number            BIGINT CHECK (number > 0) NOT NULL,
    photo_certificate VARCHAR                   NOT NULL,
    date_issuing      DATE,
    date_create       TIMESTAMP,
    date_update       TIMESTAMP,
    delete            BOOLEAN
);