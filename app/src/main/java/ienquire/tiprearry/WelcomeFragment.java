package ienquire.tiprearry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {




    public WelcomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_welcome, null);

//        WebView webView = (WebView) root.findViewById(R.id.webView1);
//        webView.loadData("<p style=\"text-align: justify;\">" + " welcome to the mobile app specifically design for Tipperary hurling fans. \n" +
//                "This is truly a banquet and celebration of Tipperary hurling both past and present.\n" +
//                "Enjoy! \n" +"</p>", "text/html", "UTF-8");
//
//        webView.setBackgroundColor( ContextCompat.getColor(getActivity(), R.color.white));
//        final WebSettings webSettings = webView.getSettings();
//        webSettings.setDefaultFontSize(20);




        return root;
    }

}
