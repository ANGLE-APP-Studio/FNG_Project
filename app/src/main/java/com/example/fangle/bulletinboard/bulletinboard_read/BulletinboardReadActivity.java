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
import android.os.Bundle;
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
import com.example.fangle.writing.writing_post.WritingPostActivity;
import com.example.fangle.writing.writing_read.WritingListItem;
import com.example.fangle.writing.writing_read.WritingReadActivity;

public class BulletinboardReadActivity extends AppCompatActivity {

    public ListView board_list;
    TextView board_text,board_name;
    ImageView board_image;
    BulletinboardListItemAdapter adapter;
    String community_name = " 아직 미정 ";
    String announcement;
    private ActivityResultLauncher<Intent> resultLauncher,updateLauncher;

    //참고로 프로그램 정의서는 관리자 기능으로 분류 되어있어서 관리자 만 생성 버튼을 보이게 해야 해서 일단 인트로 에서 관리자 분류를 해야한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletinboard_read);

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
        byte[] arr = getIntent().getByteArrayExtra("image"); // 이미지
        Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        board_image.setImageBitmap(image);
        //

        adapter.addItem(new BulletinborardListItem("공지사항"));
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

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK){
                    Intent data_intent = result.getData();
                    String data_result = data_intent.getExtras().getString("ResultData");
                    adapter.addItem(new BulletinborardListItem(data_result));
                    adapter.notifyDataSetChanged();
                }
            }
        });


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
        resultLauncher.launch(board_create_intent);
    }

    public void announcement(View view){
        Intent post_intent = new Intent(BulletinboardReadActivity.this, AnnounCementReadActivity.class);
        post_intent.putExtra("board_name",announcement);
        startActivity(post_intent);
    }
}