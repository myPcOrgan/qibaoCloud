package com.qibao.activity;

import com.qibao.activity.entity.dto.UserActivityDTO;
import net.sf.json.JSONObject;

public class JsonTest {

    public static void main(String[] args) {
        UserActivityDTO roomDTO = new UserActivityDTO();

        System.out.println(JSONObject.fromObject(roomDTO).toString());
    }
}
