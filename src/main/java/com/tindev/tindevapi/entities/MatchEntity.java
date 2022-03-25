package com.tindev.tindevapi.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "MATCH_TINDEV_USER")
public class MatchEntity {

    @Id
    @Column(name = "ID_MATCH", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;

    @Column(name = "USER_ID_FIRST")
    private Integer matchedUserFirst;

    @Column(name = "USER_ID_SECOND")
    private Integer matchedUserSecond;


}
