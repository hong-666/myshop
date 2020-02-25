package com.funtl.my.shop.web.admin.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonTest {
    public static void main(String[] args)  {
        testPojoJson();

    }

    private static void testPojoJson(){
        ObjectMapper objectMapper = new ObjectMapper();

        String json="{\"draw\":1,\"recordsTotal\":14,\"recordsFiltered\":14,\"error\":null}";


        try {
            UserTest userTest = objectMapper.readValue(json, UserTest.class);
            System.out.println(userTest);

            System.out.println(objectMapper.writeValueAsString(userTest));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UserTest{
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;

    @Override
    public String toString() {
        return "UserTest{" +
                "draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", error='" + error + '\'' +
                '}';
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}