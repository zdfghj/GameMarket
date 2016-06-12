package com.frag.classifyfragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.game.gamemarket.R;
import com.game.jsonresolve.JsonClassifyData;

/**
 *	分类界面 
 */
public class ClassifyFragment extends Fragment {
	
	private View view;
	
	private ClassifyAdapter mClassifyAdapter = null;
	private List<JsonClassifyData> dataClassifyList;
	private ListView classifyList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.classify_fragment, container, false);
		initBaseAdapter();
		return view;
	}

	private void initBaseAdapter() {
		// 绑定ListView控件
		classifyList =  (ListView) view.findViewById(R.id.classify_list);
		
		initData();
		
		mClassifyAdapter = new ClassifyAdapter(getActivity(), dataClassifyList);
		
		classifyList.setAdapter(mClassifyAdapter);
		
	}

	private void initData() {
		dataClassifyList = new ArrayList<JsonClassifyData>();
		for (int i = 0; i < 10; i++) {
			JsonClassifyData jhpd = new JsonClassifyData();
			jhpd.setGameTypeName("最新游戏");
			dataClassifyList.add(jhpd);
		}
	}
}
