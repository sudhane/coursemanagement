DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS authors;

CREATE TABLE courses (
	id SERIAL PRIMARY KEY,
	title varchar(100) NOT NULL,
	author_id int4 NOT NULL,
	category varchar(100) NOT NULL,
	created_by varchar(50) NULL,
	last_modified_by varchar(50) NULL,
	creation_date timestamp(0) NULL,
	last_modified_date timestamp(0) NULL,
	additional_details varchar(1000) NULL
);


CREATE TABLE authors (
	id SERIAL PRIMARY KEY,
	full_name varchar(100) NOT NULL,
	created_by varchar(50) NULL,
	last_modified_by varchar(50) NULL,
	creation_date timestamp(0) NULL,
	last_modified_date timestamp(0) NULL,
	additional_details varchar(1000) NULL
);