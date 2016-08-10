package yaoyaoandus.contacts;

/**
 * Created by LJY on 16/8/10.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
      * A placeholder fragment containing a simple view.
      */
public  class ThirdFragment extends android.support.v4.app.Fragment {

    public ThirdFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_3, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textview_frag3);
        textView.setText("管理我发出的名片");
        return rootView;
    }
}