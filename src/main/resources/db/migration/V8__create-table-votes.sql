create table votes(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    answer_id   BIGINT NOT NULL,
    vote_value  BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (answer_id) REFERENCES answers (id)
);