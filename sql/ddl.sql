create table Board(
                      id int not null primary key,    -- 글 id
                      author VARCHAR(200) not null,   -- 작성자 이름
                      subject VARCHAR(300) not null,  -- 글 제목
                      content VARCHAR(2000) not null, -- 글 컨텐츠
                      writeDate Date not null,        -- 작성 날짜
                      writeTime Time not null,        -- 작성 시간
                      readCount int not null,         -- 조회수
                      commentCount int not null      -- 댓글 갯수
)
    insert into board values (1, 'testAuthor', 'testSubject', 'testContent', CURDATE(), CURTIME(), 0, 0)