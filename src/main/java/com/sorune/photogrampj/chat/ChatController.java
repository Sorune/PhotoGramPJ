package com.sorune.photogrampj.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    @GetMapping("/rooms")
    public ResponseEntity<Map<String,Object>> getRooms(){
        return new ResponseEntity<>(Map.of(), HttpStatus.OK);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<Map<String,Object>> getMessages(@PathVariable long roomId){
        return new ResponseEntity<>(Map.of(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createRoom(){
        return new ResponseEntity<>(Map.of("result","room created"),HttpStatus.OK);
    }
}
