-- Drop tables
DROP TABLE IF EXISTS university_exam_record;
DROP TABLE IF EXISTS university_exam_image;
DROP TABLE IF EXISTS university_exam;
DROP TABLE IF EXISTS select_university;
DROP TABLE IF EXISTS university;
DROP TABLE IF EXISTS member;


-- Create tables
CREATE TABLE member (birth_year VARCHAR(4), gender VARCHAR(1), ticket_count INTEGER NOT NULL, member_id BIGINT NOT NULL AUTO_INCREMENT, ticket_previous_publication_time DATETIME(6), phone_number VARCHAR(13) NOT NULL, email VARCHAR(255) NOT NULL, name VARCHAR(255) NOT NULL, platform_type VARCHAR(255) NOT NULL, role VARCHAR(255) NOT NULL, PRIMARY KEY (member_id));
CREATE TABLE university (university_id BIGINT NOT NULL AUTO_INCREMENT, university_college VARCHAR(255) NOT NULL, university_name VARCHAR(255) NOT NULL, PRIMARY KEY (university_id));
CREATE TABLE select_university (member_id BIGINT, select_university_id BIGINT NOT NULL AUTO_INCREMENT, university_id BIGINT, PRIMARY KEY (select_university_id), CONSTRAINT FKec0867nm7obvy1x99a9tk9rmb FOREIGN KEY (member_id) REFERENCES member (member_id), CONSTRAINT FK3pb0dmtg91s615viu7hrb7yaa FOREIGN KEY (university_id) REFERENCES university (university_id));
CREATE TABLE university_exam (exam_time_limit INTEGER NOT NULL, exam_year INTEGER NOT NULL, university_exam_id BIGINT NOT NULL AUTO_INCREMENT, university_id BIGINT, exam_answer_url VARCHAR(255) NOT NULL, exam_name VARCHAR(255) NOT NULL, PRIMARY KEY (university_exam_id), CONSTRAINT FKrm0hmkec0inobawjyl5dxva4w FOREIGN KEY (university_id) REFERENCES university (university_id));
CREATE TABLE university_exam_image (university_exam_id BIGINT, university_exam_image_id BIGINT NOT NULL AUTO_INCREMENT, university_exam_image_url VARCHAR(255) NOT NULL, PRIMARY KEY (university_exam_image_id), CONSTRAINT FKrnbevxfm5321rafunrqw9w688 FOREIGN KEY (university_exam_id) REFERENCES university_exam (university_exam_id)) ENGINE=InnoDB;
CREATE TABLE university_exam_record (time_take_exam INTEGER NOT NULL, member_id BIGINT, university_exam_id BIGINT, university_exam_record_id BIGINT NOT NULL AUTO_INCREMENT, exam_record_result_url VARCHAR(255) NOT NULL, exam_record_sheet_url VARCHAR(255) NOT NULL, exam_result_status VARCHAR(255) CHECK (exam_result_status IN ('ONGOING', 'FINISH')) NOT NULL, PRIMARY KEY (university_exam_record_id), CONSTRAINT FKeuxybcdgr72j6se1mjco3vtxu FOREIGN KEY (member_id) REFERENCES member (member_id), CONSTRAINT FKbm9ythclpkhsr1oa7nooqgl1q FOREIGN KEY (university_exam_id) REFERENCES university_exam (university_exam_id));
