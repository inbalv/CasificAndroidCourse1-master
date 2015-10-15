package app.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import app.Objects.myPicFiles;
import app.R;


public class GalleryFragment extends Fragment {
    ArrayList<File> list;
    Activity mActivity;
    View v;
    public myPicFiles mypic = new myPicFiles();

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    public GalleryFragment() {
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
        list = imageReader(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
        v = inflater.inflate(R.layout.fragment_gallery, container, false);

        // text view
        GridView pictureGrid = (GridView) v.findViewById(R.id.gridView);

        pictureGrid.setAdapter(new GridAdapter());

        return v;
    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getActivity().getLayoutInflater().inflate(R.layout.my_grid, viewGroup, false);
            ImageView iv = (ImageView) view.findViewById(R.id.imageView);
            iv.setImageURI(Uri.parse(getItem(i).toString()));
            return view;


        }
    }

    public ArrayList<File> imageReader(File root) {
        ArrayList<File> a = new ArrayList<>();
        File[] files = root.listFiles();
        ImageLoader loader = new ImageLoader();
        loader.execute(files);

        return mypic.getMyPic();

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

    class ImageLoader extends AsyncTask<File[], Void, ArrayList> {


        @Override
        protected ArrayList doInBackground(File[]... params) {
            File[] files = params[0];
            ArrayList<File> a = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                while (files[i].isDirectory()) {
                    File[] newfiles = files[i].listFiles();
                    for (int k = 0; k < newfiles.length; k++) {
                        a.add(newfiles[k]);


                    }
                }
            }
            ArrayList<File> picList = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i).getName().endsWith(".jpg")) {
                    picList.add(a.get(i));

                }


            }

            return picList;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            if (arrayList != null) {
                mypic.setMyPic(arrayList);
            }
        }


    }
}


