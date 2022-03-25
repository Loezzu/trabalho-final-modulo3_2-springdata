package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonInfoService {

    private final PersonInfoRepository personInfoRepository;
    private final ObjectMapper objectMapper;


    public List<PersonInfoDTO> listPersonInfo() {
        return personInfoRepository.findAll()
                .stream()
                .map(persoInfo -> objectMapper.convertValue(persoInfo, PersonInfoDTO.class))
                .collect(Collectors.toList());
    }

    public PersonInfoDTO createPersonInfo(PersonInfoCreateDTO personInfoCreateDTO) {
        PersonInfoEntity personInfoEntity = objectMapper.convertValue(personInfoCreateDTO, PersonInfoEntity.class);
        PersonInfoEntity savedPersonInfoEntity = personInfoRepository.save(personInfoEntity);

        return objectMapper.convertValue(savedPersonInfoEntity, PersonInfoDTO.class);
    }



//
//
//    public PersoInfoDTO create(PersoInfoCreateDTO persoInfoCreate) throws Exception {
//        log.info("calling the Create method");
//
//       PersoInfo persoInfo = objectMapper.convertValue(persoInfoCreate, PersoInfo.class);
//
//        if (persoInfoRepository.getByEmail(persoInfo.getEmail())) {
//            throw new RegraDeNegocioException("Email já cadastrado");
//        }
//
//       PersoInfo persoInfoCreated = persoInfoRepository.create(persoInfo);
//
//        return objectMapper.convertValue(persoInfoCreated, PersoInfoDTO.class);
//    }
//
//    public List<PersoInfoDTO> list(){
//        log.info("calling the List method");
//        return persoInfoRepository.list()
//                .stream()
//                .map(persoInfo -> objectMapper.convertValue(persoInfo, PersoInfoDTO.class))
//                .collect(Collectors.toList());
//    }
//
//
//    public PersoInfoDTO update(Integer id, PersoInfoCreateDTO persoInfoUpdate) throws Exception {
//        log.info("calling the Update method");
//        PersoInfo persoInfo = objectMapper.convertValue(persoInfoUpdate, PersoInfo.class);
//        return objectMapper.convertValue(persoInfoRepository.update(id, persoInfo), PersoInfoDTO.class);
//    }
//
//    public void delete(Integer id) throws Exception {
//        log.info("calling the Delete method");
//        persoInfoRepository.delete(id);
//    }
//
//
//    public PersoInfoDTO getPersoInfoById(Integer id) throws RegraDeNegocioException {
//        log.info("Calling get personal info by id method ");
//        return objectMapper.convertValue(persoInfoRepository.getPersoInfoById(id), PersoInfoDTO.class);
//    }
}
