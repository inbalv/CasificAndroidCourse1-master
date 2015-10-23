package app.Fragments;

        import android.app.Activity;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.support.v4.app.DialogFragment;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.GridView;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import app.Control.GalleryAdapter;
        import app.R;


public class GalleryFragment extends Fragment {

    Activity mActivity;
    GridView gridView;

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
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        gridView = (GridView) v.findViewById(R.id.gridView);
        gridView.setAdapter(new GalleryAdapter(getActivity(), R.layout.gallery_item, getPhotos()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment dialogFrag  = new GalleryItemViewFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("position", position+1);
                bundle.putInt("total", parent.getCount());
                bundle.putString("uri", parent.getItemAtPosition(position).toString());

                dialogFrag.setArguments(bundle);

                dialogFrag.show(getFragmentManager(), null);
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

        Uri tablepath = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] columnsToReturn = { MediaStore.Images.Media.DATA };

        Cursor results = getActivity().getContentResolver().query(tablepath,columnsToReturn, null , null , null);

        List<String> photos = new ArrayList<String>();

        //populate photos with results
        if(results != null) {
            results.moveToFirst();
            while (!results.isAfterLast()) {

                Map<String,String> item = new HashMap<String,String>();

                int colIndex = results.getColumnIndex(MediaStore.Images.Media.DATA);

                String filepath = results.getString(colIndex);

                //convert the filepath into a Uri string for picasso
                photos.add("file://"+filepath);

                results.moveToNext();
            }
        }

        results.close();

        return photos;
    }
}