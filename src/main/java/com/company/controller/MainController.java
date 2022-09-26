package com.company.controller;

import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.service.ProfileService;
import com.company.service.SendMessageService;
import com.company.utils.DemoUtil;
import com.company.utils.InlineKeyboardUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

import static com.company.utils.ComponentContainer.BOT_TOKEN;
import static com.company.utils.ComponentContainer.BOT_USERNAME;

public class MainController extends TelegramLongPollingBot {

    public final SendMessageService sendMessageService = new SendMessageService();

    public final ProfileService profileService = new ProfileService();

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long userId = message.getChatId();

            profileService.setUser(message.getFrom());
            profileService.setMessage(message);

            Optional<ProfileEntity> userById = profileService.getUserById(String.valueOf(userId));
            if (message.hasText()) {

                if (!userById.isPresent() && message.getText().equals("/start")) {
                    sendMessageService.sendMessage(DemoUtil.CHANGE_LANGUAGE_RU, userId, InlineKeyboardUtil.getLanguageMenu());
                } else if (userById.isPresent() && message.getText().equals("/refresh")) {
                    profileService.refresh(userById.get());
                } else if (message.getText().equals(DemoUtil.SETTING_UZ) || message.getText().equals(DemoUtil.SETTING_RU)) {
                    profileService.setting(userById.get());
                } else if(userById.get().getStatus().equals(ProfileStatus.CALCLUCATE)){
                    profileService.calculate(userById.get());
                }

            } else if (userById.isPresent() && message.hasContact()) {
                profileService.getContact(message.getContact(), userById.get());
            }

        } else if (update.hasCallbackQuery()) {

            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message message = callbackQuery.getMessage();
            String data = callbackQuery.getData();
            Long userId = message.getChatId();

            profileService.setUser(callbackQuery.getFrom());
            profileService.setMessage(message);

            if (data.equals("_uz") || data.equals("_ru")) {
                profileService.addProfile(data);
            } else if (data.startsWith("c_")) {
                profileService.calculate(data);
            }

            sendMessageService.deletedMessage(message);

        }
    }


    public void sendMsg(SendPhoto sendPhoto) {

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(SendDocument sendDocument) {

        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(SendMessage sendMessage) {

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(DeleteMessage deleteMessage) {

        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void setUserMessage() {

    }
}
