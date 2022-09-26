package com.company;

import com.company.controller.MainController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class App {

    public static final MainController mainController = new MainController();

    public static void main(String[] args) {

        try {
            headerMethod();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void headerMethod() {

        try {

            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(mainController);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
