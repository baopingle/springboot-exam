package com.brian.springboot.domain;

import javax.validation.constraints.NotNull;

public enum Gender {
    Male("M"),
    Female("F"),
    Other("O"),
    Unknown("U");

    private String code;

    private Gender(String code){
        this.code = code;
    }

    public static Gender getGender(@NotNull String code){
        for(Gender gender:values()){
            if(gender.code.equals(code)){
                return gender;
            }
        }

        return Gender.Unknown;
    }

    public static Gender toGender(String name){
        return valueOf(name);
    }
}
