package yaoyaoandus.contacts;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lenovo on 2016/8/10.
 */
public class ViewHolder {
    public ImageView userPhoto=null;
    public TextView userNum=null;
    public ImageButton deleteButton=null;
    public ViewHolder(ImageView userPhoto, TextView userNum, ImageButton deleteButton
    ) {
        super();
        this.userPhoto = userPhoto;
        this.userNum= userNum;
        this.deleteButton = deleteButton;

    }

}
