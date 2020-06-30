package com.ccu.kyapp.majorTab

/**
 * set the tab item
 *
 * @author MoonSeokChoi
 * @version 0.1 create class and create tabName, viewType
 * @since 2020.06.30
 */
class TabItem(private val tabName: String , private val viewType : Int) {
    fun getTabName():String{
        return tabName
    }
    fun getViewType():Int{
        return viewType
    }
}