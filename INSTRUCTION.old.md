## 0.7.0 以下版本使用方法
在根目录的 build.gradle 里面添加
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
在子目录的 build.gradle 中添加
```groovy
dependencies {
    implementation 'com.gitee.zackratos:UltimateBarX:0.6.1'
}
```

在 `Activity` 或 `Fragment` 中
```kotlin
val background = BarBackground.newInstance()    // 创建 background 对象
    .color(Color.TRANSPARENT)                   // 状态栏/导航栏背景颜色（色值）
    .colorRes(R.color.deepSkyBlue)              // 状态栏/导航栏背景颜色（资源id）
    .drawableRes(R.drawable.bg_common)          // 状态栏/导航栏背景 drawable
                                                // 设置背景的方法三选一即可

val config = BarConfig.newInstance()            // 创建配置对象
    .fitWindow(true)                            // 布局是否侵入状态栏（true 不侵入，false 侵入）
    .background(background)                     // 设置 background 对象
    .light(false)                               // light模式
                                                // 状态栏字体 true: 灰色，false: 白色 Android 6.0+
                                                // 导航栏按钮 true: 灰色，false: 白色 Android 8.0+

UltimateBarX.with(this)                         // 对当前 Activity 或 Fragment 生效
    .config(config)                             // 使用配置
    .applyStatusBar()                           // 应用到状态栏
    
UltimateBarX.with(this)                         // 对当前 Activity 或 Fragment 生效
    .config(config)                             // 使用配置
    .applyNavigationBar()                       // 应用到导航栏
```

在低版本上，`light` 模式不生效，可以创建一个新的 `background` 对象来重新设置低版本的状态栏或导航栏背景
```kotlin
val lvLightBackground = BarBackground.newInstance()
    .color(Color.GRAY)
    .colorRes(R.color.alphaBlack)
    .drawableRes(R.drawable.bg_gradient)

val config = BarConfig.newInstance()
    .fitWindow(true)
    .background(background)
    .light(false)
    .lvLightBackground(lvLightBackground)

UltimateBarX.with(this)
    .config(config)
    .applyStatusBar()
```

也可以直接链式调用
```kotlin
UltimateBarX.with(this)
    .fitWindow(true)  
    .color(Color.TRANSPARENT)
    .colorRes(R.color.deepSkyBlue)
    .drawableRes(R.drawable.bg_gradient)
    .light(false)
    .lvLightColor(Color.GRAY)
    .lvLightColorRes(R.color.alphaBlack)
    .lvLightDrawableRes(R.drawable.bg_yellow_alpha_black)
    .applyStatusBar()
```

使用 `transparent` 方法可以快速设置透明效果
```kotlin
UltimateBarX.with(this)
    .transparent()
    .applyStatusBar()
```

跟下面的写法效果是一样的
```kotlin
UltimateBarX.with(this)
    .fitWindow(false)
    .color(Color.TRANSLUCENT)
    .applyStatusBar()
```

使用 `get` 方法可以在上一次的基础上修改  

例如，先用下面的代码实现状态栏变红色，不侵入，非 light 模式
```kotlin
UltimateBarX.with(this)
    .color(Color.RED)
    .fitWindow(true)
    .light(false)
    .applyStatusBar()
```

然后需要设置 light 模式，其他效果保持不变，直接用下面的方法即可
```kotlin
UltimateBarX.get(this)
    .light(true)
    .applyStatusBar()
```

当布局可侵入状态栏或导航栏时，如果需要给某个 `View` 增加状态栏或者导航栏的高度，可以
```kotlin
UltimateBarX.addStatusBarTopPadding(targetView)
UltimateBarX.addNavigationBarBottomPadding(targetView)

// 如果是 kotlin，可以直接使用扩展方法
targetView.addStatusBarTopPadding()
targetView.addNavigationBarBottomPadding()
```