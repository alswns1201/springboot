package com.java.effective.study.web;

import com.java.effective.study.config.auth.LoginUser;
import com.java.effective.study.config.auth.dto.SessionUser;
import com.java.effective.study.service.posts.PostService;
import com.java.effective.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
//    private final HttpSession httpSession;


    /**
     *
     * @param model
     * @param SessionUser
     * @return
     */
    @GetMapping("/") // 초기 화면
    public String index(Model model ,@LoginUser  SessionUser SessionUser){

        //@LoginUser 사용해서 자동으로 주입이되어, SessionUser user = (SessionUser) httpSession.getAttribute("user"); 사용 할 필요가 없다.

        model.addAttribute("posts",postService.findAllDesc());

        //로그인 관련 로직 추가 model에 정보를 담는다.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(SessionUser !=null){
            model.addAttribute("userName",SessionUser.getName());
        }

        return "index"; // resources/templates/index를 화면에 return
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }


}
