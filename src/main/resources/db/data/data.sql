-- tbl_short_form
-- INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
-- VALUES (1, '2023-10-20T04:43:29.783+00:00', '첫번째 영상 설명', FALSE, 1,
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.jpeg?alt=media', '이건 제목입니다.',
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.mp4?alt=media');
--
-- INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
-- VALUES (2, '2023-10-20T04:43:29.783+00:00', '두번째 영상 설명', FALSE, 1,
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.jpeg?alt=media', 'JEMOK',
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.mp4?alt=media');
--
-- INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
-- VALUES (3, '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', TRUE, 1,
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff293f9a8-3606-4c24-91c4-b99c441049b320231112192510.jpeg?alt=media', '인터랙티브 무비 제목!',
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f293f9a8-3606-4c24-91c4-b99c441049b320231112192510.mp4?alt=media');
--
-- -- tbl_interactive_movie
-- INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, thumbnail_url, title, url)
-- VALUES (1, '선택지1111111111', '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', 1, 1, 3,
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F8b183d6d-0f54-41fb-8d1b-dd7a2092308320231112192515.jpeg?alt=media', '인터랙티브 무비 제목!',
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/8b183d6d-0f54-41fb-8d1b-dd7a2092308320231112192515.mp4?alt=media');
--
-- INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, thumbnail_url, title, url)
-- VALUES (2, '선택지2222222222', '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', 1, 2, 3,
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Faf1519ff-19fa-4dea-999b-51d21371798820231102143428.jpeg?alt=media', '인터랙티브 무비 제목!',
--         'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/af1519ff-19fa-4dea-999b-51d21371798820231102143428.mp4?alt=media');



-- tbl_script
-- INSERT INTO tbl_script(script_no, member_no, short_form_no, member_content, ai_content, upload_date)
-- VALUES (1, 1, 1, 'member content', 'ai content', '2023-10-10');

-- tbl_member
INSERT INTO tbl_member(member_no, email, password, nickname, role)
VALUES
    (1, 'test1@gmail.com', 'password', '닉네임1', 'ROLE_MEMBER'),
    (2, 'test2@gmail.com', 'password', '닉네임2', 'ROLE_MEMBER'),
    (3, 'test3@gmail.com', 'password', '닉네임3', 'ROLE_MEMBER'),
    (4, 'test4@gmail.com', 'password', '닉네임4', 'ROLE_MEMBER'),
    (5, 'test5@gmail.com', 'password', '닉네임5', 'ROLE_MEMBER');

-- QUIZ scheduler 테스트
INSERT INTO tbl_quiz (korean, english, isquiz)
VALUES
    ('Korean1', 'English1', 'yes'),
    ('Korean2', 'English2', 'yes'),
    ('Korean3', 'English3', 'yes'),
    ('Korean4', 'English4', 'yes'),
    ('Korean5', 'English5', 'yes'),
    ('Korean6', 'English6', 'yes'),
    ('Korean7', 'English7', 'yes'),
    ('Korean8', 'English8', 'yes'),
    ('Korean9', 'English9', 'yes'),
    ('Korean10', 'English10', 'yes'),
    ('Korean11', 'English11', 'yes'),
    ('Korean12', 'English12', 'yes'),
    ('Korean13', 'English13', 'yes'),
    ('Korean14', 'English14', 'yes'),
    ('Korean15', 'English15', 'yes'),
    ('Korean16', 'English16', 'yes'),
    ('Korean17', 'English17', 'yes'),
    ('Korean18', 'English18', 'yes'),
    ('Korean19', 'English19', 'yes'),
    ('Korean20', 'English20', 'yes'),
    ('Korean21', 'English21', 'yes'),
    ('Korean22', 'English22', 'yes'),
    ('Korean23', 'English23', 'yes'),
    ('Korean24', 'English24', 'yes');

INSERT INTO tbl_short_form (title, thumbnail_url, url, description, date, is_interactive)
VALUES
    ('Title1', 'Thumbnail1', 'Url1', 'Description1', '2023-11-20', false),
    ('Title2', 'Thumbnail2', 'Url2', 'Description2', '2023-11-20', true),
    ('Title3', 'Thumbnail3', 'Url3', 'Description3', '2023-11-20', false),
    ('Title4', 'Thumbnail4', 'Url4', 'Description4', '2023-11-20', true),
    ('Title5', 'Thumbnail5', 'Url5', 'Description5', '2023-11-20', false),
    ('Title6', 'Thumbnail6', 'Url6', 'Description6', '2023-11-20', true),
    ('Title7', 'Thumbnail7', 'Url7', 'Description7', '2023-11-20', false),
    ('Title8', 'Thumbnail8', 'Url8', 'Description8', '2023-11-20', true),
    ('Title9', 'Thumbnail9', 'Url9', 'Description9', '2023-11-20', false),
    ('Title10', 'Thumbnail10', 'Url10', 'Description10', '2023-11-20', true),
    ('Title11', 'Thumbnail11', 'Url11', 'Description11', '2023-11-20', false),
    ('Title12', 'Thumbnail12', 'Url12', 'Description12', '2023-11-20', true),
    ('Title13', 'Thumbnail13', 'Url13', 'Description13', '2023-11-20', false),
    ('Title14', 'Thumbnail14', 'Url14', 'Description14', '2023-11-20', true),
    ('Title15', 'Thumbnail15', 'Url15', 'Description15', '2023-11-20', false),
    ('Title16', 'Thumbnail16', 'Url16', 'Description16', '2023-11-20', true),
    ('Title17', 'Thumbnail17', 'Url17', 'Description17', '2023-11-20', false),
    ('Title18', 'Thumbnail18', 'Url18', 'Description18', '2023-11-20', true),
    ('Title19', 'Thumbnail19', 'Url19', 'Description19', '2023-11-20', false),
    ('Title20', 'Thumbnail20', 'Url20', 'Description20', '2023-11-20', true),
    ('Title21', 'Thumbnail21', 'Url21', 'Description21', '2023-11-20', true),
    ('Title22', 'Thumbnail22', 'Url22', 'Description22', '2023-11-20', true),
    ('Title23', 'Thumbnail23', 'Url23', 'Description23', '2023-11-20', true),
    ('Title24', 'Thumbnail24', 'Url24', 'Description24', '2023-11-20', true);

INSERT INTO tbl_short_form_like (member_no, short_form_no)
VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 2),
    (5, 3),
    (1, 4),
    (2, 4),
    (3, 5),
    (4, 5),
    (5, 6),
    (1, 7),
    (2, 7),
    (3, 8),
    (4, 8),
    (5, 9),
    (1, 10),
    (2, 10),
    (3, 11),
    (4, 11),
    (5, 12),
    (1, 13),
    (2, 13),
    (3, 14),
    (4, 14),
    (5, 15),
    (1, 16),
    (2, 16),
    (3, 17),
    (4, 17),
    (5, 18),
    (1, 19),
    (2, 19),
    (3, 20),
    (4, 20),
    (5, 21),
    (1, 22),
    (2, 22),
    (3, 23),
    (4, 23),
    (5, 24);


INSERT INTO tbl_quiz (korean, english, isquiz, short_form_no)
VALUES
    ('Korean1', 'English1', 'yes', 1),
    ('Korean2', 'English2', 'yes', 2),
    ('Korean3', 'English3', 'yes', 3),
    ('Korean4', 'English4', 'yes', 4),
    ('Korean5', 'English5', 'yes', 5),
    ('Korean6', 'English6', 'yes', 6),
    ('Korean7', 'English7', 'yes', 7),
    ('Korean8', 'English8', 'yes', 8),
    ('Korean9', 'English9', 'yes', 9),
    ('Korean10', 'English10', 'yes', 10),
    ('Korean11', 'English11', 'yes', 11),
    ('Korean12', 'English12', 'yes', 12),
    ('Korean13', 'English13', 'yes', 13),
    ('Korean14', 'English14', 'yes', 14),
    ('Korean15', 'English15', 'yes', 15),
    ('Korean16', 'English16', 'yes', 16),
    ('Korean17', 'English17', 'yes', 17),
    ('Korean18', 'English18', 'yes', 18),
    ('Korean19', 'English19', 'yes', 19),
    ('Korean20', 'English20', 'yes', 20),
    ('Korean21', 'English21', 'yes', 21),
    ('Korean22', 'English22', 'yes', 22),
    ('Korean23', 'English23', 'yes', 23),
    ('Korean24', 'English24', 'yes', 24);


INSERT INTO tbl_quiz_rank (rank_no, date, korean, english, short_form_no)
VALUES
    (1, '2023-11-21', '한국어1', 'English1', 1),
    (2, '2023-11-21', '한국어2', 'English2', 2),
    (3, '2023-11-21', '한국어3', 'English3', 3),
    (4, '2023-11-21', '한국어4', 'English4', 4),
    (5, '2023-11-21', '한국어5', 'English5', 5),
    (6, '2023-11-21', '한국어6', 'English6', 6),
    (7, '2023-11-21', '한국어7', 'English7', 7),
    (8, '2023-11-21', '한국어8', 'English8', 8),
    (9, '2023-11-21', '한국어9', 'English9', 9),
    (10, '2023-11-21', '한국어10', 'English10', 10),
    (11, '2023-11-21', '한국어11`', 'English11', 11),
    (12, '2023-11-21', '한국어12`', 'English12', 12);
