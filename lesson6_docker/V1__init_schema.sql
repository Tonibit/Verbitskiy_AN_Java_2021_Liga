CREATE TABLE school (
    id INTEGER NOT NULL,
    name varchar (255) NOT NULL,
    address varchar (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE student (
    id INTEGER NOT NULL,
    firstname varchar (255) NOT NULL,
    lastname varchar (255) NOT NULL,
    age INTEGER NOT NULL,
    school_id INTEGER REFERENCES school(id),
    PRIMARY KEY (id)
);

CREATE TABLE teacher (
    id INTEGER NOT NULL,
    firstname varchar (255) NOT NULL,
    lastname varchar (255) NOT NULL,
    age INTEGER NOT NULL,
    school_id INTEGER REFERENCES school(id),
    PRIMARY KEY (id)
);

CREATE TABLE subject (
    id INTEGER NOT NULL,
    title varchar (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE student_subject (
    student_id INTEGER REFERENCES student (id),
    subject_id INTEGER REFERENCES subject (id),
    PRIMARY KEY(student_id, subject_id)
);

CREATE TABLE teacher_subject (
	teacher_id INTEGER REFERENCES teacher (id),
    subject_id INTEGER REFERENCES subject (id),
    PRIMARY KEY(teacher_id, subject_id)
);