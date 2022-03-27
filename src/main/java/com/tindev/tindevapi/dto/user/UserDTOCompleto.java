package com.tindev.tindevapi.dto.user;

import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.dto.like.LikeDTO;
import com.tindev.tindevapi.dto.personInfo.PersonInfoDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserDTOCompleto extends UserDTO{

    private Integer userId;
    AddressDTO addressDTO;
    PersonInfoDTO personInfoDTO;
}
