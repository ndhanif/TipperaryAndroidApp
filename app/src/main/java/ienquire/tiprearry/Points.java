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
public class Points extends Fragment implements AdapterView.OnItemClickListener{

    SliderLayout mDemoSlider;
    ListView list;
    //Tracker mytracker;


    public Points() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_points, null);
        mDemoSlider = (SliderLayout) root.findViewById(R.id.slider);

        list = (ListView) root.findViewById(R.id.listView);

        Drawable icon = this.getResources().getDrawable(R.drawable.tipp);



        ArrayList<Item> listOfItem = new ArrayList<>();
        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), R.layout.item_layout, listOfItem);
        list.setAdapter(listViewAdapter);


        listOfItem.add(new Item("Tippreary VS Killkenny","Patrick 'Bonnar' Maher","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/BonnarMehar1.mp4",icon));


        listOfItem.add(new Item("Tippreary VS Killkenny", " John 'Bubbles' O'Dwyer", "https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/Bubbles.mp4", icon));

        //listOfItem.add(new Item("Tippreary VS Killkenny","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/SeamusCallanan1.mp4",icon));

        listOfItem.add(new Item("Tippreary VS Killkenny", "John O'Dwyer", "https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/JohnODwyer1.mp4", icon));

        listOfItem.add(new Item("Tippreary VS Killkenny", "Jason Forde", "https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/JasonForde2.mp4", icon));


        listOfItem.add(new Item("Tippreary VS Killkenny","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/Seamus2.mp4",icon));
        listOfItem.add(new Item("Tippreary VS Killkenny","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/seamus3.mp4",icon));
        listOfItem.add(new Item("Tippreary VS Killkenny","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/Seamus4.mp4",icon));

        //listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/gaol9.mp4",icon));
        listOfItem.addAll(getNotifications());


//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal1.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal2.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal3.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal4.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal5.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal6.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal7.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Good Goal","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/goal8.mp4",icon));
//




//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Json Forde","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/JasonForde1.mp4",icon));
//
//
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Jhon O'Dwyer ","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/jhonDwyer2.mp4",icon));

//        listOfItem.add(new Item("Tippreary VS Killkenny","John McGrath","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/JohnMcGrath.mp4",icon));
//

//
//
//
//
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","John McGrath","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/JohnMcGrath2.mp4",icon));
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Json Forde","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/jasonForde3.mp4",icon));
//
//
//
//
//
//
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Seamus Callanan","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/SeamusCallanan5.mp4",icon));
//
//
//
//        listOfItem.add(new Item("Tippreary VS Killkenny","Bonnar Maher","https://s3-eu-west-1.amazonaws.com/hurlling/Tippreary+VS+Killkenny+updated+videos/Bonnarmehar2.mp4",icon));
//








        list.setOnItemClickListener(this);




















        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Points1", "http://m0.sportsjoe.ie/wp-content/uploads/2015/09/07154702/hurling-generic.jpg");
        url_maps.put("Points2", "http://www.gaa.ie/mm/Photo/GaaIe/GAANews/12/58/45/125845_HERO.jpg");



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

    private List<Item> getNotifications() {
        Drawable icon = ContextCompat.getDrawable(getActivity(), R.drawable.tipp);
        List<Item> items = new ArrayList<>();
        List<Clip> clips = FFFUtil.returnListOfClips(getActivity());

        for (Clip clip : clips) {
            if (clip.getCategory().equals("Points")) {
                items.add(new Item("Feed", clip.getMessage(), clip.getUrl(), icon));
            }
        }

        return items;
    }

    @Override
    public void onResume(){
        super.onResume();

        // Set title on Actionbar
        ((MainActivity) getActivity()).setActionBarTitle("Points");
        //mytracker.setScreenName("Screen Name is" + "Points");
        //mytracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

//
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
