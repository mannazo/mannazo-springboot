package com.mannazo.mannazo.global.util;

public class EnumUtils {

    public enum PreferredGender {
        상관없음(0, "상관없음"),
        남자(1, "남자"),
        여자(2, "여자");

        private final int number;
        private final String text;

        PreferredGender(int number, String text) {
            this.number = number;
            this.text = text;
        }

        public int getNumber() {
            return number;
        }

        public String getText() {
            return text;
        }

        public static PreferredGender fromNumber(int number) {
            for (PreferredGender gender : PreferredGender.values()) {
                if (gender.getNumber() == number) {
                    return gender;
                }
            }
            throw new IllegalArgumentException("No enum found with number: " + number);
        }

        public static PreferredGender fromText(String text) {
            for (PreferredGender gender : PreferredGender.values()) {
                if (gender.getText().equals(text)) {
                    return gender;
                }
            }
            throw new IllegalArgumentException("No enum found with text: " + text);
        }
    }

    public enum TravelStatus {
        등록(0, "등록"),
        매칭_중(1, "매칭 중"),
        매칭_완료(2, "매칭 완료"),
        여행_종료(3, "여행 종료");

        private final int number;
        private final String text;

        TravelStatus(int number, String text) {
            this.number = number;
            this.text = text;
        }

        public int getNumber() {
            return number;
        }

        public String getText() {
            return text;
        }

        public static TravelStatus fromNumber(int number) {
            for (TravelStatus status : TravelStatus.values()) {
                if (status.getNumber() == number) {
                    return status;
                }
            }
            throw new IllegalArgumentException("No enum found with number: " + number);
        }

        public static TravelStatus fromText(String text) {
            for (TravelStatus status : TravelStatus.values()) {
                if (status.getText().equals(text)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("No enum found with text: " + text);
        }
    }
}
