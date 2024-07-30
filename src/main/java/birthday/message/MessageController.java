package birthday.message;



import birthday.user.SiteUser;
import birthday.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Controller
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/message")
    public String getMessageList(@RequestParam(required = false) String username, Model model, Principal principal) {
        if (username == null && principal != null) {
            username = principal.getName();
        }
        if (username != null) {
            List<Message> messages = messageService.getMessagesByUsername(username);
            List<SiteUser> users = userService.getUserByUsername(username);
       ;
            if (messages.isEmpty()) {
                model.addAttribute("error", "No messages found for the user.");
                model.addAttribute("username", username);
                model.addAttribute("users", users);
            } else {
                model.addAttribute("messages", messages);
                model.addAttribute("username", username);
                model.addAttribute("users", users);
            }
        } else {
            model.addAttribute("error", "User not found.");
        }
        return "message_list";
    }
    @GetMapping("/message/new")
    public String showNewMessageForm(Model model, @RequestParam String username) {
        model.addAttribute("username", username);
        return "message_form";
    }

    @PostMapping("/message/new")
    public String submitNewMessage(@RequestParam String title, @RequestParam String content,
                                   @RequestParam String nickname, @RequestParam String username
                                  ) {
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);
        message.setNickname(nickname);
        message.setUsername(username);
       

        messageService.saveMessage(message);
        return "redirect:/message?username=" + username;
    }
    @GetMapping("/message/{id}")
    public String getMessageDetail(@PathVariable Integer id, Model model, Principal principal) {
        Optional<Message> optionalMessage = messageService.getMessageById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            if (principal != null && message.getUsername().equals(principal.getName())) {
                model.addAttribute("message", message);
            } else {
                model.addAttribute("error", "권한이 없습니다.");
            }
        } else {
            model.addAttribute("error", "메시지를 찾을 수 없습니다.");
        }
        return "message_detail";
    }


}
