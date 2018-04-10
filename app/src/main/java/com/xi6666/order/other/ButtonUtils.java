package com.xi6666.order.other;

/**
 * @作者: Mr.yang
 * 
 * @创建时间:2013-10-19下午4:56:10
 * @描述: 防止按钮被极快的速度去暴力点击
 * 
 */
public class ButtonUtils {
	
	private static long	lastClickTime	= 0;

	public synchronized static boolean isFastClick() {
		
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 4000) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public synchronized static boolean isFastClick(long time) {

		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis - lastClickTime < time) {
			return true;
		}
		lastClickTime = currentTimeMillis;
		return false;
	}

  /*使用方式:在按钮的点击事件中做如下处理
  mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 防止按钮被暴力点击
				if (ButtonUtils.isFastClick()) {
				
					return;
					
				}
               //TODO 去实现自己的逻辑

			}
		});*/
}
