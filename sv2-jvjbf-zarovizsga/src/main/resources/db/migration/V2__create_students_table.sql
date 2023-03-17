CREATE TABLE students (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   date_of_birth DATE NOT NULL,
   school_age_status VARCHAR(255) NOT NULL,
   school_id BIGINT NULL,
   CONSTRAINT pk_students PRIMARY KEY (id)
);

ALTER TABLE students ADD CONSTRAINT fk_schools_on_students FOREIGN KEY (school_id) REFERENCES schools (id);