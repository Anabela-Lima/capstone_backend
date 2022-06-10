
DELETE FROM APPLICATION_USER;

ALTER SEQUENCE APPLICATION_USER_ID_SEQ RESTART WITH 1;

INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('admin_1', 'hash', true, false, 'admin_1@email.com', '1234567890');
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('admin_2', 'hash', true, false, 'admin_2@email.com', '1234567890');
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('admin_3', 'hash', true, false, 'admin_3@email.com', '1234567890');
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('admin_4', 'hash', true, false, 'admin_4@email.com', '1234567890');
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('admin_5', 'hash', true, false, 'admin_5@email.com', '1234567890');

INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('user_1', 'hash', false, false, 'admin_6@email.com', '1234567890');
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('user_2', 'hash', false, false, 'admin_7@email.com', '1234567890');
INSERT INTO APPLICATION_USER (username, password_hash, is_admin, is_owner, email, mobile)
VALUES('user_3', 'hash', false, false, 'admin_8@email.com', '1234567890');