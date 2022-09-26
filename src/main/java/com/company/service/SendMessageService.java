package com.company.service;

import com.company.App;
import com.company.controller.MainController;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import static com.company.App.mainController;

@Setter
public class SendMessageService {


    public void sendMessage(String msg, Long chatId, ReplyKeyboardMarkup markup) {

        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), msg);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode(ParseMode.HTML);
        mainController.sendMsg(sendMessage);

    }

    public void sendDocument(SendDocument sendDocument) {

        mainController.sendMsg(sendDocument);

    }
    public void sendMessage(String msg, Long chatId, InlineKeyboardMarkup markup) {

        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), msg);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode(ParseMode.HTML);
        mainController.sendMsg(sendMessage);

    }

    public void sendMessage(String msg, Long chatId) {

        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), msg);
        sendMessage.setParseMode(ParseMode.HTML);
        mainController.sendMsg(sendMessage);

    }

    public void sendMessage(String msg, Long chatId, ReplyKeyboardRemove markup) {

        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), msg);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setParseMode(ParseMode.HTML);
        mainController.sendMsg(sendMessage);

    }

    public void deletedMessage(Message message) {

        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(String.valueOf(message.getChatId()));
        deleteMessage.setMessageId(message.getMessageId());
        mainController.sendMsg(deleteMessage);
    }

}
