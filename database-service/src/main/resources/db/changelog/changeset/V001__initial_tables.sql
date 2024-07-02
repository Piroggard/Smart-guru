--liquibase formatted sql
--changeset albert:1

CREATE TABLE users
(
    id          UUID PRIMARY KEY,
    firstname   VARCHAR(128)  NOT NULL,
    lastname    VARCHAR(128)  NOT NULL,
    middle_name VARCHAR(128),
    email       VARCHAR(128)  NOT NULL UNIQUE,
    password    VARCHAR(1024) NOT NULL,
    photo       VARCHAR(256),
    role        VARCHAR(128)           DEFAULT 'USER',
    delete      BOOLEAN       NOT NULL DEFAULT FALSE,
    date_create TIMESTAMP,
    date_update TIMESTAMP,
    date_delete TIMESTAMP
);

CREATE TABLE organizers
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(128)  NOT NULL,
    email       VARCHAR(128)  NOT NULL UNIQUE,
    password    VARCHAR(1024) NOT NULL,
    role        VARCHAR(128)           DEFAULT 'ORGANIZER',
    delete      BOOLEAN       NOT NULL DEFAULT FALSE,
    date_create TIMESTAMP,
    date_update TIMESTAMP,
    date_delete TIMESTAMP
);

CREATE TABLE courses
(
    id                 UUID PRIMARY KEY,
    name               VARCHAR(128)  NOT NULL,
    url                VARCHAR(128) UNIQUE,
    type               VARCHAR(128)  NOT NULL,
    number_seats       INT           NOT NULL,
    price              INT           NOT NULL,
    photo_profile      VARCHAR(256) UNIQUE,
    description        VARCHAR(4096) NOT NULL,
    direction          VARCHAR(128)  NOT NULL,
    duration           VARCHAR(128),
    organizer_id       UUID          NOT NULL,
    certificate        BOOLEAN,
    status             VARCHAR(128)  NOT NULL,
    delete             BOOLEAN       NOT NULL DEFAULT FALSE,
    date_start_course  DATE,
    date_finish_course DATE,
    date_create        TIMESTAMP,
    date_delete        TIMESTAMP,
    date_update        TIMESTAMP,

    CONSTRAINT fk_organizers FOREIGN KEY (organizer_id) REFERENCES organizers (id) ON DELETE CASCADE
);

CREATE TABLE technologies
(
    id        UUID PRIMARY KEY,
    name      VARCHAR(128) NOT NULL,
    photo     VARCHAR(256) UNIQUE,
    course_id UUID         NOT NULL,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE news
(
    id               UUID PRIMARY KEY,
    course_id        UUID          NOT NULL,
    direction        VARCHAR(128)  NOT NULL,
    image            VARCHAR(256),
    title            VARCHAR(128)  NOT NULL,
    description      VARCHAR(4096) NOT NULL,
    delete           BOOLEAN       NOT NULL DEFAULT FALSE,
    date_create      TIMESTAMP,
    date_publication TIMESTAMP,
    date_update      TIMESTAMP,
    date_delete      TIMESTAMP,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE address_courses
(
    id          UUID PRIMARY KEY,
    course_id   UUID         NOT NULL,
    country     VARCHAR(128) NOT NULL,
    city        VARCHAR(128) NOT NULL,
    street      VARCHAR(128),
    house       VARCHAR(128),
    district    VARCHAR(128),
    delete      BOOLEAN      NOT NULL DEFAULT FALSE,
    date_create TIMESTAMP,
    date_update TIMESTAMP,
    date_delete TIMESTAMP,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE photos_courses
(
    id        UUID PRIMARY KEY,
    photo     VARCHAR(256) NOT NULL,
    course_id UUID         NOT NULL,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE
);

CREATE TABLE address_organizations
(
    id           UUID PRIMARY KEY,
    organizer_id UUID         NOT NULL,
    country      VARCHAR(128) NOT NULL,
    city         VARCHAR(128) NOT NULL,
    street       VARCHAR(128),
    house        VARCHAR(128),
    district     VARCHAR(128),
    delete       BOOLEAN      NOT NULL DEFAULT FALSE,
    date_create  TIMESTAMP,
    date_update  TIMESTAMP,
    date_delete  TIMESTAMP,

    CONSTRAINT fk_organizers FOREIGN KEY (organizer_id) REFERENCES organizers (id) ON DELETE CASCADE
);

CREATE TABLE users_courses
(
    id                UUID PRIMARY KEY,
    course_id         UUID         NOT NULL,
    user_id           UUID         NOT NULL,
    user_status       VARCHAR(128) NOT NULL,
    date_registration TIMESTAMP,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    UNIQUE (course_id, user_id)
);

CREATE TABLE reviews
(
    id               UUID PRIMARY KEY,
    title            VARCHAR(128) NOT NULL,
    description      VARCHAR(512) NOT NULL,
    course_id        UUID         NOT NULL,
    user_id          UUID         NOT NULL,
    reyting          INT,
    delete           BOOLEAN      NOT NULL DEFAULT FALSE,
    moderation       BOOLEAN,
    date_moderation  TIMESTAMP,
    date_publication TIMESTAMP,
    date_create      TIMESTAMP,
    date_update      TIMESTAMP,
    date_delete      TIMESTAMP,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE certificates
(
    id                UUID PRIMARY KEY,
    name              VARCHAR(128) NOT NULL,
    number            VARCHAR(128) NOT NULL,
    photo_certificate VARCHAR(256) NOT NULL,
    course_id         UUID         NOT NULL,
    user_id           UUID         NOT NULL,
    organizer_id      UUID         NOT NULL,
    delete            BOOLEAN      NOT NULL DEFAULT FALSE,
    date_issuing      DATE,
    date_create       TIMESTAMP,
    date_update       TIMESTAMP,

    CONSTRAINT fk_courses FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_organizers FOREIGN KEY (organizer_id) REFERENCES organizers (id) ON DELETE CASCADE
);