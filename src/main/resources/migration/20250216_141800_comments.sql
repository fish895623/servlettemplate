CREATE TABLE comments
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    post_id    INT       NOT NULL,
    content    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL,

    FOREIGN KEY (post_id) REFERENCES posts (id)
);
