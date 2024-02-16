package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import shop.mtcoding.blog.user.User;

import java.util.List;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private Boolean boardOwner;


        //        private List<ReplyDTO> replies;
        public void isBoardOwner(User sessinUser) {
            if (sessinUser == null) {
                boardOwner = false;
            } else {
                boardOwner = sessinUser.getId() == userId;
            }
        }
    }

    @Data
    public static class ReplyDTO {
        private Integer id;
        private Integer userId;
        private String username;
        private String comment;
        private Boolean replyOwner ; // 세션값이랑 비교
        public ReplyDTO(Object[] ob,User sessionUser) {
            this.id = (Integer) ob[0];
            this.userId = (Integer) ob[1];
            this.comment = (String) ob[2];
            this.username = (String) ob[3];

            if(sessionUser==null){
                replyOwner=false;

            }else {
                replyOwner = sessionUser.getId()==userId;
            }

        }
    }
}