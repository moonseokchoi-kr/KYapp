package com.ccu.kyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccu.kyapp.news.NewItem
import com.ccu.kyapp.news.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * this script is active for main activity
 * Define the behavior when you click the layout.
 *
 * @author MoonSeok Choi
 * @version 1.0
 * @since 2020.6.25
 * todo change the code RxKotlin and RxAndroid
 */

class MainActivity : AppCompatActivity() {
    //test image
    private val newsItem : Array<NewItem> = arrayOf(NewItem("https://www.dhnews.co.kr/news/photo/202006/124161_127024_536.jpg","건양대 이원묵 총장, ‘Stay Strong 캠페인’ 동참","건양대학교 이원묵 총장이 지난 17일 논산 창의융합캠퍼스에서 코로나19 극복과 감염병의 조기종식을 응원하기 위해 외교부가 진행한 ‘Stay Strong 캠페인’에 동참했다.\n")
    ,NewItem("http://dn.joongdo.co.kr/mnt/images/file/2020y/06m/03d/2020052801002238100094162.jpg","[포스트 코로나, 대학이 경쟁력이다]-건양대학교","지역사회의 상생과 인재 육성을 추진하며, 산학협력의 대표 모델을 꼽히는 있는 건양대 산학협력단은 최근 중장기 발전전략을 통해 지속가능한 발전을 모색하고 있다."),NewItem("http://www.dhnews.co.kr/news/photo/202006/123712_126584_2425.jpg","건양대-국민연금공단, ‘개인정보보호 동반성장’ 위한 업무협약","건양대학교와 국민연금공단이 9일 오후 건양대 논산캠퍼스에서 산학협력을 통한 기관 간 개인정보보호 동반성장을 위한 업무협약을 체결했다. \n"
        ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        intent is set next page
         */
        lateinit var intent : Intent
        setContentView(R.layout.activity_main)
        /*
        viewPager2 is used to card_slider
         */
        viewPager_journal.adapter = NewsAdapter(newsItem)

        //move to major_selector
        relativeLayout_major.setOnClickListener{
            intent = Intent(this, MajorSelectorActivity::class.java)
            startActivity(intent)
        }
        //move to school introduce
        relativeLayout_school.setOnClickListener {
            intent  = Intent(this, SchoolActivity::class.java)
            startActivity(intent)
        }
        //move to admission page
        relativeLayout_admission.setOnClickListener{
            intent = Intent(this, AdmissionActivity::class.java)
            startActivity(intent)
        }


    }

}
