package com.fuyuzhen.towatalk_backend.repository;

import com.fuyuzhen.towatalk_backend.entity.MessageList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageListRepository extends JpaRepository<MessageList, Long>, JpaSpecificationExecutor {
}
