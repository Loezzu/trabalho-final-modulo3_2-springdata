package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.entities.LikeEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.LikeRepository;
import com.tindev.tindevapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {


    private final LikeRepository likeRepository;


    private final UserService userService;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    public List<LikeDTO> listAllLikes(){
        return likeRepository.findAll()
                .stream()
                .map(like -> objectMapper.convertValue(like, LikeDTO.class))
                .collect(Collectors.toList());
    }


    public LikeDTO darLike(Integer userId, Integer likedUserId) throws Exception {
        if(likeRepository.findByUserIdAndLikedUserId(userId, likedUserId) != null){

            throw new Exception("like já existe");
        }
            LikeEntity likeEntity = new LikeEntity();
            likeEntity.setUserId(userId);
            likeEntity.setLikedUserId(likedUserId);
            likeEntity.setUserEntity(userRepository.findById(userId)
                    .orElseThrow(() -> new Exception("Usuário não encontrado")));
            likeRepository.save(likeEntity);


        if(likeRepository.findByUserIdAndLikedUserId(userId, likedUserId) != null &&
                likeRepository.findByLikedUserIdAndUserId(userId, likedUserId) != null){
            throw new RegraDeNegocioException("match");


        }


        return objectMapper.convertValue(likeEntity, LikeDTO.class);



    }





//
//    public List<LikeDTO> listLikesById(Integer id) throws Exception {
//
//       UserDTO user = userService.getUserById(id);
//
//       List<Like> likes = likeRepository.listLikesByUserId(user);
//
//       return objectMapper.convertValue(likes, objectMapper.getTypeFactory().constructCollectionType(List.class, LikeDTO.class));
//    }
//

//
//
//    public void deleteLike(Integer id) throws Exception {
//        likeRepository.removerLike(id);
//    }
//
//    public void deleteLikeByUserId(Integer id) throws Exception {
//        likeRepository.getUserLike(id);
//        likeRepository.removeAllLikesByUserId(id);
//    }
//






}
