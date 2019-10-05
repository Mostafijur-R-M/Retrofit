package com.fendonus.retrofit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String , List<String >> getData() {
       // HashMap<List<AllChapter>, List<Video>> expandableListDetail = new HashMap<>();
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<Video> videoList = new ArrayList<>();
        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");

        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");

        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");
        //List<AllChapter> chapterList = new ArrayList<>();
        expandableListDetail.put("Accounts", cricket);
        expandableListDetail.put("Smart Phone About", football);
        expandableListDetail.put("Computer About", basketball);
        return expandableListDetail;
    }
    }
