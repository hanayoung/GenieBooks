package com.ssafy.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.databinding.FragmentHomeBinding

private const val TAG = "HomeFragment"
class HomeFragment: BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {
    private val viewModel : QRScanViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPickupList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_orderListFragment)
        }

        binding.viewQr.setOnClickListener {
            qrScan()
        }
    }

    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Log.d(TAG, "qr data is null: ")
        } else {
            Log.d(TAG, "qr data : ${result.contents}")
            viewModel.updatePickupState(orderId = result.contents.toInt())

        }
    }

    private fun qrScan() {
        barcodeLauncher.launch(ScanOptions())
    }
}