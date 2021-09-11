package kr.ac.daegu.jspmvc.model;

public class MemberDTO {
        private int mId;
        private String id;
        private String password;
        private String salt;

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public int getmId() {
            return mId;
        }

        public void setmId(int mId) {
            this.mId = mId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "CommentDTO{" +
                    "mId=" + mId +
                    ", id=" + id +
                    ", password=" + password +
                    '}';
        }
    }

