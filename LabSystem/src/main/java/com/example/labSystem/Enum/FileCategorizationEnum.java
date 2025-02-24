package com.example.labSystem.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum FileCategorizationEnum {
    REPORT(1, "reports"),
    MEETINGS(2, "meetings"),
    PROJECTS(3, "projects");

    private Integer code;
    private String desc;

    public static String getDesc(Integer code) {
        if (code ==null) {
            return null;
        }
        for (FileCategorizationEnum value : FileCategorizationEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getDesc();
            }
        }
        return null;
    }

    public static Integer getCode(String desc) {
        if (desc == null || desc.isEmpty()) {
            return null;
        }
        for (FileCategorizationEnum value : FileCategorizationEnum.values()) {
            if (Objects.equals(value.getDesc(), desc)) {
                return value.getCode();
            }
        }
        return null;
    }
}
