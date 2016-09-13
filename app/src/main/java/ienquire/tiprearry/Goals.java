package ienquire.tiprearry;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ienquire.ie.libfff.model.Clip;
import ienquire.ie.libfff.util.FFFUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class Goals extends Fragment implements AdapterView.OnItemClickListener{

    SliderLayout mDemoSlider;
    ListView list;
    //Tracker mytracker;

    public Goals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_goals, null);
        mDemoSlider = (SliderLayout) root.findViewById(R.id.slider);
        list = (ListView) root.findViewById(R.id.listView);

        Drawable icon = this.getResources().getDrawable(R.drawable.tipp);



        ArrayList<Item> listOfItem = new ArrayList<>();
        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), R.layout.item_layout, listOfItem);
        list.setAdapter(listViewAdapter);

//        listOfItem.add(new Item("Tippreary","Jhon Mc Grath","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JhonMcGrath1.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/johnODwyer1.mp4",icon));
//
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer2.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer3.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer4.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer5.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer6.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer7.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer8.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Jhon O'Dwyer","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/JohnODwyer9.mp4",icon));
//
//
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan1.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan2.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan3.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan4.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan5.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan6.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tipp+app+content+Sept+2016/SeamusCallanan7.mp4",icon));

        listOfItem.add(new Item("Tippreary VS Killkenny","Noel Mc Grath","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/NoelMcGrath.mp4",icon));
        listOfItem.add(new Item("Tippreary VS Killkenny", "Noel Mc Grath", "https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/NoelMcGrath.mp4", icon));

        listOfItem.addAll(getNotifications());
        list.setOnItemClickListener(this);







        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Goal1", "http://www.rappstars.com/images/hurling2.jpg");
        url_maps.put("Goal2", "http://kclr96fm.com/media/2015/04/hurling-stock-images.png");



        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);



            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);


        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
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

    private List<Item> getNotifications() {
        Drawable icon = ContextCompat.getDrawable(getActivity(), R.drawable.tipp);
        List<Item> items = new ArrayList<>();
        List<Clip> clips = FFFUtil.returnListOfClips(getActivity());

        for (Clip clip : clips) {
            if (clip.getCategory().equals("Goals")) {
                items.add(new Item("Feed", clip.getMessage(), clip.getUrl(), icon));
            }
        }

        return items;
    }


    @Override
    public void onResume(){
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle("Goals");
//        mytracker.setScreenName("Screen Name is" + "Goals");
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
