package example.android.custom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    ImageView imageView1;
    TextView textView1, textView2, textView3, textView4;
    YouTubePlayerView youTubePlayerView;
    Button btn1, btn2, btn3, btn4, btn5;
    String id = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final GioiThieu gioiThieu = new GioiThieu();
        textView1 = findViewById(R.id.textview1);
        textView2 = findViewById(R.id.textview2);
        textView3 = findViewById(R.id.textview3);
        textView4 = findViewById(R.id.textview4);
        imageView1 = findViewById(R.id.imageview1);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        // youTubePlayerView=findViewById(R.id.id_youtube);
        List<Fragment> fragmentList=getfragment();
        Intent intent = getIntent();
        Integer key = intent.getIntExtra("key", 0);
        String name = intent.getStringExtra("name");
        String icon = intent.getStringExtra("icon");
        String tittle = intent.getStringExtra("tittle");
        String tags = intent.getStringExtra("tags");


        textView1.setText(name);
        textView2.setText(tittle);
        textView3.setText(tags);
        Picasso.with(MainActivity2.this).load(icon).into(imageView1);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.AddFragment(GioiThieu.newInstance(), "Giới Thiệu");
        viewPagerAdapter.AddFragment(TongQuan.newInstance(), "Tổng Quan");
        viewPagerAdapter.AddFragment(KiNang.newInstance(), "Kĩ Năng");
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);

    }

    private List<Fragment> getfragment()
    {   List<Fragment> fragmentList=new ArrayList<>();
        viewPagerAdapter.AddFragment(GioiThieu.newInstance(), "Giới Thiệu");
        viewPagerAdapter.AddFragment(TongQuan.newInstance(), "Tổng Quan");
        viewPagerAdapter.AddFragment(KiNang.newInstance(), "Kĩ Năng");
        return  fragmentList;
    }










   /*     youTubePlayerView.initialize(MainActivity.API_Key,this);
        youTubePlayerView.setBackgroundColor(0x000000);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(id);

        } else {
            youTubePlayer.loadVideo(id);


        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(MainActivity2.this,REQUESTCODE);
        }
        else
        {
            Toast.makeText(MainActivity2.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUESTCODE)
        {
            youTubePlayerView.initialize(MainActivity.API_Key,MainActivity2.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/



}


