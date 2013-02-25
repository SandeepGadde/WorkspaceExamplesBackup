/*
 * Copyright (C) 2012 0xlab - http://0xlab.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authored by Julian Chu <walkingice AT 0xlab.org>
 */

package ent.dom.slidingbars.widget;

// update the package name to match your app


import ent.dom.slidingbars.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class AnimationLayout extends ViewGroup {

    public final static int DURATION = 400;

    protected boolean mPlaceLeft=false;
    protected boolean mOpened;
    protected View mSidebar;
    protected View mContent;
    protected int mSidebarWidth = 150; /* assign default value. It will be overwrite
                                          in onMeasure by Layout xml resource. */
    protected int mSidebarHeight = 150;
    protected Animation mAnimation;
    protected OpenListener    mOpenListener;
    protected CloseListener   mCloseListener;
    protected Listener mListener;
    int dir=0,constVal = 120;
    protected boolean mPressed = false;
    
    public AnimationLayout(Context context) {
        this(context, null);
    }

    public AnimationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        mSidebar = findViewById(R.id.animation_layout_sidebar);
        mContent = findViewById(R.id.animation_layout_content);

        if (mSidebar == null) {
            throw new NullPointerException("no view id = animation_sidebar");
        }

        if (mContent == null) {
            throw new NullPointerException("no view id = animation_content");
        }

        mOpenListener = new OpenListener(mSidebar, mContent);
        mCloseListener = new CloseListener(mSidebar, mContent);
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        /* the title bar assign top padding, drop it */
        int sidebarLeft = l;
        if (!mPlaceLeft) {
            sidebarLeft = r - mSidebarWidth;
        }
        mSidebar.layout(sidebarLeft,
                0,
                sidebarLeft + mSidebarWidth,
                0 + mSidebar.getMeasuredHeight());
        if (mOpened) {
        	Log.d("mOpened","true");
            if (mPlaceLeft) {           
            	mContent.layout(mSidebarWidth-constVal, mSidebarHeight-constVal,r+mSidebarWidth,b+mSidebarHeight);
            } else  {
            	//mContent.layout(-mSidebarWidth+constVal,mSidebarHeight-constVal,constVal,(r+(mSidebarHeight)));  -- This is right top to left bottom animation	            	
            	mContent.layout(-mSidebarWidth+constVal,-mSidebarHeight+constVal,constVal,constVal);
            }
        } else {
        	Log.d("mOpened","false");	
        	if(mPlaceLeft) {
        		mContent.layout(0, 0, mSidebarWidth, mSidebarHeight);
        	} else {
        		//mContent.layout(0, 0, mSidebarWidth, mSidebarHeight);  -- This is right top to left bottom animation
        		mContent.layout(0, 0, mSidebarWidth, mSidebarHeight);
        	}
        }
    }

    @Override
    public void onMeasure(int w, int h) {
        super.onMeasure(w, h);
        super.measureChildren(w, h);
        mSidebarWidth = mSidebar.getMeasuredWidth();
        mSidebarHeight = mSidebar.getMeasuredHeight();
    }

    @Override
    protected void measureChild(View child, int parentWSpec, int parentHSpec) {
        /* the max width of Sidebar is 90% of Parent */
        if (child == mSidebar) {
            int mode = MeasureSpec.getMode(parentWSpec);            
            int width = (int)(getMeasuredWidth() * 1);//should be 0.9
            int height = (int)(getMeasuredHeight() * 1);//should be 0.9
            super.measureChild(child, MeasureSpec.makeMeasureSpec(width, mode), 
            		MeasureSpec.makeMeasureSpec(height, MeasureSpec.getMode(parentHSpec)));
        } else {
            super.measureChild(child, parentWSpec, parentHSpec);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isOpening()) {
            return false;
        }

        int action = ev.getAction();

        if (action != MotionEvent.ACTION_UP
                && action != MotionEvent.ACTION_DOWN) {
            return false;
        }

        /* if user press and release both on Content while
         * sidebar is opening, call listener. otherwise, pass
         * the event to child. */
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        if (mContent.getLeft() < x
                && mContent.getRight() > x
                && mContent.getTop() < y
                && mContent.getBottom() > y) {
            if (action == MotionEvent.ACTION_DOWN) {
                mPressed = true;
            }

            if (mPressed
                    && action == MotionEvent.ACTION_UP
                    && mListener != null) {
                mPressed = false;
                return mListener.onContentTouchedWhenOpening();
            }
        } else {
            mPressed = false;
        }

        return false;
    }

    public void setListener(Listener l) {
        mListener = l;
    }

    /* to see if the Sidebar is visible */
    public boolean isOpening() {
        return mOpened;
    }

    public void toggleSidebar(int dir) {
    	this.dir = dir;
    	if(dir == 0) 
    		mPlaceLeft = true;
    	else if(dir == 1)
    		mPlaceLeft = false;
        if (mContent.getAnimation() != null) {
            return;
        }

        if (mOpened) {
            /* opened, make close animation*/
            if (mPlaceLeft) {
                mAnimation = new TranslateAnimation(0,-(mSidebarWidth-constVal),0,-(mSidebarHeight-constVal));
            } else {            	
                //mAnimation = new TranslateAnimation(0, mSidebarWidth-constVal, 0, -(mSidebarHeight-constVal)); -- This is right top to left bottom animation
            	mAnimation = new TranslateAnimation(0, mSidebarWidth-constVal, 0, mSidebarHeight-constVal);
            	//mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.hyperspace_jump);
            }
            mAnimation.setAnimationListener(mCloseListener);
        } else {
            /* not opened, make open animation */
            if (mPlaceLeft) {
                mAnimation = new TranslateAnimation(0, mSidebarWidth-constVal, 0, mSidebarHeight-constVal);
            } else {
                //mAnimation = new TranslateAnimation(0, -mSidebarWidth+constVal, 0, mSidebarHeight-constVal);  -- This is right top to left bottom animation
            	mAnimation = new TranslateAnimation(0, -mSidebarWidth+constVal, 0, -mSidebarHeight+constVal);
            }
            mAnimation.setAnimationListener(mOpenListener);
        }
        mAnimation.setDuration(DURATION);
        mAnimation.setFillAfter(true);
        mAnimation.setFillEnabled(true);
        mContent.startAnimation(mAnimation);
    }

    public void openSidebar() {
        if (!mOpened) {
            toggleSidebar(dir);
        }
    }

    public void closeSidebar() {
        if (mOpened) {
            toggleSidebar(dir);
        }
    }

    class OpenListener implements Animation.AnimationListener {
        View iSidebar;
        View iContent;

        OpenListener(View sidebar, View content) {
            iSidebar = sidebar;
            iContent = content;
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            iSidebar.setVisibility(View.VISIBLE);
        }

        public void onAnimationEnd(Animation animation) {
            iContent.clearAnimation();
            mOpened = !mOpened;
            requestLayout();
            if (mListener != null) {
                mListener.onSidebarOpened();
            }
        }
    }

    class CloseListener implements Animation.AnimationListener {
        View iSidebar;
        View iContent;

        CloseListener(View sidebar, View content) {
            iSidebar = sidebar;
            iContent = content;
        }

        public void onAnimationRepeat(Animation animation) {
        }
        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            iContent.clearAnimation();
            iSidebar.setVisibility(View.INVISIBLE);
            mOpened = !mOpened;
            requestLayout();
            if (mListener != null) {
                mListener.onSidebarClosed();
            }
        }
    }

    public interface Listener {
        public void onSidebarOpened();
        public void onSidebarClosed();
        public boolean onContentTouchedWhenOpening();
    }
}
