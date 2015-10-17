package app.Fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import app.Objects.GridAdapter;
import app.R;


public class MyGalleryFragment extends Fragment {

    Activity mActivity;
    GridView pictureGrid;

    public static MyGalleryFragment newInstance() {
        MyGalleryFragment fragment = new MyGalleryFragment();
        return fragment;
    }

    public MyGalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);



      pictureGrid = (GridView) v.findViewById(R.id.gridView);
        pictureGrid.setAdapter(new GridAdapter(getActivity(), R.layout.singleimage, getPhotos()));
        pictureGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.app.DialogFragment dialogFragment= new GalleryItemViewFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("position", position+1);
                bundle.putInt("total", parent.getCount());
                bundle.putString("uri", parent.getItemAtPosition(position).toString());
                dialogFragment.setArguments(bundle);

                dialogFragment.show(getActivity().getFragmentManager(), null);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private List<String> getPhotos(){
        Uri tablepath= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] columsToReturn={MediaStore.Images.Media.DATA};
        Cursor results=getActivity().getContentResolver().query(tablepath,columsToReturn,null,null,null);
        List<String> photos=new ArrayList<String>();
        if (results!=null) {
            results.moveToFirst();
            while (!results.isAfterLast()) {
                int colIndex = results.getColumnIndex(MediaStore.Images.Media.DATA);
                String filePath = results.getString(colIndex);
                photos.add("file://" + filePath);
                results.moveToNext();
            }
        }
        return photos;
            }
        }





