# UltimateBarX
一款方便的设置状态栏和导航栏的各种效果的框架
* 可以设置各种效果，透明、半透明、固定颜色、布局是否侵入等
* 状态栏和导航栏分开设置，互不影响
* 支持 Android 4.4 以上，各系统版本的现实效果高度统一
* 同一个 Activity 可以多次设置不同的效果

### 使用方法
在 gradle 中添加
```groovy
dependencies {
    implementation 'com.zackratos.ultimatebarx:ultimatebarx:0.1.0'
}
```

###### 设置状态栏
在 Activity 中
```kotlin
UltimateBarX.create(UltimateBarX.STATUS_BAR)        // 设置状态栏
    .fitWindow(true)                                // 布局是否侵入状态栏（true 不侵入，false 侵入）  
    .bgColor(Color.BLACK)                           // 状态栏背景颜色 
    .bgRes(R.drawable.bg_gradient)                  // 状态栏背景 drawable
    .light(false)                                   // light模式（状态栏字体灰色 Android 6.0 以上支持）
    .apply(this)
```

###### 设置导航栏
在 Activity 中
```kotlin
UltimateBarX.create(UltimateBarX.NAVIGATION_BAR)    // 设置导航栏
    .fitWindow(true)                                // 布局是否侵入导航栏（true 不侵入，false 侵入）  
    .bgColor(Color.BLACK)                           // 导航栏背景颜色 
    .bgRes(R.drawable.bg_gradient)                  // 导航栏背景 drawable
    .light(false)                                   // light模式（导航栏字体灰色 Android 8.0 以上支持）
    .apply(this)
```

###### 快速设置状态栏透明
在 Activity 中
```kotlin
UltimateBarX.create(UltimateBarX.STATUS_BAR)
    .transparent(true)
    .apply(this)
```
跟下面的写法效果是一样的
```kotlin
UltimateBarX.create(UltimateBarX.STATUS_BAR)
    .fitWindow(false)
    .bgColor(Color.TRANSLUCENT)
    .light(false)
    .apply(this)
```
快速设置导航栏透明的方法也类似，把 **UltimateBarX.STATUS_BAR** 改成 **UltimateBarX.NAVIGATION_BAR** 就行了

### 截图
![](screenshots/transparent_1.png)　![](screenshots/transparent_2.png)

![](screenshots/effect_1.png)　![](screenshots/effect_2.png)

![](screenshots/dynamic_1.gif)　![](screenshots/dynamic_2.gif)