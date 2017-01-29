package com.fluffylighty.productscroller.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fluffylighty.productscroller.BuildConfig;
import com.fluffylighty.productscroller.interfaces.ApiInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Currency;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by Nico Adler on 28.01.17.
 */

public class RestCallService {

    private static final String LOG_TAG = RestCallService.class.getSimpleName();
    private static final String X_LOCALE_HEADER_KEY = "X-Locale";
    private static final String X_API_KEY_HEADER_KEY = "X-apiKey";
    private static ApiInterface apiInterface;

    public static ApiInterface getAPIInterface() {

        if (apiInterface == null) {
            apiInterface = createApiInterface();
        }

        return apiInterface;
    }

    private static ApiInterface createApiInterface() {

        OkHttpClient client = createOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_IP + BuildConfig.SERVER_API_EXTENSION)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        return retrofit.create(ApiInterface.class);
    }

    public static <T> void getProductsForCategoryId(Call<T> call, final SuccessCallback<T> callback) {

        retrofit2.Response<T> response = null;

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {

                if (response != null && response.code() == HTTP_OK) {

                    T body = response.body();

                    if (body != null) {

                        callback.onSuccess(body);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                Log.e(LOG_TAG, "onFailure: ", t);
            }
        });
    }

    /**
     * Creates a custom {@link OkHttpClient} with {@value X_LOCALE_HEADER_KEY} and {@value X_API_KEY_HEADER_KEY} as specified in the BuildConfig.
     *
     * @return
     */
    @NonNull
    private static OkHttpClient createOkHttpClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header(X_LOCALE_HEADER_KEY, BuildConfig.X_LOCALE)
                        .header(X_API_KEY_HEADER_KEY, BuildConfig.X_API_KEY)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        return httpClient.build();
    }

    public interface SuccessCallback<T> {
        void onSuccess(T body);
    }
}
