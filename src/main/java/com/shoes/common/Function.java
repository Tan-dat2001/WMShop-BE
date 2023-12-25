package com.shoes.common;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Function {

    public static long toLongFromTimeStamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime).getTime();
    }

    public static LocalDateTime toLocalDateTimeFromLong(Long longDate){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDate), ZoneId.systemDefault());
    }
    public static String normalizeName(String name) {
        if (name != null && !name.isEmpty()) {
            // Loại bỏ khoảng trống dư thừa và chuyển tất cả chữ cái về chữ thường
            name = name.trim().toLowerCase();

            // Tạo một mảng các từ (dựa trên khoảng trống) và chuẩn hoá từng từ
            String[] words = name.split("\\s+");
            StringBuilder result = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    // Chuyển chữ cái đầu tiên của từ thành chữ hoa
                    word = word.substring(0, 1).toUpperCase() + word.substring(1);
                    result.append(word).append(" "); // Thêm khoảng trống sau mỗi từ
                }
            }
            // Loại bỏ khoảng trống ở cuối chuỗi
            name = result.toString().trim();
        }
        return name;
    }


    public static String formatKeyWord(String input) {
        // Loại bỏ khoảng trống thừa ở giữa và chuyển đổi thành chữ thường
        String cleanedAndLowercased = input.trim().replaceAll("\\s+", " ").toLowerCase();
        return cleanedAndLowercased;
    }
}
