package app.Objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.R;

/**
 * Created by inbal144 on 15/10/2015.
 */
public class GridAdapter extends ArrayAdapter<String> {
    private Context context;


    public GridAdapter(Context context, int resource, List<String> items) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        if (convertView == null) {
            holder = new ViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.singleimage, parent, false);
        holder.view = (ImageView) convertView.findViewById(R.id.imageView2);
        convertView.setTag(holder);
    }

    else {
            holder = (ViewHolder) convertView.getTag();
    }
       String currentItem=getItem(position);
        Picasso.with(context).load(currentItem).resize(300,300)
                .centerCrop().into(holder.view);
        return  convertView;
}


 class ViewHolder {
        private ImageView view;

    }
}