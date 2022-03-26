package com.tindev.tindevapi.controller.address;

import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.AddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@Validated
@Api(value = "2 - Address API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"2 - Address API"})
public class AddressController{

    @Autowired
    private AddressService addressService;

    @GetMapping("/list-address/{id}")
    public ResponseEntity<List<AddressDTO>> listAddress(@RequestParam(required = false) Integer id){
        return ResponseEntity.ok(addressService.listAddress(id));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressCreateDTO addressCreateDTO){
        return ResponseEntity.ok(addressService.createAddress(addressCreateDTO));
    }
    @PostMapping("/update")
    public ResponseEntity<AddressDTO> update(@RequestBody AddressCreateDTO addressCreateDTO, @RequestParam("id") Integer id){
        return ResponseEntity.ok(addressService.updateAddress(addressCreateDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id){
        addressService.deleteAddress(id);
        return new ResponseEntity<>("PersoInfo deleted", HttpStatus.ACCEPTED);
    }

}
