package com.example.labSystem.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum FileTypeEnum {
    REPORT(1, "reports", "报告"),
    MEETINGS(2, "meetings", "会议"),
    PROJECTS(3, "projects", "项目");

    private Integer code;
    private String desc;
    private String typeName;

    /**
     * 根据 code 获取描述
     * @param code 文件类型的 code
     * @return 描述
     */
    public static String getDesc(Integer code) {
        if (code == null) {
            return null;
        }
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getDesc();
            }
        }
        return null;
    }

    /**
     * 根据描述获取 code
     * @param desc 文件类型的描述
     * @return 文件类型的 code
     */
    public static Integer getCode(String desc) {
        if (desc == null || desc.isEmpty()) {
            return null;
        }
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (Objects.equals(value.getDesc(), desc)) {
                return value.getCode();
            }
        }
        return null;
    }

    /**
     * 根据typeName获取 code
     * @param
     * @return 文件类型的 code
     */
    public static Integer getCodeByTypeName(String typeName) {
        if (typeName == null || typeName.isEmpty()) {
            return null;
        }
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (Objects.equals(value.getDesc(), typeName)) {
                return value.getCode();
            }
        }
        return null;
    }

    /**
     * 根据 typeName 获取对应的 FileTypeEnum
     * @param typeName 文件类型的名称
     * @return 对应的 FileTypeEnum
     */
    public static FileTypeEnum getByTypeName(String typeName) {
        if (typeName == null || typeName.isEmpty()) {
            return null;
        }
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (Objects.equals(value.getTypeName(), typeName)) {
                return value;
            }
        }
        return null;
    }

    /**
     * 根据 code 获取 typeName
     * @param code 文件类型的 code
     * @return 文件类型的 typeName
     */
    public static String getTypeNameByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getTypeName();
            }
        }
        return null;
    }
}

