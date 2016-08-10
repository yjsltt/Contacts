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
public  class FirstFragment extends android.support.v4.app.Fragment {

    public FirstFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_1, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textview_frag1);
        textView.setText("我会有一个通讯录list");
        return rootView;
    }
}