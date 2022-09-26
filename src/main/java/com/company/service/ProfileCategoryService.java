package com.company.service;

import com.company.database.Database;
import com.company.entity.CalculateEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.CategoryStatus;
import com.company.enums.Language;
import com.company.repository.CalculateRepository;


public class ProfileCategoryService {

    private final CalculateRepository calculateRepository = new CalculateRepository();
    private final SendMessageService sendMessageService = new SendMessageService();

    public void calc(Integer categoryId, ProfileEntity profile){

        CalculateEntity calculate = new CalculateEntity();
        calculate.setCategoryId(categoryId);
        calculate.setProfileId(profile.getId());
        calculate.setStatus(CategoryStatus.ACTIVE);

        Database.calculate.put(profile.getUserId(), calculate);

//        Database.calculate.forEach((profile1, calculate1) -> System.out.println(profile1+" - "+calculate1));
//        Optional<CalculateEntity> calculate1 = calculateRepository.save(calculate);


        sendMessageService.sendMessage(profile.getLanguage().equals(Language.UZ) ?
                "<b>Iltimos qiymatlarni ushbu ketma-ketlikda kiriting:</b> \n\n value1/value2/valu3" :
                "<b>Введите значения в следующем порядке:</b> \n\n value1/value2/value3", Long.valueOf(profile.getUserId()));

    }

    public void save(CalculateEntity calculate) {
        calculateRepository.save(calculate);
    }
}
