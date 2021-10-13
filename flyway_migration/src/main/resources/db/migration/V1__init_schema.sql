CREATE TABLE IF NOT EXISTS schools (
    id INT PRIMARY KEY NOT NULL,
    title varchar(255) NOT NULL,
    adderss varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY NOT NULL,
    firstname varchar(255) NOT NULL,
    lastname varchar(255) NOT NULL,
    age INTEGER NOT NULL,
    gender varchar(1) NOT NULL,
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES schools(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS posts (
    id INT PRIMARY KEY NOT NULL,
    text varchar(1024),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS friends (
    user_id INT,
    friend_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users(id) ON DELETE CASCADE
);