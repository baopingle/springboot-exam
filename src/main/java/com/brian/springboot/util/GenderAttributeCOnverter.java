package com.brian.springboot.util;

import com.brian.springboot.domain.Gender;

import javax.persistence.AttributeConverter;

public class GenderAttributeCOnverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender==null?Gender.Unknown.name():gender.name();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return Gender.toGender(dbData);
    }
}
