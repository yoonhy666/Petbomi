package com.example.petbomi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

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
        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(37.579694202134775, 126.96600302268465);
        MapPoint MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.57972788368821, 126.9656379048131);
        MapPoint MARKER_POINT3 = MapPoint.mapPointWithGeoCoord(37.58003205684234, 126.965940603259);
        MapPoint MARKER_POINT4 = MapPoint.mapPointWithGeoCoord(37.5795, 126.966);
        MapPoint MARKER_POINT5 = MapPoint.mapPointWithGeoCoord(37.5801, 126.9652);
        MapPoint MARKER_POINT6 = MapPoint.mapPointWithGeoCoord(37.5802, 126.9664);
        MapPoint MARKER_POINT7 = MapPoint.mapPointWithGeoCoord(37.58, 126.9662);
        MapPoint MARKER_POINT8 = MapPoint.mapPointWithGeoCoord(37.5801, 126.9672);
        MapPoint MARKER_POINT9 = MapPoint.mapPointWithGeoCoord(37.5803, 126.9659);
        MapPoint MARKER_POINT10 = MapPoint.mapPointWithGeoCoord(37.5802, 126.9659);

        // 마커 아이콘 추가하는 함수
        MapPOIItem marker1 = new MapPOIItem();
        MapPOIItem marker2 = new MapPOIItem();
        MapPOIItem marker3 = new MapPOIItem();
        MapPOIItem marker4 = new MapPOIItem();
        MapPOIItem marker5 = new MapPOIItem();
        MapPOIItem marker6 = new MapPOIItem();
        MapPOIItem marker7 = new MapPOIItem();
        MapPOIItem marker8 = new MapPOIItem();
        MapPOIItem marker9 = new MapPOIItem();
        MapPOIItem marker10 = new MapPOIItem();


        // 클릭 했을 때 나오는 호출 값
        marker1.setItemName("코디 님");
        marker2.setItemName("에덴 님");
        marker3.setItemName("히힝 님");
        marker4.setItemName("폭탄마 님");
        marker5.setItemName("호두 님");
        marker6.setItemName("동치미 님");
        marker7.setItemName("왕벌 님");
        marker8.setItemName("노랑바지 님");
        marker9.setItemName("골든 님");
        marker10.setItemName("로얄 님");
        //
        marker1.setTag(0);
        marker2.setTag(0);
        marker3.setTag(0);
        marker4.setTag(0);
        marker5.setTag(0);
        marker6.setTag(0);
        marker7.setTag(0);
        marker8.setTag(0);
        marker9.setTag(0);
        marker10.setTag(0);

        marker1.setMapPoint(MARKER_POINT1);
        marker2.setMapPoint(MARKER_POINT2);
        marker3.setMapPoint(MARKER_POINT3);
        marker4.setMapPoint(MARKER_POINT4);
        marker5.setMapPoint(MARKER_POINT5);
        marker6.setMapPoint(MARKER_POINT6);
        marker7.setMapPoint(MARKER_POINT7);
        marker8.setMapPoint(MARKER_POINT8);
        marker9.setMapPoint(MARKER_POINT9);
        marker10.setMapPoint(MARKER_POINT10);

        //  (클릭 전)기본으로 제공하는 BluePin 마커 모양의 색.
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker3.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker4.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker5.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker6.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker7.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker8.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker9.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker10.setMarkerType(MapPOIItem.MarkerType.BluePin);




        // 지도화면 위에 추가되는 아이콘을 추가하기 위한 호출(말풍선 모양)
        mapView.addPOIItem(marker1);
        mapView.addPOIItem(marker2);
        mapView.addPOIItem(marker3);
        mapView.addPOIItem(marker4);
        mapView.addPOIItem(marker5);
        mapView.addPOIItem(marker6);
        mapView.addPOIItem(marker7);
        mapView.addPOIItem(marker8);
        mapView.addPOIItem(marker9);
        mapView.addPOIItem(marker10);

        recyclerView = findViewById(R.id.recyclerView); // 아디 연결
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 어레이 리스트 (어댑터쪽으로)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("User"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    User user = snapshot.getValue(User.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    arrayList.add(user); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 디비를 가져오던중 에러 발생 시
                Log.e("LocationSheet", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });


        adapter = new UserAdapter(arrayList,this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결

    }
}



