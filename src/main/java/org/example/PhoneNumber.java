package org.example;

public class PhoneNumber {
    private String number;
    private String country;
    private String updated_at;
    private String data_humans;
    private String full_number;
    private String country_text;
    private String maxdate;
    private String status;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getData_humans() {
        return data_humans;
    }

    public void setData_humans(String data_humans) {
        this.data_humans = data_humans;
    }

    public String getFull_number() {
        return full_number;
    }

    public void setFull_number(String full_number) {
        this.full_number = full_number;
    }

    public String getCountry_text() {
        return country_text;
    }

    public void setCountry_text(String country_text) {
        this.country_text = country_text;
    }

    public String getMaxdate() {
        return maxdate;
    }

    public void setMaxdate(String maxdate) {
        this.maxdate = maxdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "full_number='" + full_number + '\'' +
                '}';
    }
}
