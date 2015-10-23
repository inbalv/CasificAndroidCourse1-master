package app.Fragments;


        import android.graphics.Matrix;
        import android.os.Bundle;
        import android.support.v4.app.DialogFragment;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.ScaleGestureDetector;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import com.squareup.picasso.Picasso;

        import app.Objects.TouchImageView;
        import app.R;

public class GalleryItemViewFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gallery_large_view, container);
        ImageView imageView = (ImageView) view.findViewById(R.id.largeImageView);

        Bundle bundle = getArguments();

        if(bundle != null) {
            getDialog().setTitle(bundle.getInt("position") + " / " + bundle.getInt("total"));
            Picasso.with(getActivity()).load(bundle.getString("uri")).into(imageView);
        }

        return view;


    }



}