package com.alevel.java.messagestore.repository;

import com.alevel.java.messagestore.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}
