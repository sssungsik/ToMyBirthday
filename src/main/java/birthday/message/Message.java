package birthday.message;


import birthday.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private LocalDateTime createDate;
    private String username;
    private String nickname;


}


/* @Id : id 속성에 적용한 @Id 애너테이션은 id 속성을 기본키로 지정한다.*/

/* @GeneratedValue  자동으로 1씩 증가하여 저장된다.*/

/* @Column 열의 길이를 설정할 때 사용
   columnDefinition 은 열 데이터의 유형이나 성격을 정의할 때 사용*/


/*
CascadeType.REMOVE
질문을 삭제하면 그에 달린 답변들도 모두 삭제되도록 함.
*/
