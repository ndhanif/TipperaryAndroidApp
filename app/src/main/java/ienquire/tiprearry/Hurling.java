package ienquire.tiprearry;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Hurling extends Fragment implements AdapterView.OnItemClickListener {

    SliderLayout mDemoSlider;
    ListView list;
    Tracker mytracker;


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




         listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Hurling+final+tippr+vs+killk/goal2withslow.mp4",icon));

//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal1.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal10.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal11.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal12.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal13.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal2.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal3.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal4.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal5.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal6.mp4",icon));
//
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal7.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal8.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Galway","Good Goal","https://s3-eu-west-1.amazonaws.com/gaagoals/Galway+vs+Tipprary/goal9.mp4",icon));

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
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);


        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);







        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.i("app", "onItemClick onimte ");
        Item clip = (Item) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(this.getContext(), ClipActivity.class);
        intent.putExtra("clip", clip.getUrl());
        startActivity(intent);

    }

    @Override
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle("Super Hurling");
        mytracker.setScreenName("Screen Name is" + "Super Hurling");
        mytracker.send(new HitBuilders.ScreenViewBuilder().build());

    }


    @Override
    public void onStart() {
        super.onStart();

        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mytracker = application.getDefaultTracker();


    }

}