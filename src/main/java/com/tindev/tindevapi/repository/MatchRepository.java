package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.MatchEntity;
import com.tindev.tindevapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {







//    private static final List<Match> matchList = new ArrayList<>();
//    private final AtomicInteger COUNTER = new AtomicInteger();
//
//    @Autowired
//    private UserService userService;
//
//    public List<Match> list() {
//        return matchList;
//    }
//
//    public Match addMatch(Integer matchedUserIdFirst, Integer matchedUserIdSecond) throws Exception {
//        for(Match match : matchList){
//            if((match.getMatchedUserFirst().equals(matchedUserIdFirst) && match.getMatchedUserSecond().equals(matchedUserIdSecond))
//                    || match.getMatchedUserFirst().equals(matchedUserIdSecond) && match.getMatchedUserSecond().equals(matchedUserIdFirst)){
//                throw new RegraDeNegocioException("Você ja deu Match nessa pessoa!");
//            }
//        }
//        if(userService.getUserById(matchedUserIdFirst).getProgLangs().equals(userService.getUserById(matchedUserIdSecond).getProgLangs())){
//            Match match = new Match(COUNTER.incrementAndGet(), matchedUserIdFirst, matchedUserIdSecond);
//            matchList.add(match);
//            return match;
//        }
//        return null;
//    }
//
//    public void deleteMatch(Integer matchId) throws Exception {
//        matchList.stream()
//                .filter(match -> match.getMatchId().equals(matchId))
//                .findFirst()
//                .orElseThrow( () -> new RegraDeNegocioException("Match não encontrado"));
//
//        matchList.removeIf(match -> match.getMatchId().equals(matchId));
//    }
//
//    public List<Match> listMatchesOfUser(Integer idUser){
//        return matchList.stream()
//                .filter(match -> match.getMatchedUserFirst().equals(idUser) || match.getMatchedUserSecond().equals(idUser))
//                .collect(Collectors.toList());
//    }
}
