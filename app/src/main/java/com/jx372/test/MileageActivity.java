package com.jx372.test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.tesseract.android.TessBaseAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by pys on 2017. 9. 18..
 */

public class MileageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;


    Bitmap image; //사용되는 이미지
    private TessBaseAPI mTess; //Tess API reference
    String datapath = "" ; //언어데이터가 있는 경로
    String uploadPath="";
    private Uri mImageCaptureUri;
    private ImageView mPhotoImageView;
    private Button mButton;
    private static boolean state;
    Bitmap photo;




    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(MileageActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(MileageActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    Toast.makeText(MileageActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(MileageActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    public static Intent newIntent(Context packageContext, boolean value){

        Intent i = new Intent(packageContext, MileageActivity.class);
        i.putExtra("state",value);
        state = value;
        return i;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_distance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // itemUpdate();
        //onBackPressed();
        if (item.getItemId() == R.id.disfinish) {

            state = true;

            if (state) {
                File file = new File(uploadPath);
                Log.v("파일 이름", file.getName());
                String st = "start";

                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("files[0]", "start.jpg", reqFile);
                RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "file");


                HttpConnector httpcon = new HttpConnector();
                httpcon.accessPhoto("postimage", body, name, mCallback2);
                //    NavUtils.navigateUpFromSameTask(this);
                //   onBackPressed();
            } else {
                File file = new File(uploadPath);
                Log.v("파일 이름", file.getName());
                String st = "end";

                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("files[0]", "end.jpg", reqFile);
                RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "file");


                HttpConnector httpcon = new HttpConnector();
                httpcon.accessPhoto("postimage", body, name, mCallback2);


            }

        }

        if(item.getItemId()==R.id.disback){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mileage);

        getSupportActionBar().setTitle("주행거리");
        mButton = (Button) findViewById(R.id.button);
        mPhotoImageView = (ImageView) findViewById(R.id.image);

        //언어파일 경로
        datapath = getFilesDir()+ "/tesseract/";

        //트레이닝데이터가 카피되어 있는지 체크
        checkFile(new File(datapath + "tessdata/"));

        //Tesseract API
        String lang = "eng";
        Log.v("and버전",android.os.Build.VERSION.SDK_INT+"");
        if(android.os.Build.VERSION.SDK_INT < 23){
            mTess = new TessBaseAPI();
            mTess.init(datapath, lang);

        }





        mButton.setOnClickListener(this);
    }

    public void processImage(View view) {
        String OCRresult = null;
        mTess.setImage(photo);
        OCRresult = mTess.getUTF8Text();
        TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
        OCRTextView.setText(OCRresult);
    }

    public void processImage2() {
        String OCRresult = null;
        if(android.os.Build.VERSION.SDK_INT < 23){
            mTess.setImage(photo);
            OCRresult = mTess.getUTF8Text();
            EditText ocrEdit = (EditText) findViewById(R.id.ocrTextedit);
            ocrEdit.setText(OCRresult);

        }


    }

    //copy file to device
    private void copyFiles() {
        try{
            String filepath = datapath + "/tessdata/eng.traineddata";

            AssetManager assetManager = getAssets();
            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //check file on the device
    private void checkFile(File dir) {
        //디렉토리가 없으면 디렉토리를 만들고 그후에 파일을 카피
        if(!dir.exists()&& dir.mkdirs()) {
            copyFiles();
        }
        //디렉토리가 있지만 파일이 없으면 파일카피 진행
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);
            if(!datafile.exists()) {
                copyFiles();
            }
        }
    }


    /**
     * 카메라에서 이미지 가져오기
     */
    private void doTakePhotoAction()
    {
    /*
     * 참고 해볼곳
     * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
     * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
     * http://www.damonkohler.com/2009/02/android-recipes.html
     * http://www.firstclown.us/tag/android/
     */

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
        //intent.putExtra("return-data", true);
        if(android.os.Build.VERSION.SDK_INT < 24){
            startActivityForResult(intent, PICK_FROM_CAMERA);

        }
        else{
//             intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            File folder = new File(Environment.getExternalStorageDirectory() + "/test/");
//            File tempFile = new File(Environment.getExternalStorageDirectory() + "/test/");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                Uri photoURI = FileProvider.getUriForFile(MileageActivity.this,
//                        "com.jx372.test", tempFile);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//            } else {
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(url)));
//            }
//
//            startActivityForResult(intent, CAMERA);
doTakeAlbumAction();


        }

    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK)
        {
            return;
        }

        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = data.getExtras();

                if(extras != null)
                {

                    photo = extras.getParcelable("data");
                    mPhotoImageView.setImageBitmap(photo);
                    String folder = "ocerocer";
                    String name = "22";
                    String ex_storage =Environment.getExternalStorageDirectory().getAbsolutePath();
                    // Get Absolute Path in External Sdcard
                    String foler_name = "/"+folder+"/";
                    String file_name = name+".jpg";
                    String string_path = ex_storage+foler_name;
                    Log.v("파일경로",string_path);

                    File file_path;
                    try{
                        file_path = new File(string_path);

                        if(!file_path.isDirectory()){
                            file_path.mkdirs();
                        }
                        FileOutputStream out = new FileOutputStream(string_path+file_name);
                        uploadPath = string_path+file_name;
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.close();

                    }catch(FileNotFoundException exception){
                        Log.e("FileNotFoundException", exception.getMessage());
                    }catch(IOException exception){
                        Log.e("IOException", exception.getMessage());
                    }

                    processImage2();

                }

                // 임시 파일 삭제
            /*    File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                {
                    f.delete();
                }*/

                break;
            }

            case PICK_FROM_ALBUM:
            {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 300);
                intent.putExtra("outputY", 150);
                //  intent.putExtra("aspectX", 1);
                // intent.putExtra("aspectY", 0.5);
                intent.putExtra("scale", true);
                intent.putExtra("crop",true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };

        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진촬영", cameraListener)
                .setNeutralButton("앨범선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }
}
