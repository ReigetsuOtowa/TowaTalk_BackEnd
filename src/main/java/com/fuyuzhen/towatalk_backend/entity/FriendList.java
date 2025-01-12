package com.fuyuzhen.towatalk_backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "friend_list")
@Data
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userId;
    Long friendUserId;
    String friendNickName;
    Boolean isDelete = false;
}
