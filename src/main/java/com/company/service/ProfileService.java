package com.company.service;

import com.company.database.Database;
import com.company.entity.CalculateEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;
import com.company.repository.ProfileRepository;
import com.company.utils.DemoUtil;
import com.company.utils.InlineKeyboardUtil;
import com.company.utils.ReplyKeyboardUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

@Setter
@Service
public class ProfileService {

    private Message message;
    private User user;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileCategoryService profileCategoryService;

    @Autowired
    @Lazy
    private SendMessageService sendMessageService;

    public Optional<ProfileEntity> getUserById(String userId) {

        return profileRepository.findByUserId(userId);
    }


    public void addProfile(String data) {

        ProfileEntity entity = new ProfileEntity();
        entity.setUserId(String.valueOf(user.getId()));
        entity.setUsername(user.getUserName());
        entity.setStatus(ProfileStatus.NEW);
        entity.setLanguage(data.equals("_uz") ? Language.UZ : Language.RU);
        entity.setVisible(Boolean.TRUE);

        profileRepository.save(entity);

        sendMessageService.sendMessage(data.equals("_uz") ? DemoUtil.GET_CONTACT_UZ : DemoUtil.GET_CONTACT_RU,
                Long.valueOf(entity.getUserId()), ReplyKeyboardUtil.getContact());

    }

    public void getContact(Contact contact, ProfileEntity profile) {

        if (profile.getStatus().equals(ProfileStatus.NEW)) {
            profile.setPhoneNumber(contact.getPhoneNumber());
            profile.setStatus(ProfileStatus.ACTIVE);
            profileRepository.update(profile);

            Language language = profile.getLanguage();

            sendMessageService.sendMessage("<b>CATEGORY MENU</b>", Long.valueOf(profile.getUserId()),
                    ReplyKeyboardUtil.getAdminMenu(profile.getLanguage()));
            sendMessageService.sendMessage(language.equals(Language.UZ) ?
                            DemoUtil.CHANGE_CATEGORY_UZ : DemoUtil.CHANGE_CATEGORY_RU, Long.valueOf(profile.getUserId()),
                    InlineKeyboardUtil.getAllCategory());
        }
    }

    public void refresh(ProfileEntity profile) {

        Language language = profile.getLanguage();
        sendMessageService.sendMessage(language.equals(Language.UZ) ?
                        DemoUtil.CHANGE_CATEGORY_UZ : DemoUtil.CHANGE_CATEGORY_RU, Long.valueOf(profile.getUserId()),
                InlineKeyboardUtil.getAllCategory());
    }

    public void setting(ProfileEntity profile) {

        profile.setLanguage(profile.getLanguage().equals(Language.UZ) ? Language.RU : Language.UZ);
        profileRepository.update(profile);

        refresh(profile);
    }

    public void calculate(String data) {

        Optional<ProfileEntity> userById = getUserById(String.valueOf(user.getId()));
        ProfileEntity entity = userById.get();
        entity.setStatus(ProfileStatus.CALCLUCATE);

        profileRepository.update(entity);

        int categoryId = Integer.parseInt(data.split("_")[1]);

        profileCategoryService.calc(categoryId, entity);


    }

    public void calculate(ProfileEntity profile) {

        String text = message.getText();
        double v1 = Double.parseDouble(text.split("/")[0]);
        double v2 = Double.parseDouble(text.split("/")[1]);
        double v3 = Double.parseDouble(text.split("/")[2]);

        CalculateEntity calculate = Database.calculate.get(profile.getUserId());
        calculate.setValue1(v1);
        calculate.setValue2(v2);
        calculate.setValue3(v3);

        profileCategoryService.save(calculate);

        profile.setStatus(ProfileStatus.ACTIVE);
        profileRepository.update(profile);

        sendCalculate(calculate,profile.getLanguage());

    }

    private void sendCalculate(CalculateEntity calculate,Language language) {

        String response = String.valueOf(calculate.getValue1() * calculate.getValue2() * calculate.getValue3());
        sendMessageService.sendMessage(response,user.getId());
        sendMessageService.sendMessage(language.equals(Language.UZ) ? DemoUtil.CHANGE_CATEGORY_UZ : DemoUtil.CHANGE_CATEGORY_RU,
                user.getId(),InlineKeyboardUtil.getAllCategory());
    }
}
