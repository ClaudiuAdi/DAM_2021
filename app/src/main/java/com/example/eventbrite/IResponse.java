package com.example.eventbrite;

import java.util.List;

public interface IResponse {
    public void onSuccess(List<Event> lista);
    public void onError(String errorMessage);
}
