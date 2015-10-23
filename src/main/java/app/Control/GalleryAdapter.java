package app.Control;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.R;

public class GalleryAdapter extends ArrayAdapter<String> {

    private Context context;

    public GalleryAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);

        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false);
            holder.view = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String currentItem = getItem(position);

        //picasso can accept either Uri, Uri-string or a File
        Picasso.with(context).load(currentItem)
                //resize the picture to avoid out of memory issues since this is just a thumbnail
                .resize(300, 300).centerCrop()
                //put the edited picture into the ImageView
                .into(holder.view);

        return convertView;
    }

    private class ViewHolder{
        private ImageView view;
    }
}