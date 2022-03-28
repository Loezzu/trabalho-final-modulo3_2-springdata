package com.tindev.tindevapi.controller.userAPI;

import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import com.tindev.tindevapi.dto.user.UserCreateDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.dto.user.UserDTOCompleto;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
@Api(value = "3 - User API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"3 - User API"})
public class UserController{


    private final UserService userService;

    @GetMapping ("/listar/{id}")
    public ResponseEntity<List<UserDTO>> listUser(@RequestParam(required = false) Integer id){
        return ResponseEntity.ok(userService.listUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@Valid @RequestBody UserCreateDTO userCreateDTO) throws Exception {
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updatedUser(@PathVariable("userId") Integer id,
                                                    @Valid @RequestBody UserCreateDTO userCreateDTO) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, userCreateDTO));
    }
//
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer id) throws Exception {
        userService.delete(id);
        return ResponseEntity.ok("User deleted!");
    }

    @GetMapping("/list-likes-by-id")
    public ResponseEntity<List<UserDTOCompleto>> listLikesById(@RequestParam("id") Integer id) throws Exception {
        return ResponseEntity.ok(userService.listLikeById(id));
    }

    @GetMapping("/list-received-likes-by-id")
    public ResponseEntity<List<UserDTOCompleto>> listReceivedLikesById(@RequestParam("id") Integer id) throws Exception {
        return ResponseEntity.ok(userService.listReceivedLikeById(id));
    }

    @GetMapping("/getCompleto")
    public List<UserDTOCompleto> listUserComplete(@RequestParam(value = "id", required = false) Integer id){
        return userService.userDTOCompletos(id);
    }

    @GetMapping("/get-matches-by-id")
    public ResponseEntity<List<UserDTOCompleto>> listMatchesById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(userService.listMatchesOfTheUser(id));
    }

}
