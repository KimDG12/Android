package com.icarus.recycle_app.ui.current_situation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.icarus.recycle_app.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

class CurrentSituationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var fusedLocationSource: FusedLocationSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_current_situation, container, false)
        mapView = view.findViewById(R.id.map_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        checkLocationPermission()
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = fusedLocationSource

        // 기본 지도 유형 설정
        naverMap.mapType = NaverMap.MapType.Basic

        // 기본 카메라 위치 설정
        val defaultLocation = LatLng(35.94534, 126.68293)
        val cameraUpdate = CameraUpdate.scrollAndZoomTo(defaultLocation, 17.0)
        naverMap.moveCamera(cameraUpdate)

        // 쓰레기장 위치 마커 추가
        val trashLocations = listOf(
            LatLng(35.946, 126.683) to "쓰레기장 1",
            LatLng(35.947, 126.684) to "쓰레기장 2",
            LatLng(35.945, 126.682) to "쓰레기장 3"
        )

        trashLocations.forEach { (location, name) ->
            addTrashMarkerToMap(location, name)
        }

        // 사용자 위치 버튼 활성화
        naverMap.uiSettings.isLocationButtonEnabled = true
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // 권한이 허용된 경우 추가 작업 없음
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(requireContext(), "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addTrashMarkerToMap(latLng: LatLng, name: String) {
        Marker().apply {
            position = latLng
            captionText = name
            iconTintColor = android.graphics.Color.BLUE
            map = naverMap
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
