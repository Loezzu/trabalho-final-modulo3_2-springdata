package com.tindev.tindevapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "like_tindev_user")
public class LikeEntity {

    @Id
    @Column(name = "id_like", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "liked_user_id")
    private Integer likedUserId;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;

}
