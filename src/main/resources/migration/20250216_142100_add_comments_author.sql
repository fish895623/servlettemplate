ALTER TABLE comments
    ADD COLUMN author_id INT NOT NULL REFERENCES user (id);
