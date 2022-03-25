package com.tindev.tindevapi.service;



import com.fasterxml.jackson.databind.ObjectMapper;
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
//
//    public UserDTO createUser(UserCreateDTO userToBeCreated) throws Exception {
//        log.info("Calling the Create user method");
//        userRepository.getAddressById(userToBeCreated.getAddressId());
//        userRepository.getPersoInfoById(userToBeCreated.getPersoInfoId());
//        User userToCreate = objectMapper.convertValue(userToBeCreated, User.class);
//        return objectMapper.convertValue(userRepository.create(userToCreate), UserDTO.class);
//    }
//
    public List<UserDTO> listUser(){
        log.info("Calling the list user method");
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());
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


//
//    public UserDTO updateUser(Integer id, UserCreateDTO userUpdated) throws Exception {
//        userRepository.getAddressById(userUpdated.getAddressId());
//        userRepository.getPersoInfoById(userUpdated.getPersoInfoId());
//        log.info("Calling the Update user method");
//        User userToUpdate = objectMapper.convertValue(userUpdated, User.class);
//        return objectMapper.convertValue(userRepository.update(id, userToUpdate), UserDTO.class);
//    }
//
//    public void deleteUser(Integer id) throws Exception {
//        log.info("Calling the Delete user method");
//        userRepository.delete(id);
//    }
//
//    public UserDTO getUserById(Integer id) throws Exception {
//        log.info("Calling the get user by id method");
//        return objectMapper.convertValue(userRepository.getUserById(id), UserDTO.class);
//    }
//
//    public String getUsernameById(Integer id) throws Exception {
//        log.info("Calling the get username by id method");
//        return userRepository.getUsernameById(id);
//    }
//
//    public String getEmailById(Integer id) throws Exception {
//        log.info("Calling the get email by id method");
//        return userRepository.getEmailById(id);
//    }
//
//    public List<UserDTO> listAvailable(Integer id) throws Exception {
//        log.info("Calling the list available method");
//        return userRepository.listAvailable(id).stream()
//                .map(user -> objectMapper.convertValue(user, UserDTO.class))
//                .collect(Collectors.toList());
//    }
}
