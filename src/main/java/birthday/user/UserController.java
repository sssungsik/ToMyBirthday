package birthday.user;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordIncorrect", "비밀번호가 일치하지 않아요.");
            return "signup_form";
        }
        try {
            userService.create(userCreateForm.getUsername(),  userCreateForm.getPassword1(), userCreateForm.getBirthday());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }  catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {
        return "login_form2";
    }


    @PostMapping("/kakao-login")
    public String kakaoLogin(@RequestBody Map<String, String> userInfo) {
        String kakaoId = userInfo.get("id");
        String nickname = userInfo.get("nickname");
        String profileImage = userInfo.get("profileImage");

        try {
            // 카카오 로그인 정보를 사용하여 사용자 저장 또는 업데이트
            userService.saveOrUpdateKakaoUser(kakaoId, nickname, profileImage);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error"; // 에러 처리 페이지
        }

        return "redirect:/"; // 로그인 성공 후 리다이렉트할 페이지
    }
}