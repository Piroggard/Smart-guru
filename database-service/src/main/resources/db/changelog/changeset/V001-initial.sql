--liquibase formatted sql
--changeset albert:1

CREATE TABLE organizers
(
    id          UUID PRIMARY KEY,
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
    id                 UUID PRIMARY KEY,
    name               VARCHAR(100)                 NOT NULL,
    url                VARCHAR UNIQUE,
    type               VARCHAR                      NOT NULL,
    number_seats       INT CHECK (number_seats > 0) NOT NULL,
    price              INT                          NOT NULL,
    photo_profile      VARCHAR UNIQUE,
    description        VARCHAR                      NOT NULL,
    direction          VARCHAR                      NOT NULL,
    duration           VARCHAR,
    organizer_id       UUID                         NOT NULL REFERENCES organizers (id) ON DELETE CASCADE,
    certificate        BOOLEAN,
    about_technology   VARCHAR                      NOT NULL,
    date_create        TIMESTAMP,
    date_start_course  DATE,
    date_finish_course DATE,
    delete             BOOLEAN,
    date_delete        TIMESTAMP,
    date_update        TIMESTAMP,
    status             VARCHAR                      NOT NULL
);

CREATE TABLE news
(
    id               UUID PRIMARY KEY,
    course_id        UUID         NOT NULL REFERENCES courses (id) ON DELETE CASCADE,
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
    id        UUID PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    photo     VARCHAR      NOT NULL,
    course_id UUID         NOT NULL REFERENCES courses (id) ON DELETE SET NULL
);

CREATE TABLE address_courses
(
    id          UUID PRIMARY KEY,
    course_id   UUID         NOT NULL UNIQUE REFERENCES courses (id) ON DELETE CASCADE,
    country     VARCHAR(100) NOT NULL,
    city        VARCHAR(100) NOT NULL,
    street      VARCHAR(100),
    house       VARCHAR(100),
    district    VARCHAR,
    date_create TIMESTAMP,
    date_update TIMESTAMP,
    delete      BOOLEAN
);

CREATE TABLE photos_courses
(
    id        UUID PRIMARY KEY,
    photos    VARCHAR NOT NULL,
    course_id UUID    NOT NULL REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE address_organizations
(
    id              UUID PRIMARY KEY,
    organization_id UUID         NOT NULL REFERENCES organizers (id) ON DELETE CASCADE,
    country         VARCHAR(100) NOT NULL,
    city            VARCHAR(100) NOT NULL,
    street          VARCHAR(100),
    house           VARCHAR(100),
    district        VARCHAR,
    date_create     TIMESTAMP,
    date_update     TIMESTAMP,
    delete          BOOLEAN
);

CREATE TABLE "users"
(
    id             UUID PRIMARY KEY,
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
    id                UUID PRIMARY KEY,
    course_id         UUID REFERENCES courses (id) ON DELETE SET NULL,
    user_id           UUID REFERENCES "users" (id) ON DELETE SET NULL,
    UNIQUE (course_id, user_id),
    date_registration TIMESTAMP,
    user_status       INT
);

CREATE TABLE reviews
(
    id               UUID PRIMARY KEY,
    title            VARCHAR(100) NOT NULL,
    description      VARCHAR      NOT NULL,
    date_publication TIMESTAMP,
    date_update      TIMESTAMP,
    course_id        UUID         NOT NULL REFERENCES courses (id) ON DELETE CASCADE,
    user_id          UUID         NOT NULL REFERENCES "users" (id) ON DELETE SET NULL,
    date_delete      TIMESTAMP,
    date_moderation  TIMESTAMP,
    delete           BOOLEAN,
    moderation       BOOLEAN,
    raiting          INT
);

CREATE TABLE certificates
(
    id                UUID PRIMARY KEY,
    course_id         UUID         NOT NULL REFERENCES courses (id) ON DELETE SET NULL,
    organizer_id      UUID         NOT NULL REFERENCES organizers (id) ON DELETE SET NULL,
    user_id           UUID         NOT NULL REFERENCES "users" (id) ON DELETE CASCADE,
    name              VARCHAR(100) NOT NULL,
    number            VARCHAR(100) NOT NULL,
    photo_certificate VARCHAR      NOT NULL,
    date_issuing      DATE,
    date_create       TIMESTAMP,
    date_update       TIMESTAMP,
    delete            BOOLEAN
);