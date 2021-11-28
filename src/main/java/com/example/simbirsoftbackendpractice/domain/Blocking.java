package com.example.simbirsoftbackendpractice.domain;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Entity
public class Blocking {

    public Blocking() {}

    public Blocking(User user, int minutes) {
        this.user = user;
        blockDate = ZonedDateTime.now();
        unblockDate = ZonedDateTime.now();
        unblockDate.plusMinutes(minutes);
    }

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "block_date")
    private ZonedDateTime blockDate;

    @Column(name = "unblock_date")
    private ZonedDateTime unblockDate;

}
