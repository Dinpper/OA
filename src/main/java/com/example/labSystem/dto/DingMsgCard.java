package com.example.labSystem.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class DingMsgCard {

    private final String msgtype = "actionCard";
    private final Map<String, Object> actionCard;

    private DingMsgCard(Builder builder) {
        this.actionCard = builder.actionCard;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public Map<String, Object> getActionCard() {
        return actionCard;
    }

    public static class Builder {
        private final Map<String, Object> actionCard = new LinkedHashMap<>();
        private final List<Map<String, String>> btns = new ArrayList<>();

        public Builder title(String title) {
            actionCard.put("title", title);
            return this;
        }

        public Builder text(String text) {
            actionCard.put("text", text);
            return this;
        }

        public Builder btnOrientation(String orientation) {
            actionCard.put("btnOrientation", orientation); // 0：垂直 1：水平
            return this;
        }

        public Builder addActionButton(String key, String title, String style, String url) {
            btns.add(Map.of("title", title, "actionURL", url));
            return this;
        }

        public DingMsgCard build() {
            actionCard.put("btns", btns);
            return new DingMsgCard(this);
        }
    }
}
