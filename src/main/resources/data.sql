
DELETE FROM DAY_ACTIVITY_ASSIGNMENT;
DELETE FROM DAY_ACTIVITY;
DELETE FROM DAY;
DELETE FROM TRIP_ASSIGNMENT;
DELETE FROM TRIP;
DELETE FROM APPLICATION_USER;

ALTER SEQUENCE DAY_ACTIVITY_ASSIGNMENT_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE DAY_ACTIVITY_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE DAY_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE TRIP_ASSIGNMENT_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE TRIP_ID_SEQ RESTART WITH 1;
ALTER SEQUENCE APPLICATION_USER_ID_SEQ RESTART WITH 1;

