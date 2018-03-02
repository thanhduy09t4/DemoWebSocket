package springboot.test.DemoWebSocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import springboot.test.DemoWebSocket.model.ChatMessage;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	System.out.println("12314");
    	if(chatMessage.getContent().equals("1")) {
    		chatMessage.setSender("Hệ thống");
    		chatMessage.setContent("đừng viết bậy nha");
    	}
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println("aaaa");
        String sender = chatMessage.getSender();
        chatMessage.setSender("con gà" + sender);
        return chatMessage;
    }

}
