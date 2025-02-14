CREATE TABLE posts
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    author     INT NOT NULL,
    title      TEXT,
    content    CHAR(40),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author) REFERENCES user (id)
);
