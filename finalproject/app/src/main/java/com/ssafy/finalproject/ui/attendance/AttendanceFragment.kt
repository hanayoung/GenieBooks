package com.ssafy.finalproject.ui.attendance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.remote.RetrofitUtil
import com.ssafy.finalproject.databinding.CalendarDayBinding
import com.ssafy.finalproject.databinding.FragmentAttendanceBinding
import com.ssafy.finalproject.util.CommonUtils
import com.ssafy.finalproject.util.CommonUtils.displayText
import java.sql.Date
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId

private const val TAG = "AttendanceFragment"
class AttendanceFragment: BaseFragment<FragmentAttendanceBinding>(
    FragmentAttendanceBinding::bind,
    R.layout.fragment_attendance
) {
    private val viewModel : AttendanceViewModel by viewModels()
    private var currentMonth = YearMonth.now()
    private val startMonth = currentMonth
    private val endMonth = currentMonth
    private var isFirst = true
    private val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()

        binding.btnAttendance.setOnClickListener {
            viewModel.addAttendance()
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateTitle() {
        isFirst = true
        val month = binding.calendar.findFirstVisibleMonth()?.yearMonth ?: return
        val year = month.year.toString() +"년"
        currentMonth = month
        binding.year.text = year
        binding.month.text = month.month.displayText(short = false)
    }

    private fun bindDate(date: LocalDate, dayText: TextView,  img: ImageView, isSelectable: Boolean) {
        dayText.text = date.dayOfMonth.toString()
        if (isSelectable) {
            val element = viewModel.attendanceList.value!!.find {
                it.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() == date
            }
            // 제대로 변환되는거 맞는지 확인하기
            Log.d(TAG, "bindDate: element ${element}")
            if (element != null){
                img.visibility = View.VISIBLE
                Glide.with(this)
                    .load(R.drawable.attendance_check_item)
                    .centerCrop()
                    .into(img)
            } else {
                img.visibility = View.INVISIBLE
            }
        } else {
            dayText.setTextColor(resources.getColor(R.color.disabled))
        }
    }

    private fun initObserver(){
        viewModel.attendanceList.observe(viewLifecycleOwner){
            Log.d(TAG, "initObserver: ${it} ")
            initCalendar()
            val element = it.find { date ->
                date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() == LocalDate.now()
            }
            if(element != null) binding.btnAttendance.isEnabled = false
        }

        viewModel.isAttend.observe(viewLifecycleOwner){
            if(it == true) {
                binding.btnAttendance.isEnabled = false
                viewModel.attendanceList.value?.add(Date.from((LocalDate.now()).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                binding.calendar.notifyDateChanged(LocalDate.now())
                showToast("출석체크에 성공하였습니다.")
            }else{
                showToast("출석체크에 실패하였습니다. 나중에 다시 시도해주세요.")
            }
        }
    }

    private fun initCalendar(){
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay
            val dayBinding = CalendarDayBinding.bind(view)
            val dayText = dayBinding.day
            val img = dayBinding.img
        }

        binding.calendar.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                bindDate(data.date, container.dayText, container.img, data.position == DayPosition.MonthDate)
            }
            override fun create(view: View): DayViewContainer = DayViewContainer(view)
        }

        for ((index, dayText) in daysOfWeek.withIndex()) {
            val dayLayout = binding.dayWeek.getChildAt(index)
            if (dayLayout!=null){
                val textView: TextView? = dayLayout.findViewById(R.id.dayWeekText)
                if (textView != null) {
                    textView.text = dayText.displayText()

                    textView.setTextColor(requireContext().getColor(R.color.text))

                }
            }
        }

        binding.calendar.monthScrollListener = { updateTitle() }
        binding.calendar.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendar.scrollToMonth(currentMonth)
    }
}