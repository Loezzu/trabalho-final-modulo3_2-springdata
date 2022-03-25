package com.tindev.tindevapi.controller.address;

import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.AddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@Validated
@Api(value = "2 - Address API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"2 - Address API"}, description = "Address Controls")
public class AddressController{

    @Autowired
    private AddressService addressService;

    @GetMapping //localhost:8080/address
    public ResponseEntity<List<AddressDTO>> listAddress(){
        return ResponseEntity.ok(addressService.listAddress());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> postAddress(@Valid @RequestBody AddressCreateDTO addressCreateDTO){
        return ResponseEntity.ok(addressService.createAddress(addressCreateDTO));
    }

//    @GetMapping("/{addressId}")
//    public ResponseEntity<AddressDTO> getByIdAddress(@PathVariable("addressId") Integer id) throws RegraDeNegocioException {
//        return ResponseEntity.ok(addressService.getAddressById(id));
//    }
//

//
//    @PutMapping("/{addressId}")
//    public ResponseEntity<AddressDTO> updatedAddress(@PathVariable("addressId") Integer id,
//                                                    @Valid @RequestBody AddressCreateDTO addressCreateDTO) throws RegraDeNegocioException {
//        return ResponseEntity.ok(addressService.updateAddress(id, addressCreateDTO));
//    }
//
//    @DeleteMapping("/{addressId}")
//    public ResponseEntity<String> deleteAddress(@PathVariable("addressId") Integer id){
//        return ResponseEntity.ok("Address Deleted!");
//    }

}
