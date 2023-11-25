-- tbl_short_form
INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, title, thumbnail_url_kr, url_kr, thumbnail_url_eng, url_eng)
VALUES (1, '2023-10-20T04:43:29.783+00:00', '첫번째 영상 설명', FALSE, 1, '이건 제목입니다.',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.mp4?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/9e4e1fa1-ffd4-4c51-a828-70bbadc57e6a20231103150452.mp4?alt=media');

INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no,  title, thumbnail_url_kr,url_kr, thumbnail_url_eng, url_eng)
VALUES (2, '2023-10-20T04:43:29.783+00:00', '두번째 영상 설명', FALSE, 1, 'JEMOK',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.mp4?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f1088f68-e0a4-4a4b-82ea-b73528e098ec20231102180049.mp4?alt=media');

INSERT INTO tbl_short_form(short_form_no, date, description, is_interactive, member_no, title, thumbnail_url_kr, url_kr, thumbnail_url_eng, url_eng)
VALUES (3, '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', TRUE, 1, '인터랙티브 무비 제목!',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff293f9a8-3606-4c24-91c4-b99c441049b320231112192510.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f293f9a8-3606-4c24-91c4-b99c441049b320231112192510.mp4?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff293f9a8-3606-4c24-91c4-b99c441049b320231112192510.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f293f9a8-3606-4c24-91c4-b99c441049b320231112192510.mp4?alt=media');

-- tbl_interactive_movie
INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, title, thumbnail_url_kr,url_kr, thumbnail_url_eng, url_eng)
VALUES (1, '선택지1111111111', '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', 1, 1, 3, '인터랙티브 무비 제목!',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2F8b183d6d-0f54-41fb-8d1b-dd7a2092308320231112192515.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/8b183d6d-0f54-41fb-8d1b-dd7a2092308320231112192515.mp4?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff293f9a8-3606-4c24-91c4-b99c441049b320231112192510.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f293f9a8-3606-4c24-91c4-b99c441049b320231112192510.mp4?alt=media');

INSERT INTO tbl_interactive_movie(interactive_movie_no, choice, date, description, member_no, sequence, short_form_no, title, thumbnail_url_kr, url_kr, thumbnail_url_eng, url_eng)
VALUES (2, '선택지2222222222', '2023-10-20T04:43:29.783+00:00', '인터랙티브 무비 설명설명설명명며여명며염', 1, 2, 3, '인터랙티브 무비 제목!',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Faf1519ff-19fa-4dea-999b-51d21371798820231102143428.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/af1519ff-19fa-4dea-999b-51d21371798820231102143428.mp4?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/thumbnails%2Ff293f9a8-3606-4c24-91c4-b99c441049b320231112192510.jpeg?alt=media',
        'https://firebasestorage.googleapis.com/v0/b/meta-mingle.appspot.com/o/f293f9a8-3606-4c24-91c4-b99c441049b320231112192510.mp4?alt=media');

-- tbl_member
INSERT INTO tbl_member(member_no, nickname, email)
VALUES (1, '메타밍글', 'email');

-- tbl_script
-- INSERT INTO tbl_script(script_no, member_no, short_form_no, member_content, ai_content, upload_date)
-- VALUES (1, 1, 1, 'member content', 'ai content', '2023-10-10');


INSERT INTO tbl_quiz_rank (rank_no, date, korean, english, short_form_no)
VALUES
    (1, '2023-11-21', '한국에서 "당근이세요?"라는 말을 사람들이 주로 어떤 상황에서 쓸까요?', 'In what situation do people mainly use the phrase "당근이세요?"', 1),
    (2, '2023-11-21', '한국 카페에 가서 먼저 확인해야하는 것은 무엇일까요?', 'What is the first thing to check when you go to a cafe?', 2),
    (3, '2023-11-21', '한국에서 원하지 않는 버스가 정류장에 왔을 때 어떤 행동을 해야할까요?', 'What should you do when a bus you don''t want comes to the bus stop?', 3),
    (4, '2023-11-21', '최애 라는 뜻을 가진 미국 슬랭은 무엇일까요?', 'What is the American slang that means "favorite"?', 4),
    (5, '2023-11-21', '미국에서도 지역 특성에 따른 방언이 존재한다는 사실, 사실일까요?', ' Is it true that there are dialects in the U.S. depending on the regional characteristics?', 5),
    (6, '2023-11-21', '미국에서 남자들이 비가 와도 우산을 사용하지 않는 이유는 무엇일까요?', 'What is the reason men in America don''t use umbrellas even when it''s raining?', 6),
    (7, '2023-11-21', '미국에서 ''인스타각''을 표현하는 슬랭은 무엇일까요?', 'What is the slang used to describe ''Instagram worthy'' in America?', 7),
    (8, '2023-11-21', '미국의 할로윈에서 아이들이 주로 외치는 구절은 무엇일까요?', 'What is the phrase that children usually shout on Halloween in America?', 8),
    (9, '2023-11-21', '미국에서 "잘했다" 라는 표현을 반려견에게 하면 어떤 말을 사용할까요?', 'What phrase do they use in America to tell a pet dog "Well done"?', 9),
    (10, '2023-11-21', '한국에서 악수를 할 때 연장자와 어린 사람 중 누가 먼저 악수를 해야 할까요?', 'In Korea, who should shake hands first, the elder or the younger person?', 10),
    (11, '2023-11-21', '미국에서는 하루에 보통 몇 번 양치를 하나요?', 'How many times a day do people usually brush their teeth in America?', 11),
    (12, '2023-11-21', '한국에서 카페에 가면 사람들이 주로 어떤 커피를 마시나요?', 'What type of coffee do people usually drink in a cafe in Korea?', 12);
