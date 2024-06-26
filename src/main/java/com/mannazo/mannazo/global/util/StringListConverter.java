package com.mannazo.mannazo.global.util;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * List&lt;String&gt;과 String간의 변환을 처리합니다.
 * 데이터베이스의 'interests' 컬럼에서 데이터를 가져와서 List&lt;String&gt;으로 변환하거나, List&lt;String&gt;를 다시 String으로 변환할 때 사용합니다.
 *
 */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return String.join(SEPARATOR, stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return Arrays.asList(string.split(SEPARATOR));
    }
}