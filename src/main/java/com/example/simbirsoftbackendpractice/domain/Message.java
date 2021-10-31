package com.example.simbirsoftbackendpractice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Message {

    @Id
    private Long id;

    String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    Room room;
}
