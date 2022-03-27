package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.match.MatchDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.MatchEntity;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {


    private final MatchRepository matchRepository;

    private final UserService userService;

    private final ObjectMapper objectMapper;


    public List<MatchDTO> list() {
        return matchRepository.findAll()
                .stream()
                .map(match -> objectMapper.convertValue(match, MatchDTO.class))
                .collect(Collectors.toList());
    }


    public MatchDTO addMatch(Integer userid1, Integer userid2) throws Exception {
        MatchEntity match = new MatchEntity();
        if(userService.getUserById(userid1).getProgLangs().equals(userService.getUserById(userid2).getProgLangs())){
            match.setMatchedUserFirst(userid1);
            match.setMatchedUserSecond(userid2);
            return objectMapper.convertValue(matchRepository.save(match), MatchDTO.class);
        } else {
            throw new RegraDeNegocioException("Não deu Match");
        }
    }

//
//    public List<MatchDTO> listMatchesOfTheUser(Integer idUser){
//        return matchRepository.listMatchesOfUser(idUser).stream()
//                .map(this::getMatchDTOWithUsernameAndEmail).collect(Collectors.toList());
//    }
//

//
//    public void deleteMatch(Integer matchid) throws Exception {
//        matchRepository.deleteMatch(matchid);
//    }
//
//    private MatchDTO getMatchDTOWithUsernameAndEmail(Match match) {
//        try {
//            MatchDTO matchDTO = objectMapper.convertValue(match, MatchDTO.class);
//
//            String usernameFirst =  userService.getUsernameById(match.getMatchedUserFirst());
//            String emailFirst =  userService.getEmailById(match.getMatchedUserFirst());
//            String usernameSecond =  userService.getUsernameById(match.getMatchedUserSecond());
//            String emailSecond =  userService.getEmailById(match.getMatchedUserSecond());
//
//            matchDTO.setMatchedUserNameFirst(usernameFirst);
//            matchDTO.setMachedUserEmailFirst(emailFirst);
//            matchDTO.setMatchedUserNameSecond(usernameSecond);
//            matchDTO.setMatchedUserEmailSecond(emailSecond);
//
//            return matchDTO;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }


}
