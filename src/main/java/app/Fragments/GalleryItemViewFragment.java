package app.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import app.R;


public class GalleryItemViewFragment extends DialogFragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.gallery_large_view, container);
        ImageView imageView=(ImageView) view.findViewById(R.id.largeImageView);
        Bundle bundle=getArguments();
        if (bundle!=null) {
            getDialog().setTitle(bundle.getInt("position") + "/" + bundle.getInt("total"));
            Picasso.with(getActivity()).load(bundle.getString("uri")).into(imageView);
        }
        return view;
        }
    }
