package com.example.simbirsoftbackendpractice.domain;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Blocking {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "block_date")
    private Date blockDate;

    @Column(name = "unblock_date")
    private Date unblockDate;

}
