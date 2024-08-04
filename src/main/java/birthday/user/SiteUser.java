package birthday.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String birthday;

    // 카카오 로그인 사용자 식별을 위한 필드
    @Column(unique = true)
    private String kakaoId;

    private String profileImage;
}