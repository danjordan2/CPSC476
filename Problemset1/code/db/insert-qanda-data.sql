INSERT INTO users(id, username, password) VALUES(1, 'fishygut', 'password');
INSERT INTO users(id, username, password) VALUES(2, 'puffstone', 'qwerty');
INSERT INTO users(id, username, password) VALUES(3, 'barkingcustard', 'welcome');

INSERT INTO questions(id, user_id, text) VALUES(1, 1, 'What are the main differences between Java EE 7 and Java EE 6 ?');
INSERT INTO questions(id, user_id, text) VALUES(2, 3, 'Where can I find code examples for Java 7 EE Tutorial?');

INSERT INTO answers(user_id, question_id, text) VALUES(2, 1, 'Support for JSON');
INSERT INTO answers(user_id, question_id, text) VALUES(3, 1, 'GlassFish v4');
INSERT INTO answers(user_id, question_id, text) VALUES(2, 1, 'Improved Bean Validation');
INSERT INTO answers(user_id, question_id, text) VALUES(3, 1, 'WebSocket support');
INSERT INTO answers(user_id, question_id, text) VALUES(2, 2, 'https://java.net/projects/javaeetutorial/sources/svn/show/trunk/examples');
INSERT INTO answers(user_id, question_id, text) VALUES(3, 2, 'Thanks!');

