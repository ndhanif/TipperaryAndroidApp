package ienquire.tiprearry;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ienquire.ie.libfff.model.Clip;
import ienquire.ie.libfff.util.FFFUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class Hurling extends Fragment implements AdapterView.OnItemClickListener {

    SliderLayout mDemoSlider;
    ListView list;
    // Tracker mytracker;


    public Hurling() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_hurling, null);

        mDemoSlider = (SliderLayout) root.findViewById(R.id.slider);
        list = (ListView) root.findViewById(R.id.listView);


        Drawable icon = this.getResources().getDrawable(R.drawable.tipp);


        ArrayList<Item> listOfItem = new ArrayList<>();
        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), R.layout.item_layout, listOfItem);
        list.setAdapter(listViewAdapter);


        listOfItem.add(new Item("Tippreary VS Killkenny", "Super cut from John O’Dwyer", "https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal2withslow.mp4", icon));
        listOfItem.add(new Item("Tippreary VS Killkenny", "Awesome Brendan Cummins", "https://s3-eu-west-1.amazonaws.com/ienquirevideos/hurling+final+tipp+vs+galwy/Awesome+Brendan+Cummins.mp4", icon));
        listOfItem.add(new Item("Tipperary Vs Clare", "Super save", "https://s3-eu-west-1.amazonaws.com/ienquirevideos/Tipperary+Vs+Clare/super+save.mp4", icon));
        listOfItem.addAll(getNotifications());

        list.setOnItemClickListener(this);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hurling1", "http://kclrfanzone.com/wp-content/uploads/2016/05/hurlpen.jpg");
        url_maps.put("Hurling2", "http://m0.sportsjoe.ie/wp-content/uploads/2015/03/02104218/hurling.jpg");


        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);


            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);


        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);







        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item clip = (Item) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(this.getContext(), ClipActivity.class);
        intent.putExtra("clip", clip.getUrl());
        startActivity(intent);

    }

    private List<Item> getNotifications() {
        Drawable icon = ContextCompat.getDrawable(getActivity(), R.drawable.tipp);
        List<Item> items = new ArrayList<>();
        List<Clip> clips = FFFUtil.returnListOfClips(getActivity());

        for (Clip clip : clips) {
            if (clip.getCategory().equals("Super Hurling")) {
                items.add(new Item("Feed", clip.getMessage(), clip.getUrl(), icon));
            }
        }

        return items;
    }

    @Override
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle("Super Hurling");
//        mytracker.setScreenName("Screen Name is" + "Super Hurling");
//        mytracker.send(new HitBuilders.ScreenViewBuilder().build());

    }


//    @Override
//    public void onStart() {
//        super.onStart();
//
//        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
//        mytracker = application.getDefaultTracker();
//
//
//    }

}
