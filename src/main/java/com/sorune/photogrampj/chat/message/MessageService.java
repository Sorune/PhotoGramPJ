package com.sorune.photogrampj.chat.message;

import com.sorune.photogrampj.repository.jpa.MessageRepository;
import com.sorune.photogrampj.repository.redis.RedisMessageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final RedisMessageRepository redisMessageRepository;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    private static final int MAX_REDIS_MESSAGES = 100;
}
