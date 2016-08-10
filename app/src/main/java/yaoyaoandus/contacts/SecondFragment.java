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
public  class SecondFragment extends android.support.v4.app.Fragment {

    public SecondFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_2, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textview_frag2);
        textView.setText("我是群");
        return rootView;
    }
}