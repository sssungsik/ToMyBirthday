package birthday.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreateForm {

    @Size(min = 3, max = 25)
    @NotEmpty(message = "아이디는 필수항목이에요.")
    private String username;

    @NotEmpty(message = "비밀번호는 꼭 필요해요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 꼭 필요해요.")
    private String password2;

    @NotEmpty(message = "생일 입력은 꼭 필요해요.")
    private String birthday;
}
