package com.tindev.tindevapi.controller.personinfo;

import com.tindev.tindevapi.dto.personInfo.PersonInfoCreateDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.service.PersonInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/personinfo")
@Api(value = "1 - PersonInfo API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"1 - PersonInfo API"}, description = "PersonInfo Controls")
public class PersonInfoController{


    @Autowired
    private PersonInfoService persoInfoService;


    @GetMapping
    public ResponseEntity<List<PersonInfoDTO>> list(){
        return ResponseEntity.ok(persoInfoService.listPersonInfo());
    }

    @PostMapping
    public ResponseEntity<PersonInfoDTO> create(@RequestBody PersonInfoCreateDTO persoInfoDTO) throws Exception {
        return ResponseEntity.ok(persoInfoService.createPersonInfo(persoInfoDTO));
    }


//
//    @GetMapping("/{persoInfoId}")
//    public ResponseEntity<PersoInfoDTO> getByPersoInfoId(@PathVariable("persoInfoId") Integer id) throws RegraDeNegocioException {
//        return ResponseEntity.ok(persoInfoService.getPersoInfoById(id));
//    }
//
//
//    @PutMapping("/{persoInfoId}")
//    public ResponseEntity<PersoInfoDTO> update(@PathVariable("persoInfoId") Integer id, @Valid @RequestBody PersoInfoCreateDTO persoInfoUpdate) throws Exception {
//        return ResponseEntity.ok(persoInfoService.update(id, persoInfoUpdate));
//    }
//
//    @DeleteMapping("/{persoInfoId}")
//    public ResponseEntity<String> delete(@PathVariable("persoInfoId") Integer id) throws Exception {
//        persoInfoService.delete(id);
//        return new ResponseEntity<>("PersoInfo deleted", HttpStatus.ACCEPTED);
//    }
}
