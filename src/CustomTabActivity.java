package com.joshclemm.android.tabs;
/*
    Copyright (c) 2010 Josh Clemm
    Copyright 2012 Lawrence D'Oliveiro <ldo@geek-central.gen.nz>

    Licensed under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.
*/

import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class CustomTabActivity extends android.app.Activity
  {

    private TabHost mTabHost;

    @Override
    public void onCreate
      (
        android.os.Bundle savedInstanceState
      )
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTabHost = (TabHost)findViewById(R.id.mytabhost);
        mTabHost.setup();
        mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

        for (String TabName : new String[]{"Tab 1", "Tab 2", "Tab 3"})
          {
            final View contentview = new TextView(this); /* should really put something into this for show */
            View indicatorview =
                android.view.LayoutInflater.from(this).inflate(R.layout.tabs_bg, null);
            ((TextView)indicatorview.findViewById(R.id.tabsText)).setText(TabName);
            mTabHost.addTab
              (
                mTabHost.newTabSpec(TabName)
                    .setIndicator(indicatorview)
                    .setContent
                      (
                        new android.widget.TabHost.TabContentFactory()
                          {
                            public View createTabContent(String tag)
                              {
                                return contentview;
                              } /*createTabContent*/
                          } /*TabContentFactory*/
                      )
              );
          } /*for*/
      } /*onCreate*/
  } /*CustomTabActivity*/
