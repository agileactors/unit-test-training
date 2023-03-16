CREATE TABLE trainer (
	trainer_id uuid NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT trainer_pkey PRIMARY KEY (trainer_id),
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE training (
	training_id uuid NOT NULL,
	training_name varchar(255) NOT NULL,
	trainer_id uuid NOT NULL,
	CONSTRAINT training_pkey PRIMARY KEY (training_id),
)
WITH (
	OIDS=FALSE
) ;

