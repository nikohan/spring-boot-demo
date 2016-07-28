package com.test.demo.effective.build;

/**
 *
 * Created by zhaohan on 2016/7/27.
 */
public class Settings {
    private String setting_1;
    private String setting_2;
    private String setting_3;
    private String setting_4;

    private Settings(SettingBuilder builder) {
        setting_1 = builder.setting_1;
        setting_2 = builder.setting_2;
        setting_3 = builder.setting_3;
        setting_4 = builder.setting_4;
    }

    public static class SettingBuilder implements Builder<Settings> {
        private String setting_1;
        private String setting_2;
        private String setting_3 = "";
        private String setting_4 = "";

        public SettingBuilder(String setting_1, String setting_2) {
            this.setting_1 = setting_1;
            this.setting_2 = setting_2;
        }

        public SettingBuilder setting3(String str) {
            setting_3 = str;
            return this;//可以链式调用
        }

        public SettingBuilder setting4(String str) {
            setting_4 = str;
            return this;
        }

        public Settings build() {
            return new Settings(this);
        }
    }

    public String getSetting_1() {
        return setting_1;
    }

    public String getSetting_2() {
        return setting_2;
    }

    public String getSetting_3() {
        return setting_3;
    }

    public String getSetting_4() {
        return setting_4;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "setting_1='" + setting_1 + '\'' +
                ", setting_2='" + setting_2 + '\'' +
                ", setting_3='" + setting_3 + '\'' +
                ", setting_4='" + setting_4 + '\'' +
                '}';
    }
}
