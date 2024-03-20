CREATE SEQUENCE IF NOT EXISTS floof_auth.role_sequence START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS floof_auth.user_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE floof_auth.roles
(
    role_id    BIGINT NOT NULL DEFAULT nextval('floof_auth.role_sequence'::regclass),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    is_deleted BOOLEAN,
    deleted_by TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    role       VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (role_id)
);

CREATE TABLE floof_auth.users
(
    user_id                    BIGINT NOT NULL DEFAULT nextval('floof_auth.user_sequence'::regclass),
    created_at                 TIMESTAMP WITHOUT TIME ZONE,
    updated_at                 TIMESTAMP WITHOUT TIME ZONE,
    is_deleted                 BOOLEAN,
    deleted_by                 TIMESTAMP WITHOUT TIME ZONE,
    deleted_at                 TIMESTAMP WITHOUT TIME ZONE,
    username                   VARCHAR(255),
    password                   VARCHAR(255),
    email                      VARCHAR(255),
    is_account_non_expired     BOOLEAN,
    is_account_non_locked      BOOLEAN,
    is_credentials_non_expired BOOLEAN,
    is_enabled                 BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE floof_auth.users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

ALTER TABLE floof_auth.users
    ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE floof_auth.users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES floof_auth.roles (role_id);

ALTER TABLE floof_auth.users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES floof_auth.users (user_id);

ALTER SEQUENCE floof_auth.role_sequence OWNED BY floof_auth.roles.role_id;

ALTER SEQUENCE floof_auth.user_sequence OWNED BY floof_auth.users.user_id;