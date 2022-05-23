package com.heritage.util;

import com.heritage.pojo.MigrationDataSheet;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * @author Hari Pathuri
 * 5/17/2022 6:11 PM
 */
public class Heritageconstants {

    public static final String SHOULD_BE_GREATERTHAN_0 = "Should be greaterthan 0";
    public static final String SHOULD_NOT_BE_NULL = "Should not be null";
    public static final Character Y = 'Y';
    public static final String CURRENT = "CURRENT";
    public static final String SAVINGS = "SAVINGS";


    private static String[] getNullPropertyNames (Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static List<String> getNullProperties(List<MigrationDataSheet> objectList) {

        List<String> propertiesNames = new ArrayList<>();
        objectList.forEach(object -> {
        propertiesNames.addAll(Arrays.asList(getNullPropertyNames(object)));
        });
        return  propertiesNames;
    }


}
