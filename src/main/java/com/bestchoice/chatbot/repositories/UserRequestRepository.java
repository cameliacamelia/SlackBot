package com.bestchoice.chatbot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bestchoice.chatbot.models.UserRequest;

public interface UserRequestRepository extends CrudRepository<UserRequest, String> {


}
