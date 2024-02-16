package shop.mtcoding.blog.Reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Controller

public class ReplyController {
    private final HttpSession session ;
    private final ReplyRepository replyRepository ;
    @PostMapping("/reply/save")
    public String write(ReplyRequest.WriteDTO requestDTO){

        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser==null){
            return "redirect:/loginForm";
        }

        // 유효성 검사

        // 핵심 로직

        replyRepository.save(requestDTO,sessionUser.getId());

        return "";
    }

}
