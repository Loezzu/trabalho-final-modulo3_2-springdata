package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select pi from personinfo pi inner join like_tindev_user ltu " +
            "on pi.idPersonInfo = ltu.likedUserId where (:id) = ltu.userId")
    List<UserEntity> listLikesById (Integer id);


    @Query("select pi from personinfo pi inner join like_tindev_user ltu " +
            "on pi.idPersonInfo = ltu.userId where ltu.likedUserId = (:id)")
    List<UserEntity> listReceivedLikesById (Integer id);

}