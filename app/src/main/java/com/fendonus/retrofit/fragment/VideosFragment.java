package com.fendonus.retrofit.fragment;


import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fendonus.retrofit.R;
import com.fendonus.retrofit.adapter.CustomExpandableListAdapter;
import com.fendonus.retrofit.model.Child;
import com.fendonus.retrofit.model.ExpandableListDataPump;
import com.fendonus.retrofit.model.Parent;
import com.fendonus.retrofit.model.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    View v;
    ExpandableListAdapter mAdapter;
    List<String> _listDataHeader;
    HashMap<String, List<String>> _listDataChild;
    private Parent parent;
    private Child child;
    ExpandableListView lv;
    Context context;

    public VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_videos, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parent = new Parent();
        child = new Child();
        ExpandableListView lv = (ExpandableListView) v.findViewById(R.id.expandableListView);

        //here setting all the values to Parent and child classes
        setDataValues();
        prepareListData();//here get the values and set this values to adoptor and set it visible


        mAdapter = new ExpandableListAdapter() {

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();

            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello1", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onGroupExpanded(int groupPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello2", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onGroupCollapsed(int groupPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello3", Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean isEmpty() {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello4", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello5", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean hasStableIds() {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello6", Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                Toast.makeText(getActivity(), "hello7", Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub
                return v;
            }

            @Override
            public long getGroupId(int groupPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello8", Toast.LENGTH_LONG).show();
                return 0;
            }

            @Override
            public int getGroupCount() {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello9", Toast.LENGTH_LONG).show();
                return 0;
            }

            @Override
            public Object getGroup(int groupPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello10", Toast.LENGTH_LONG).show();
                return null;
            }

            @Override
            public long getCombinedGroupId(long groupId) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello11", Toast.LENGTH_LONG).show();
                return 0;
            }

            @Override
            public long getCombinedChildId(long groupId, long childId) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello12", Toast.LENGTH_LONG).show();
                return 0;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello13", Toast.LENGTH_LONG).show();
                return 0;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello14", Toast.LENGTH_LONG).show();
                return v;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello15", Toast.LENGTH_LONG).show();
                return 0;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello16", Toast.LENGTH_LONG).show();
                return null;
            }

            @Override
            public boolean areAllItemsEnabled() {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "hello17", Toast.LENGTH_LONG).show();
                return false;
            }
        };

        // mAdapter = new ExpandableListAdapter(this, _listDataHeader, _listDataChild);

        // setting list adapter
        lv.setAdapter(mAdapter);


    }


    public void prepareListData() {
        // testing purpose
        _listDataHeader = new ArrayList<String>();
        _listDataChild = new HashMap<String, List<String>>();


        // declare the references
        //add the parent values to List
        _listDataHeader.add(parent.getCardName());
        _listDataHeader.add(String.valueOf(parent.getMinimum_salary()));
        _listDataHeader.add(String.valueOf(parent.getInterest_Rate()));


        //set Child views to parent
        List<String> cardDetails = new ArrayList<String>();
        cardDetails.add("");

        List<String> mininum_sal_details = new ArrayList<String>();
        mininum_sal_details.add(child.get_interest_rate_details());

        List<String> interest_details = new ArrayList<String>();
        interest_details.add(child.get_interest_rate_details());

        //set to adoptor

        _listDataChild.put(_listDataHeader.get(0), cardDetails);
        _listDataChild.put(_listDataHeader.get(1), mininum_sal_details);

        //

        for (int i = 0; i < _listDataHeader.size(); i++) //cars name of arraylist
        {
            String value = _listDataHeader.get(i);
            Toast toast = Toast.makeText(getActivity(), value, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }


    }

    public void setDataValues() {
        //set Parent values
        parent.setCardName("Platinum credit Card");
        parent.setMinimum_salary(15000.00);
        parent.setInterest_Rate(1.2);

        //set Child values
        child.set_card_details("You require minimum salary of 1500 per month");
        child.set_interest_rate_details("interest rate is 2.0%");


    }

    class ExpandabelListAdoptor extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader;
        private HashMap<String, List<String>> _listDataChild;


        ExpandabelListAdoptor(Context con, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
            this._context = con;

            this._listDataChild = listDataChild;
            this._listDataHeader = listDataHeader;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_row_child, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.expandedListItem);

            txtListChild.setText(childText);
            return convertView;

        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            // TODO Auto-generated method stub
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            // TODO Auto-generated method stub
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            // TODO Auto-generated method stub
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {

            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_row_group, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.listTitle);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
