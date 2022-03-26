package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.LikeEntity;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.LikeRepository;
import com.tindev.tindevapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ObjectMapper objectMapper;
    private final MatchService matchService;

    public List<LikeDTO> listAllLikes() {
        return likeRepository.findAll()
                .stream()
                .map(like -> objectMapper.convertValue(like, LikeDTO.class))
                .collect(Collectors.toList());
    }

    public LikeDTO darLike(Integer userId, Integer likedUserId) throws Exception {
        if (likeRepository.findByUserIdAndLikedUserId(userId, likedUserId) != null) {
            throw new RegraDeNegocioException("like já existe");
        }
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setUserId(userId);
        likeEntity.setLikedUserId(likedUserId);
        likeRepository.save(likeEntity);
        if (likeRepository.findByUserIdAndLikedUserId(userId, likedUserId) != null &&
                likeRepository.findByLikedUserIdAndUserId(userId, likedUserId) != null) {
            matchService.addMatch(userId, likedUserId);
        }
        return objectMapper.convertValue(likeEntity, LikeDTO.class);
    }

//    public void deleteLike(Integer id) throws Exception {
//        likeRepository.removerLike(id);
//    }
//    public void deleteLikeByUserId(Integer id) throws Exception {
//        likeRepository.getUserLike(id);
//        likeRepository.removeAllLikesByUserId(id);
//    }


    }



