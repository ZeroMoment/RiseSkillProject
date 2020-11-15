package lzm.cn.riseskillproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lzm.cn.riseskillproject.R;

/**
 * Created by lizhiming on 2018/1/31.
 */

public class ItemFragment extends Fragment {

    public static ItemFragment newInstance (String item) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", item);
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item, null);
        TextView textView = (TextView) view.findViewById(R.id.fragment_item_textview);
        Bundle bundle = getArguments();
        textView.setText(bundle.getString("title"));
        return view;
    }
}
