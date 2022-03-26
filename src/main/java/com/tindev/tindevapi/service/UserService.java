package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.repository.AddressRepository;
import com.tindev.tindevapi.repository.PersonInfoRepository;
import com.tindev.tindevapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PersonInfoRepository personInfoRepository;
    private final ObjectMapper objectMapper;

    public List<UserDTO> listUser(Integer id){
        if(id != null){
          getUserById(id);
        } {
            log.info("Calling the list user method");
            return userRepository.findAll().stream()
                    .map(user -> objectMapper.convertValue(user, UserDTO.class))
                    .collect(Collectors.toList());
        }
    }

    public UserDTO createUser(UserCreateDTO userCreateDTO) throws Exception {
        log.info("Calling the Create user method");
        UserEntity userEntity = objectMapper.convertValue(userCreateDTO, UserEntity.class);

        AddressEntity addressEntity = addressRepository.findById(userEntity.getAddressId())
                .orElseThrow(() -> new Exception("Address not found"));
        PersonInfoEntity personInfoEntity = personInfoRepository.findById(userEntity.getPersoInfoId())
                .orElseThrow(() -> new Exception("Person info not found"));
        userEntity.setAddress(addressEntity);
        userEntity.setAddressId(addressEntity.getIdAddress());
        userEntity.setPersonInfoEntity(personInfoEntity);
        userEntity.setPersoInfoId(personInfoEntity.getIdPersonInfo());
        return objectMapper.convertValue(userRepository.save(userEntity), UserDTO.class);
    }

    public UserDTO updateUser(Integer id, UserCreateDTO userUpdated) {
        UserEntity userEntity = userRepository.getById(id);
        userEntity.setUserId(id);
        userEntity.setPersoInfoId(userEntity.getPersoInfoId());
        userEntity.setAddressId(userEntity.getAddressId());
        userEntity.setGender(userUpdated.getGender());
        userEntity.setPassword(userUpdated.getPassword());
        return objectMapper.convertValue((userRepository.save(userEntity)), UserDTO.class);
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Integer id){
        return objectMapper.convertValue((userRepository.findById(id)), UserDTO.class);
    }

    //lista os likes que a pessoa deu
    public List<UserDTO> listLikesById(Integer id){
        return userRepository.listLikesById(id).stream()
                .map(userEntity -> objectMapper.convertValue(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

    //lista os likes que a pessoa recebeu
    public List<UserDTO> listReceivedLikeById(Integer id) {
        return userRepository.listReceivedLikesById(id).stream().map(
                userEntity -> objectMapper.convertValue(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

}
