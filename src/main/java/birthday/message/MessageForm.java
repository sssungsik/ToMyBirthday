package birthday.message;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageForm {
    @NotEmpty(message = "제목을 입력하세요")
    @Size(max=25)
    private String title;

    @NotEmpty(message = "내용을 입력하세요")
    private String content;

}
