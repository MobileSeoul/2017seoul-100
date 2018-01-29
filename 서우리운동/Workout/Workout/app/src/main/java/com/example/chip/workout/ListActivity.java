package com.example.chip.workout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView=(ListView) findViewById(R.id.listView);

        adapter=new ListAdapter();
        adapter.addItem(new com.example.chip.workout.Listitem("공중 걷기","심폐 지구력",R.drawable.chip_01));
        adapter.addItem(new com.example.chip.workout.Listitem("철봉","상체, 어깨, 복부",R.drawable.chip_02));
        adapter.addItem(new com.example.chip.workout.Listitem("달리기 운동","상폐 지구력, 상하체,허리",R.drawable.chip_03));
        adapter.addItem(new com.example.chip.workout.Listitem("파도타기","허리, 하체",R.drawable.chip_04));
        adapter.addItem(new com.example.chip.workout.Listitem("지압기","척추 유연성, 신장, 허리, 복부",R.drawable.chip_05));
        adapter.addItem(new com.example.chip.workout.Listitem("허리 돌리기","허리, 복부",R.drawable.chip_06));


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                com.example.chip.workout.Listitem item=(com.example.chip.workout.Listitem) adapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                if(item.getName()=="공중 걷기")
                {
                    int[] mypid = new int[]{1,1,0};
                    intent.putExtra("pid",mypid);

                    startActivityForResult(intent,101);
                }

                else if(item.getName()=="철봉")
                {
                    int[] mypid = new int[]{1,0,1};
                    intent.putExtra("pid",mypid);

                    startActivityForResult(intent,101);
                }

                else if(item.getName()=="지압기")
                {
                    int[] mypid = new int[]{1,0,1};
                    intent.putExtra("pid",mypid);

                    startActivityForResult(intent,101);
                }
                else if(item.getName()=="허리 돌리기")
                {
                    int[] mypid = new int[]{1,1,1};
                    intent.putExtra("pid",mypid);

                    startActivityForResult(intent,101);
                }

                else if(item.getName()=="파도타기")
                {
                    int[] mypid = new int[]{1,1,0};
                    intent.putExtra("pid",mypid);

                    startActivityForResult(intent,101);
                }


                else if(item.getName()=="달리기 운동")
                {
                    int[] mypid = new int[]{1,0,1};
                    intent.putExtra("pid",mypid);

                    startActivityForResult(intent,101);
                }

                else{
                }


                Toast.makeText(getApplicationContext(),"선택 :"+item.getName(),Toast.LENGTH_LONG).show();

            }
        });

        Button button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    class ListAdapter extends BaseAdapter {
        ArrayList<com.example.chip.workout.Listitem> items=new ArrayList<com.example.chip.workout.Listitem>();

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
}
