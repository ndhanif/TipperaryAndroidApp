package ienquire.tiprearry;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_welcome, null);

        WebView view = (WebView) root.findViewById(R.id.textContent);
        String text;
        text = "<html><body><p align=\"justify\">";
        text += getString(R.string.welcome_text);
        text += "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");

        final WebSettings webSettings = view.getSettings();
        webSettings.setDefaultFontSize(20);


        return root;
    }

}
