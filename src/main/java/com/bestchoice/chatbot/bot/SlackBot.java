package com.bestchoice.chatbot.bot;


import java.util.Date;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.bestchoice.chatbot.models.UserRequest;
import com.bestchoice.chatbot.repositories.UserRequestRepository;
import com.bestchoice.chatbot.service.BestChoiceSlackBotService;

import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;

@Component
public class SlackBot extends Bot {
	private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);
	
	@Autowired
	private BestChoiceSlackBotService slackService;
	
	private UserRequestRepository userRequestRepository;
	
	@Value("${slackBotToken}")
	private String slackToken;
	
	@Override
	public String getSlackToken() {
	    return slackToken;
	}

	@Override
	public Bot getSlackBot() {
	    return this;
	}
	
	
	@Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
	public void onReceiveDM(WebSocketSession session, Event event) {
	    reply(session, event, new Message("Hi, I am " + slackService.getCurrentUser().getName()));
	}
	
	
	@Controller(events = EventType.MESSAGE, pattern = "buy tv|")
	public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {
	    if(!matcher.group(0).isEmpty()) {
	        UserRequest userRequest = new UserRequest(event.getUserId(),matcher.group(0), new Date());
	        userRequestRepository.save(userRequest);
	    }
	}
}