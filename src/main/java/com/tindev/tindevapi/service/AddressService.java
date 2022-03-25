package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.entities.AddressEntity;
import com.tindev.tindevapi.entities.PersonInfoEntity;
import com.tindev.tindevapi.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ObjectMapper objectMapper;


    public List<AddressDTO> listAddress(){
        return addressRepository.findAll()
                .stream()
                .map(address -> objectMapper.convertValue(address, AddressDTO.class))
                .collect(Collectors.toList());
    }

    public AddressDTO createAddress(AddressCreateDTO addressCreateDTO) {
        AddressEntity addressEntity = objectMapper.convertValue(addressCreateDTO, AddressEntity.class);
        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        return objectMapper.convertValue(savedAddressEntity, AddressDTO.class);
    }


//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    public AddressDTO createAddress(AddressCreateDTO addressToBeCreated){
//        log.info("Calling the Create address method");
//        Address addressToCreate = objectMapper.convertValue(addressToBeCreated, Address.class);
//        return objectMapper.convertValue(addressRepository.create(addressToCreate), AddressDTO.class);
//    }
//
//    public List<AddressDTO> listAddress(){
//        log.info("Calling the List address method");
//        return addressRepository.list().stream()
//                .map(address -> objectMapper.convertValue(address, AddressDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    public AddressDTO updateAddress(Integer id, AddressCreateDTO addressUpdated) throws RegraDeNegocioException {
//        log.info("Calling the Update address method");
//        Address addressToUpdate = objectMapper.convertValue(addressUpdated, Address.class);
//        return objectMapper.convertValue(addressRepository.update(id, addressToUpdate), AddressDTO.class);
//    }
//
//    public void deleteAddress(Integer id) throws RegraDeNegocioException {
//        log.info("Calling the Delete address method");
//        addressRepository.delete(id);
//    }
//
//    public AddressDTO getAddressById(Integer id) throws RegraDeNegocioException {
//        log.info("Calling the get address by ID method");
//        return objectMapper.convertValue(addressRepository.getAddressById(id), AddressDTO.class);
//    }
}
