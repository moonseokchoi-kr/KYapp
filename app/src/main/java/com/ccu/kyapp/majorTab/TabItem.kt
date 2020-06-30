package com.ccu.kyapp.majorTab

/**
 * set the tab item
 *
 * @author MoonSeokChoi
 * @version 0.1 create class and create tabName, viewType
 * @version 0.2 add content
 * @since 2020.06.30
 */
class TabItem(private val tabName: String , private val viewType : Int, private val content : Any) {
    fun getTabName():String{
        return tabName
    }
    fun getViewType():Int{
        return viewType
    }
    fun getContent():Any{
        return content
    }
}