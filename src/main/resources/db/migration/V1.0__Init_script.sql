CREATE TABLE unit_test_training.trainer
(
    trainer_id uuid         NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    rates      text[] NULL,
    CONSTRAINT trainer_pkey PRIMARY KEY (trainer_id)
)
    WITH (
        OIDS = FALSE
        );

CREATE TABLE unit_test_training.training
(
    training_id   uuid         NOT NULL,
    training_name varchar(255) NOT NULL,
    trainer_id    uuid         NOT NULL,
    CONSTRAINT training_pkey PRIMARY KEY (training_id)
)
    WITH (
        OIDS = FALSE
        );

CREATE TABLE unit_test_training.email
(
    email_id     uuid      NOT NULL,
    subject      text      NOT NULL,
    body         text      NOT NULL,
    email        text      NOT NULL,
    email_status text      NOT NULL,
    rates        int[] NULL,
    created_at   timestamp NOT NULL,
    sent_at      timestamp NULL,
    CONSTRAINT emails_pkey PRIMARY KEY (email_id)
)
    WITH (
        OIDS = FALSE
        );
