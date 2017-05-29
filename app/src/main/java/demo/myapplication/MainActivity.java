package demo.myapplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    SurfaceView surface;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        surface=(SurfaceView)findViewById(R.id.videoview);

        surfaceHolder=surface.getHolder();//SurfaceHolder是SurfaceView的控制接口
        //surfaceHolder.setFixedSize(320, 220);//显示的分辨率,不设置为视频默认
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {//因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //player=new MediaPlayer();
                player=MediaPlayer.create(getApplication(), R.raw.test1);
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDisplay(surfaceHolder);
                player.setVolume(0f,0f);//设置无声
                player.setLooping(true);//设置无线循环
                //设置显示视频显示在SurfaceView上
                try {
                    player.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                player.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
        //initView();
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
    }
}
