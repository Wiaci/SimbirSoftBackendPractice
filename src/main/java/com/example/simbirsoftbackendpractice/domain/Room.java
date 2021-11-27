package com.example.simbirsoftbackendpractice.domain;

import lombok.Data;
import org.springframework.cglib.core.GeneratorStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    public Room() {};

    public Room(String name, User owner, RoomType roomType) {
        this.name = name;
        this.owner = owner;
        this.roomType = roomType;
    }

    @OneToMany(mappedBy = "room")
    Set<Message> messages = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_in_rooms",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

}
