package com.util;

import com.github.javafaker.Faker;
import com.ui.pojo.AddressPOJO;

import java.util.Locale;

public class FakeAddressUtility {

    public static AddressPOJO getFakeAddress(){
        Faker faker = new Faker(Locale.US);
        AddressPOJO addressPOJO = new AddressPOJO(faker.company().name(),faker.address().buildingNumber(),
                faker.address().streetAddress(),faker.address().city(),
                faker.numerify("#####"), faker.phoneNumber().cellPhone(),
                faker.phoneNumber().cellPhone(), "other","home address",
                faker.address().state());
        return addressPOJO;
    }
}
