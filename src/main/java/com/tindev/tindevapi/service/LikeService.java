package com.tindev.tindevapi.service;

import org.springframework.stereotype.Service;

@Service
public class LikeService {

//    @Autowired
//    private LikeRepository likeRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    public List<LikeDTO> listAllLikes(){
//        return likeRepository.list().stream().map(like -> objectMapper.convertValue(like, LikeDTO.class)).collect(Collectors.toList());
//    }
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
//    public LikeDTO darLike(Integer userId, Integer likedUserId) throws Exception {
//        Like like = likeRepository.darLike(userId, likedUserId);
//        return objectMapper.convertValue(like, LikeDTO.class);
//
//    }
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
