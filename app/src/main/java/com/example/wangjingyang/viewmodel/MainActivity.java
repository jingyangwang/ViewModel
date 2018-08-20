package com.example.wangjingyang.viewmodel;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



/*
*
* 代码逻辑很简单，首先定义一个CommunicateViewModel类，继承自ViewModel。然后定义两个Fragment：
* FragmentOne和FragmentTwo。在FragmentOne中改变CommunicateViewModel中LiveData保存的数据，
* 然后在FragmentTwo中会收到数据改变的通知。这样一个过程就完成了FragmentOne和FragmentTwo之间的一次简单的通信。
* 至于其中的原理，相信看过LiveData这篇文章话，这都不是问题了。在上面的例子中有个小陷阱：在初始化mCommunicateViewModel时，
* ViewModelProviders.of()方法传入的是Activity对象，如果你改成Fragment对象，那FragmentTwo里就只能傻等了，永远不会收到数据改变的通知。
* 因为如果传给方法ViewModelProviders.of()的对象不同时，
* 最终得到的就不是同一个ViewModel对象，这一点也可以通过打印两个Fragment中的mCommunicateViewModel验证。
*
*
* */
public class MainActivity extends AppCompatActivity {
    Button changefragment1, changefragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changefragment1 = findViewById(R.id.changefragment1);
        changefragment2 = findViewById(R.id.changefragment2);
        changefragment1.setOnClickListener(listener);
        changefragment2.setOnClickListener(listener);
        initFragmentOne();
    }


    public void initFragmentOne() {
        FragmentOne liveDataFragment = new FragmentOne();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, liveDataFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void initFragmentTwo() {
        FragmentTwo liveDataFragment = new FragmentTwo();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, liveDataFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.changefragment1:
                    initFragmentOne();
                    break;
                case R.id.changefragment2:
                    initFragmentTwo();
                    break;

            }
        }
    };


}
