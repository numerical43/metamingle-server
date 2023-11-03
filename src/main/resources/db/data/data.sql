-- tbl_short_form
INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
VALUES (1, '2023-10-20T04:43:29.783+00:00', '첫번째 영상 설명', FALSE, 1,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Faf1519ff-19fa-4dea-999b-51d21371798820231102143428.jpeg?alt=media', '이건 제목입니다.',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/af1519ff-19fa-4dea-999b-51d21371798820231102143428.mp4?alt=media');

INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
VALUES (2, '2023-10-20T04:43:29.783+00:00', '두번째 영상 설명', FALSE, 1,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.jpeg?alt=media', 'JEMOK',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.mp4?alt=media');

INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
VALUES (3, '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', TRUE, 1,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Fd3f60c83-d523-442a-a16c-3cfc794ed1e520231103150446.jpeg?alt=media', '인터랙티브 무비 제목!',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/d3f60c83-d523-442a-a16c-3cfc794ed1e520231103150446.mp4?alt=media');

-- tbl_interactive_movie
INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, thumbnail_url, title, url)
VALUES (1, '선택지1111111111', '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', 1, 1, 3,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F5215839b-5f12-4b26-95d5-a41ee33059ca20231103150449.jpeg?alt=media', '인터랙티브 무비 제목!',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/5215839b-5f12-4b26-95d5-a41ee33059ca20231103150449.mp4?alt=media');

INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, thumbnail_url, title, url)
VALUES (2, '선택지2222222222', '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', 1, 2, 3,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.jpeg?alt=media', '인터랙티브 무비 제목!',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.mp4?alt=media');

-- tbl_member
INSERT INTO tbl_member(member_no, provider_id, member_name, nickname, email)
VALUES (1, '111111', '메타밍글', '닉네임', 'email');

-- tbl_script
INSERT INTO tbl_script(script_no, member_no, short_form_no, member_content, ai_content, upload_date)
VALUES (1, 1, 1, 'member content', 'ai content', '2023-10-10');