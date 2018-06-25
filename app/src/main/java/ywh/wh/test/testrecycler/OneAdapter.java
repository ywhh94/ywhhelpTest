package ywh.wh.test.testrecycler;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import wh.ywh.base.CommonAdapter;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-06-22.
 */
public class OneAdapter extends CommonAdapter<String>{
    private Context mContext ;
    public OneAdapter(Context context, List<String> s) {
        super(R.layout.item_one,s);
        this.mContext = context;
    }

    @Override
    protected void bindData(CommonViewHolder holder, String s, int position) {
        holder.setText(R.id.item_tv,""+s);
//        holder.setImage();
        String gif ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529667609867&di=c576a1807cbfc5e56b3334aba8a76e12&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012f4a59a2830fa8012028a930cc24.gif";
        Glide.with(mContext)
                .load(gif)
//                .load(Uri.fromFile(new File("12")))            // 可以加载视频，只能是本地视频
                .asGif()
//                .transform()            //预处理（圆角。。）
//                .load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3522984811,3915528443&fm=27&gp=0.jpg")
                .crossFade(1500)     //强制开启 Glide 默认的图片淡出淡入动画，可以控制动画的持续时间。动画默认的持续时间是300ms。
                .placeholder(R.drawable.loading)            // 图片加载出来前显示的图片
                .error(R.drawable.error)                    // 图片加载失败后显示的图片
//                .thumbnail(0.1f)                            // 缩略图
                .override(300,100)                          // 图片大小与裁剪
                .skipMemoryCache(true)                       //取消内存缓存
                .diskCacheStrategy( DiskCacheStrategy.NONE )  //取消磁盘缓存
//                .centerCrop()
                .priority( Priority.LOW )                    // 加载优先级
                .into((ImageView)holder.findView(R.id.item_iv));

    }
}
