package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select tu from tindev_user tu join fetch like_tindev_user ltu " +
            "on tu.userId = ltu.likedUserId where ltu.userId = (:id)")
    List<UserEntity> listLikesById (@Param("id") Integer id);


    @Query("select tu from tindev_user tu join fetch like_tindev_user ltu " +
            "on tu.userId = ltu.userId where ltu.likedUserId = (:id)")
    List<UserEntity> listReceivedLikesById (@Param("id") Integer id);


    @Query("select tu from tindev_user tu join fetch MATCH_TINDEV_USER mtu " +
            "on tu.userId = mtu.matchedUserFirst or tu.userId = mtu.matchedUserSecond and tu.userId = (:id)")
    List<UserEntity> listMatchesOfTheUser(@Param("id") Integer id);

}