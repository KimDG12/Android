package com.icarus.recycle_app.ui.setting

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityDeveloperInfoBinding

class DeveloperInfoActivity : AppCompatActivity() {

    val impression = mutableMapOf<String, String>()
    init {
        impression["차도훈"] = "저 혼자서는 하기 힘들었을 것 같지만 모두와 협력해서 이끌어낸 결과라고 생각합니다. 모두 고생하셨습니다. 이카루스 파이팅!!"
        impression["김주영"] = "모두 열심히 참여해서 고생 많이 하셨습니다. 꼭 1차 평가에 붙어서 끝까지 프로젝트를 완성하고 플레이스토어에 올리기를 기원합니다!! 멋지다 이카루스~"
        impression["김민기"] = "웹크로링과 서버의 다양한 기능을 구현하면서\n" +
                "저에게 많은 도움이 되었습니다. 작은 프로젝트를 많이 참여 해왔지만\n" +
                "S/W 개발자 대회라는 큰 대회를 참가하면서 백엔드에 자신감이 많이\n" +
                "생겼고 웹크로링이라는 데이터 수집을 경험하면서 저의 기량이 높일 수 있는\n" +
                "좋은 경험이었습니다."
        impression["박지홍"] = "Object Detection모델 중 하나인 yolo5를 사용하면서 이미지 인식과 영상 처리에 관해 배울 수 있었습니다.\n" +
                "학습에 필요한 데이터를 모으는 것이 생각보다 어렵고, 모델을 실제로 학습시키는 과정에서 많은 시간이 소요된다는 것 또한 몸소 느낄 수 있는 시간이었습니다.\n" +
                "해당 어플을 통해 올바른 쓰레기 분리수거를 할 수 있게 된다면 쓰레기 재활용 비율을 높여 환경보전에 도움을 줄 수 있을 것입니다."
        impression["최민화"] = "쓰레기를 인식하는 분리수거 어플을 개발하면서 환경 보호에 일조하고 미래 세대를 위한 지속 가능한 미션에 참여하는 것에 큰 자부심을 느낄 수 있었습니다. 이 어플을 통한 우리 모두의 작은 노력으로 큰 환경 변화를 이끌어낼 수 있었으면 좋겠습니다."
        impression["임소희"] = "이번 대회를 통해 컴퓨터 비전과 딥러닝 기술을 활용하여 실제 환경에서 유용한 문제를 해결해 보는 기회가 되었습니다.  딥러닝을 모델을 구축하고 최적화하는 과정, 실제 데이터셋을 수집하고 라벨링하는 과정을 경험할 수 있었습니다. 쓰레기 분류 어플은 잘 사용된다면, 환경 보호와 재활용을 촉진하는 데 중요한 역할을 할 것이라 기대합니다."
        impression["이지훈"] = "제게 과분한 팀원들과 좋은 경험을 가질 수 있는 시간이었습니다.  스스로의 부족한 역량 개발에도 도움이 되는 경험을 할 수 있게 해준 감사한 대회였습니다. 이카루스 화이팅."
        impression["차호련"] = "경험이 없어 많이 어리버리 탔으나 참가한 것만으로 좋은 경험이었습니다"
        impression["김나은"] = "데이터 셋을 수집하기 위해 웹 크롤링으로 사진을 수집한 후 라벨링 작업을 진행했습니다. 이런 대회에 참여해 본 경험이 없어서 모든 것이 미숙했는데, 다른 분들이 열심히 도와주셔서 수월하게 진행할 수 있었고 유익한 경험이 되었습니다. 이 경험을 토대로 실력 발전에 힘써보겠습니다.\n" +
                "평소 재활용을 열심히 한다고 생각은 했지만 아직 부족하다는 것을 느꼈습니다. 재활용에 대해 자세히 알아가는 계기가 되었습니다. 이 앱을 통해 많은 사람들이 재활용의 필요성과 방법을 알게 되었으면 좋겠습니다."
        impression["김재겸"] = "애플리케이션 개발에 처음으로 참여해보게 되었습니다.  아는게 많이 없이 참여하게 되어 많은 두려움이 있었지만 선배님들의 이끎과 제공받은 다양한 학습 자료로 좋은 경험을 쌓을 수 있어 좋았습니다. \n" +
                "특히 요즘 강조되고 있는 웹 크롤링을 직접 해보는 경험을 통해 웹 상의 다양한 정보를 수집하는 방법을 배우는 중요하고 특별한 경험을 얻었습니다.\n" +
                "이후에도 서버에 대한 다양한 측면을 배우고, 앱 계발도 직접 해보는 다양한 계기가 많이 찾아오면 좋겠습니다."
        impression["이가은"] = "앱을 다루는걸 이번에 처음하게되어서 정말 아는게 없었는데 친절하게 설명해주셔서 감사했습니다 :) 기본적인것들을 익혀서 조금이라도 도움이 될수있어서 좋았습니다!! 모두 수고많으셨습니다:) "
        impression["김태원"] = "사실상 아무런 지식이 없는 상태여서 많은 걱정이 있었으나 그 걱정과는 반대로 선배님들이 쉽게 가르쳐주어 많은 공부를 할 수 있었고 이번에 이런 규모가 있는 대회에 참여하는 팀의 구성원으로써 많은 부분이 부족하였지만 이끌어주신 분들 덕분에 매우 값진 시간이 될 수 있었습니다. 다음에는 모두가 더욱이 발전하여 더 큰 대회에 참여할 수 있으면 좋겠습니다. 다들 고생많으셨습니다."

    }

        private lateinit var binding: ActivityDeveloperInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeveloperInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayout = binding.linearLayoutForText

        val layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.MarginLayoutParams.WRAP_CONTENT,
            ViewGroup.MarginLayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(
                dpToPx(20), // Start
                dpToPx(10), // Top
                dpToPx(20), // End
                0
            )
        }

        val drawable = ContextCompat.getDrawable(this, R.drawable.custom_circle_background_gray)


        for (item in impression) {

            val nameText = TextView(this)
            nameText.text = item.key

            nameText.setPadding(
                dpToPx(20),
                dpToPx(15),
                dpToPx(20),
                dpToPx(15)
            )

            nameText.layoutParams = layoutParams

            nameText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            nameText.background = drawable

            val contentText = TextView(this)
            contentText.text = item.value

            contentText.setPadding(
                dpToPx(20),
                dpToPx(20),
                dpToPx(20),
                dpToPx(20)
            )

            contentText.layoutParams = layoutParams


            contentText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            contentText.background = drawable

            linearLayout.addView(nameText)
            linearLayout.addView(contentText)
        }



    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}