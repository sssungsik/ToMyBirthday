package birthday.message;



import birthday.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
            if (messages.isEmpty()) {
                model.addAttribute("error", "No messages found for the user.");
            } else {
                model.addAttribute("messages", messages);
                model.addAttribute("username", username);
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
                                   @RequestParam String nickname, @RequestParam String username) {
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


    /*@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String messageDelete(Principal principal, @PathVariable("id") Integer id) {
        Message message = this.messageService.getMessage(id);
        if(!message.getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.messageService.deleteMessage(message);
        return "redirect:/";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String messageCreate(MessageForm messageForm) {
        return "message_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String messageCreate(@Valid MessageForm messageForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "message_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.messageService.create(messageForm.getTitle(), messageForm.getContent(), siteUser);
        return "redirect:/message/list";

    }


    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Message> paging = this.messageService.getList(page);
        model.addAttribute("paging", paging);

        return "message_list";
    }
    @PreAuthorize("isAuthenticated()++")
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {

        Message message = this.messageService.getMessage(id);
        model.addAttribute("message", message);
        return "message_detail";

        // @PathVariable : URL 뒤 {id} 값처럼 변하는 값 얻을 때
    }
*/
}
