package com.sismics.sapparot.http;

import com.sismics.sapparot.exception.ValidationException;
import com.sismics.sapparot.function.CheckedConsumer;
import com.sismics.sapparot.function.CheckedFunction;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpHelper {
    public static <T> T execute(HttpClient client, HttpRequest request, CheckedFunction<HttpResponse<InputStream>, T> onSuccess, CheckedConsumer<HttpResponse<InputStream>> onFailure) {
        HttpResponse<InputStream> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (!(response.statusCode() / 100 == 2)) {
                if (onFailure != null) {
                    onFailure.accept(response);
                }
                return null;
            }
            if (onSuccess != null) {
                return onSuccess.apply(response);
            } else {
                return null;
            }
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null && response.body() != null) {
                try {
                    response.body().close();
                } catch (IOException e) {
                    // NOP
                }
            }
        }
    }
}
