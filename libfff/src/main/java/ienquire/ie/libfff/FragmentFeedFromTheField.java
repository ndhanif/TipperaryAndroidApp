package ienquire.ie.libfff;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;

import ienquire.ie.libfff.adapter.ListAdapter;
import ienquire.ie.libfff.model.Clip;
import ienquire.ie.libfff.util.FFFUtil;

public class FragmentFeedFromTheField extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listview;
    private ArrayList<Clip> listOfClips;
    private FFFUtil fffUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, null);
        fffUtil = new FFFUtil();
        listview = (ListView) view.findViewById(R.id.listView);
        listview.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listOfClips = fffUtil.returnListOfClips(getActivity()); //{"Sample 1","Sample 2"}
        for (Clip clip: listOfClips){
            Log.i("fff", "onResume: clip thum = " + clip.getThumbnail());
        }
        listview.setAdapter(new ListAdapter(getActivity(), listOfClips));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        fffUtil.watchClip(getActivity(), listOfClips.get(position));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fff, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_notification) {
            Dialog dialog = new AppCompatDialog(getActivity(), R.style.DialogTheme);
            dialog.setTitle(R.string.enable_notificaion);
            dialog.setContentView(R.layout.fff_list_dialog);
            dialog.show();
            ListView lv = (ListView) dialog.findViewById(R.id.listView);
            final String[] values = getResources().getStringArray(R.array.en);
            lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, values) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    CheckedTextView checkedTxtView = (CheckedTextView) super.getView(position, convertView, parent);

                    String yourValue = values[position];
                    checkedTxtView.setText(yourValue);
                    checkedTxtView.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.black));
                    return checkedTxtView;
                }

            });

            lv.setItemsCanFocus(true);
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lv.setItemChecked(FFFUtil.isEnableNotification(getActivity().getApplicationContext()) ? 0 : 1, true);

        }
        return true;

    }

}
