package com.joshclemm.android.tabs;

import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class CustomTabActivity extends android.app.Activity {

	private TabHost mTabHost;

	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(R.id.mytabhost);
		mTabHost.setup();
	}

	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// construct the tabhost
		setContentView(R.layout.main);

		setupTabHost();
		mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		setupTab(new TextView(this), "Tab 1");
		setupTab(new TextView(this), "Tab 2");
		setupTab(new TextView(this), "Tab 3");
	}

	private void setupTab(final View view, final String tag) {
		View tabview = createTabView(mTabHost.getContext(), tag);

		android.widget.TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new android.widget.TabHost.TabContentFactory() {
			public View createTabContent(String tag) {return view;}
		});
		mTabHost.addTab(setContent);

	}

	private static View createTabView(final android.content.Context context, final String text) {
		View view = android.view.LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}