package example.android.custom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class TongQuan extends Fragment {


    public  static TongQuan newInstance() {
        // Required empty public constructor
        TongQuan tongQuan=new TongQuan();
        return tongQuan;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tong_quan, container, false);
    }
}