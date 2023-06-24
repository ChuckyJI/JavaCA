INSERT INTO javaca.collage (id, name) VALUES (1, 'ISS');
INSERT INTO javaca.collage (id, name) VALUES (2, 'Computing');
INSERT INTO javaca.collage (id, name) VALUES (3, 'Science');
INSERT INTO javaca.collage (id, name) VALUES (8, 'admin');
INSERT INTO javaca.role (id, name) VALUES (1, 'student');
INSERT INTO javaca.role (id, name) VALUES (2, 'lecturer');
INSERT INTO javaca.role (id, name) VALUES (3, 'administrator');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (3, 'test1@stu.nus.edu', 'test1', 'test1', 1, 1, 'S1000008K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (4, 'test2@stu.nus.edu', 'test2', 'test2', 1, 1, 'S1000002K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (5, 'test3@stu.nus.edu', 'test3', 'test3', 2, 1, 'S2000001K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (6, 'test4@stu.nus.edu', 'test4', 'test4', 1, 1, 'S1000003K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (7, 'test1@lec.nus.edu', 'test1-lecturer', 'test1', 1, 2, 'L1000001K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (8, 'test2@lec.nus.edu', 'test2-lecturer', 'test2', 1, 2, 'L1000002K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (9, 'test3@lec.nus.edu', 'test3-lecturer', 'test3', 2, 2, 'L2000001K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (10, 'test4@lec.nus.edu', 'test4-lecturer', 'test4', 1, 2, 'L1000010K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (11, 'test@admin.nus.edu', 'test-admin', 'test', 8, 3, 'A1000001K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (12, 'satotroy.ji@gmail.com', 'emailtest', 'emailtest', 2, 1, 'S2000003K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (13, 'javaCAstudent@gmail.com', 'nusstu/CAtest', 'javacatest', 1, 1, 'S1000005K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (18, 'test5@lec.nus.edu', 'test5Science', 'test5science', 3, 2, 'L3000001K');
INSERT INTO javaca.student (id, email, name, password, collage_id, role_id, student_id) VALUES (19, 'test6@lec.nus.edu', 'test6Computing', 'test6computing', 2, 2, 'L2000002K');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (1, true, 'SA4101', 'Design', 6, '10:00:00', 'Beacon', 30, '08:00:00', 1, '10101');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (2, true, 'SA4102', 'C#', 8, '12:00:00', 'Beacon', 30, '10:00:00', 1, '10101');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (3, false, 'SA4105', 'Web Application Development', 6, '16:00:00', 'Beacon', 3, '13:00:00', 1, '01010');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (4, true, 'SA4108', 'Mobile Application Development', 6, '16:00:00', 'Beacon', 45, '14:00:00', 1, '00011');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (11, false, 'SA4104', 'Digital Product Management', 4, '11:00:00', 'Beacon', 15, '09:00:00', 1, '10001');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (12, true, 'SA4106', 'AD Project', 6, '18:00:00', 'LT-27', 25, '13:00:00', 1, '00011');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (13, false, 'SA4107', 'Industrial Attachment Project', 12, '18:00:00', 'COM-201', 45, '15:00:00', 1, '01001');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (14, true, 'SA4110', 'Machine Learning Application Development', 6, '12:00:00', 'LT-31', 30, '09:00:00', 1, '00010');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (15, false, 'SA5211', 'Sampling from Finite Populations', 6, '20:00:00', 'Sci-104', 50, '14:00:00', 1, '10000');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (17, false, 'CS1010', 'Programming Methodology', 4, '15:00:00', 'COM-305', 25, '13:00:00', 2, '10100');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (18, true, 'CS1231', 'Discrete Structures', 4, '20:00:00', 'COM-602', 15, '19:00:00', 2, '01010');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (19, true, 'ST2131', 'Probability', 4, '20:00:00', 'LT-19', 20, '15:00:00', 3, '00100');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (20, true, 'ST2132', 'Mathematical Statistics', 4, '20:00:00', 'SCI-102', 45, '18:00:00', 3, '00001');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (21, true, 'MA3264', 'Mathematical Modelling', 4, '09:00:00', 'Sci-502', 35, '11:00:00', 3, '10010');
INSERT INTO javaca.course (id, compulsory, course_id, coursename, credit, endingtime, room, size, startingtime, collage_id, date) VALUES (30, false, 'HY2263', 'The Ancient World: Ancient Greece', 4, '20:00:00', 'LT-13', 15, '19:00:00', 3, '00001');
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (5, true, false, false, 3, 4, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (6, true, false, false, 3, 5, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (15, true, false, false, 4, 3, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (17, true, false, false, 3, 6, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (18, true, false, false, 13, 3, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (19, true, true, false, 2, 3, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (20, true, false, false, 14, 4, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (21, true, false, false, 1, 3, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (22, true, false, false, 14, 3, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (30, true, false, false, 18, 4, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (31, true, false, false, 20, 4, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (33, true, false, false, 19, 4, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (34, true, false, false, 2, 4, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (35, false, false, false, 19, 3, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (36, true, true, false, 15, 3, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (40, false, false, false, 11, 3, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (41, true, false, false, 20, 3, true);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (42, false, false, false, 12, 3, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (43, true, false, false, 2, 6, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (44, false, false, false, 12, 6, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (45, false, false, false, 18, 3, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (46, true, false, false, 1, 5, false);
INSERT INTO javaca.enrollment (id, is_enroll, is_failed, is_reject, course_id, student_id, is_complete) VALUES (47, true, false, false, 21, 12, false);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (1, 15, 2, 3);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (2, 72, 4, 3);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (3, 18, 1, 3);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (12, 70, 14, 4);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (13, 88.3, 20, 4);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (16, 45, 13, 3);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (17, 90, 3, 6);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (18, 19, 15, 3);
INSERT INTO javaca.grade (id, coursemark, course_id, student_id) VALUES (19, 89.11999999999999, 20, 3);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 1);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (7, 1);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (7, 2);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (7, 3);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (10, 3);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (9, 4);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 4);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (9, 4);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 11);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (7, 12);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 12);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (10, 13);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (9, 14);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (10, 14);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 15);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 17);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (9, 18);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (7, 19);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (7, 20);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 21);
INSERT INTO javaca.lecturer_course (student_id, course_id) VALUES (8, 30);
