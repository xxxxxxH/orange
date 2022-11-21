package com.beta.orange.ui.activity

import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.OnTabSelectedListener
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.beta.orange.R
import com.beta.orange.base.BaseActivity
import com.beta.orange.databinding.ActivityMainBinding
import com.beta.orange.ui.fragment.Fragment1
import com.beta.orange.ui.fragment.Fragment2
import com.beta.orange.ui.fragment.Fragment3
import com.beta.orange.ui.fragment.Fragment4
import com.beta.orange.utils.dp2px
import com.beta.orange.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Field


class MainActivity : BaseActivity<ActivityMainBinding>(), OnTabSelectedListener {

//    //  同一个
//    private val viewModel by activityViewModel<MainActivityViewModel>()
//
//    //   非同一个
//    private val viewModel by viewModel<MainActivityViewModel>()

    private val viewModel by viewModel<MainActivityViewModel>()

    private var fragment1: Fragment1? = null
    private var fragment2: Fragment2? = null
    private var fragment3: Fragment3? = null
    private var fragment4: Fragment4? = null

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initialization() {
//        viewModel.request()
//        viewModel.liveData.observe(this){
//            "result = $it".print()
//        }
//        viewModel.request2().observe(this){
//            when(it){
//               is RequestEvent.StartRequestEvent->{
//                    "on start".print()
//                }
//                is RequestEvent.SuccessRequestEvent->{
//                    "result = ${it.data}".print()
//                }
//                is RequestEvent.FailRequestEvent->{
//                    "on fail = ${it.exception?.message}".print()
//                }
//            }
//        }
        switchFragment(0)
        activityBinding.navigationBar
            .setMode(BottomNavigationBar.MODE_FIXED)
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
            .setTabSelectedListener(this)
            .setBarBackgroundColor(R.color.white)
            //选中时的图片的资源、文字
            .addItem(
                BottomNavigationItem(R.drawable.icon1, "首页")
                    //选中的颜色
                    .setActiveColor(R.color.black)
                    //未选中的颜色(默认灰色 可注释)
                    .setInActiveColor("#999999")
                    //未选中时的图片的资源
                    .setInactiveIconResource(R.drawable.icon5)
            )
            .addItem(
                BottomNavigationItem(R.drawable.icon2, "订单")
                    .setActiveColorResource(R.color.black)
                    .setInActiveColor("#999999")
                    .setInactiveIconResource(R.drawable.icon5)
            ).addItem(
                BottomNavigationItem(R.drawable.icon3, "购物车")
                    .setActiveColorResource(R.color.black)
                    .setInActiveColor("#999999")
                    .setInactiveIconResource(R.drawable.icon5)
            ).addItem(
                BottomNavigationItem(R.drawable.icon4, "我的")
                    .setActiveColorResource(R.color.black)
                    .setInActiveColor("#999999")
                    .setInactiveIconResource(R.drawable.icon5)
            )
            .setFirstSelectedPosition(0)
            .initialise()
        setIconItemMargin(activityBinding.navigationBar,12,25,12)
    }

    override fun onTabSelected(position: Int) {
        switchFragment(position)
    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabReselected(position: Int) {

    }

    private fun hideAll(fm: FragmentTransaction) {
        fragment1?.apply {
            fm.hide(this)
        }
        fragment2?.apply {
            fm.hide(this)
        }
        fragment3?.apply {
            fm.hide(this)
        }
        fragment4?.apply {
            fm.hide(this)
        }
    }

    private fun switchFragment(position: Int) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        hideAll(ft)
        when (position) {
            0 -> {
                fragment1 = fm.findFragmentByTag("1") as Fragment1?
                if (fragment1 == null) {
                    fragment1 = Fragment1()
                    ft.add(R.id.content, fragment1!!, "1")
                } else {
                    ft.show(fragment1!!)
                }
            }
            1 -> {
                fragment2 = fm.findFragmentByTag("2") as Fragment2?
                if (fragment2 == null) {
                    fragment2 = Fragment2()
                    ft.add(R.id.content, fragment2!!, "2")
                } else {
                    ft.show(fragment2!!)
                }
            }
            2 -> {
                fragment3 = fm.findFragmentByTag("3") as Fragment3?
                if (fragment3 == null) {
                    fragment3 = Fragment3()
                    ft.add(R.id.content, fragment3!!, "3")
                } else {
                    ft.show(fragment3!!)
                }
            }
            3 -> {
                fragment4 = fm.findFragmentByTag("4") as Fragment4?
                if (fragment4 == null) {
                    fragment4 = Fragment4()
                    ft.add(R.id.content, fragment4!!, "4")
                } else {
                    ft.show(fragment4!!)
                }
            }
        }
        ft.commit()

    }

    /**
     * 修改间距及图片文字大小
     * @param bottomNavigationBar
     * @param space 文字与图片的间距
     * @param imgLen 单位：dp，图片大小
     * @param textSize 单位：dp，文字大小
     */
    private fun setIconItemMargin(
        bottomNavigationBar: BottomNavigationBar,
        space: Int,
        imgLen: Int,
        textSize: Int
    ) {
        val barClass: Class<*> = bottomNavigationBar.javaClass
        val fields: Array<Field> = barClass.declaredFields
        for (i in fields.indices) {
            val field: Field = fields[i]
            field.isAccessible = true
            if (field.name.equals("mTabContainer")) {
                try {
                    //反射得到 mTabContainer
                    val mTabContainer = field.get(bottomNavigationBar) as LinearLayout
                    for (j in 0 until mTabContainer.childCount) {
                        //获取到容器内的各个Tab
                        val view: View = mTabContainer.getChildAt(j)
                        //获取到Tab内的各个显示控件
                        var params = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            dp2px(56f)

                        )
                        val container =
                            view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_container) as FrameLayout
                        container.layoutParams = params
                        container.setPadding(dp2px(12f), dp2px(0f), dp2px(12f), dp2px(0f))

                        //获取到Tab内的文字控件
                        val labelView =
                            view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_title) as TextView
                        //计算文字的高度DP值并设置，setTextSize为设置文字正方形的对角线长度，所以：文字高度（总内容高度减去间距和图片高度）*根号2即为对角线长度，此处用DP值，设置该值即可。
                        labelView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize.toFloat())
                        labelView.includeFontPadding = false
                        val value = 20 - textSize - space / 2
                        labelView.setPadding(0, 0, 0, dp2px(value.toFloat()))

                        //获取到Tab内的图像控件
                        val iconView: ImageView =
                            view.findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon) as ImageView
                        //设置图片参数，其中，MethodUtils.dip2px()：换算dp值
                        params = FrameLayout.LayoutParams(dp2px(imgLen.toFloat()), dp2px(imgLen.toFloat()))
                        params.setMargins(0, 0, 0, space / 2)
                        params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                        iconView.layoutParams = params
                    }
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }
    }
}