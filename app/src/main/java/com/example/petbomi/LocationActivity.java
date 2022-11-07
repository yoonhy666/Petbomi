package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.w3c.dom.Document;

public class LocationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);


        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);



        // 중심점 변경 - 배화여자대학교
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.57920988530214, 126.96589003358444), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(1, true);

        // 줌 인
        mapView.zoomIn(true);

        // 줌 아웃
        mapView.zoomOut(true);

        /*마커 추가*/

        //마커 찍기
        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(37.57951675204435, 126.96777200611596);
        MapPoint MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.57972788368821, 126.9656379048131);

        // 마커 아이콘 추가하는 함수
        MapPOIItem marker1 = new MapPOIItem();
        MapPOIItem marker2 = new MapPOIItem();

        // 클릭 했을 때 나오는 호출 값
        marker1.setItemName("코디 님");
        marker2.setItemName("에덴 님");
        //
        marker1.setTag(0);
        marker2.setTag(0);

        marker1.setMapPoint(MARKER_POINT1);
        marker2.setMapPoint(MARKER_POINT2);

        //  (클릭 전)기본으로 제공하는 BluePin 마커 모양의 색.
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin);

        // (클릭 후) 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        // 지도화면 위에 추가되는 아이콘을 추가하기 위한 호출(말풍선 모양)
        mapView.addPOIItem(marker1);
        mapView.addPOIItem(marker2);

    }
}



