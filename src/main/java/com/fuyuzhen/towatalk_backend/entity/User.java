package com.fuyuzhen.towatalk_backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String password;
    Integer sex;
    String avatar;
    Boolean isOnline = false;
}
