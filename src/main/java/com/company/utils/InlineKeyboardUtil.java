package com.company.utils;


import com.company.enums.Language;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardUtil {
    public static InlineKeyboardMarkup getLanguageMenu() {

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("\uD83C\uDDFA\uD83C\uDDFF UZ");
        button.setCallbackData("_uz");
        row.add(button);
        rowList.add(row);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("\uD83C\uDDF7\uD83C\uDDFA RU");
        button1.setCallbackData("_ru");
        row1.add(button1);
        rowList.add(row1);

        markup.setKeyboard(rowList);
        return markup;
    }

    public static InlineKeyboardMarkup getAllCategory() {


        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(String.valueOf(i+1));
            button.setCallbackData("c_"+(i+1));
            if(i < 6){
                row.add(button);
            }else{
                row1.add(button);
            }
        }
        rowList.add(row);
        rowList.add(row1);
//
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        InlineKeyboardButton button1 = new InlineKeyboardButton();
//        button1.setText("\uD83C\uDDF7\uD83C\uDDFA RU");
//        button1.setCallbackData("_ru");
//        row1.add(button1);
//        rowList.add(row1);

        markup.setKeyboard(rowList);
        return markup;
    }
}
