package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.dto.user.UserDTOCompleto;
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

import java.util.ArrayList;
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

    public List<UserDTO> listUser(Integer id) {
        if (id != null) {
            return userRepository.findById(id).stream().map(userEntity ->
                            objectMapper.convertValue(userEntity, UserDTO.class))
                    .collect(Collectors.toList());
        }
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

    public UserDTO updateUser(Integer id, UserCreateDTO userUpdated) {
        UserEntity userEntity = userRepository.getById(id);
        userEntity.setUserId(id);
        userEntity.setPersoInfoId(userEntity.getPersoInfoId());
        userEntity.setAddressId(userEntity.getAddressId());
        userEntity.setGender(userUpdated.getGender());
        userEntity.setPassword(userUpdated.getPassword());
        return objectMapper.convertValue((userRepository.save(userEntity)), UserDTO.class);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public UserDTO getUserById(Integer id) {
        return objectMapper.convertValue((userRepository.findById(id)), UserDTO.class);
    }

    //    lista os likes que a pessoa deu
    public List<UserDTOCompleto> listLikeById(Integer id) {
        List<UserDTOCompleto> userDTOCompletos = userRepository.listLikesById(id).stream().map(
                userEntity -> {
                    UserDTOCompleto userDTOCompleto = objectMapper.convertValue(userEntity, UserDTOCompleto.class);
                    userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
                    userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
                    userDTOCompleto.setPersonInfoDTO(objectMapper.convertValue(userEntity.getPersonInfoEntity(), PersonInfoDTO.class));
                    userDTOCompleto.setLikes(userEntity.getLikes().stream()
                            .map(likeEntity -> objectMapper.convertValue(likeEntity, LikeDTO.class)).collect(Collectors.toList()));
                    return userDTOCompleto;
                }).collect(Collectors.toList());
        return userDTOCompletos;
    }

    //lista os likes que a pessoa recebeu
    public List<UserDTOCompleto> listReceivedLikeById(Integer id) {
        List<UserDTOCompleto> userDTOCompletos = userRepository.listReceivedLikesById(id).stream().map(
                userEntity -> {
                    UserDTOCompleto userDTOCompleto = objectMapper.convertValue(userEntity, UserDTOCompleto.class);
                    userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
                    userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
                    userDTOCompleto.setPersonInfoDTO(objectMapper.convertValue(userEntity.getPersonInfoEntity(), PersonInfoDTO.class));
                    userDTOCompleto.setLikes(userEntity.getLikes().stream()
                            .map(likeEntity -> objectMapper.convertValue(likeEntity, LikeDTO.class)).collect(Collectors.toList()));
                    return userDTOCompleto;
                }).collect(Collectors.toList());
        return userDTOCompletos;
    }

    public List<UserDTOCompleto> userDTOCompletos() {
        return new ArrayList<>(userRepository.findAll().stream().map(this::getUserComplete).toList());
    }

    private UserDTOCompleto getUserComplete(UserEntity userEntity) {
        UserDTOCompleto userDTOCompleto = objectMapper.convertValue(userEntity, UserDTOCompleto.class);
        userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
        userDTOCompleto.setPersonInfoDTO(objectMapper.convertValue(userEntity.getPersonInfoEntity(), PersonInfoDTO.class));
        userDTOCompleto.setLikes(userEntity.getLikes().stream()
                .map(likeEntity -> objectMapper.convertValue(likeEntity, LikeDTO.class)).collect(Collectors.toList()));
        return userDTOCompleto;
    }


    public List<UserDTOCompleto> listMatchesOfTheUser (Integer id){

        List<UserDTOCompleto> userDTOCompletos = userRepository.listMatchesOfTheUser(id).stream().map(
                userEntity -> {
                    UserDTOCompleto userDTOCompleto = objectMapper.convertValue(userEntity, UserDTOCompleto.class);
                    userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
                    userDTOCompleto.setAddressDTO(objectMapper.convertValue(userEntity.getAddress(), AddressDTO.class));
                    userDTOCompleto.setPersonInfoDTO(objectMapper.convertValue(userEntity.getPersonInfoEntity(), PersonInfoDTO.class));
                    userDTOCompleto.setLikes(userEntity.getLikes().stream()
                            .map(likeEntity -> objectMapper.convertValue(likeEntity, LikeDTO.class)).collect(Collectors.toList()));
                    return userDTOCompleto;
                }).collect(Collectors.toList());
        return userDTOCompletos;



    }


}




