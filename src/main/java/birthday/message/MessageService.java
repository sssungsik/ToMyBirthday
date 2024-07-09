package birthday.message;


import birthday.DataNotFoundException;
import birthday.user.SiteUser;
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

    /*public List<Message> getList(){

        return this.messageRepository.findAll();
    }

    public Message getMessage(Integer id) {
        Optional<Message> message = this.messageRepository.findById(id);

        if (message.isPresent()) {
            return message.get();
        } else {
            throw new DataNotFoundException("메세지 not found");
        }
    }

    public void create(String title, String content, SiteUser user) {
        Message m = new Message();
        m.setTitle(title);
        m.setContent(content);
        m.setCreateDate(LocalDateTime.now());
        m.setAuthor(user);
        this.messageRepository.save(m);
    }

    public Page<Message> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.messageRepository.findAll(pageable);
    }
    public void deleteMessage(Message message) {
        this.messageRepository.delete(message);
    }*/

}
