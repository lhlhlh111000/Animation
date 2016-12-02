#为什么要有动画
很多时候我们第一眼被产品所吸引的时候可以是产品酷炫的交互；简单说动画做的好坏是提升一款产品体验很重要的**因素**; 在日常的Android开发中，经常会使用到动画，这里就对Android开发中的动画做一下总结

#安卓中动画的（分类）实现方式（API）

总的来说，Android动画可以分为两类，最初的**传统动画**和Android3.0 之后出现的**属性动画**

>  传统动画又包括**帧动画**（Frame Animation）和**补间动画**（Tweened Animation）
	
1. **帧动画**</br>
更多的依赖于完善的UI资源，他的原理就是将一张张单独的图片连贯的进行播放，从而在视觉上产生一种动画的效果；有点类似于某些软件制作gif动画的方式。
		
		<?xml version="1.0" encoding="utf-8"?>
		<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
		     android:oneshot="false" >
		    <item
		        android:drawable="@drawable/light01"
		        android:duration="50"/>
		    <item
		        android:drawable="@drawable/light3"
		        android:duration="50"/>
		    <item
		        android:drawable="@drawable/light02"
		        android:duration="50"/>
		</animation-list>

		//获取 AnimationDrawable
         animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
        //开始动画
        animationDrawable.start();

2. **补间动画**</br>

补间动画又可以分为四种形式，分别是 alpha（淡入淡出），translate（位移），scale（缩放大小），rotate（旋转）。

		<?xml version="1.0" encoding="utf-8"?>
		<alpha xmlns:android="http://schemas.android.com/apk/res/android"
		    android:duration="1000"
		    android:fromAlpha="1.0"
		    android:interpolator="@android:anim/accelerate_decelerate_interpolator"
		    android:toAlpha="0.0" />

		// 引用
		Animation animation = AnimationUtils.loadAnimation(this, res);
        ivAnim.startAnimation(animation);

   也可以使用set 标签将多个动画组合

		<?xml version="1.0" encoding="utf-8"?>
		<set xmlns:android="http://schemas.android.com/apk/res/android"
		    android:interpolator="@[package:]anim/interpolator_resource"
		    android:shareInterpolator=["true" | "false"] >
		    <alpha
		        android:fromAlpha="float"
		        android:toAlpha="float" />
		    <scale
		        android:fromXScale="float"
		        android:toXScale="float"
		        android:fromYScale="float"
		        android:toYScale="float"
		        android:pivotX="float"
		        android:pivotY="float" />
		    <translate
		        android:fromXDelta="float"
		        android:toXDelta="float"
		        android:fromYDelta="float"
		        android:toYDelta="float" />
		    <rotate
		        android:fromDegrees="float"
		        android:toDegrees="float"
		        android:pivotX="float"
		        android:pivotY="float" />
		    <set>
		        ...
		    </set>
		</set>

解释下interpolator和pivot

> Interpolator 主要作用是可以控制动画的变化速率 ，就是动画进行的快慢节奏。

> pivot 决定了当前动画执行的参考位置

10	距离动画所在view自身左边缘10像素

10%	距离动画所在view自身左边缘 的距离是整个view宽度的10%

10%p距离动画所在view父控件左边缘的距离是整个view宽度的10%


3.**属性动画**</br>

属性动画，顾名思义它是对于对象属性的动画。因此，所有补间动画的内容，都可以通过属性动画实现。

		ObjectAnimator anim = ObjectAnimator.ofFloat(ivAnim, "alpha", 0.0f, 1.0f);
        anim.setDuration(2000);
        anim.start();

也可以进行组合实现：

	ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(myView, "alpha", 1.0f, 0.5f, 0.8f, 1.0f);
    ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(myView, "scaleX", 0.0f, 1.0f);
    ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(myView, "scaleY", 0.0f, 2.0f);
    ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(myView, "rotation", 0, 360);
    ObjectAnimator transXAnim = ObjectAnimator.ofFloat(myView, "translationX", 100, 400);
    ObjectAnimator transYAnim = ObjectAnimator.ofFloat(myView, "tranlsationY", 100, 750);
    AnimatorSet set = new AnimatorSet();
    set.playTogether(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
	// set.playSequentially(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
    set.setDuration(3000);
    set.start();

可以指定按顺序播放，还是一起播放。

> 动画的实现原理

>（MD）材料设计

> 好的动画效果

http://blog.csdn.net/startfromweb/article/details/7644405