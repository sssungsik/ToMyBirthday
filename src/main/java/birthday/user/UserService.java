package birthday.user;

import birthday.DataNotFoundException;
import birthday.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String birthday) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setBirthday(birthday);

        user.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(user);
        return user;


    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");

        }
    }

    public List<SiteUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
