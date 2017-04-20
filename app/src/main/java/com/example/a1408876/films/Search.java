package com.example.a1408876.films;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Search extends Fragment {

    private SearchView searchField;
    private LinearLayout ll;
    private ArrayList<Movie> movies;
    private Button b;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search, container, false);
        this.searchField = (SearchView) rootView.findViewById(R.id.searchView);
        this.ll = (LinearLayout) rootView.findViewById(R.id.linlayId);


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

                                    String jsonData  = response.body().string();
                                    try {
                                        JSONObject Jobject = new JSONObject(jsonData);
                                        JSONArray Jarray = Jobject.getJSONArray("results");
                                        movies = new ArrayList<>(Jarray.length());

                                        for (int i = 0; i < Jarray.length(); i++) {
                                            JSONObject object = Jarray.getJSONObject(i);
                                            //System.out.println(object.toString());
                                            System.out.println(object.getString("title"));
                                            System.out.println(object.getString("overview"));
                                            System.out.println(object.getString("release_date"));
                                            System.out.println(object.getString("original_title"));
                                            System.out.println(object.getString("original_language"));
                                            System.out.println(object.getString("popularity"));
                                            System.out.println(object.getString("vote_average"));
                                            System.out.println(object.getString("vote_count"));
                                            System.out.println("-------------");

                                            Movie tempMovie = new Movie();
                                            tempMovie.setOriginal_title(object.getString("title"));
                                            tempMovie.setOverview(object.getString("overview"));
                                            tempMovie.setRelease_date(object.getString("release_date"));
                                            tempMovie.setOriginal_title(object.getString("original_title"));
                                            tempMovie.setOriginal_language(object.getString("original_language"));
                                            tempMovie.setPopularity(object.getString("popularity"));
                                            tempMovie.setVote_average(object.getString("vote_average"));
                                            tempMovie.setVote_count(object.getString("vote_count"));

                                            movies.add(tempMovie);
                                        }

                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                        Log.e("MYAPP", "unexpected JSON exception", e);

                                    }


                                }
                            });
                    textAppear(tv);
                    buttonAppear(b);
                }



                System.out.println(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                ll.removeAllViews();
                return false;
            }
        });


        this.searchField.getQuery();
        return rootView;
    }


    public void buttonAppear(Button bt){

        bt = new Button(Search.this.getActivity());
        bt.setText("Save");
        ll.addView(bt);

    }

    public void textAppear(TextView rowTextView){

            rowTextView = new TextView(Search.this.getActivity());
            rowTextView.setText("Results");
            rowTextView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ll.addView(rowTextView);

    }

}
