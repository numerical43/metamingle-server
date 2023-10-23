-- tbl_short_form
INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
VALUES (1, '2023-10-20T04:43:29.783+00:00', '첫번째 영상 설명', FALSE, 1,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F4d47053e-5eaf-4f7c-8360-93df0e4ff6e220231020134326.jpeg?alt=media', '이건 제목입니다.',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/4d47053e-5eaf-4f7c-8360-93df0e4ff6e220231020134326.mp4?alt=media');

INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
VALUES (2, '2023-10-20T04:43:29.783+00:00', '두번째 영상 설명', FALSE, 1,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F5b8acaa2-0cc5-4826-83c6-e23ec181d8e020231020133238.jpeg?alt=media', 'JEMOK',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/5b8acaa2-0cc5-4826-83c6-e23ec181d8e020231020133238.mp4?alt=media');

INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, thumbnail_url, title, url)
VALUES (3, '2023-10-20T04:43:29.783+00:00', '영상 설명설명설명명며여명며염', TRUE, 1,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F5b8acaa2-0cc5-4826-83c6-e23ec181d8e020231020133238.jpeg?alt=media', '영상 제목!! 제목!!@!@!@!@',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/a4fe82ee-fd51-4137-81bd-7345f2a4b1c320231019200217.mp4?alt=media');

-- tbl_interactive_movie
INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, thumbnail_url, title, url)
VALUES (1, '선택지1111111111', '2023-10-20T04:43:29.783+00:00', '영상 설명설명설명명며여명며염', 1, 1, 3,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F5b8acaa2-0cc5-4826-83c6-e23ec181d8e020231020133238.jpeg?alt=media', '영상 제목!! 제목!!@!@!@!@',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/776df933-2e03-4e15-93a3-5f6ee889b1ce20231019195852.mp4?alt=media');

INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, thumbnail_url, title, url)
VALUES (2, '선택지2222222222', '2023-10-20T04:43:29.783+00:00', '영상 설명설명설명명며여명며염', 1, 2, 3,
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F5b8acaa2-0cc5-4826-83c6-e23ec181d8e020231020133238.jpeg?alt=media', '영상 제목!! 제목!!@!@!@!@',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/6edaff0f-fa4e-4a62-988d-f65b583c91b020231019195508.mp4?alt=media');

-- tbl_member
INSERT INTO tbl_member(member_no, provider_id, member_name, nickname, email)
VALUES (1, '111111', '메타밍글', '닉네임', 'email');