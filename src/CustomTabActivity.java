package com.joshclemm.android.tabs;

import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class CustomTabActivity extends android.app.Activity {

	private TabHost mTabHost;

	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mTabHost = (TabHost) findViewById(R.id.mytabhost);
		mTabHost.setup();
		mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		for (String TabName : new String[]{"Tab 1", "Tab 2", "Tab 3"})
		  {
			setupTab(new TextView(this), TabName);
		  } /*for*/
	}

	private void setupTab(final View view, final String tag) {
		View tabview = android.view.LayoutInflater.from(this).inflate(R.layout.tabs_bg, null);
		((TextView)tabview.findViewById(R.id.tabsText)).setText(tag);

		android.widget.TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(new android.widget.TabHost.TabContentFactory() {
			public View createTabContent(String tag) {return view;}
		});
		mTabHost.addTab(setContent);

	}
}
