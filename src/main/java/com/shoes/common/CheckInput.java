package com.shoes.common;


import com.shoes.dto.manager.ProductDto;
import com.shoes.entity.Product;
import com.shoes.entity.ProductDetail;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckInput {

    public static boolean stringIsNullOrEmpty (String string) {
        return null == string || string.equals("");
    }

    public static boolean isValidEmail(String string) {
        if (stringIsNullOrEmpty(string)) return true;
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean isValidUUID(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            return true; // Nếu không có ngoại lệ, chuỗi là UUID hợp lệ
        } catch (IllegalArgumentException e) {
            return false; // Nếu có ngoại lệ, chuỗi không phải là UUID hợp lệ
        }
    }

    public static boolean isValidDate(long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            return true; // Nếu không có lỗi, thì timestamp hợp lệ
        } catch (Exception e) {
            return false; // Nếu có lỗi, thì timestamp không hợp lệ
        }
    }

    public static boolean isValidDateForPriceDetail(long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now(); // Lấy thời điểm hiện tại

            if (date.isAfter(currentDate)) {
                return true; // Kiểm tra ngày hợp lệ và từ ngày hiện tại trở đi
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isValidName(String name) {
        // Tên không được là null
        if (name == null) {
            return false;
        }
        // Tên không được rỗng
        if (name.trim().isEmpty()) {
            return false;
        }
        // Tên chỉ chứa các ký tự chữ cái và dấu cách
        if (!name.matches("^[a-zA-Z\\p{L} ]+$")) {
            return false;
        }
        // Nếu tất cả các điều kiện trên được thỏa mãn, tên được coi là hợp lệ
        return true;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Định dạng số điện thoại theo regex
        String regex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
        // Biên dịch regex
        Pattern pattern = Pattern.compile(regex);
        // So khớp số điện thoại với regex
        Matcher matcher = pattern.matcher(phoneNumber);
        // Kiểm tra xem số điện thoại có khớp với định dạng không
        return matcher.matches();
    }

    public static boolean checkInventoryQuantityForCart(ProductDetail productDetail, int quantity) {
        return  quantity > productDetail.getQuantity() ? false : true;
    }
}
