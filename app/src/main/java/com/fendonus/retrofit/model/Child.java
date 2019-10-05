package com.fendonus.retrofit.model;

public class Child {
    String _card_details, _interest_rate_details;

    public Child() {
    }

    public Child(String _card_details, String _interest_rate_details) {
        this._card_details = _card_details;
        this._interest_rate_details = _interest_rate_details;
    }

    public String get_card_details() {
        return _card_details;
    }

    public void set_card_details(String _card_details) {
        this._card_details = _card_details;
    }

    public String get_interest_rate_details() {
        return _interest_rate_details;
    }

    public void set_interest_rate_details(String _interest_rate_details) {
        this._interest_rate_details = _interest_rate_details;
    }
}
