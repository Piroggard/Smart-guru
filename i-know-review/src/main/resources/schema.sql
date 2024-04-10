

--CREATE TABLE IF NOT EXISTS courses
--(
--  course_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--  name VARCHAR(255) NOT NULL,
--  type VARCHAR(255) NOT NULL
--
--);

CREATE TABLE IF NOT EXISTS reviews
(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  post_date TIMESTAMP,
  user_id BIGINT NOT NULL
);

--CREATE TABLE IF NOT EXISTS reviews
--(
--  review_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--  title VARCHAR(255) NOT NULL,
--  description VARCHAR(255) NOT NULL,
--  post_date TIMESTAMP,
--  course_id BIGINT NOT NULL,
--  user_id BIGINT NOT NULL,
--  CONSTRAINT fk_courses_to_reviews FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE,
--  CONSTRAINT fk_users_to_reviews FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
--);
--CREATE TABLE IF NOT EXISTS users
--(
-- user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
-- email VARCHAR(255) NOT NULL,
-- name VARCHAR(255) NOT NULL
--);
--CREATE TABLE IF NOT EXISTS completed_courses
--(
--  completed_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
--  course_id BIGINT NOT NULL,
--  user_id BIGINT NOT NULL,
--  CONSTRAINT fk_course_to_completed_courses FOREIGN KEY (course_id) REFERENCES courses (course_id) ON DELETE CASCADE,
--  CONSTRAINT fk_user_to_completed_courses FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
--);