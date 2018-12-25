package com.bestchoice.chatbot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bestchoice.chatbot.models.BadWord;

public interface BadwordRepository extends CrudRepository<BadWord, String> {

}
