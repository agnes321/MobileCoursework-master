package com.example.a1408876.films;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.SearchView;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.Manifest.permission;

import org.json.JSONArray;
import org.json.JSONObject;


public class Search extends Fragment {

    private SearchView searchField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search, container, false);
        this.searchField = (SearchView) rootView.findViewById(R.id.searchView);


        // perform set on query text listener event
        this.searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String url = "https://api.themoviedb.org/3/search/movie?api_key=c3b71235edcd1d31c7043892fc4dcf4d&language=en-US&query="+query+"&page=1&include_adult=false";
                //URLUtil.isValidUrl(query);
                if (URLUtil.isValidUrl(url) == true) {

                    OkHttpClient client = new OkHttpClient();

                    MediaType mediaType = MediaType.parse("application/octet-stream");
                    RequestBody body = RequestBody.create(mediaType, "{}");
                    Request request = new Request.Builder().url(url).get().build();


                            client.newCall(request).enqueue(new Callback() {
                                @Override public void onFailure(Call call, IOException e) {
                                    e.printStackTrace();
                                }

                                @Override public void onResponse(Call call, Response response) throws IOException {

                                    Headers responseHeaders = response.headers();
                                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                                    }

                                    System.out.println(response.body().string());


                                }
                            });

                }

                System.out.println(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                return false;
            }
        });

        this.searchField.getQuery();
        return rootView;
    }




}
