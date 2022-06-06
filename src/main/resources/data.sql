

DELETE FROM APPLICATION_USER;

ALTER SEQUENCE APPLICATION_USER_ID_SEQ RESTART WITH 1;

INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('admin_1', 'test_hash', true, false);
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('admin_2', 'test_hash', true, false);
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('admin_3', 'test_hash', true, false);
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('admin_4', 'test_hash', true, false);
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('admin_5', 'test_hash', true, false);

INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('user_1', 'test_hash', false, false);
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('user_2', 'test_hash', false, false);
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner) VALUES('user_3', 'test_hash', false, false);