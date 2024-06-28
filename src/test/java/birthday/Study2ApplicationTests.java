package birthday;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import birthday.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import birthday.answer.Answer;
import birthday.answer.AnswerRepository;
import birthday.message.MessageRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class Study2ApplicationTests {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void contextLoads() {

       /* 데이터 UPDATE
        Optional<Message> ms = this.messageRepository.findById(1);
        assertTrue(ms.isPresent());
        Message m = ms.get();
        m.setTitle("수정된 제목");
        this.messageRepository.save(m);
        */


        /* 데이터 DELETE
        Optional<Message> ms = this.messageRepository.findById(4);
        assertTrue(ms.isPresent());
        Message m = ms.get();
        this.messageRepository.delete(m);
        assertEquals(3, this.messageRepository.count());
        */


       /*    답변 데이터 저장
        Optional<Message> ms = this.messageRepository.findById(2);
        assertTrue(ms.isPresent());
        Message m = ms.get();

        Answer a = new Answer();
        a.setContent("안녕하세요 스프링부트입니다.응. ");
        a.setMessage(m); // 어떤 질문의 답변인지 알기 위해
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
        */

        /* 답변 데이터 조회
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getMessage().getId()); */

        Message m1 = new Message();
        m1.setTitle("안녕하세요");
        m1.setContent("축하해요");
        m1.setCreateDate(LocalDateTime.now());
        this.messageRepository.save(m1);



    }

}
//https://wikidocs.net/160890