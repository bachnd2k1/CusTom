package example.android.custom;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class GioiThieu extends Fragment /*implements YouTubePlayer.OnInitializedListener*/ {
    private TextView tv4;
    // private YouTubePlayerView youTubePlayerView;
    private View view;
    int REQUESTCODE = 123;
    Interface anInterface;
    public static GioiThieu newInstance() {
        // Required empty public constructor
        GioiThieu gioiThieu=new GioiThieu();
        return gioiThieu;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
        TextView tv4=(view).findViewById(R.id.textview4);
        Custom custom= (Custom)getArguments().getSerializable("custom");
        tv4.setText(custom.getDescription());
        return view;
    }




    /*  @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(id);

        } else {
            youTubePlayer.loadVideo(id);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUESTCODE)
        {
            youTubePlayerView.initialize(MainActivity.API_Key,GioiThieu.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
