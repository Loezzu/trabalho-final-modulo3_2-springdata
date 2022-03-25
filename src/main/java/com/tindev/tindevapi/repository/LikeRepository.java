package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    LikeEntity findByUserIdAndLikedUserId(Integer userId, Integer likedUserId);
//
//    private static final List<Like> likeList = new ArrayList<>();
//    private final AtomicInteger COUNTER = new AtomicInteger();
//
//    @Autowired
//    private MatchService matchService;
//
//    public List<Like> list() {
//        return likeList;
//    }
//
//    public List<Like> listLikesByUserId(UserDTO user) {
//        return likeList.stream()
//                .filter(like -> like.getUserId().equals(user.getUserId()))
//                .collect(Collectors.toList());
//    }
//
//    public Like darLike(Integer userId, Integer likedUserId) throws Exception {
//        for (Like likes : likeList){
//            if(likes.getUserId().equals(userId) && likes.getLikedUserId().equals(likedUserId)){
//                throw new RegraDeNegocioException("Você ja deu like nessa pessoa");
//            }
//        }
//        Like like = new Like(COUNTER.incrementAndGet(), userId, likedUserId);
//        if (like.getUserId().equals(like.getLikedUserId())) {
//            throw new RegraDeNegocioException("Não é possível dar like para você mesmo");
//        }
//        likeList.add(like);
//        if(likeList.stream().map(Like::getUserId).toList().contains(like.getLikedUserId()) && likeList.stream().map(Like::getLikedUserId).toList().contains(like.getUserId())){
//            matchService.addMatch(userId, likedUserId);
//        }
//        return like;
//    }
//
//
//    public void removerLike(Integer id) throws Exception {
//        Like like = likeList.stream().filter(l -> l.getLikeId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RegraDeNegocioException("Like não encontrado"));
//        likeList.remove(like);
//    }
//
//    public void removeAllLikesByUserId(Integer id) {
//        likeList.removeIf(like -> like.getUserId().equals(id));
//    }
//
//    public Like getUserLike(Integer id) throws Exception {
//        return likeList.stream()
//                .filter(like -> like.getUserId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RegraDeNegocioException("Like não encontrado"));
//    }

}
