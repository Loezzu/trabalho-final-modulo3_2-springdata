package com.tindev.tindevapi.entities;

import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    private Integer userId;
    private Integer PersoInfoId;
    private Integer AddressId;

    private String username;
    private String password;
    private ProgLangs progLangs;
    private Gender gender;
    private Pref pref;



}
