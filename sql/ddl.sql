-- 게시글
CREATE TABLE Board (
           id           INT           primary key COMMENT '게시글id', -- 게시글id
           author       VARCHAR(200)  NOT NULL COMMENT '작성자', -- 작성자
           subject      VARCHAR(300)  NOT NULL COMMENT '제목', -- 제목
           content      VARCHAR(2000) NOT NULL COMMENT '내용', -- 내용
           writeDate    DATE          NOT NULL COMMENT '작성일', -- 작성일
           writeTime    TIME          NOT NULL COMMENT '작성시각', -- 작성시각
           readCount    INT           NOT NULL COMMENT '조회수', -- 조회수
           commentCount INT           NOT NULL COMMENT '댓글수', -- 댓글수
           password     VARCHAR(300)  NOT NULL default '0000' COMMENT '수정삭제비밀번호' -- 수정삭제비밀번호
)
    COMMENT '게시글';

-- 댓글
CREATE TABLE Comment (
             cid       INT           primary key COMMENT '댓글id', -- 댓글id
             id        INT           NOT NULL COMMENT '게시글id', -- 게시글id
             author    VARCHAR(200)  NOT NULL COMMENT '작성자', -- 작성자
             content   VARCHAR(2000) NOT NULL COMMENT '댓글내용', -- 댓글내용
             writeDate DATE          NOT NULL COMMENT '작성일', -- 작성일
             writeTime TIME          NOT NULL COMMENT '작성시각' -- 작성시각
)
    COMMENT '댓글';

-- 댓글 - 관계설정 (1:N)
ALTER TABLE Comment
    ADD CONSTRAINT FK_Board_TO_Comment -- 게시글 -> 댓글
        FOREIGN KEY (
                     id -- 게시글id
            )
            REFERENCES Board ( -- 게시글
                              id -- 게시글id
                );


insert into board values (1, 'testAuthor', 'testSubject', 'testContent', CURDATE(), CURTIME(), 0, 0)