package com.myapplicationdev.android.p10knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.RSS;
import com.crazyhitty.chdev.ks.rssmanager.RssReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1 extends Fragment implements RssReader.RssCallback {

    View v;
    ArrayList<String> colors;
    Random r = new Random();
    RssReader rssReader;
    TextView tv1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Frag1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag1 newInstance(String param1, String param2) {
        Frag1 fragment = new Frag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_frag1, container, false);
        Button b1 = (Button) v.findViewById(R.id.button2);
        tv1 = (TextView) v.findViewById(R.id.tv1);

        rssReader = new RssReader(this);
        String[] x = new String[]{"https://www.channelnewsasia.com/rssfeeds/8395986", "https://www.singstat.gov.sg/rss"};
        rssReader.loadFeeds(x);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colors = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F"));
                String color = "#";
                for (int i=0; i<6; i++) {
                    color += colors.get(r.nextInt((colors.size())));
                }
                v.setBackgroundColor(Color.parseColor(color));
            }
        });
        return v;
    }

    public void loadFeeds(String[] urls) {
        rssReader.loadFeeds(urls);
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        String text = "";
        for (int j=0; j<rssList.size(); j++) {
            for (int i = 0; i < rssList.get(j).getChannel().getItems().size(); i++) {
                text += rssList.get(j).getChannel().getItems().get(i).getDescription();
            }
        }
        tv1.setText(text);
    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {
        Log.d("error", errorMessage);
    }


}