package com.example.chip.workout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.chip.workout.R.id.listView2;


/**
 * Created by lg01 on 2017-09-29.
 */

public class DetailActivity extends AppCompatActivity {

    ListAdapter adapter;
    ParkAdapter adapter2;
    String gid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent passedIntent = getIntent();                                                                                                       //메뉴 엑티비티에서 숫자를 받아온다
        gid = getIntent().getStringExtra("gid");                                                                                                //받아온 공원이름을 gid라는 String에 값을 넣어준다
       // Toast.makeText(this, gid,           Toast.LENGTH_SHORT).show();           //테스트용 토스트
        ListView listView = (ListView) findViewById(listView2);
        ListView listView2 = (ListView) findViewById(R.id.listView3);
        adapter = new ListAdapter();
        if (new String("와우근린공원1\n").equals(gid)){             //string이 같은지 비교하여 같으면 if문으로 들어가 리스트의 아이템을 추가해 준다.
            adapter.addItem(new com.example.chip.workout.Listitem("공중 걷기", "심폐 지구력", R.drawable.chip_01));
            adapter.addItem(new com.example.chip.workout.Listitem("철봉", "상체, 어깨, 복부", R.drawable.chip_02));
            adapter.addItem(new com.example.chip.workout.Listitem("달리기 운동", "상폐 지구력, 상하체,허리", R.drawable.chip_03));
            adapter.addItem(new com.example.chip.workout.Listitem("하체 흔들기", "허리, 하체", R.drawable.chip_04));
            adapter.addItem(new com.example.chip.workout.Listitem("지압기", "척추 유연성, 신장, 허리, 복부", R.drawable.chip_05));
            adapter.addItem(new com.example.chip.workout.Listitem("허리 돌리기", "허리, 복부", R.drawable.chip_06));
         }
        else if  (new String("와우어린이공원\n").equals(gid)) {
            adapter.addItem(new com.example.chip.workout.Listitem("허리 돌리기", "허리, 복부", R.drawable.chip_06));
            adapter.addItem(new com.example.chip.workout.Listitem("하체 흔들기", "허리, 하체", R.drawable.chip_04));
            adapter.addItem(new com.example.chip.workout.Listitem("공중 걷기", "심폐 지구력", R.drawable.chip_01));
        }
        else if  (new String("와우근린공원2\n").equals(gid)) {
            adapter.addItem(new com.example.chip.workout.Listitem("철봉", "상체, 어깨, 복부", R.drawable.chip_02));
            adapter.addItem(new com.example.chip.workout.Listitem("달리기 운동", "상폐 지구력, 상하체,허리", R.drawable.chip_03));
            adapter.addItem(new com.example.chip.workout.Listitem("지압기", "척추 유연성, 신장, 허리, 복부", R.drawable.chip_05));
            adapter.addItem(new com.example.chip.workout.Listitem("허리 돌리기", "허리, 복부", R.drawable.chip_06));
        }
        else{
            finish(); //내위치 마커 클릭 -> 상자뜨고 그 상자 눌렀을 때 액티비티 넘어가는거 방지
        }

        listView.setAdapter(adapter);


        adapter2=new ParkAdapter();                             //위쪽 리스트도 아래 운동리스트처럼 if문으로 골라준다.
        if (new String("와우근린공원1\n").equals(gid)){
            adapter2.addItem(new com.example.chip.workout.Parkitem("와우공원","위치 : 서울특별시 \n마포구 창전동 3-245 ",R.drawable.wau_park1));

        }
        else if  (new String("와우어린이공원\n").equals(gid)) {
            adapter2.addItem(new com.example.chip.workout.Parkitem("와우어린이공원","위치 : 서울특별시 \n마포구 상수동 72-7 ",R.drawable.wau_children_park));

            }
        else if  (new String("와우근린공원2\n").equals(gid)) {
            adapter2.addItem(new com.example.chip.workout.Parkitem("와우근린공원","위치 : 서울특별시 \n마포구 상수동 72-9 ",R.drawable.wau_park2));

        }

        listView2.setAdapter(adapter2);
        //adapter2.addItem(new com.example.chip.workout.Listitem("와우공원1","위치 : ",R.drawable.wowpark));//내위치 클릭시 띄우는것


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                com.example.chip.workout.Listitem item=(com.example.chip.workout.Listitem) adapter.getItem(i);

                //edit by Min<-

                Context mContext = getApplicationContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                //R.layout.dialog는 xml 파일명이고  R.id.Popup은 보여줄 레이아웃 아이디
                View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.Popup));
                AlertDialog.Builder aDialog = builder;

                //운동기구 조건문
                if (new String("와우근린공원1\n").equals(gid)){
                    if(item.getName()=="공중 걷기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.airwalk_dialog_1);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="철봉") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.bar_dialog_1);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="달리기 운동") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.running_dialog_1);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="하체 흔들기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.lowerbodyshake_dialog_1);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="지압기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.trunkroller_dialog_1);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="허리 돌리기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.waisttwisting_dialog_1);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                }

                else if (new String("와우어린이공원\n").equals(gid)){

                    if(item.getName()=="공중 걷기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.airwalk_dialog_3);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="하체 흔들기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.lowerbodyshake_dialog_3);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="허리 돌리기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.waisttwisting_dialog_3);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }

                }

                else  if (new String("와우근린공원2\n").equals(gid)){

                    if(item.getName()=="철봉") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.bar_dialog_2);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="달리기 운동") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.running_dialog_2);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="지압기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.trunkroller_dialog_2);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                    else if(item.getName()=="허리 돌리기") {
                        // Layout에 있는 TextView및 ImageView에 아이콘 및 Text지정
                        ImageView image = (ImageView) layout.findViewById(R.id.imageView2);
                        image.setImageResource(R.drawable.waisttwisting_dialog_2);
                        aDialog.setTitle(item.getName()); //타이틀바 제목
                    }
                }
                else{}
                aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
                //그냥 닫기버튼을 위한 부분
                aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                //팝업창 생성
                AlertDialog ad = aDialog.create();
                ad.show();//보여줌!
                Toast.makeText(getApplicationContext(), "선택 :" + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
            //->edit by Min
    }

    class ListAdapter extends BaseAdapter {
        ArrayList<Listitem> items=new ArrayList<com.example.chip.workout.Listitem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(com.example.chip.workout.Listitem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            com.example.chip.workout.ListitemView view =new com.example.chip.workout.ListitemView(getApplicationContext());

            if(convertView==null){
                view=new com.example.chip.workout.ListitemView(getApplicationContext());
            }
            else{
                view=(com.example.chip.workout.ListitemView)convertView;
            }
            com.example.chip.workout.Listitem item=items.get(i);
            view.setName(item.getName());
            view.setStimulus(item.getStimulus());
            view.setImage(item.getResId());

            return view;
        }
    }

    class ParkAdapter extends BaseAdapter {
        ArrayList<Parkitem> items=new ArrayList<com.example.chip.workout.Parkitem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(com.example.chip.workout.Parkitem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            com.example.chip.workout.ListparkView view =new com.example.chip.workout.ListparkView(getApplicationContext());

            if(convertView==null){
                view=new com.example.chip.workout.ListparkView(getApplicationContext());
            }
            else{
                view=(com.example.chip.workout.ListparkView)convertView;
            }


            com.example.chip.workout.Parkitem item=items.get(i);


            view.setName(item.getName());
            view.setStimulus(item.getStimulus());
            view.setImage(item.getResId());


            return view;
        }
    }

}
