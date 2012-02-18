package com.joshclemm.android.tabs;
/*
    Example of how to apply custom styles to tab indicators. Android styling/theming
    does seem to lead to a proliferation of XML files. Explanation of ones in this
    project:

        res/layout/tabs_bg_plain.xml
            -- plain tab indicator with no special styles, for comparison.
        res/layout/tabs_bg_styled.xml
            -- defines layout of tab indicator, using following drawables.
        res/drawable/tab_bg_selector.xml
            -- defines various states of background of tab indicator, selecting
               from among the following two tab_bg_xxx ones depending on state:
        res/drawable/tab_bg_selected.xml
            -- background of tab indicator in “selected” state.
        res/drawable/tab_bg_unselected.xml
            -- background of tab indicator in “unselected” state.
        res/drawable/tab_text_selector.xml
            -- defines various states of tab indicator text.

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

    @Override
    public void onCreate
      (
        android.os.Bundle savedInstanceState
      )
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        for (boolean CustomStyle : new boolean[]{true, false})
          {
            final TabHost ThisTabHost =
                (TabHost)findViewById(CustomStyle ? R.id.tabhost1 : R.id.tabhost2);
            ThisTabHost.setup();
            ThisTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
            class TabDef
              {
                public final String Indicator, Content;
                public TabDef
                  (
                    String Indicator,
                    String Content
                  )
                  {
                    this.Indicator = Indicator;
                    this.Content = Content;
                  } /*TabDef*/
              } /*TabDef*/;
            for
              (
                TabDef ThisTab :
                    new TabDef[]
                      {
                        new TabDef("Tab 1", "Content 1"),
                        new TabDef("Tab 2", "Content 2"),
                        new TabDef("Tab 3", "Content 3"),
                      }
              )
              {
                final TextView contentview = new TextView(this);
                contentview.setText(ThisTab.Content);
                View indicatorview =
                    android.view.LayoutInflater.from(this).inflate
                      (
                        CustomStyle ? R.layout.tabs_bg_styled : R.layout.tabs_bg_plain,
                        null
                      );
                ((TextView)indicatorview.findViewById(R.id.tabsText)).setText(ThisTab.Indicator);
                ThisTabHost.addTab
                  (
                    ThisTabHost.newTabSpec(ThisTab.Indicator)
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
          } /*for*/
      } /*onCreate*/
  } /*CustomTabActivity*/
