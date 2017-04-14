####虽然是个很鸡肋的工具类，但是等5.0以下的设备越来越少的时候就有用了,用到的是MediaProjection，做了封装，使用更简单

调用方法：


初始化：
<pre>
private ShotUtils shotUtils;
shotUtils = new ShotUtils(getApplicationContext());
shotUtils.init(MainActivity.this); 
</pre>


回调：
<pre>
   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ShotUtils.REQUEST_MEDIA_PROJECTION:
                shotUtils.setData(data);
                break;
        }
    }
</pre>

调用截图的方法：
<pre>
 shotUtils.startScreenShot(new ShotUtils.ShotListener() {
                    @Override
                    public void OnSuccess(final Bitmap bitmap) {
                                iv.setImageBitmap(bitmap);
                    }
                });
</pre>

截取桌面的效果图

![shot.jpg](http://upload-images.jianshu.io/upload_images/3029020-7881668423820af6.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
