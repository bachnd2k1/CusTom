package example.android.custom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class KiNang extends Fragment {



    public static KiNang newInstance() {
        // Required empty public constructor
        KiNang kiNang=new KiNang();
        return kiNang;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ki_nang, container, false);
    }
}