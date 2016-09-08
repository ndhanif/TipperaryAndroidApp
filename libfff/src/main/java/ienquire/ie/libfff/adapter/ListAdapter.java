package ienquire.ie.libfff.adapter;

import android.content.*;
import android.view.*;
import android.widget.*;

import java.util.*;

import ienquire.ie.libfff.*;
import ienquire.ie.libfff.model.*;


public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Clip> objects;

    public ListAdapter(Context context, ArrayList<Clip> list) {
        this.context = context;
        this.objects = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_videos_item, null);
            holder = new ViewHolder();
            holder.tv_1 = (TextView) convertView.findViewById(R.id.textView);
            holder.tv_2 = (TextView) convertView.findViewById(R.id.textView2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_1.setText(objects.get(position).getMessage());
        holder.tv_2.setText(objects.get(position).getName());
        return convertView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        TextView tv_1, tv_2;
    }
}