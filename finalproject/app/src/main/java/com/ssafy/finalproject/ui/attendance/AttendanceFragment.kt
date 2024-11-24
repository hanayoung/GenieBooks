package com.ssafy.finalproject.ui.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.CalendarDayBinding
import com.ssafy.finalproject.databinding.FragmentAttendanceBinding
import com.ssafy.finalproject.util.CommonUtils
import com.ssafy.finalproject.util.CommonUtils.displayText
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class AttendanceFragment: BaseFragment<FragmentAttendanceBinding>(
    FragmentAttendanceBinding::bind,
    R.layout.fragment_attendance
) {
    private var currentMonth = YearMonth.now()
    private val startMonth = currentMonth
    private val endMonth = currentMonth
    private var isFirst = true
    private val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun updateTitle() {
        isFirst = true
        val month = binding.calendar.findFirstVisibleMonth()?.yearMonth ?: return
        val year = month.year.toString() +"년"
        currentMonth = month
        binding.year.text = year
        binding.month.text = month.month.displayText(short = false)
        binding.monthMinus.isEnabled = month > startMonth
        binding.monthPlus.isEnabled = month < endMonth
    }

    private fun bindDate(date: LocalDate, dayText: TextView,  img: ImageView, isSelectable: Boolean) {
        dayText.text = date.dayOfMonth.toString()
        if (isSelectable) {
//            val element = viewModel.scheduleList.value!!.find { LocalDate.parse(it.eventDay, format) == date }
//            if (element != null){
//                img.visibility = View.VISIBLE
//                if (element.eventImageUrl != null && LocalDate.parse(element.eventDay, format) == date){
//                    Glide.with(this)
//                        .load(element.eventImageUrl)
//                        .centerCrop()
//                        .into(img)
//                    if (element.ticketId.size > 1){
//                        ticketCount.text = element.ticketId.size.toString()
//                        ticketCountBg.visibility = View.VISIBLE
//                        ticketCount.visibility = View.VISIBLE
//                    } else if (element.ticketId.size == 1) {
//                        ticketCount.visibility = View.GONE
//                        ticketCountBg.visibility = View.GONE
//                    }
//                }
//            } else {
//                img.visibility = View.INVISIBLE
//                imgBg.visibility = View.GONE
//                ticketCount.visibility = View.GONE
//                ticketCountBg.visibility = View.GONE
//            }
        } else {
            dayText.setTextColor(resources.getColor(R.color.disabled))
        }
    }
}