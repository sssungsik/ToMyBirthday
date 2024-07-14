package birthday.message;


import birthday.DataNotFoundException;
import birthday.user.SiteUser;
import birthday.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public List<Message> getMessagesByUsername(String username) {
        return messageRepository.findByUsername(username);
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public void saveMessage(Message message) {
        message.setCreateDate(LocalDateTime.now());
        messageRepository.save(message);
    }


}
