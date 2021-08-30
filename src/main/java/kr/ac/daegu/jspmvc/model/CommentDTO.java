package kr.ac.daegu.jspmvc.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

// db에서 Board테이블의 컬럼과 row를 정의.
public class CommentDTO { //DataTransferObject : db에서 가져오는 테이블 묶음
    private int Cid;
    private int id;                      // 글 id(글번호)
    private String author;               // 작성자 이름
    private String subject;              // 글 제목
    private String content;              // 글 컨텐츠
    private Date writeDate;              // 작성 날짜
    private Time writeTime;              // 작성 시간


    public int getCid() {
        return Cid;
    }

    public void setCid(int cid) {
        Cid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public Time getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Time writeTime) {
        this.writeTime = writeTime;
    }


    @Override
    public String toString() {
        return "CommentDTO{" +
                "Cid=" + Cid +
                "id=" + id +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", writeDate=" + writeDate +
                ", writeTime=" + writeTime +
                '}';
    }
}