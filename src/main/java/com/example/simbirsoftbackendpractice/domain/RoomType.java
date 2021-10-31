package com.example.simbirsoftbackendpractice.domain;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class RoomType {

    @Id
    private Long id;

    private String name;

    @Column(name = "max_members_num")
    private Integer maxMembersNumber;

    @OneToMany(mappedBy = "room_type")
    private Set<Room> rooms = new HashSet<>();
}
