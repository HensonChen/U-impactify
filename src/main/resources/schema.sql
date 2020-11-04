CREATE SEQUENCE APP_SEQ start with 1 increment by 50;

CREATE TABLE ROLE (
	id SERIAL PRIMARY KEY NOT NULL, 
	name VARCHAR(32) NOT NULL
);

CREATE TABLE SOCIAL_INITIATIVE (
	id SERIAL PRIMARY KEY NOT NULL, 
	name VARCHAR(64) NOT NULL
);

CREATE TABLE UI_USER (
	id SERIAL PRIMARY KEY NOT NULL, 
	role_id INTEGER,
	socialinit_id INTEGER, 
	FOREIGN KEY (role_id) REFERENCES ROLE(id), 
	FOREIGN KEY (socialinit_id) REFERENCES SOCIAL_INITIATIVE(id), 
	username TEXT NOT NULL, 
	age INTEGER NOT NULL, 
	pw VARCHAR(64),
	first_name VARCHAR(32),
	last_name VARCHAR(32)
);

CREATE TABLE IMPACT_LEARNER (
	id SERIAL PRIMARY KEY NOT NULL,
	FOREIGN KEY (id) REFERENCES UI_USER(id),
	course_id INTEGER
	
);

CREATE TABLE IMPACT_CONSULTANT (
	id SERIAL PRIMARY KEY NOT NULL, 
	FOREIGN KEY (id) REFERENCES UI_USER(id)
);

CREATE TABLE POSTING (
	id SERIAL PRIMARY KEY NOT NULL,
	posting_creator_id INTEGER,
	FOREIGN KEY (posting_creator_id) REFERENCES UI_USER(id),
	social_init_id INTEGER,
	FOREIGN KEY (social_init_id) REFERENCES SOCIAL_INITIATIVE(id),
	posting_date TIMESTAMP NOT NULL, 
	posting_desc VARCHAR(256),
	name VARCHAR(32),
	posting_type VARCHAR(32)
);

CREATE TABLE COURSE (
	id SERIAL PRIMARY KEY NOT NULL,
	course_name VARCHAR(64),
	course_desc VARCHAR(256),
	impact_consultant_id INTEGER,
	FOREIGN KEY (impact_consultant_id) REFERENCES IMPACT_CONSULTANT(id),
	student_id INTEGER, 
	price INTEGER
);

CREATE TABLE EVENT (
	id SERIAL PRIMARY KEY NOT NULL,
	event_name VARCHAR(32),
	event_desc VARCHAR(256),
	user_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES UI_USER(id),
	event_start_date_time TIMESTAMP NOT NULL, 
	event_end_date_time TIMESTAMP NOT NULL,
	img_url VARCHAR(256)
);

CREATE TABLE CLASS_SESSION (
	id SERIAL PRIMARY KEY NOT NULL,
	course_id INTEGER,
	FOREIGN KEY (course_id) REFERENCES COURSE(id),
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL
);

CREATE TABLE APPLICATION (
	id SERIAL PRIMARY KEY NOT NULL,
	applicant_id INTEGER,
	posting_id INTEGER,
	email VARCHAR(32),
	FOREIGN KEY (applicant_id) REFERENCES UI_USER(id),
	FOREIGN KEY (posting_id) REFERENCES POSTING(id)
);

CREATE TABLE IMPACT_LEARNER_COURSE (
	id SERIAL PRIMARY KEY NOT NULL,
	course_id INTEGER,
	FOREIGN KEY (course_id) REFERENCES COURSE(id),
	student_id INTEGER,
	FOREIGN KEY (student_id) REFERENCES IMPACT_LEARNER(id),
    price INTEGER
);

CREATE TABLE INVOICE (
	id SERIAL PRIMARY KEY NOT NULL,
	user_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES UI_USER(id),
	course_id INTEGER,
	FOREIGN KEY (course_id) REFERENCES COURSE(id),
    price INTEGER,
    init_price INTEGER
);

ALTER TABLE IMPACT_LEARNER
ADD CONSTRAINT
fk_impact_learner_course FOREIGN KEY (course_id) REFERENCES IMPACT_LEARNER_COURSE(course_id);

ALTER TABLE COURSE
ADD CONSTRAINT
fk_course_student FOREIGN KEY (student_id) REFERENCES IMPACT_LEARNER_COURSE(student_id);

ALTER TABLE COURSE
ADD CONSTRAINT
fk_course_invoice FOREIGN KEY (invoice_id) REFERENCES INVOICE(id);

	

