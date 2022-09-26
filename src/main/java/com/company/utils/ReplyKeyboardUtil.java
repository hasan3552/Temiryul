package com.company.utils;

import com.company.enums.Language;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardUtil {

    public static ReplyKeyboardMarkup getContact() {

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setRequestContact(true);
        button.setText(DemoUtil.CONTACT);
        row.add(button);
        rowList.add(row);

        markup.setKeyboard(rowList);
        markup.setResizeKeyboard(true);

        return markup;
    }

    public static ReplyKeyboardMarkup getAdminMenu(Language language) {

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton(language.equals(Language.UZ) ?
                DemoUtil.SETTING_UZ : DemoUtil.SETTING_RU);
        row.add(button1);
        rowList.add(row);

        markup.setKeyboard(rowList);
        return markup;
    }

    public static ReplyKeyboardMarkup getCustomerMenu() {

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        List<KeyboardRow> rowList = new ArrayList<>();

        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton button4 = new KeyboardButton("Customer");
        row2.add(button4);
        rowList.add(row2);

        markup.setKeyboard(rowList);
        return markup;

    }
}
