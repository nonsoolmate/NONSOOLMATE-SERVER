INSERT INTO member (birth_year, gender, ticket_count, member_id, ticket_previous_publication_time, phone_number, email,
                    name, platform_type, role)
VALUES ('2001', 'F', 5, DEFAULT, '2024-01-09 12:30:00.000000', '010-4688-4301', 'wlgns72@naver.com', '김성은', 'NAVER',
        'USER'),
       ('1999', 'M', 5, DEFAULT, '2024-01-09 12:30:00.000000', '010-9472-6796', 'thdalsrb79@naver.com', '송민규', 'NAVER',
        'USER');
INSERT INTO university (university_id, university_college, university_name)
VALUES (1, '인문', '숙명여자대학교'),
       (2, '인문사회', '중앙대학교'),
       (3, '인문사회', '한양대학교');

INSERT INTO university_exam (exam_time_limit, exam_year, university_exam_id, university_id, exam_answer_url, exam_name)
VALUES (70, 2021, 1, 1, 'http://example.com/answer1', '숙명여자대학교 인문1'),
       (60, 2022, 2, 1, 'http://example.com/answer2', '숙명여자대학교 인문2'),
       (90, 2023, 3, 1, 'http://example.com/answer3', '숙명여자대학교 인문3');

INSERT INTO university_exam (exam_time_limit, exam_year, university_exam_id, university_id, exam_answer_url, exam_name)
VALUES (70, 2021, 4, 2, 'http://example.com/answer1', '중앙대학교 인문사회1'),
       (60, 2022, 5, 2, 'http://example.com/answer2', '중앙대학교 인문사회2'),
       (90, 2023, 6, 2, 'http://example.com/answer3', '중앙대학교 인문사회3');

INSERT INTO university_exam (exam_time_limit, exam_year, university_exam_id, university_id, exam_answer_url, exam_name)
VALUES (70, 2021, 7, 3, 'http://example.com/answer1', '한양대학교 인문사회1'),
       (60, 2022, 8, 3, 'http://example.com/answer2', '한양대학교 인문사회2'),
       (90, 2023, 9, 3, 'http://example.com/answer3', '한양대학교 인문사회3');

INSERT INTO university_exam_image (university_exam_id, university_exam_image_id, university_exam_image_url)
VALUES (1, 1, 'http://example.com/image1.jpg'),
       (1, 2, 'http://example.com/image2.jpg'),
       (1, 3, 'http://example.com/image3.jpg'),
       (1, 4, 'http://example.com/image2.jpg'),
       (1, 5, 'http://example.com/image3.jpg');

INSERT INTO university_exam_image (university_exam_id, university_exam_image_id, university_exam_image_url)
VALUES (2, 6, 'http://example.com/image1.jpg'),
       (2, 7, 'http://example.com/image2.jpg'),
       (2, 8, 'http://example.com/image3.jpg'),
       (2, 9, 'http://example.com/image2.jpg'),
       (2, 10, 'http://example.com/image3.jpg');

INSERT INTO select_university(select_university_id, member_id, university_id)
VALUES (1, 2, 1),
       (2, 2, 2),
       (3, 2, 3);