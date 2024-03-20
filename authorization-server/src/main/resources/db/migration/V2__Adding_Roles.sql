INSERT INTO floof_auth.roles (created_at, updated_at, is_deleted, deleted_by, deleted_at, role)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL, NULL, 'ROLE_USER'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false, NULL, NULL, 'ROLE_ADMIN');