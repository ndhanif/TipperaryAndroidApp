package ienquire.tiprearry;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by barryoreilly on 13/5/16.
 */
public class ListViewAdapter extends ArrayAdapter<Item> {

    private final List<Item> items;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Item getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View v = convertView;
        ViewHolder holder = null;

        if (v == null) {
            holder = new ViewHolder();
            int layout = R.layout.item_layout;
            v = inflater.inflate(layout, null);

            holder.tt1 = (TextView) v.findViewById(R.id.item);
            holder.tt12 = (TextView) v.findViewById(R.id.textView1);
            holder.img = (ImageView) v.findViewById(R.id.icon);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        Item p = items.get(position); //getItem(position);

        holder.tt1.setText(p.getTitle());
        holder.tt12.setText(p.getDetails());

        Log.i("fff", "getView: image = " + p.getImage());

        //load image directly

        if (p.getImage() != null){
            holder.img.setImageDrawable(p.getImage());
        }else if ( p.getImageurl() != null){
            if (!p.getImageurl().isEmpty())
            Picasso.with(getContext()).load(p.getImageurl()).error(R.drawable.tipp).into(holder.img);

        }else{
            Picasso.with(getContext()).load(R.drawable.tipp)
                    .into(holder.img);
        }

        return v;
    }


    static class ViewHolder {
        TextView tt1, tt12;
        ImageView img;

    }

}
