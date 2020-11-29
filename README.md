# UltimateBarX
一款方便的设置状态栏和导航栏的各种效果的框架
* 可以设置各种效果，透明、半透明、固定颜色、布局是否侵入等
* 状态栏和导航栏分开设置，互不影响
* 支持 Android 4.4 以上，各系统版本的现实效果高度统一
* 支持 Activity 和 Fragment
* 同一个 Activity 或 Fragment 可以多次设置不同的效果

### 使用方法
在 gradle 中添加
```groovy
dependencies {
    implementation 'com.zackratos.ultimatebarx:ultimatebarx:0.2.4'
}
```

#### 设置状态栏
在 Activity 或 Fragment 中
```kotlin
UltimateBarX.create(UltimateBarX.STATUS_BAR)        // 设置状态栏
    .fitWindow(true)                                // 布局是否侵入状态栏（true 不侵入，false 侵入）  
    .bgColor(Color.BLACK)                           // 状态栏背景颜色（色值）
    .bgColorRes(R.color.deepSkyBlue)                // 状态栏背景颜色（资源id）
    .bgRes(R.drawable.bg_gradient)                  // 状态栏背景 drawable
    .light(false)                                   // light模式（状态栏字体灰色 Android 6.0 以上支持）
    .apply(this)
```

#### 设置导航栏
在 Activity 或 Fragment 中
```kotlin
UltimateBarX.create(UltimateBarX.NAVIGATION_BAR)    // 设置导航栏
    .fitWindow(true)                                // 布局是否侵入导航栏（true 不侵入，false 侵入）  
    .bgColor(Color.BLACK)                           // 导航栏背景颜色（色值）
    .bgColorRes(R.color.deepSkyBlue)                // 导航栏背景颜色（资源id）
    .bgRes(R.drawable.bg_gradient)                  // 导航栏背景 drawable
    .light(false)                                   // light模式（导航栏按钮灰色 Android 8.0 以上支持）
    .apply(this)
```

> * 设置背景的三个方法写一个即可，优先级 `bgRes` > `bgColor` > `bgColorRes`
> * 如果要在 Fragment 中使用，Fragment 的根布局必须是 ViewGroup

#### 快速设置状态栏透明
在 Activity 或 Fragment 中
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
    .apply(this)
```
快速设置导航栏透明的方法也类似，把 `UltimateBarX.STATUS_BAR` 改成 `UltimateBarX.NAVIGATION_BAR` 即可

### 截图
![](screenshots/transparent_1.png)　![](screenshots/transparent_2.png)

![](screenshots/effect_1.png)　![](screenshots/effect_2.png)

![](screenshots/dynamic_1.gif)　![](screenshots/dynamic_2.gif)

### Change Log
[CHANGELOG](CHANGELOG.md)

### License
```
Copyright 2020 Zackratos

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```