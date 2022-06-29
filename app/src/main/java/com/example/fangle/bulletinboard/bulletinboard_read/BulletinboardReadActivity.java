package com.example.fangle.bulletinboard.bulletinboard_read;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.announcement.announcement_read.AnnounCementReadActivity;
import com.example.fangle.bulletinboard.bulletinboard_create.BulletinboardCreateActivity;
import com.example.fangle.bulletinboard.bulletinboard_update.BulletinboardUpdateActivity;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;
import com.example.fangle.writing.writing_post.WritingPostActivity;
import com.example.fangle.writing.writing_read.WritingListItem;
import com.example.fangle.writing.writing_read.WritingReadActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BulletinboardReadActivity extends AppCompatActivity {

    public ListView board_list;
    TextView board_text,board_name;
    TextView jyp_text,twitter_text,facebook_text,youtube_text;
    ImageView board_image;
    BulletinboardListItemAdapter adapter;
    String community_name = " 아직 미정 ";
    String announcement;
    String image_text ="";
    private ActivityResultLauncher<Intent> resultLauncher,updateLauncher;

    // 파이어베이스 DB연결 관련 선언
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    //참고로 프로그램 정의서는 관리자 기능으로 분류 되어있어서 관리자 만 생성 버튼을 보이게 해야 해서 일단 인트로 에서 관리자 분류를 해야한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_read);

        // 링크 텍스트
        jyp_text = (TextView) findViewById(R.id.jyp_text);
        twitter_text = (TextView) findViewById(R.id.twitter_text);
        facebook_text = (TextView) findViewById(R.id.facebook_text);
        youtube_text = (TextView) findViewById(R.id.youtube_text);
        
        board_image = (ImageView) findViewById(R.id.board_image);
        board_name = (TextView) findViewById(R.id.board_name);
        announcement = board_name.getText().toString();
        // listview 참조
        board_list = (ListView) findViewById(R.id.bulletinboard_list);
        registerForContextMenu(board_list);

        //adapter 참조
        adapter = new BulletinboardListItemAdapter();

        // 커뮤니티 이름가져오기
        board_text = (TextView)findViewById(R.id.board_text);

        // getIntent
        Intent board_name = getIntent();
        board_text.setText((board_name.getStringExtra("artist_name")) + " 게시판");
        image_text = (board_name.getStringExtra("artist_name"));

        byte[] arr = getIntent().getByteArrayExtra("image"); // 이미지
        Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        board_image.setImageBitmap(image);
        //

        board_list.setAdapter(adapter);

        board_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                //  리스트 아이템 에서
                String board = ((BulletinborardListItem)adapter.getItem(position)).getBoard_name();
                // 클릭시 게시글로 넘어감
                Intent post_intent = new Intent(BulletinboardReadActivity.this, WritingReadActivity.class);
                post_intent.putExtra("board_name",board);
                startActivity(post_intent);

            }
        });

//        board_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
//            @Override
//            public boolean onItemLongClick(AdapterView parent, View v, int position, long id){
//                // 롱 클릭시 수정 삭제
////                Intent dialog_intent = new Intent(BulletinboardReadActivity.this,BulletinboardDialogActivity.class);
////                startActivity(dialog_intent);
//
//                return true;
//            }
//        });

//        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if(result.getResultCode() == RESULT_OK){
//                    Intent data_intent = result.getData();
//                    String data_result = data_intent.getExtras().getString("ResultData");
//                    adapter.addItem(new BulletinborardListItem(data_result));
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });


        updateLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Intent data_intent = result.getData();
                    String data_result = data_intent.getExtras().getString("ResultData");
                    int index = data_intent.getExtras().getInt("index");
                    BulletinborardListItem name = new BulletinborardListItem(data_result);
                    adapter.set(index,name);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        databaseReference.child("Bulletinboard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BulletinborardListItem group = snapshot.getValue(BulletinborardListItem.class);
                    String value = Objects.requireNonNull(group).getBoard_name();
                    adapter.addItem(new BulletinborardListItem(value));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

    }

    // 게시판 롱 클릭 시 나오는 메뉴
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index= info.position;
        String Board_name = ((BulletinborardListItem)adapter.getItem(index)).getBoard_name();

        int itemId = item.getItemId();
        if(itemId == R.id.update){
            Intent update = new Intent(this, BulletinboardUpdateActivity.class);
            update.putExtra("Board_name",Board_name);
            update.putExtra("index",index);
            updateLauncher.launch(update);
        }else if(itemId == R.id.delete){
            adapter.remove(index);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }

    public void board_create(View view) {
        Intent board_create_intent = new Intent(this, BulletinboardCreateActivity.class);
        board_create_intent.putExtra("community_name",community_name);
        startActivity(board_create_intent);
    }

    public void announcement(View view){

        Intent post_intent = new Intent(BulletinboardReadActivity.this, AnnounCementReadActivity.class);
        post_intent.putExtra("board_name",announcement);
        post_intent.putExtra("image_text",image_text);
        startActivity(post_intent);

    }

    public void web(View v){
        if(v.getId() == R.id.jyp_text){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.jype.com/"));
            startActivity(intent);
        }
        if(v.getId() == R.id.twitter_text){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/jypnation"));
            startActivity(intent);
        }
        if(v.getId() == R.id.facebook_text){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/jypnation"));
            startActivity(intent);
        }
        if(v.getId() == R.id.youtube_text){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/JYPEntertainment"));
            startActivity(intent);
        }
    }
}