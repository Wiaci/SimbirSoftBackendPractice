package com.example.simbirsoftbackendpractice.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user")
    @Nullable
    private Blocking blocking;

    @ManyToMany
    @JoinTable(
            name = "user_in_rooms",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Room> managedRooms;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages;

}
