package urfu.emelianov.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import urfu.emelianov.entity.Player;

import java.util.Objects;

@Controller
public class PlayerWebSocketController {

    @MessageMapping("/list.updatePlayersList")
    @SendTo("/topic/public")
    public Player updatePlayersList(@Payload Player player) {
        return player;
    }

    @MessageMapping("/list.addPlayer")
    @SendTo("/topic/public")
    public Player addPlayer(@Payload Player player,
                            SimpMessageHeaderAccessor headerAccessor) {
        // Добавление игрока в веб сокет сессию
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("name", player.getName());
        return player;
    }
}
