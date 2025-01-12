package com.fuyuzhen.towatalk_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "message_list")
@Data
public class MessageList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long sendUserId;
    Long receiveUserId;
    String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="UTC")
    Date sendTime;
}
